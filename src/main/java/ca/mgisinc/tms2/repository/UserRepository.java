package ca.mgisinc.tms2.repository;

import ca.mgisinc.tms2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
}
