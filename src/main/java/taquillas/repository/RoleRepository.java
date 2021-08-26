package taquillas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taquillas.model.RoleName;
import taquillas.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	//Optional<Role> findByRoleName(RoleName name);
	Optional<Role> findByRoleName(String name);
}
