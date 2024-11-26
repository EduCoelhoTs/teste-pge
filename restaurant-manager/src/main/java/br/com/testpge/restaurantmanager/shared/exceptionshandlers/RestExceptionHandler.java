package br.com.testpge.restaurantmanager.shared.exceptionshandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.testpge.restaurantmanager.shared.exceptions.BadRequestException;
import br.com.testpge.restaurantmanager.shared.exceptions.InternalServerErrorException;
import br.com.testpge.restaurantmanager.shared.exceptions.NotFoundException;
import br.com.testpge.restaurantmanager.shared.exceptions.UnauthorizedException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RestErrorResponse> runtimeExceptionErrorHandler(
            RuntimeException exception
    ) {
        System.out.println("error ==> " + exception.getMessage());
        final var threatResponse = new RestErrorResponse(
                500,
                "internal server error"
        );

        return ResponseEntity
                .status(threatResponse.status)
                .body(threatResponse);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<RestErrorResponse> internalServerErrorHandler(
            InternalServerErrorException exception
    ) {
        final var threatResponse = new RestErrorResponse(
                500,
                exception.message,
                exception.errors
        );

        return ResponseEntity
                .status(threatResponse.status)
                .body(threatResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RestErrorResponse> badRequestErrorHandler(
            BadRequestException exception
    ) {
        System.out.println("error ==> " + exception);
        final var threatResponse = new RestErrorResponse(
                400,
                exception.message,
                exception.errors
        );

        return ResponseEntity
                .status(threatResponse.status)
                .body(threatResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RestErrorResponse> notFoundErrorHandler(
            NotFoundException exception
    ) {
        final var threatResponse = new RestErrorResponse(
                404,
                exception.message,
                exception.errors
        );

        return ResponseEntity
                .status(threatResponse.status)
                .body(threatResponse);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<RestErrorResponse> unauthorizedErrorHandler(
            UnauthorizedException exception
    ) {
        final var threatResponse = new RestErrorResponse(
                401,
                exception.message,
                exception.errors
        );

        return ResponseEntity
                .status(threatResponse.status)
                .body(threatResponse);
    }

//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     public ResponseEntity<RestErrorResponse> handleMethodArgumentNotValid(
//             MethodArgumentNotValidException exception
//     ) {
//         final var errors = new HashSet<String>();
//         exception.getBindingResult().getAllErrors().forEach((error) -> {
//             final String field = ((FieldError) error).getField();
//             final String errorMessage = error.getDefaultMessage();
//             errors.add(field + " " + errorMessage);
//         });
//         final var threatResponse = new RestErrorResponse(
//                 400,
//                 "Bad Request",
//                 errors
//         );
//         return ResponseEntity
//                 .status(threatResponse.status)
//                 .body(threatResponse);
//     }
}
