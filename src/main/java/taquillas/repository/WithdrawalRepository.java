package taquillas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taquillas.model.Withdrawal;

@Repository
public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {

	List<Withdrawal> findAllByUserId(Long userId);

	List<Withdrawal> findAllByItemId(Long itemId);

	List<Withdrawal> findAllByItemLockerId(Long lockerId);
}
