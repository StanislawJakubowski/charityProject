package pl.coderslab.charity.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.donation.Donation;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class ZipCodeValidatorTest {

    Donation testDonation = new Donation();
    final Long correctQuantity = 3L;
    final String correctStreet = "Marszałkowska";
    final String correctCity = "Warszawa";
    final String correctPhoneNumber = "123456789";

    public Donation getTestDonation() {
        testDonation.setCity(correctCity);
        testDonation.setQuantity(correctQuantity);
        testDonation.setStreet(correctStreet);
        testDonation.setPhoneNumber(correctPhoneNumber);
        return testDonation;
    }

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Test
    public void zipCodeValidatorTestForCorrectZipCode() {
        final Donation testDonation = new Donation();
        String correctZipCode= "10-999";
        getTestDonation().setZipCode(correctZipCode);
        Set<ConstraintViolation<Donation>> violations  = validator.validate(getTestDonation());
        assertTrue(violations.isEmpty());
        violations.forEach(error -> System.err.println(error.getPropertyPath() + " " + error.getMessage()));

    }
    @Test
    public void zipCodeValidatorTestForInCorrectZipCode() {
        final Donation testDonation = new Donation();
        String incorrectZipCode= "22-55W";
        getTestDonation().setZipCode(incorrectZipCode);
        Set<ConstraintViolation<Donation>> violations  = validator.validate(getTestDonation());
        assertFalse(violations.isEmpty());
        violations.forEach(error -> System.err.println(error.getPropertyPath() + " " + error.getMessage()));

    }
}
