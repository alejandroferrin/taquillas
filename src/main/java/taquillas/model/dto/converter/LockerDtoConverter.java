package taquillas.model.dto.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import taquillas.model.Item;
import taquillas.model.Locker;
import taquillas.model.Role;
import taquillas.model.RoleName;
import taquillas.model.dto.LockerDto;
import taquillas.repository.ItemRepository;
import taquillas.repository.LockerRepository;
import taquillas.repository.RoleRepository;

@Component
public class LockerDtoConverter {

    @Autowired
    private LockerRepository lockerRepo;
    @Autowired
    private ItemRepository itemRepo;
    @Autowired
    private RoleRepository roleRepo;

    public Locker transform(LockerDto dto) {
        List<Role> roles = new ArrayList<>();
        if (dto.getRoles() != null) {
            dto.getRoles()
                    .forEach(r -> {
                        roles.add(roleRepo
                                .findByRoleName(r)
                                .orElse(null));
						/*
						if (RoleName.exist(r)) {
							roles.add(roleRepo
									.findByRoleName(RoleName.valueOf(r))
									.orElse(null));
						}
						 */
                    });
        }
        List<Item> items = new ArrayList<>();
        if (dto.getItems() != null) {
            dto.getItems()
                    .forEach(i -> items
                            .add(itemRepo.findByDenominacion(i).orElse(null)));
        }

        return new Locker().builder()
                .number(dto.getNumber())
                .rolesAutorizados(roles)
                .items(items)
                .build();
    }

    public LockerDto inverseTransform(Locker model) {

        return new LockerDto().builder()
                .id(model.getId())
                .number(model.getNumber())
                .roles(model.getRolesAutorizados().stream()
                        //.map(x -> x.getRoleName().name())
                        .map(x -> x.getRoleName())
                        .collect(Collectors.toList()))
                .items(model.getItems().stream()
                        .map(x -> x.getDenominacion())
                        .collect(Collectors.toList()))
                .build();

    }

    public Locker edit(LockerDto dto) {
        Locker model = lockerRepo.findById(dto.getId()).get();
        List<Role> roles = new ArrayList<>();
        if (dto.getRoles() != null) {
            dto.getRoles()
                    .forEach(r -> {
                        roles.add(roleRepo
                                .findByRoleName(r)
                                .orElse(null));
                        /*
                        if (RoleName.exist(r)) {
                            roles.add(roleRepo
                                    .findByRoleName(RoleName.valueOf(r))
                                    .orElse(null));
                        }
                         */
                    });
        }

        List<Item> items = new ArrayList<>();
        if (dto.getItems() != null) {
            dto.getItems()
                    .forEach(i -> items
                            .add(itemRepo.findByDenominacion(i).orElse(null)));
        }
        model.setRolesAutorizados(roles);
//		model.setItems(items);
        model.setNumber(dto.getNumber());

        return model;
    }

}
