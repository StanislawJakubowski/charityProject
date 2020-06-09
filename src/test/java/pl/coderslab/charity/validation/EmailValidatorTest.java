package pl.coderslab.charity.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.user.User;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class EmailValidatorTest  {

    User testUser = new User();
    final String correctLastName = "Bingo";
    final String correctFirstName = "John";
    final String correctPassword = "123";

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    public User getTestUser(){
        testUser.setPassword(correctPassword);
        testUser.setLastName(correctLastName);
        testUser.setFirstName(correctFirstName);
        return testUser;
    }
    @Test
    public void emailValidatorTestForCorrectEmail(){
        final User testUser = new User();
        String correctEmail = "tjancz@gmail.com";
        getTestUser().setEmail(correctEmail);
        Set<ConstraintViolation<User>> violations   = validator.validate(getTestUser());
        assertTrue(violations.isEmpty());
        violations.forEach(error -> System.err.println(error.getPropertyPath() + " " + error.getMessage()));

    }
    @Test
    public void emailValidatorTestForIncorrectEmail(){
        final User testUser= new User();
        String inCorrectEmail = "tjancz@gmail";
        getTestUser().setEmail(inCorrectEmail);
        Set<ConstraintViolation<User>> violations   = validator.validate(getTestUser());
        assertFalse(violations.isEmpty());
        violations.forEach(error -> System.err.println(error.getPropertyPath() + " " + error.getMessage()));
    }

}