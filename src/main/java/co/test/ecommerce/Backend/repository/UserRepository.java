package co.test.ecommerce.Backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.test.ecommerce.Backend.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
