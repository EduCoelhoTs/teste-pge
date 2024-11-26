package br.com.testpge.restaurantmanager.consumptiontable.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.testpge.restaurantmanager.consumptiontable.repositories.ConsumptionTableRepository;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.CreateConsumptionTableImpl;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.CreateConsumptionTableUsecase;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.DeleteConsumptionTableImpl;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.DeleteConsumptionTableUsecase;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.FinAllConsumptionTableImpl;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.FinAllConsumptionTableUsecase;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.FindConsumptionTableByIdImpl;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.FindConsumptionTableByIdUsecase;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.UpdateConsumptionTableImpl;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.UpdateConsumptionTableUsecase;

@Configuration
public class ConsumptionTableDependenciesConfig {

    @Bean
    public CreateConsumptionTableUsecase createConsumptionTableUsecase(
            ConsumptionTableRepository repository
    ) {
        return new CreateConsumptionTableImpl(repository);
    }

    @Bean
    public FindConsumptionTableByIdUsecase findConsumptionTableByIdUsecase(
            ConsumptionTableRepository repository
    ) {
        return new FindConsumptionTableByIdImpl(repository);
    }

    @Bean
    public UpdateConsumptionTableUsecase updateConsumptionTableUsecase(
            ConsumptionTableRepository repository
    ) {
        return (UpdateConsumptionTableUsecase) new UpdateConsumptionTableImpl(repository);
    }

    @Bean
    public FinAllConsumptionTableUsecase finAllConsumptionTableUsecase(
            ConsumptionTableRepository repository
    ) {
        return new FinAllConsumptionTableImpl(repository);
    }

    @Bean
    public DeleteConsumptionTableUsecase deleteConsumptionTableUsecase(
            ConsumptionTableRepository repository
    ) {
        return new DeleteConsumptionTableImpl(repository);
    }
}
