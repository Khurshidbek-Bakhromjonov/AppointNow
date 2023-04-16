package com.appointnow.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsMatchesValidator implements ConstraintValidator<FieldsMatches,Object> {

    private String field;
    private String matchingField;

    @Override
    public void initialize(final FieldsMatches constraintAnnotation) {
        field = constraintAnnotation.field();
        matchingField = constraintAnnotation.matchingField();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        Object field1Value = new BeanWrapperImpl(value).getPropertyValue(field);
        Object field2Value = new BeanWrapperImpl(value).getPropertyValue(matchingField);

        if (field1Value != null)
            return field1Value.equals(field2Value);
        else
            return field2Value == null;
    }
}
