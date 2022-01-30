/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquillas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taquillas.model.Item;
import taquillas.model.Role;
import taquillas.model.User;
import taquillas.model.dto.WithdrawalDto;
import taquillas.repository.ItemRepository;
import taquillas.repository.UserRepository;

/**
 *
 * @author alex
 */
@Service
public class CheckRoleService {

	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private UserRepository userRepo;

	public boolean isAuthorized(String cardNumber, Item itemCandidate) {

		String userNumber = cardNumber;
		long itemId = itemCandidate.getId();

		User user = userRepo.findByNumber(userNumber).orElse(null);
		Item item = itemRepo.findById(itemId).orElse(null);
		if (user == null || item == null) {
			return false;
		}

		Role userRole = user.getRole();
		List<Role> lockerRole = item.getLocker().getRolesAutorizados();

		for (Role role : lockerRole) {
			if (role == userRole) {
				return true;
			}

		}
		return false;
	}

	public boolean isAuthorized(WithdrawalDto dto) {

		String userNumber = dto.getUserNumber();
		long itemId = dto.getItemId();

		User user = userRepo.findByNumber(userNumber).orElse(null);
		Item item = itemRepo.findById(itemId).orElse(null);
		if (user == null || item == null) {
			return false;
		}

		Role userRole = user.getRole();
		List<Role> lockerRole = item.getLocker().getRolesAutorizados();

		for (Role role : lockerRole) {
			if (role == userRole) {
				return true;
			}

		}
		return false;
	}
}
