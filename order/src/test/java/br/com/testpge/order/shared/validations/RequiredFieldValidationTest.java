package br.com.testpge.order.shared.validations;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

import br.com.testpge.order.shared.exceptions.BadRequestException;
import br.com.testpge.order.shared.validations.stubs.StubToValidate;

public class RequiredFieldValidationTest {

    private final StubToValidate dataTOValidate = new StubToValidate(
            "teste",
            21,
            true,
            "2000-01-25T10:00:00.929Z"
    );

    @Test
    public void validate_WhenNameIsNotEmpty_ShouldNotThrows() {

        final var sut = new RequiredFieldValidation<StubToValidate>(
                "name",
                "isAdmin in invalid format"
        );
        dataTOValidate.setName("test");

        assertThatCode(() -> sut.validate(dataTOValidate))
                .doesNotThrowAnyException();

    }

    @Test
    public void validate_WithEmptyName_ShouldThrows() {

        final var sut = new RequiredFieldValidation<StubToValidate>(
                "name",
                new BadRequestException("isAdmin in invalid format")
        );
        dataTOValidate.setName(null);

        assertThatThrownBy(() -> sut.validate(dataTOValidate))
                .isInstanceOf(BadRequestException.class);

    }
}
