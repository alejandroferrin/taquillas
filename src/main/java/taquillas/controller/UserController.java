package taquillas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import taquillas.model.User;
import taquillas.model.dto.UserDto;
import taquillas.model.dto.converter.UserDtoConverter;
import taquillas.repository.RoleRepository;
import taquillas.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository repo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private UserDtoConverter dtoConverter;

	@GetMapping("/new")
	public String newElementForm(Model model) {
		model.addAttribute("userForm", new UserDto());
		model.addAttribute("rolesList", roleRepo.findAll());
		return "user_form";
	}

	@PostMapping("/save")
	public String save(
			@Valid @ModelAttribute("userForm") UserDto newElement,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "user_form";
		} else {
			repo.save(dtoConverter.transform(newElement));
			return "redirect:/user/list";
		}
	}

	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable long id, Model model) {
		User user = repo.findById(id).get();
		if (user != null) {
			model.addAttribute("userForm",
					dtoConverter.inverseTransform(user));
			model.addAttribute("rolesList", roleRepo.findAll());
			return "user_form";
		} else
			return "redirect:/user/new";
	}

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("usersList", repo.findAll());
		return "users_list";
	}

	@PostMapping("/edit/submit")
	public String editSubmit(@Valid @ModelAttribute("userForm") UserDto edit,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "user_form";
		} else {
			repo.save(dtoConverter.edit(edit));
			return "redirect:/user/list";
		}
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		repo.deleteById(id);
		return "redirect:/user/list";
	}

}
