package br.com.testpge.restaurantmanager.shared.validations;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class Validator {

    public static Boolean isUUID(String value) {
        if (value == null) {
            return false;
        }

        try {
            UUID.fromString(value);

            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static Boolean isEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(emailRegex);
    }

    /*
        1. The password must contain at least one digit.
        2. The total length of the password must be at least six characters.
        3. The password must include at least one non-alphanumeric character (special character).
        4. The password should not contain the character '\n'.
        5. There must be at least one uppercase letter.
        6. There must be at least one lowercase letter.
     */
    public static Boolean isStrongPassword(String password) {
        String passwordRegex = "^(?=.*\\d)(?=^.{6,}$)((?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
        return password != null && password.matches(passwordRegex);
    }

    // The date must in the iso format '2000-01-25T10:00:00.929Z'
    public static Boolean isISODate(String date) {
        try {
            OffsetDateTime.parse(date);
            return true; // A data é válida
        } catch (DateTimeParseException e) {
            return false; // A data não é válida
        }
    }

    public static Boolean isCEP(String cep) {
        String cepRegex = "^[0-9]{8}$";
        return cep != null && cep.matches(cepRegex);
    }

    // The data must in the latitude format -90.0000000 until +90.0000000
    public static Boolean isLatitude(String latitude) {
        try {
            double value = Double.parseDouble(latitude);
            return value >= -90.0000000 && value <= 90.0000000;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // The date must in the longitude format -180.0000000 until +180.0000000
    public static Boolean isLongitude(String longitude) {
        try {
            double value = Double.parseDouble(longitude);
            return value >= -180.0000000 && value <= 180.0000000;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Boolean isCPF(String cpf) {
        String cpfRegex = "^[0-9]{11}$";
        return cpf != null && cpf.matches(cpfRegex);
    }

    public static Boolean isCNPJ(String cnpj) {
        String cnpjRegex = "^[0-9]{14}$";
        return cnpj != null && cnpj.matches(cnpjRegex);
    }

    public static Boolean validateAgeGreaterThanEighteen(String birthday) {
        try {
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(birthday);
            LocalDate birthDate = offsetDateTime.toLocalDate();
            LocalDate currentDate = LocalDate.now();

            int age = Period.between(birthDate, currentDate).getYears();

            return age >= 18;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isFoneNumber(String foneNumber) {
        String tenDigitsRegex = "^[0-9]{10}$";
        String elevenDigitsRegex = "^[0-9]{11}$";

        boolean isElevenDigits = foneNumber != null && foneNumber.matches(elevenDigitsRegex);
        boolean isTenDigits = foneNumber != null && foneNumber.matches(tenDigitsRegex);

        return isTenDigits || isElevenDigits;
    }
}
