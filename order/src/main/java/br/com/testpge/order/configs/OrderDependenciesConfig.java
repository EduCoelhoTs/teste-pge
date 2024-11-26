package br.com.testpge.order.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import br.com.testpge.order.repositories.OrderRepository;
import br.com.testpge.order.shared.httpclient.RestaurantApiClient;
import br.com.testpge.order.usecases.CreateOrderImpl;
import br.com.testpge.order.usecases.CreateOrderUsecase;
import br.com.testpge.order.usecases.DeleteOrderImpl;
import br.com.testpge.order.usecases.DeleteOrderUsecase;
import br.com.testpge.order.usecases.FinAllOrderImpl;
import br.com.testpge.order.usecases.FinAllOrderUsecase;
import br.com.testpge.order.usecases.FindOrderByIdImpl;
import br.com.testpge.order.usecases.FindOrderByIdUsecase;
import br.com.testpge.order.usecases.UpdateOrderImpl;
import br.com.testpge.order.usecases.UpdateOrderUsecase;

@Configuration
public class OrderDependenciesConfig {

    @Bean
    public CreateOrderUsecase createConsumptionTableUsecase(
            OrderRepository repository,
            RestaurantApiClient restaurantApiClient,
            KafkaTemplate<String, String> kafkaTemplate
    ) {
        return new CreateOrderImpl(
                repository,
                restaurantApiClient,
                kafkaTemplate
        );
    }

    @Bean
    public FindOrderByIdUsecase findConsumptionTableByIdUsecase(
            OrderRepository repository
    ) {
        return new FindOrderByIdImpl(repository);
    }

    @Bean
    public UpdateOrderUsecase updateConsumptionTableUsecase(
            OrderRepository repository,
            KafkaTemplate<String, String> kafkaTemplate
    ) {
        return new UpdateOrderImpl(repository, kafkaTemplate);
    }

    @Bean
    public FinAllOrderUsecase finAllConsumptionTableUsecase(
            OrderRepository repository
    ) {
        return new FinAllOrderImpl(repository);
    }

    @Bean
    public DeleteOrderUsecase deleteConsumptionTableUsecase(
            OrderRepository repository
    ) {
        return new DeleteOrderImpl(repository);
    }
}
