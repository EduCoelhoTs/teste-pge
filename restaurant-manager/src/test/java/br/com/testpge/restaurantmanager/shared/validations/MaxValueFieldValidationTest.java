package br.com.testpge.restaurantmanager.shared.validations;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

import br.com.testpge.restaurantmanager.shared.exceptions.BadRequestException;
import br.com.testpge.restaurantmanager.shared.validations.stubs.StubToValidate;

public class MaxValueFieldValidationTest {

    @Test
    public void validate_WhenAgeIsLessThanOrEqual150_ShouldNotThrows() {
        final StubToValidate dataTOValidate = new StubToValidate(
                "teste",
                21,
                true,
                "2000-01-25T10:00:00.929Z"
        );

        final var sut = new MaxValueFieldValidation<StubToValidate>(
                "age",
                150.0,
                true,
                "isAdmin in invalid format"
        );
        dataTOValidate.setAge(10);

        assertThatCode(() -> sut.validate(dataTOValidate))
                .doesNotThrowAnyException();

    }

    @Test
    public void validate_WithEmptyage_ShouldThrows() {
        final StubToValidate dataTOValidate = new StubToValidate(
                "teste",
                21,
                true,
                "2000-01-25T10:00:00.929Z"
        );

        final var sut = new MaxValueFieldValidation<StubToValidate>(
                "age",
                150.0,
                true,
                new BadRequestException("isAdmin in invalid format")
        );
        dataTOValidate.setAge(null);

        assertThatThrownBy(() -> sut.validate(dataTOValidate))
                .isInstanceOf(BadRequestException.class);

    }

    @Test
    public void validate_WhenAgeIsMoreThan150_ShouldThrows() {
        final StubToValidate dataTOValidate = new StubToValidate(
                "teste",
                21,
                true,
                "2000-01-25T10:00:00.929Z"
        );

        final var sut = new MaxValueFieldValidation<StubToValidate>(
                "age",
                150.0,
                true,
                new BadRequestException("isAdmin in invalid format")
        );
        dataTOValidate.setAge(151);

        assertThatThrownBy(() -> sut.validate(dataTOValidate))
                .isInstanceOf(BadRequestException.class);

    }
}
