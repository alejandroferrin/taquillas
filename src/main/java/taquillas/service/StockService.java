package taquillas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taquillas.model.Item;
import taquillas.repository.ItemRepository;

@Service
public class StockService {

	@Autowired
	private ItemRepository repo;

	public void stockModify(Item i, int q) {

		if (!i.isConsumable()) {
			int current = i.getExistencias();
			if (current <= 0) {
				i.setExistencias(1);
			} else {
				i.setExistencias(0);
			}
			repo.save(i);
		} else {
			int current = i.getExistencias();
			i.setExistencias(current - q);
			repo.save(i);
		}

	}

}
