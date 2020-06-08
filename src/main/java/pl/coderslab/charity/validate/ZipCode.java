package pl.coderslab.charity.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ZipCodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZipCode {
    String message() default "{Niepoprawny kod pocztowy}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {}; }
