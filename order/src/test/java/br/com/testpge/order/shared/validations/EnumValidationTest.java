package br.com.testpge.order.shared.validations;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

import br.com.testpge.order.shared.exceptions.BadRequestException;
import br.com.testpge.order.shared.validations.stubs.StubEnum;
import br.com.testpge.order.shared.validations.stubs.StubToValidate;

public class EnumValidationTest {

    private final StubToValidate dataTOValidate = new StubToValidate(
            "teste name",
            21,
            true,
            "2000-01-25T10:00:00.929Z"
    );

    @Test
    public void validate_WithCorrectEnum_ShouldNotThrows() {

        final var sut = new EnumValidation<StubToValidate, StubEnum>(
                "role",
                StubEnum.class,
                true,
                "isAdmin in invalid format"
        );
        dataTOValidate.setRole(StubEnum.ANALIST);

        assertThatCode(() -> sut.validate(dataTOValidate))
                .doesNotThrowAnyException();

    }

    @Test
    public void validate_WithEmptyEnum_ShouldThrows() {

        final var sut = new EnumValidation<StubToValidate, StubEnum>(
                "role",
                StubEnum.class,
                true,
                new BadRequestException("isAdmin in invalid format")
        );
        dataTOValidate.setRole(null);

        assertThatThrownBy(() -> sut.validate(dataTOValidate))
                .isInstanceOf(BadRequestException.class);

    }
}
