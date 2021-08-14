package taquillas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taquillas.model.Locker;

@Repository
public interface LockerRepository extends JpaRepository<Locker, Long> {

	Optional<Locker> findByNumber(int number);

}
