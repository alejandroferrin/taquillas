package taquillas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import taquillas.gpio.GPIO_Service;
import taquillas.gpio.SerialService;

import taquillas.model.Locker;
import taquillas.model.dto.LockerDto;
import taquillas.model.dto.LockerDtoAddRole;
import taquillas.model.dto.converter.LockerDtoConverter;
import taquillas.repository.LockerRepository;
import taquillas.repository.RoleRepository;

@Controller
@RequestMapping("/locker")
@CrossOrigin(origins = "https://localhost:8081")
public class LockerController {

  @Autowired
  private LockerRepository repo;
  @Autowired
  private RoleRepository roleRepo;
  @Autowired
  private LockerDtoConverter dtoConverter;
  @Autowired
  private SerialService serialService;
  @Autowired
  private GPIO_Service gpio;

  @GetMapping("/serial_ports")
  public String getPorts(Model model) {
    model.addAttribute("serial_ports", serialService.getSerialPorts());
    return "serial_ports";
  }

  @GetMapping("/new")
  public String newElementForm(Model model) {
    model.addAttribute("lockerForm", new LockerDto());
    return "locker_form";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST, params = "action=save")
  public String save(
    @Valid @ModelAttribute("lockerForm") LockerDto newElement,
    BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "locker_form";
    } else {
      repo.save(dtoConverter.transform(newElement));
      return "redirect:/locker/list";
    }
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST, params = "action=edit")
  public String editSubmit(
    @Valid @ModelAttribute("lockerForm") LockerDto edit,
    BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "locker_form";
    } else {
      repo.save(dtoConverter.edit(edit));
      return "redirect:/locker/list";
    }
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST, params = "action=addRole")
  public String addRole(
    Model model,
    @Valid @ModelAttribute("lockerForm") LockerDto edit,
    BindingResult bindingResult) {

    LockerDtoAddRole lda = new LockerDtoAddRole().builder()
      .id(edit.getId())
      .number(edit.getNumber())
      .roles(edit.getRoles())
      .items(edit.getItems())
      .build();

    model.addAttribute("lockerFormAdd", lda);
    model.addAttribute("rolesList", roleRepo.findAll());

    if (bindingResult.hasErrors()) {
      return "redirect:/locker/new";
    } else {
      return "addRole_form";
    }
  }

  @PostMapping("/roleAdded")
  public String roleAdded(
    Model model,
    @Valid @ModelAttribute("lockerFormAdd") LockerDtoAddRole edit,
    BindingResult bindingResult) {

    List<String> roles = edit.getRoles();
    if (roles == null) {
      roles = new ArrayList<>();
    }
    if (edit.getAddedRole() != null) {
      roles.add(edit.getAddedRole());
    }

    LockerDto dto = new LockerDto().builder()
      .id(edit.getId())
      .number(edit.getNumber())
      .roles(roles)
      .items(edit.getItems())
      .build();

    model.addAttribute("lockerForm", dto);

    return "locker_form";
  }

  @GetMapping("/edit/{id}")
  public String editForm(@PathVariable long id, Model model) {
    Locker locker = repo.findById(id).get();
    if (locker != null) {
      model.addAttribute("lockerForm",
        dtoConverter.inverseTransform(locker));
      return "locker_form";
    } else {
      return "redirect:/locker/new";
    }
  }

  @GetMapping("/list")
  public String list(Model model) {
    model.addAttribute("lockersList", repo.findAll());
    //System.out.println(os);
    //System.out.println(raspberry);
    //System.out.println(other);
    return "locker_list";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable long id) {
    repo.deleteById(id);
    return "redirect:/locker/list";
  }

  @GetMapping("/open/{number}")
  public String open(@PathVariable int number) {
    gpio.open(number);
    return "redirect:/locker/list";
  }

  @GetMapping("/close/{number}")
  public String close(@PathVariable int number) {
    gpio.close(number);
    return "redirect:/locker/list";
  }

}
