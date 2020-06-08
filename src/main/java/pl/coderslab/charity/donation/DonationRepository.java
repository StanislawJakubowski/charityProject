package pl.coderslab.charity.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DonationRepository extends JpaRepository<Donation, Long> {


    @Query(value = "SELECT SUM(quantity) from donations", nativeQuery = true)
    Long sumOfQuantities();

    @Query(value = "SELECT count(id) from donations", nativeQuery = true)
    Long sumOfDonations();

}
