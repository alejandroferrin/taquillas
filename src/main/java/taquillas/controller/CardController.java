package taquillas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import taquillas.smartcard.CardReader;

@Controller
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardReader cardReader;

    /* Deprecated
    @GetMapping("/card")
    public String redirect(Model model) {
        String cardNumber = cardReader.getRead();
        model.addAttribute("num_tarjeta", cardNumber);
        return "num_tarjeta";
    }
    */
    @GetMapping("/number")
    public ResponseEntity<String> getNumber() {
        String cardNumber = cardReader.getRead();
        return ResponseEntity.ok(cardNumber);
    }

    @GetMapping()
    public String newElementForm(Model model) {
        return "card_reader";
    }
}
