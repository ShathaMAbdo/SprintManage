package se.BTH.ITProjectManagement.validators;

import se.BTH.ITProjectManagement.Annotations.PositiveNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PositiveNumberValidator implements ConstraintValidator<PositiveNumber, Integer> {

    @Override
    public void initialize(PositiveNumber contactNumber) {
    }

    @Override
    public boolean isValid(Integer contactField, ConstraintValidatorContext cxt) {
        return ((contactField != null )&& (contactField > -1)) ;
    }

}