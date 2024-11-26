package br.com.testpge.restaurantmanager.shared.validations;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

import br.com.testpge.restaurantmanager.shared.exceptions.BadRequestException;
import br.com.testpge.restaurantmanager.shared.validations.stubs.StubToValidate;

public class MaxLengthFieldValidationTest {

    private final StubToValidate dataTOValidate = new StubToValidate(
            "teste",
            21,
            true,
            "2000-01-25T10:00:00.929Z"
    );

    @Test
    public void validate_WhenNameHasLessThanOrEqual10Digits_ShouldNotThrows() {

        final var sut = new MaxLengthFieldValidation<StubToValidate>(
                "name",
                10,
                true,
                "isAdmin in invalid format"
        );
        dataTOValidate.setName("test");

        assertThatCode(() -> sut.validate(dataTOValidate))
                .doesNotThrowAnyException();

    }

    @Test
    public void validate_WithEmptyName_ShouldThrows() {

        final var sut = new MaxLengthFieldValidation<StubToValidate>(
                "name",
                10,
                true,
                new BadRequestException("isAdmin in invalid format")
        );
        dataTOValidate.setName(null);

        assertThatThrownBy(() -> sut.validate(dataTOValidate))
                .isInstanceOf(BadRequestException.class);

    }

    @Test
    public void validate_WhenNameHasMoreThan10Digits_ShouldThrows() {

        final var sut = new MaxLengthFieldValidation<StubToValidate>(
                "name",
                10,
                true,
                new BadRequestException("isAdmin in invalid format")
        );
        dataTOValidate.setName("name with invalid format");

        assertThatThrownBy(() -> sut.validate(dataTOValidate))
                .isInstanceOf(BadRequestException.class);

    }
}
