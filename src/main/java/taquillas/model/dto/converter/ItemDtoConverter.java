package taquillas.model.dto.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import taquillas.model.Item;
import taquillas.model.dto.ItemDto;
import taquillas.repository.ItemRepository;
import taquillas.repository.LockerRepository;

@Component
public class ItemDtoConverter {

	@Autowired
	private LockerRepository lockerRepo;
	@Autowired
	private ItemRepository itemRepo;

	public Item transform(ItemDto dto) {

		return new Item().builder()
				.codigo(dto.getCodigo())
				.denominacion(dto.getDenominacion())
				.existencias(dto.getExistencias())
				.locker(lockerRepo.findByNumber(dto.getTaquillaNumber())
						.orElse(null))
				.build();

	}

	public ItemDto inverseTransform(Item model) {

		return new ItemDto().builder()
				.id(model.getId())
				.codigo(model.getCodigo())
				.denominacion(model.getDenominacion())
				.existencias(model.getExistencias())
				.taquillaNumber(model.getLocker().getNumber())
				.build();

	}

	public Item edit(ItemDto dto) {

		Item item = itemRepo.findById(dto.getId()).get();

		item.setCodigo(dto.getCodigo());
		item.setDenominacion(dto.getDenominacion());
		item.setExistencias(dto.getExistencias());
		item.setLocker(
				lockerRepo.findByNumber(dto.getTaquillaNumber()).orElse(null));
		return item;

	}

}
