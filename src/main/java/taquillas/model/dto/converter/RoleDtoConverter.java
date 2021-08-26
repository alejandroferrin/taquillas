package taquillas.model.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import taquillas.model.Role;
import taquillas.model.User;
import taquillas.model.dto.RoleDto;
import taquillas.model.dto.UserDto;
import taquillas.repository.RoleRepository;
import taquillas.repository.UserRepository;

@Component
public class RoleDtoConverter {

    @Autowired
    private RoleRepository roleRepo;

    public Role transform(RoleDto dto) {
        return new Role().builder()
                .roleName(dto.getRoleName())
                .build();
    }

    public RoleDto inverseTransform(Role model) {
        return new RoleDto().builder()
                .id(model.getId())
                .roleName(model.getRoleName())
                .build();
    }

    public Role edit(RoleDto dto) {
        Role role = roleRepo.findById(dto.getId()).get();
        role.setRoleName(dto.getRoleName());
        return role;

    }

}
