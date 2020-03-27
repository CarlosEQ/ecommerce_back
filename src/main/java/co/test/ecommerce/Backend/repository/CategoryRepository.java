package co.test.ecommerce.Backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.test.ecommerce.Backend.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}