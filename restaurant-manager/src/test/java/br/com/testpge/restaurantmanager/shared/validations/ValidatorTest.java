package br.com.testpge.restaurantmanager.shared.validations;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

    @Test
    public void isUUID_WithValidInputedData_ReturnsTrue() {

        assertThat(Validator.isUUID(UUID.randomUUID().toString()))
                .isEqualTo(true);
    }

    @Test
    public void isUUID_WithInvalidIputedData_ReturnsFalse() {

        assertThat(Validator.isUUID("wrong uuid"))
                .isEqualTo(false);
    }

    @Test
    public void isEmail_WithValidInputedData_ReturnsTrue() {

        assertThat(Validator.isEmail("test@example.com"))
                .isEqualTo(true);
    }

    @Test
    public void isEmail_WithInvalidIputedData_ReturnsFalse() {

        assertThat(Validator.isEmail("testexample.com"))
                .isEqualTo(false);

        assertThat(Validator.isEmail("test@examplecom"))
                .isEqualTo(false);

        assertThat(Validator.isEmail("test@example."))
                .isEqualTo(false);

        assertThat(Validator.isEmail("@example.com"))
                .isEqualTo(false);
    }

    @Test
    public void isStrongPassword_WithValidInputedData_ReturnsTrue() {

        assertThat(Validator.isStrongPassword("Test@123"))
                .isEqualTo(true);
    }

    @Test
    public void isStrongPassword_WithInvalidIputedData_ReturnsFalse() {

        assertThat(Validator.isStrongPassword("Test1234"))
                .isEqualTo(false);

        assertThat(Validator.isStrongPassword("Test12348"))
                .isEqualTo(false);

        assertThat(Validator.isStrongPassword("test@123"))
                .isEqualTo(false);

        assertThat(Validator.isStrongPassword("onlyLetters"))
                .isEqualTo(false);

        assertThat(Validator.isStrongPassword("onlyNumbers"))
                .isEqualTo(false);
    }

    @Test
    public void isISODate_WithValidInputedData_ReturnsTrue() {

        assertThat(Validator.isISODate("2000-01-25T10:00:00.929Z"))
                .isEqualTo(true);
    }

    @Test
    public void isISODate_WithInvalidIputedData_ReturnsFalse() {

        assertThat(Validator.isISODate("2000-01-25T10:00:00.929"))
                .isEqualTo(false);

        assertThat(Validator.isISODate("2000-01-25T10:00:00."))
                .isEqualTo(false);

        assertThat(Validator.isISODate("2000-01-25T10:00:00"))
                .isEqualTo(false);

        assertThat(Validator.isISODate("2000-01-2510:00:00.929Z"))
                .isEqualTo(false);

        assertThat(Validator.isISODate("01-25T10:00:00.929Z"))
                .isEqualTo(false);
    }

    @Test
    public void isCEP_WithValidInputedData_ReturnsTrue() {

        assertThat(Validator.isCEP("60124557"))
                .isEqualTo(true);
    }

    @Test
    public void isCEP_WithInvalidIputedData_ReturnsFalse() {

        assertThat(Validator.isCEP("6012455"))
                .isEqualTo(false);

        assertThat(Validator.isCEP("601245570"))
                .isEqualTo(false);

        assertThat(Validator.isCEP("worng cep"))
                .isEqualTo(false);

        assertThat(Validator.isCEP("6012t557"))
                .isEqualTo(false);
    }

    @Test
    public void isLatitude_WithValidInputedData_ReturnsTrue() {

        assertThat(Validator.isLatitude("-90.0000000"))
                .isEqualTo(true);
        assertThat(Validator.isLatitude("+90.0000000"))
                .isEqualTo(true);
    }

    @Test
    public void isLatitude_WithInvalidIputedData_ReturnsFalse() {

        assertThat(Validator.isLatitude("-90.0000001"))
                .isEqualTo(false);

        assertThat(Validator.isLatitude("+90.0000001"))
                .isEqualTo(false);

        assertThat(Validator.isLatitude("90.0000001"))
                .isEqualTo(false);
    }

    @Test
    public void isLongitude_WithValidInputedData_ReturnsTrue() {

        assertThat(Validator.isLongitude("-180.0000000"))
                .isEqualTo(true);
        assertThat(Validator.isLongitude("+180.0000000"))
                .isEqualTo(true);
    }

    @Test
    public void isLongitude_WithInvalidIputedData_ReturnsFalse() {

        assertThat(Validator.isLongitude("-180.0000001"))
                .isEqualTo(false);

        assertThat(Validator.isLongitude("+180.0000001"))
                .isEqualTo(false);

        assertThat(Validator.isLongitude("180.0000001"))
                .isEqualTo(false);
    }

    @Test
    public void isCPF_WithValidInputedData_ReturnsTrue() {

        assertThat(Validator.isCPF("04024568399"))
                .isEqualTo(true);
    }

    @Test
    public void isCPF_WithInvalidIputedData_ReturnsFalse() {

        assertThat(Validator.isCPF("0402456839"))
                .isEqualTo(false);

        assertThat(Validator.isCPF("040245688399"))
                .isEqualTo(false);

        assertThat(Validator.isCPF("04024t68399"))
                .isEqualTo(false);
    }

    @Test
    public void isCNPJ_WithValidInputedData_ReturnsTrue() {

        assertThat(Validator.isCNPJ("04024568000104"))
                .isEqualTo(true);
    }

    @Test
    public void isCNPJ_WithInvalidIputedData_ReturnsFalse() {

        assertThat(Validator.isCNPJ("0402456800010"))
                .isEqualTo(false);

        assertThat(Validator.isCNPJ("040244568000104"))
                .isEqualTo(false);

        assertThat(Validator.isCNPJ("04024568T00104"))
                .isEqualTo(false);
    }

    @Test
    public void validateAgeGreaterThanEighteen_WithValidInputedData_ReturnsTrue() {

        assertThat(Validator.validateAgeGreaterThanEighteen("2006-10-22T10:00:00.929Z"))
                .isEqualTo(true);
    }

    @Test
    public void validateAgeGreaterThanEighteen_WithInvalidIputedData_ReturnsFalse() {
        LocalDate currentDate = LocalDate.now();
        LocalDate seventeenYearsAgo = currentDate.minusYears(17);

        assertThat(Validator.validateAgeGreaterThanEighteen(seventeenYearsAgo.toString()))
                .isEqualTo(false);
    }

    @Test
    public void isFoneNumber_WithValidInputedData_ReturnsTrue() {

        assertThat(Validator.isFoneNumber("85988652314"))
                .isEqualTo(true);

        assertThat(Validator.isFoneNumber("8588652314"))
                .isEqualTo(true);
    }

    @Test
    public void isFoneNumber_WithInvalidIputedData_ReturnsFalse() {

        assertThat(Validator.isFoneNumber("8652314"))
                .isEqualTo(false);

        assertThat(Validator.isFoneNumber("885988652314"))
                .isEqualTo(false);

        assertThat(Validator.isFoneNumber("wrong number"))
                .isEqualTo(false);
    }
}
