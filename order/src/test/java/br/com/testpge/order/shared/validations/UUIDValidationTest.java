package br.com.testpge.order.shared.validations;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import br.com.testpge.order.shared.exceptions.BadRequestException;
import br.com.testpge.order.shared.validations.stubs.StubToValidate;
import br.com.testpge.order.shared.valueobjects.Identifier;

public class UUIDValidationTest {

    private final StubToValidate dataTOValidate = new StubToValidate(
            "teste name",
            21,
            true,
            "2000-01-25T10:00:00.929Z"
    );

    @Test
    public void validate_WithCorrectId_ShouldNotThrows() {

        final var sut = new UUIDValidation<StubToValidate>(
                "id",
                true,
                "isAdmin in invalid format"
        );
        dataTOValidate.setId(UUID.randomUUID().toString());

        assertThatCode(() -> sut.validate(dataTOValidate))
                .doesNotThrowAnyException();

        dataTOValidate.setId(UUID.randomUUID());

        assertThatCode(() -> sut.validate(dataTOValidate))
                .doesNotThrowAnyException();

        dataTOValidate.setId(
                new Identifier(UUID.randomUUID().toString())
        );

        assertThatCode(() -> sut.validate(dataTOValidate))
                .doesNotThrowAnyException();

    }

    @Test
    public void validate_WithEmptyId_ShouldThrows() {

        final var sut = new UUIDValidation<StubToValidate>(
                "id",
                true,
                new BadRequestException("isAdmin in invalid format")
        );
        dataTOValidate.setId(null);

        assertThatThrownBy(() -> sut.validate(dataTOValidate))
                .isInstanceOf(BadRequestException.class);

    }

    @Test
    public void validate_WhenidIsNotValidId_ShouldThrows() {

        final var sut = new UUIDValidation<StubToValidate>(
                "id",
                true,
                new BadRequestException("isAdmin in invalid format")
        );
        dataTOValidate.setId("wrong value");

        assertThatThrownBy(() -> sut.validate(dataTOValidate))
                .isInstanceOf(BadRequestException.class);

        dataTOValidate.setId("dsa-dsaf4fdf-sdf4df878-afsd748df7");
    }
}
