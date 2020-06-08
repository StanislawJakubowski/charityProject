package pl.coderslab.charity.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

    final private Pattern zipCodePattern = Pattern.compile("[0-9]{2}-[0-9]{3}");

    @Override
    public void initialize(ZipCode constraintAnnotation) {

    }

    @Override
    public boolean isValid(String zipCode, ConstraintValidatorContext constraintValidatorContext) {
        return !(zipCode == null || zipCode.isEmpty()) &&  zipCodePattern.matcher(zipCode).matches();
    }
}
