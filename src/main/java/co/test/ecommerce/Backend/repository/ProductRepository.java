package co.test.ecommerce.Backend.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.test.ecommerce.Backend.model.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(value = "SELECT * FROM categories JOIN products ON categories.id = products.id_category WHERE categories.id = :id_category", nativeQuery = true)
	public List<Product> findProductByCategoryId(@Param("id_category") Long id_category);

}
