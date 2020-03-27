package co.test.ecommerce.Backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.test.ecommerce.Backend.model.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
