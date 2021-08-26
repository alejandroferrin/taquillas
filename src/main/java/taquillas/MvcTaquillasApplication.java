package taquillas;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import taquillas.model.RoleName;
import taquillas.model.Role;
import taquillas.repository.RoleRepository;
import taquillas.smartcard.CardReader;

@SpringBootApplication
public class MvcTaquillasApplication {

    @Autowired
    private RoleRepository repo;

    @PostConstruct
    public void init() {
        List<Role> roles = repo.findAll();
        if (roles.size() == 0) {
            Role[] array = {
                 new Role().builder().roleName("ADMIN").build()
                    /*
                 new Role().builder().roleName(RoleName.OPERARIO).build(),
                 new Role().builder().roleName(RoleName.JEFE_EQUIPO).build(),
                 new Role().builder().roleName(RoleName.ADMIN).build()

                     */
            };
            for (Role role : array) {
                repo.save(role);
            }

            System.out.println("#####_Roles creados");
        } else {
            System.out.println("#####_Roles existen");
        }


    }

    public static void main(String[] args) {
        SpringApplication.run(MvcTaquillasApplication.class, args);
    }

}
