package pl.coderslab.charity.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

   final private Pattern phoneNumberPattern = Pattern.compile("[0-9]{9}");

   @Override
   public void initialize(Phone constraint) {
   }

   @Override
   public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
      return !(phoneNumber == null || phoneNumber.isEmpty()) &&  phoneNumberPattern.matcher(phoneNumber).matches();
   }
}
