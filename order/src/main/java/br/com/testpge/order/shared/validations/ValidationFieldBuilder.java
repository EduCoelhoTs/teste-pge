package br.com.testpge.order.shared.validations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ValidationFieldBuilder<T> {

    private final List<Validation<T>> validations = new ArrayList<>();
    private final String field;

    public ValidationFieldBuilder(final String field) {
        this.field = field;
    }

    public ValidationFieldBuilder<T> required(
            String message,
            Exception error,
            Runnable log
    ) {
        final Validation<T> validation = new RequiredFieldValidation<>(
                this.field,
                message,
                error,
                log
        );
        this.validations.add(0, validation);

        return this;
    }

    public ValidationFieldBuilder<T> required(String message) {
        final Validation<T> validation = new RequiredFieldValidation<>(
                this.field,
                message
        );
        this.validations.add(0, validation);

        return this;
    }

    public ValidationFieldBuilder<T> required(Exception error) {
        final Validation<T> validation = new RequiredFieldValidation<>(
                this.field,
                error
        );
        this.validations.add(0, validation);

        return this;
    }

    public ValidationFieldBuilder<T> required() {
        final Validation<T> validation = new RequiredFieldValidation<>(this.field);
        this.validations.add(0, validation);

        return this;
    }

    public ValidationFieldBuilder<T> minValue(
            Double minValue,
            String message,
            Exception error,
            Runnable log
    ) {
        final Validation<T> validation = new MinValueFieldValidation<>(
                this.field,
                minValue,
                false,
                message,
                error,
                log
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> minValue(
            Double minValue,
            Exception error
    ) {
        final Validation<T> validation = new MinValueFieldValidation<>(
                this.field,
                minValue,
                false,
                error
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> minValue(
            Double minValue,
            String message
    ) {
        final Validation<T> validation = new MinValueFieldValidation<>(
                this.field,
                minValue,
                false,
                message
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> minValue(Double minValue) {
        final Validation<T> validation = new MinValueFieldValidation<>(
                this.field,
                minValue
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> minValue(BigDecimal minValue) {
        final Validation<T> validation = new MinValueFieldValidation<>(
                this.field,
                minValue.doubleValue()
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> maxValue(
            Double maxValue,
            String message,
            Exception error,
            Runnable log
    ) {
        final Validation<T> validation = new MaxValueFieldValidation<>(
                this.field,
                maxValue,
                false,
                message,
                error,
                log
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> maxValue(Double maxValue, String message) {
        final Validation<T> validation = new MaxValueFieldValidation<>(
                this.field,
                maxValue,
                false,
                message
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> maxValue(Double maxValue) {
        final Validation<T> validation = new MaxValueFieldValidation<>(
                this.field,
                maxValue
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> maxValue(BigDecimal maxValue) {
        final Validation<T> validation = new MaxValueFieldValidation<>(
                this.field,
                maxValue.doubleValue()
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> maxValue(Double maxValue, Exception error) {
        final Validation<T> validation = new MaxValueFieldValidation<>(
                this.field,
                maxValue,
                false,
                error
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> minLength(
            int minLength,
            String message,
            Exception error,
            Runnable log
    ) {
        final Validation<T> validation = new MinLengthFieldValidation<>(
                this.field,
                minLength,
                false,
                message,
                error,
                log
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> minLength(int minLength, String message) {
        final Validation<T> validation = new MinLengthFieldValidation<>(
                this.field,
                minLength,
                false,
                message
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> minLength(int minLength, Exception error) {
        final Validation<T> validation = new MinLengthFieldValidation<>(
                this.field,
                minLength,
                false,
                error
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> minLength(int minLength) {
        final Validation<T> validation = new MinLengthFieldValidation<>(
                this.field,
                minLength,
                false
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> maxLength(
            int maxLength,
            String message,
            Exception error,
            Runnable log
    ) {
        final Validation<T> validation = new MaxLengthFieldValidation<>(
                this.field,
                maxLength,
                false,
                message,
                error,
                log
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> maxLength(int maxLength, Exception error) {
        final Validation<T> validation = new MaxLengthFieldValidation<>(
                this.field,
                maxLength,
                false,
                error
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> maxLength(int maxLength, String message) {
        final Validation<T> validation = new MaxLengthFieldValidation<>(
                this.field,
                maxLength,
                false,
                message
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> maxLength(int maxLength) {
        final Validation<T> validation = new MaxLengthFieldValidation<>(
                this.field,
                maxLength,
                false
        );
        this.validations.add(validation);

        return this;
    }

    public <E extends Enum<E>> ValidationFieldBuilder<T> enumToCompare(
            Class<E> enumToCompare,
            String message,
            Exception error,
            Runnable log
    ) {
        final Validation<T> validation = new EnumValidation<>(
                this.field,
                enumToCompare,
                false,
                message,
                error,
                log
        );
        this.validations.add(validation);

        return this;
    }

    public <E extends Enum<E>> ValidationFieldBuilder<T> enumToCompare(Class<E> enumToCompare, String message) {
        final Validation<T> validation = new EnumValidation<>(
                this.field,
                enumToCompare,
                false,
                message
        );
        this.validations.add(validation);

        return this;
    }

    public <E extends Enum<E>> ValidationFieldBuilder<T> enumToCompare(Class<E> enumToCompare, Exception error) {
        final Validation<T> validation = new EnumValidation<>(
                this.field,
                enumToCompare,
                false,
                error
        );
        this.validations.add(validation);

        return this;
    }

    public <E extends Enum<E>> ValidationFieldBuilder<T> enumToCompare(Class<E> enumToCompare) {
        final Validation<T> validation = new EnumValidation<>(
                this.field,
                enumToCompare,
                false
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> isoDate(
            String message,
            Exception error,
            Runnable log
    ) {
        final Validation<T> validation = new ISODateValidation<>(
                this.field,
                false,
                message,
                error,
                log
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> isoDate(String message) {
        final Validation<T> validation = new ISODateValidation<>(
                this.field,
                false,
                message
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> isoDate(Exception error) {
        final Validation<T> validation = new ISODateValidation<>(
                this.field,
                false,
                error
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> isoDate() {
        final Validation<T> validation = new ISODateValidation<>(
                this.field,
                false
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> uuid(
            String message,
            Exception error,
            Runnable log
    ) {
        final Validation<T> validation = new UUIDValidation<>(
                this.field,
                false,
                message,
                error,
                log
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> uuid(String message) {
        final Validation<T> validation = new UUIDValidation<>(
                this.field,
                false,
                message
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> uuid(
            Exception error
    ) {
        final Validation<T> validation = new UUIDValidation<>(
                this.field,
                false,
                error
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> uuid() {
        final Validation<T> validation = new UUIDValidation<>(
                this.field,
                false
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> bool(
            String message,
            Exception error,
            Runnable log
    ) {
        final Validation<T> validation = new BooleanValidation<>(
                this.field,
                false,
                message,
                error,
                log
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> bool(String message) {
        final Validation<T> validation = new BooleanValidation<>(
                this.field,
                false,
                message
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> bool(
            Exception error
    ) {
        final Validation<T> validation = new BooleanValidation<>(
                this.field,
                false,
                error
        );
        this.validations.add(validation);

        return this;
    }

    public ValidationFieldBuilder<T> bool() {
        final Validation<T> validation = new BooleanValidation<>(
                this.field,
                false
        );
        this.validations.add(validation);

        return this;
    }

    public Validation<T> build() {
        return new ValidationComposite<>(new HashSet<>(this.validations));
    }
}
