package br.com.testpge.restaurantmanager.consumptiontable.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.testpge.restaurantmanager.consumptiontable.controllers.api.ConsumptionTableApi;
import br.com.testpge.restaurantmanager.consumptiontable.controllers.dtos.CreateConsumptionTableDto;
import br.com.testpge.restaurantmanager.consumptiontable.controllers.dtos.UpdateConsumptionTableDto;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.CreateConsumptionTableUsecase;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.DeleteConsumptionTableUsecase;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.FinAllConsumptionTableUsecase;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.FindConsumptionTableByIdUsecase;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.UpdateConsumptionTableUsecase;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.ConsumptionTableOutput;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.CreateConsumptionTableCommand;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.DeleteConsumptionTableCommand;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.FindConsumptionTableByIdInput;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.UpdateConsumptionTableCommand;
import br.com.testpge.restaurantmanager.shared.data.dtos.EntityIdOutput;

@RestController
public class ConsumptionTableController implements ConsumptionTableApi {

    private final CreateConsumptionTableUsecase createConsumptionTableUsecase;
    private final UpdateConsumptionTableUsecase updateConsumptionTableUsecase;
    private final FindConsumptionTableByIdUsecase findConsumptionTableByIdUsecase;
    private final FinAllConsumptionTableUsecase finAllConsumptionTableUsecase;
    private final DeleteConsumptionTableUsecase deleteConsumptionTableUsecase;

    public ConsumptionTableController(
            CreateConsumptionTableUsecase createConsumptionTableUsecase,
            UpdateConsumptionTableUsecase updateConsumptionTableUsecase,
            FindConsumptionTableByIdUsecase findConsumptionTableByIdUsecase,
            FinAllConsumptionTableUsecase finAllConsumptionTableUsecase,
            DeleteConsumptionTableUsecase deleteConsumptionTableUsecase
    ) {
        this.createConsumptionTableUsecase = Objects.requireNonNull(createConsumptionTableUsecase);
        this.updateConsumptionTableUsecase = Objects.requireNonNull(updateConsumptionTableUsecase);
        this.findConsumptionTableByIdUsecase = Objects.requireNonNull(findConsumptionTableByIdUsecase);
        this.finAllConsumptionTableUsecase = Objects.requireNonNull(finAllConsumptionTableUsecase);
        this.deleteConsumptionTableUsecase = Objects.requireNonNull(deleteConsumptionTableUsecase);
    }

    @Override
    public ResponseEntity<EntityIdOutput> createConsumptionTable(CreateConsumptionTableDto request) throws Exception {
        final var input = new CreateConsumptionTableCommand(request.name());
        final EntityIdOutput output = this.createConsumptionTableUsecase.execute(input);

        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }

    @Override
    public ResponseEntity<?> updateConsumptionTable(UpdateConsumptionTableDto request) throws Exception {
        final var input = new UpdateConsumptionTableCommand(
                request.id(),
                request.name(),
                request.status()
        );
        this.updateConsumptionTableUsecase.execute(input);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<ConsumptionTableOutput> findConsumptionTableById(String id) throws Exception {
        final var input = new FindConsumptionTableByIdInput(id);
        final ConsumptionTableOutput output = this.findConsumptionTableByIdUsecase.execute(input);

        return ResponseEntity.status(HttpStatus.OK).body(output);
    }

    @Override
    public ResponseEntity<List<ConsumptionTableOutput>> findConsumptionTables() throws Exception {
        final List<ConsumptionTableOutput> output = this.finAllConsumptionTableUsecase.execute();

        return ResponseEntity.status(HttpStatus.OK).body(output);
    }

    @Override
    public ResponseEntity<?> deleteConsumptionTable(String id) throws Exception {
        final var input = new DeleteConsumptionTableCommand(id);
        this.deleteConsumptionTableUsecase.execute(input);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
