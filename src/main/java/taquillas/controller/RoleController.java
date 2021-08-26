package taquillas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import taquillas.model.Role;
import taquillas.model.User;
import taquillas.model.dto.RoleDto;
import taquillas.model.dto.UserDto;
import taquillas.model.dto.converter.RoleDtoConverter;
import taquillas.model.dto.converter.UserDtoConverter;
import taquillas.repository.RoleRepository;
import taquillas.repository.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleRepository repo;
    @Autowired
    private RoleDtoConverter dtoConverter;

    @GetMapping("/new")
    public String newElementForm(Model model) {
        model.addAttribute("roleForm", new RoleDto());
        return "role_form";
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("roleForm") RoleDto newElement,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "role_form";
        } else {
            repo.save(dtoConverter.transform(newElement));
            return "redirect:/role/list";
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable long id, Model model) {
        Role role = repo.findById(id).get();
        if (role != null) {
            model.addAttribute("roleForm",
                    dtoConverter.inverseTransform(role));
            return "role_form";
        } else
            return "redirect:/role/new";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("rolesList", repo.findAll());
        return "roles_list";
    }

    @PostMapping("/edit/submit")
    public String editSubmit(@Valid @ModelAttribute("roleForm") RoleDto edit,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "role_form";
        } else {
            repo.save(dtoConverter.edit(edit));
            return "redirect:/role/list";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        repo.deleteById(id);
        return "redirect:/role/list";
    }

}
