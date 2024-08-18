package ca.mgisinc.tms2.repository;

import ca.mgisinc.tms2.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
	
	Privilege findByName(String name);
}
