package co.test.ecommerce.Backend.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.test.ecommerce.Backend.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT * FROM u = ?1", nativeQuery = true)
	public List<User> findUserByEmail(@Param("id_category") Long id_category);

}
