package br.com.testpge.order.shared.constants;

public class ErrorMessages {

    public static String invalidFormat(String field) {
        return field + " em formato inválido";
    }

    public static String charactersRange(String field, int min, int max) {
        return field + " Deve ser entre " + 3 + " e " + 255 + " caracteres";
    }

    public static String empty(String field) {
        return field + " não pode ser vazio(a)";
    }

    public static String mustBe(String field, String type) {
        return field + " deve ser " + type;
    }

    public static String maxLenth(String field, int max) {
        return field + " deve conter no máximo " + max + " caracteres";
    }

    public static String minLenth(String field, int min) {
        return field + " deve conter no mínimo " + min + " caracteres";
    }

    public static String maxValue(String field, int max) {
        return field + " dever ser no máximo " + max;
    }

    public static String minValue(String field, int min) {
        return field + " deve ser no mínimo " + min;
    }

    public static String notFoundEntityById(String entityName) {
        return "Não há " + entityName + " com esse id";
    }

    public static String entityWithUniqeFieldAlreadyExists(String entityName, String field) {
        return "Já há " + entityName + " registrada com esse " + field;
    }
}
