package br.com.testpge.restaurantmanager.shared.validations;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import br.com.testpge.restaurantmanager.shared.exceptions.BadRequestException;
import br.com.testpge.restaurantmanager.shared.validations.stubs.StubToValidate;

public class BooleanValidationTest {

    private final StubToValidate dataTOValidate = new StubToValidate(
            "teste name",
            21,
            true,
            "2000-01-25T10:00:00.929Z"
    );

    @Test
    public void validate_WithCorrectIsAdmin_ShouldNotThrows() {

        final var sut = new BooleanValidation<StubToValidate>(
                "isAdmin",
                true,
                "isAdmin in invalid format"
        );

        assertThatCode(() -> sut.validate(dataTOValidate))
                .doesNotThrowAnyException();

        dataTOValidate.setIsAdmin(false);

        assertThatCode(() -> sut.validate(dataTOValidate))
                .doesNotThrowAnyException();

    }

    @Test
    public void validate_WithIsAdminNotBoolean_ShouldThrows() {

        final var sut = new BooleanValidation<StubToValidate>(
                "isAdmin",
                true,
                new BadRequestException("isAdmin in invalid format")
        );
        dataTOValidate.setIsAdmin(null);

        assertThatThrownBy(() -> sut.validate(dataTOValidate))
                .isInstanceOf(BadRequestException.class);

    }
}
