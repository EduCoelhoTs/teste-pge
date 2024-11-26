package br.com.testpge.order.shared.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.testpge.order.shared.httpclient.dtos.ConsumptionTableDto;

@FeignClient(
        name = "restaurant-api",
        url = "http://localhost:8082/restaurant-manager/api")
public interface RestaurantApiClient {

    @GetMapping("/consumption-tables/{id}")
    ConsumptionTableDto findConsumptionTableById(@PathVariable String id);
}
