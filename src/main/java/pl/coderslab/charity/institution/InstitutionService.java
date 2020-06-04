package pl.coderslab.charity.institution;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public void save(Institution institution) {
        institutionRepository.save(institution);
    }

    public void update(Institution institution) {
        institutionRepository.save(institution);
    }

    public void delete(long id) {
        institutionRepository.deleteById(id);
    }

    public Institution findById(long id) {
        return institutionRepository.findById(id).orElse(null);
    }

    public List<Institution> finaAll() {
        return institutionRepository.findAll();
    }

}
