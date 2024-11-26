package br.com.testpge.restaurantmanager.consumptiontable.controllers.api;

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

import br.com.testpge.restaurantmanager.consumptiontable.controllers.dtos.CreateConsumptionTableDto;
import br.com.testpge.restaurantmanager.consumptiontable.controllers.dtos.UpdateConsumptionTableDto;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.ConsumptionTableOutput;
import br.com.testpge.restaurantmanager.shared.data.dtos.EntityIdOutput;
import br.com.testpge.restaurantmanager.shared.exceptionshandlers.RestErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/consumption-tables")
@Tag(name = "Mesas", description = "api de mesas")
public interface ConsumptionTableApi {

    @Operation(
            operationId = "createConsumptionTable",
            summary = "Cadastro de mesa.",
            description = "Este endpoint recebe os dados iniciais para o cadastro de mesa.",
            tags = {"mesas"},
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
    ResponseEntity<EntityIdOutput> createConsumptionTable(
            @RequestBody CreateConsumptionTableDto request
    ) throws Exception;

    @Operation(
            operationId = "updateConsumptionTable",
            summary = "Atualização de mesa.",
            description = "Este endpoint recebe os dados a serem atualizados de mesa.",
            tags = {"mesas"},
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
    ResponseEntity<?> updateConsumptionTable(
            @RequestBody UpdateConsumptionTableDto request
    ) throws Exception;

    @Operation(
            operationId = "findConsumptionTables",
            summary = "Busca de mesas.",
            description = "Este endpoint retorna uma lista de mesas.",
            tags = {"mesas"},
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
    ResponseEntity<List<ConsumptionTableOutput>> findConsumptionTables() throws Exception;

    @Operation(
            operationId = "findConsumptionTables",
            summary = "Busca de mesa por id.",
            description = "Este endpoint retorna uma mesa pelo id.",
            tags = {"mesas"},
            responses = {
                @ApiResponse(responseCode = "200", description = "Ok", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ConsumptionTableOutput.class))}),
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
    @GetMapping(
            value = "/{id}")
    ResponseEntity<ConsumptionTableOutput> findConsumptionTableById(@PathVariable String id) throws Exception;

    @Operation(
            operationId = "deleteConsumptionTable",
            summary = "Exclusão de mesa.",
            description = "Este endpoint recebe id e exclui mesa correspondente.",
            tags = {"mesas"},
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
    ResponseEntity<?> deleteConsumptionTable(@PathVariable String id) throws Exception;
}
