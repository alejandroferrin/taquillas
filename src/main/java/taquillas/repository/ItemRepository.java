package taquillas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taquillas.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	Optional<Item> findByDenominacion(String denom);
}
