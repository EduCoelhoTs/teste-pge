package br.com.testpge.order.shared.httpclient;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.testpge.order.shared.exceptions.BadRequestException;
import br.com.testpge.order.shared.exceptions.InternalServerErrorException;
import br.com.testpge.order.shared.exceptions.NotFoundException;
import br.com.testpge.order.shared.exceptions.UnauthorizedException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

public class ApiErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public ApiErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String bodyStr = Util.toString(response.body().asReader(Util.UTF_8));

            System.out.println("response.body() ==> " + response.body());
            Map<String, Map<String, Object>> errorMap = objectMapper.readValue(bodyStr, Map.class);

            int statusCode = HttpStatus.valueOf(response.status()).value();

            String errorMessage = (String) errorMap.get("error").get("message");

            return this.returnsCorrespondingError(statusCode, errorMessage);
        } catch (Exception e) {
            return new InternalServerErrorException("Erro ao decodificar resposta: " + e.getMessage());
        }
    }

    private RuntimeException returnsCorrespondingError(int httpStatus, String errorMessage) {
        switch (httpStatus) {
            case 400:
                return new BadRequestException(errorMessage);
            case 404:
                return new NotFoundException(errorMessage);
            case 401:
                return new UnauthorizedException(errorMessage);

            default:
                return new InternalServerErrorException(errorMessage);

        }
    }
}
