package br.com.testpge.restaurantmanager.shared.validations;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

import br.com.testpge.restaurantmanager.shared.exceptions.BadRequestException;
import br.com.testpge.restaurantmanager.shared.validations.stubs.StubToValidate;

public class MinValueFieldValidationTest {

    private final StubToValidate dataTOValidate = new StubToValidate(
            "teste",
            21,
            true,
            "2000-01-25T10:00:00.929Z"
    );

    @Test
    public void validate_WhenAgeIsMoreThanOrEqual18_ShouldNotThrows() {

        final var sut = new MinValueFieldValidation<StubToValidate>(
                "age",
                18.0,
                true,
                "isAdmin in invalid format"
        );
        dataTOValidate.setAge(20);

        assertThatCode(() -> sut.validate(dataTOValidate))
                .doesNotThrowAnyException();

    }

    @Test
    public void validate_WithEmptyage_ShouldThrows() {

        final var sut = new MinValueFieldValidation<StubToValidate>(
                "age",
                18.0,
                true,
                new BadRequestException("isAdmin in invalid format")
        );
        dataTOValidate.setAge(null);

        assertThatThrownBy(() -> sut.validate(dataTOValidate))
                .isInstanceOf(BadRequestException.class);

    }

    @Test
    public void validate_WhenAgeIsLessThan18_ShouldThrows() {

        final var sut = new MinValueFieldValidation<StubToValidate>(
                "age",
                18.0,
                true,
                new BadRequestException("isAdmin in invalid format")
        );
        dataTOValidate.setAge(17);

        assertThatThrownBy(() -> sut.validate(dataTOValidate))
                .isInstanceOf(BadRequestException.class);

    }
}
