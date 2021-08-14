package taquillas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taquillas.model.Item;
import taquillas.repository.ItemRepository;

@Service

public class StockService {

	@Autowired
	private ItemRepository repo;

	public void stockModify(long id, int q) {

		Item i = repo.findById(id).get();
		int current = i.getExistencias();
		i.setExistencias(current - q);
		repo.save(i);

	}

}
