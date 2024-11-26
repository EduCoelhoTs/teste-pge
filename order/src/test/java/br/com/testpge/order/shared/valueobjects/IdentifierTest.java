package br.com.testpge.order.shared.valueobjects;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

import br.com.testpge.order.shared.exceptions.BadRequestException;
import br.com.testpge.order.shared.exceptions.ErrorsContainer;

public class IdentifierTest {

    @Test
    public void IdentifierInstance_WithValidInputedData_ShouldIntanciateIdentifierCorrectly() {
        final var data = UUID.randomUUID().toString();
        final var sut = new Identifier(data);

        assertThat(sut.value).isEqualTo(data);
    }

    @Test
    public void IdentifierInstance_WithInvalidInputedData_ShouldThrows() {
        assertThatThrownBy(() -> new Identifier("g4fd5g4+-fdgg87fd-dfgf54"))
                .isInstanceOf(BadRequestException.class);

        assertThatThrownBy(() -> new Identifier(
                "859865231",
                "invalid Identifier"
        ))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("invalid Identifier");

        assertThatThrownBy(() -> new Identifier("wrong data"))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    public void IdentifierInstance_WithInvalidInputedDataAndErrorsContainer_ShouldNotThrows() {
        final var wrongData = "859865231470";
        final var errorsContainer = new ErrorsContainer();

        assertThatCode(() -> new Identifier(
                wrongData,
                errorsContainer
        )).doesNotThrowAnyException();
        assertThat(
                errorsContainer.getErrors().toArray()[0]
        ).isInstanceOf(BadRequestException.class);
    }
}
