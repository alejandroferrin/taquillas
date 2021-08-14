package taquillas.model.dto.converter;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import taquillas.model.Withdrawal;
import taquillas.model.dto.WithdrawalDto;
import taquillas.repository.ItemRepository;
import taquillas.repository.UserRepository;
import taquillas.repository.WithdrawalRepository;

@Component
public class WithdrawalDtoConverter {

	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private WithdrawalRepository wdRepo;

	public Withdrawal transform(WithdrawalDto dto) {
		return new Withdrawal().builder()
				.at(LocalDateTime.now())
				.user(userRepo.findByNumber(dto.getUserNumber()).orElse(null))
				.quantity(dto.getQuantity())
				.item(itemRepo.getById(dto.getItemId()))
				.build();
	}

	public WithdrawalDto inverseTransform(Withdrawal model) {

		return new WithdrawalDto().builder()
				.id(model.getId())
				.userNumber(model.getUser().getNumber())
				.itemId(model.getItem().getId())
				.quantity(model.getQuantity())
				.at(model.getAt().toString())
				.build();
	}

	public Withdrawal edit(WithdrawalDto dto) {
		Withdrawal model = wdRepo.getById(dto.getId());
		model.setAt(LocalDateTime.parse(dto.getAt()));
		model.setUser(userRepo.findByNumber(dto.getUserNumber()).orElse(null));
		model.setQuantity(dto.getQuantity());
		model.setItem(itemRepo.findById(dto.getItemId()).orElse(null));

		return model;

	}

}
