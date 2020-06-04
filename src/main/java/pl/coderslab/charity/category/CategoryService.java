package pl.coderslab.charity.category;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    public final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public void update(Category category) {
        categoryRepository.save(category);
    }

    public void delete(long id) {

        categoryRepository.deleteById(id);
    }

    public void deleteCategoryRelations(long id) {
        categoryRepository.deleteCategoryRelations(id);
    }

    public Category findById(long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<Category> finaAll() {
        return categoryRepository.findAll();
    }

}
