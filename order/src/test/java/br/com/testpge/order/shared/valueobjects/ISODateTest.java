package br.com.testpge.order.shared.valueobjects;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

import br.com.testpge.order.shared.exceptions.BadRequestException;
import br.com.testpge.order.shared.exceptions.ErrorsContainer;

public class ISODateTest {

    @Test
    public void ISODateInstance_WithValidInputedData_ShouldIntanciateISODateCorrectly() {
        final var data = OffsetDateTime.now().toString();
        final var sut = new ISODate(data);

        assertThat(sut.value).isEqualTo(data);
    }

    @Test
    public void ISODateInstance_WithInvalidInputedData_ShouldThrows() {
        assertThatThrownBy(() -> new ISODate("2000-01-2510:00:00.929Z"))
                .isInstanceOf(BadRequestException.class);

        assertThatThrownBy(() -> new ISODate(
                "2000--25T10:00:00.929Z",
                "invalid ISODate"
        ))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("invalid ISODate");

        assertThatThrownBy(() -> new ISODate("wrong data"))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    public void ISODateInstance_WithInvalidInputedDataAndErrorsContainer_ShouldNotThrows() {
        final var wrongData = "2000-25T10:00:00.929Z";
        final var errorsContainer = new ErrorsContainer();

        assertThatCode(() -> new ISODate(
                wrongData,
                errorsContainer
        )).doesNotThrowAnyException();
        assertThat(
                errorsContainer.getErrors().toArray()[0]
        ).isInstanceOf(BadRequestException.class);
    }
}
