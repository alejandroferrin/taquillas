package taquillas.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import taquillas.gpio.GPIO_Service;
import taquillas.model.Item;
import taquillas.model.User;
import taquillas.model.Withdrawal;
import taquillas.model.dto.WithdrawalDto;
import taquillas.model.dto.converter.WithdrawalDtoConverter;
import taquillas.repository.ItemRepository;
import taquillas.repository.UserRepository;
import taquillas.repository.WithdrawalRepository;
import taquillas.service.CheckRoleService;
import taquillas.service.LoginService;
import taquillas.service.StockService;
import taquillas.smartcard.CardReader;

@Controller
@RequestMapping("/withdrawal")
public class WithdrawalController {

	@Autowired
	private WithdrawalRepository repo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private WithdrawalDtoConverter dtoConverter;
	@Autowired
	private StockService stockService;
	@Autowired
	private CardReader cardReader;
	@Autowired
	private CheckRoleService checkService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private GPIO_Service gpio;

	@Value("${admin_password}")
	private String pass;

	@GetMapping("/card")
	public String redirect(Model model) {
		String cardNumber;
		try {
			cardNumber = cardReader.getRead();
			WithdrawalDto dto = new WithdrawalDto().builder().userNumber(cardNumber).build();
			User user = userRepo.findByNumber(cardNumber).orElse(null);
			if (user != null) {
				if (user.getRole().getRoleName().equals("ADMIN")) {
					loginService.login("admin", pass);
					return "redirect:/item/list";
				}
				model.addAttribute("withdrawalForm", dto);
				List<Item> items = itemRepo.findAll();
				model.addAttribute("itemsList", items.stream()
						.filter(i -> checkService.isAuthorized(cardNumber, i))
						.collect(Collectors.toList()));
				return "withdrawal_form_card";
			}
		} catch (Exception ex) {
			return "redirect:/";
		}
		return "redirect:/";

	}

	@GetMapping("/new")
	public String newElementForm(Model model) {
		model.addAttribute("withdrawalForm", new WithdrawalDto());
		model.addAttribute("itemsList", itemRepo.findAll());
		model.addAttribute("usersList", userRepo.findAll());
		return "withdrawal_form_manual";
	}

	@PostMapping("/save")
	public String save(Model model, @Valid @ModelAttribute("withdrawalForm") WithdrawalDto newElement,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/";
		} else {
			if (checkService.isAuthorized(newElement)) {
				try {
					int quantity = newElement.getQuantity();
					Item item = itemRepo.findById(newElement.getItemId()).orElse(null);

					if (item != null) {
						if (quantity <= item.getExistencias()) {
							repo.save(dtoConverter.transform(newElement));
							stockService.stockModify(newElement.getItemId(), quantity);

							gpio.open(item.getLocker().getNumber());

							model.addAttribute("lockerNumber", item.getLocker().getNumber());
							model.addAttribute("itemId", newElement.getItemId());
							return "close_locker";
						} else {
							model.addAttribute("existenciasItem", item.getExistencias());
							model.addAttribute("nombreItem", item.getDenominacion());
							return "sin_existencias";
						}

					} else {
						return "redirect:/";
					}

				} catch (Exception e) {
					model.addAttribute("error", "No se pudo realizar la retirada: " + e.getMessage());
					return "error";
				}

			} else {
				return "not_authorized";
			}
		}
	}

	@GetMapping("/close/{id}")
	public String close(@PathVariable long id) {
		Item item = itemRepo.findById(id).orElse(null);
		if (item != null) {

			gpio.close(item.getLocker().getNumber());

		}
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable long id, Model model) {
		Withdrawal wd = repo.findById(id).get();
		if (wd != null) {
			model.addAttribute("withdrawalForm", dtoConverter.inverseTransform(wd));
			model.addAttribute("itemsList", itemRepo.findAll());
			model.addAttribute("usersList", userRepo.findAll());
			return "withdrawal_form_manual";
		} else {
			return "redirect:/withdrawal/new";
		}
	}

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("withdrawalList", repo.findAll());
		return "withdrawal_list";
	}

	@GetMapping("/list/byLocker/{id}")
	public String listByLocker(Model model, @PathVariable long id) {
		model.addAttribute("withdrawalList", repo.findAllByItemLockerId(id));
		return "withdrawal_list";
	}

	@GetMapping("/list/byItem/{id}")
	public String listByItem(Model model, @PathVariable long id) {
		model.addAttribute("withdrawalList", repo.findAllByItemId(id));
		return "withdrawal_list";
	}

	@GetMapping("/list/byUser/{id}")
	public String listByUser(Model model, @PathVariable long id) {
		model.addAttribute("withdrawalList", repo.findAllByUserId(id));
		return "withdrawal_list";
	}

	@PostMapping("/edit/submit")
	public String editSubmit(Model model, @Valid @ModelAttribute("withdrawalForm") WithdrawalDto edit,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "withdrawal_form";
		} else {
			try {
				repo.save(dtoConverter.edit(edit));
				return "redirect:/withdrawal/list";
			} catch (Exception e) {
				model.addAttribute("error", "No se pudo editar la retirada: " + e.getMessage());
				return "error";
			}
		}
	}

	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable long id) {
		try {
			repo.deleteById(id);
			return "redirect:/withdrawal/list";
		} catch (Exception e) {
			model.addAttribute("error", "No se pudo eliminar la retirada: " + e.getMessage());
			return "error";
		}
	}

}
