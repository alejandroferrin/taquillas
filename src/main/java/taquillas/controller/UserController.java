package taquillas.controller;

import java.sql.SQLException;
import javax.validation.Valid;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

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
    Model model,
    @Valid @ModelAttribute("userForm") UserDto newElement,
    BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "user_form";
    } else {
      try {
        repo.save(dtoConverter.transform(newElement));
        return "redirect:/user/list";
      }catch (DataIntegrityViolationException e){
        model.addAttribute("error", "No se pudo realizar la operación por violar alguna restricción.\n"+e.getMostSpecificCause());
        return "error";
      } catch (Exception e) {
        model.addAttribute("error", "No se pudo guardar el usuario: " + e);
        return "error";
      }
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
    } else {
      return "redirect:/user/new";
    }
  }

  @GetMapping("/list")
  public String list(Model model) {
    model.addAttribute("usersList", repo.findAll());
    return "users_list";
  }

  @PostMapping("/edit/submit")
  public String editSubmit(
    Model model,
    @Valid @ModelAttribute("userForm") UserDto edit,
    BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "user_form";
    } else {
      try {
        repo.save(dtoConverter.edit(edit));
        return "redirect:/user/list";
      }catch (DataIntegrityViolationException e){
        model.addAttribute("error", "No se pudo realizar la operación por violar alguna restricción.\n"+e.getMostSpecificCause());
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
      return "redirect:/user/list";
    } catch (Exception e) {
      model.addAttribute("error", "No se pudo borrar el usuario: " + e.getMessage());
      return "error";
    }
  }

}
