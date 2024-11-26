package br.com.testpge.order.controllers.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import br.com.testpge.order.controllers.dtos.CreateOrderDto;
import br.com.testpge.order.controllers.dtos.UpdateOrderDto;
import br.com.testpge.order.shared.data.dtos.EntityIdOutput;
import br.com.testpge.order.shared.exceptionshandlers.RestErrorResponse;
import br.com.testpge.order.usecases.dtos.OrderOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/orders")
@Tag(name = "Pedidos", description = "api de pedidos")
public interface Orderapi {

    @Operation(
            operationId = "createOrder",
            summary = "Cadastro de pedido.",
            description = "Este endpoint recebe os dados iniciais para o cadastro de pedido.",
            tags = {"pedidos"},
            responses = {
                @ApiResponse(responseCode = "201", description = "Created", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EntityIdOutput.class))}),
                @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))}),
                @ApiResponse(responseCode = "403", description = "Forbidden", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))}),
                @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))}),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))})
            }
    )
    @PostMapping
    ResponseEntity<EntityIdOutput> createOrder(
            @RequestBody CreateOrderDto request
    ) throws Exception;

    @Operation(
            operationId = "updateOrder",
            summary = "Atualização de pedido.",
            description = "Este endpoint recebe os dados a serem atualizados de pedido.",
            tags = {"pedidos"},
            responses = {
                @ApiResponse(responseCode = "200", description = "Ok"),
                @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))}),
                @ApiResponse(responseCode = "403", description = "Forbidden", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))}),
                @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))}),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))})
            }
    )
    @PatchMapping
    ResponseEntity<?> updateOrder(
            @RequestBody UpdateOrderDto request
    ) throws Exception;

    @Operation(
            operationId = "findOrders",
            summary = "Busca de pedidos.",
            description = "Este endpoint retorna uma lista de pedidos.",
            tags = {"pedidos"},
            responses = {
                @ApiResponse(responseCode = "200", description = "Ok", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = List.class))}),
                @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))}),
                @ApiResponse(responseCode = "403", description = "Forbidden", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))}),
                @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))}),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))})
            }
    )
    @GetMapping
    ResponseEntity<List<OrderOutput>> findOrders() throws Exception;

    @Operation(
            operationId = "findOrders",
            summary = "Busca de pedido por id.",
            description = "Este endpoint retorna uma pedido pelo id.",
            tags = {"pedidos"},
            responses = {
                @ApiResponse(responseCode = "200", description = "Ok", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderOutput.class))}),
                @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))}),
                @ApiResponse(responseCode = "404", description = "Not found", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))}),
                @ApiResponse(responseCode = "403", description = "Forbidden", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))}),
                @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))}),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))})
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<OrderOutput> findOrderById(@PathVariable String id) throws Exception;

    @Operation(
            operationId = "deleteOrder",
            summary = "Exclusão de pedido.",
            description = "Este endpoint recebe id e exclui pedido correspondente.",
            tags = {"pedidos"},
            responses = {
                @ApiResponse(responseCode = "204", description = "No Content"),
                @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))}),
                @ApiResponse(responseCode = "403", description = "Forbidden", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))}),
                @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))}),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrorResponse.class))})
            }
    )

    @DeleteMapping(
            value = "/{id}")
    ResponseEntity<?> deleteOrder(@PathVariable String id) throws Exception;

    @GetMapping("/status-updates")
    SseEmitter subscribeToStatusUpdates();
}
