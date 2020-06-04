package pl.coderslab.charity.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     *
     */
    @Modifying
    @Query(value = "delete from donations_categories where categories_id =?1", nativeQuery = true)
    void deleteCategoryRelations(Long id);

}
