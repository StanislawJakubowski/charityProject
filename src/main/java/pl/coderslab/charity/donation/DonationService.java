package pl.coderslab.charity.donation;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public void save(Donation donation) {
        donationRepository.save(donation);
    }

    public void update(Donation donation) {
        donationRepository.save(donation);
    }

    public void delete(long id) {
        donationRepository.deleteById(id);
    }

    public Donation findById(long id) {
        return donationRepository.findById(id).orElse(null);
    }

    public List<Donation> finaAll() {
        return donationRepository.findAll();
    }

    public Long sumOfQuantities() {
        return donationRepository.sumOfQuantities();
    }

    public Long sumOfDonations() {
        return donationRepository.sumOfDonations();
    }
}
