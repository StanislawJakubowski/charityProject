package pl.coderslab.charity.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.donation.Donation;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneNumberValidatorTest {

    Donation testDonation = new Donation();
    final Long correctQuantity = 3L;
    final String correctStreet = "Marszałkowska";
    final String correctCity = "Warszawa";
    final String correctZipCode= "10-999";

    public Donation getTestDonation() {
        testDonation.setCity(correctCity);
        testDonation.setQuantity(correctQuantity);
        testDonation.setStreet(correctStreet);
        testDonation.setZipCode(correctZipCode);
        return testDonation;
    }

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();


    @Test
    public void phoneNumberValidatorTestForCorrectPhoneNumber() {
        String correctPhoneNumber = "123456789";
        getTestDonation().setPhoneNumber(correctPhoneNumber);
        Set<ConstraintViolation<Donation>> violations  = validator.validate(getTestDonation());
        assertTrue(violations.isEmpty());
        violations.forEach(error -> System.err.println(error.getPropertyPath() + " " + error.getMessage()));

    }

    @Test
    public void phoneNumberValidatorTestForInCorrectPhoneNumber() {
        String correctPhoneNumber = "F23456789";
        getTestDonation().setPhoneNumber(correctPhoneNumber);
        Set<ConstraintViolation<Donation>> violations  = validator.validate(getTestDonation());
        assertFalse(violations.isEmpty());
        violations.forEach(error -> System.err.println(error.getPropertyPath() + " " + error.getMessage()));

    }
}
