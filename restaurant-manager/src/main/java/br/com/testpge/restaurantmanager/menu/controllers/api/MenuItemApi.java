package br.com.testpge.restaurantmanager.menu.controllers.api;

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

import br.com.testpge.restaurantmanager.menu.controllers.dtos.CreateMenuItemDto;
import br.com.testpge.restaurantmanager.menu.controllers.dtos.UpdateMenuItemDto;
import br.com.testpge.restaurantmanager.menu.enums.MenuItemType;
import br.com.testpge.restaurantmanager.menu.usecases.dtos.MenuItemOutput;
import br.com.testpge.restaurantmanager.shared.data.dtos.EntityIdOutput;
import br.com.testpge.restaurantmanager.shared.exceptionshandlers.RestErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/menu-items")
@Tag(name = "Itens do cardápio", description = "api de itens do cardápio")
public interface MenuItemApi {

    @Operation(
            operationId = "createMenuItem",
            summary = "Cadastro de item do cardápio.",
            description = "Este endpoint recebe os dados iniciais para o cadastro de item do cardápio.",
            tags = {"itens do cardápio"},
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
    ResponseEntity<EntityIdOutput> createMenuItem(
            @RequestBody CreateMenuItemDto request
    ) throws Exception;

    @Operation(
            operationId = "updateMenuItem",
            summary = "Atualização de item do cardápio.",
            description = "Este endpoint recebe os dados a serem atualizados de item do cardápio.",
            tags = {"itens do cardápio"},
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
    ResponseEntity<?> updateMenuItem(
            @RequestBody UpdateMenuItemDto request
    ) throws Exception;

    @Operation(
            operationId = "findMenuItems",
            summary = "Busca de itens do cardápio por tipo.",
            description = "Este endpoint retorna uma lista de itens do cardápio.",
            tags = {"itens do cardápio"},
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
    @GetMapping(
            value = "/types/{type}")
    ResponseEntity<List<MenuItemOutput>> findMenuItems(@PathVariable MenuItemType type) throws Exception;

    @Operation(
            operationId = "findMenuItems",
            summary = "Busca de item do cardápio por id.",
            description = "Este endpoint retorna uma item do cardápio pelo id.",
            tags = {"itens do cardápio"},
            responses = {
                @ApiResponse(responseCode = "200", description = "Ok", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MenuItemOutput.class))}),
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
    ResponseEntity<MenuItemOutput> findMenuItemById(@PathVariable String id) throws Exception;

    @Operation(
            operationId = "deleteMenuItem",
            summary = "Exclusão de item do cardápio.",
            description = "Este endpoint recebe id e exclui item do cardápio correspondente.",
            tags = {"itens do cardápio"},
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
    ResponseEntity<?> deleteMenuItem(@PathVariable String id) throws Exception;
}
