package taquillas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import taquillas.model.Item;
import taquillas.model.dto.ItemDto;
import taquillas.model.dto.converter.ItemDtoConverter;
import taquillas.repository.ItemRepository;
import taquillas.repository.LockerRepository;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemRepository repo;
	@Autowired
	private LockerRepository lockerRepo;
	@Autowired
	private ItemDtoConverter dtoConverter;

	@GetMapping("/new")
	public String newElementForm(Model model) {
		model.addAttribute("itemForm", new ItemDto());
		model.addAttribute("lockersList", lockerRepo.findAll());
		return "item_form";
	}

	@PostMapping("/save")
	public String save(
			Model model,
			@Valid @ModelAttribute("itemForm") ItemDto newElement,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "item_form";
		} else {
			try {
				repo.save(dtoConverter.transform(newElement));
				return "redirect:/item/list";
			} catch (DataIntegrityViolationException e) {
				model.addAttribute("error",
						"No se pudo realizar la operación por violar alguna restricción.\n" + e.getMostSpecificCause());
				return "error";
			} catch (Exception e) {
				model.addAttribute("error", "No se pudo guardar: " + e);
				return "error";
			}
		}
	}

	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable long id, Model model) {
		Item item = repo.findById(id).get();
		if (item != null) {
			model.addAttribute("itemForm",
					dtoConverter.inverseTransform(item));
			model.addAttribute("lockersList", lockerRepo.findAll());
			return "item_form";
		} else {
			return "redirect:/item/new";
		}
	}

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("itemsList", repo.findAll());
		return "item_list";
	}

	@PostMapping("/edit/submit")
	public String editSubmit(
			Model model,
			@Valid @ModelAttribute("itemForm") ItemDto edit,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "item_form";
		} else {
			try {
				repo.save(dtoConverter.edit(edit));
				return "redirect:/item/list";
			} catch (DataIntegrityViolationException e) {
				model.addAttribute("error",
						"No se pudo realizar la operación por violar alguna restricción.\n" + e.getMostSpecificCause());
				return "error";
			} catch (Exception e) {
				model.addAttribute("error", "No se pudo guardar: " + e);
				return "error";
			}
		}
	}

	@GetMapping("/delete/{id}")
	public String delete(
			Model model,
			@PathVariable long id) {
		try {
			repo.deleteById(id);
			return "redirect:/item/list";
		} catch (Exception e) {
			model.addAttribute("error", "No se pudo borrar el artículo: " + e.getMessage());
			return "error";
		}
	}

}
