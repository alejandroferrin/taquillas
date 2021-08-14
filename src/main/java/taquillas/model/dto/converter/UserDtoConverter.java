package taquillas.model.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import taquillas.model.User;
import taquillas.model.dto.UserDto;
import taquillas.repository.RoleRepository;
import taquillas.repository.UserRepository;

@Component
public class UserDtoConverter {

	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private UserRepository userRepo;

	public User transform(UserDto dto) {
		return new User().builder()
				.name(dto.getName())
				.number(dto.getNumber())
				.role(roleRepo.findById(dto.getRoleId()).get())
				.build();
	}

	public UserDto inverseTransform(User model) {
		return new UserDto().builder()
				.id(model.getId())
				.name(model.getName())
				.number(model.getNumber())
				.roleId(model.getRole().getId())
				.build();
	}

	public User edit(UserDto dto) {
		User user = userRepo.findById(dto.getId()).get();
		user.setName(dto.getName());
		user.setNumber(dto.getNumber());
		user.setRole(roleRepo.findById(dto.getRoleId()).get());
		return user;

	}

}
