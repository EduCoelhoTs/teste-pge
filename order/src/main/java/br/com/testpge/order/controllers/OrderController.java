package br.com.testpge.order.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import br.com.testpge.order.controllers.api.Orderapi;
import br.com.testpge.order.controllers.dtos.CreateOrderDto;
import br.com.testpge.order.controllers.dtos.UpdateOrderDto;
import br.com.testpge.order.shared.data.dtos.EntityIdOutput;
import br.com.testpge.order.usecases.CreateOrderUsecase;
import br.com.testpge.order.usecases.DeleteOrderUsecase;
import br.com.testpge.order.usecases.FinAllOrderUsecase;
import br.com.testpge.order.usecases.FindOrderByIdUsecase;
import br.com.testpge.order.usecases.UpdateOrderUsecase;
import br.com.testpge.order.usecases.dtos.CreateOrderCommand;
import br.com.testpge.order.usecases.dtos.DeleteOrderCommand;
import br.com.testpge.order.usecases.dtos.FindOrderByIdInput;
import br.com.testpge.order.usecases.dtos.OrderOutput;
import br.com.testpge.order.usecases.dtos.UpdateOrderCommand;

@RestController
public class OrderController implements Orderapi {

    private final CreateOrderUsecase createOrderUsecase;
    private final UpdateOrderUsecase updateOrderUsecase;
    private final FindOrderByIdUsecase findOrderByIdUsecase;
    private final FinAllOrderUsecase finAllOrderUsecase;
    private final DeleteOrderUsecase deleteOrderUsecase;
    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public OrderController(
            CreateOrderUsecase createOrderUsecase,
            UpdateOrderUsecase updateOrderUsecase,
            FindOrderByIdUsecase findOrderByIdUsecase,
            FinAllOrderUsecase finAllOrderUsecase,
            DeleteOrderUsecase deleteOrderUsecase
    ) {
        this.createOrderUsecase = Objects.requireNonNull(createOrderUsecase);
        this.updateOrderUsecase = Objects.requireNonNull(updateOrderUsecase);
        this.findOrderByIdUsecase = Objects.requireNonNull(findOrderByIdUsecase);
        this.finAllOrderUsecase = Objects.requireNonNull(finAllOrderUsecase);
        this.deleteOrderUsecase = Objects.requireNonNull(deleteOrderUsecase);
    }

    @Override
    public ResponseEntity<EntityIdOutput> createOrder(CreateOrderDto request) throws Exception {
        final var input = new CreateOrderCommand(
                request.consumptionTableId(),
                request.orderItens()
        );
        final EntityIdOutput output = this.createOrderUsecase.execute(input);

        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }

    @Override
    public ResponseEntity<?> updateOrder(UpdateOrderDto request) throws Exception {
        final var input = new UpdateOrderCommand(
                request.id(),
                request.status()
        );
        this.updateOrderUsecase.execute(input);
        this.notifyStatusChange(request);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<OrderOutput> findOrderById(String id) throws Exception {
        final var input = new FindOrderByIdInput(id);
        final OrderOutput output = this.findOrderByIdUsecase.execute(input);

        return ResponseEntity.status(HttpStatus.OK).body(output);
    }

    @Override
    public ResponseEntity<List<OrderOutput>> findOrders() throws Exception {
        final List<OrderOutput> output = this.finAllOrderUsecase.execute();

        return ResponseEntity.status(HttpStatus.OK).body(output);
    }

    @Override
    public ResponseEntity<?> deleteOrder(String id) throws Exception {
        final var input = new DeleteOrderCommand(id);
        this.deleteOrderUsecase.execute(input);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public SseEmitter subscribeToStatusUpdates() {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        return emitter;
    }

    public void notifyStatusChange(UpdateOrderDto data) {
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event()
                        .name("status-change")
                        .data(data)
                );
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        });
    }
}
