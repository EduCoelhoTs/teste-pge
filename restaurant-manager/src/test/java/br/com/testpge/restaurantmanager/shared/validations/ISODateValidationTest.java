package br.com.testpge.restaurantmanager.shared.validations;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;

import br.com.testpge.restaurantmanager.shared.exceptions.BadRequestException;
import br.com.testpge.restaurantmanager.shared.validations.stubs.StubToValidate;
import br.com.testpge.restaurantmanager.shared.valueobjects.ISODate;

public class ISODateValidationTest {

    private final StubToValidate dataTOValidate = new StubToValidate(
            "teste name",
            21,
            true,
            "2000-01-25T10:00:00.929Z"
    );

    @Test
    public void validate_WithCorrectBirthDate_ShouldNotThrows() {

        final var sut = new ISODateValidation<StubToValidate>(
                "birthDate",
                true,
                "isAdmin in invalid format"
        );
        dataTOValidate.setBirthDate("2000-01-25T10:00:00.929Z");

        assertThatCode(() -> sut.validate(dataTOValidate))
                .doesNotThrowAnyException();

        dataTOValidate.setBirthDate(
                new ISODate("2000-01-25T10:00:00.929Z")
        );

        assertThatCode(() -> sut.validate(dataTOValidate))
                .doesNotThrowAnyException();

        dataTOValidate.setBirthDate(
                OffsetDateTime.now()
        );

        assertThatCode(() -> sut.validate(dataTOValidate))
                .doesNotThrowAnyException();

    }

    @Test
    public void validate_WithEmptyBirthDate_ShouldThrows() {

        final var sut = new ISODateValidation<StubToValidate>(
                "birthDate",
                true,
                new BadRequestException("isAdmin in invalid format")
        );
        dataTOValidate.setBirthDate(null);

        assertThatThrownBy(() -> sut.validate(dataTOValidate))
                .isInstanceOf(BadRequestException.class);

    }

    @Test
    public void validate_WhenbirthDateIsNotValidBirthDate_ShouldThrows() {

        final var sut = new ISODateValidation<StubToValidate>(
                "birthDate",
                true,
                new BadRequestException("isAdmin in invalid format")
        );
        dataTOValidate.setBirthDate("2000-01-2510:00:00.929Z");

        assertThatThrownBy(() -> sut.validate(dataTOValidate))
                .isInstanceOf(BadRequestException.class);

        dataTOValidate.setBirthDate("2000--25T10:00:00.929Z");

        assertThatThrownBy(() -> sut.validate(dataTOValidate))
                .isInstanceOf(BadRequestException.class);

        dataTOValidate.setBirthDate("2000-25T10:00:00.929Z");

        assertThatThrownBy(() -> sut.validate(dataTOValidate))
                .isInstanceOf(BadRequestException.class);

        dataTOValidate.setBirthDate("01-25T10:00:00.929Z");

        assertThatThrownBy(() -> sut.validate(dataTOValidate))
                .isInstanceOf(BadRequestException.class);

    }
}
