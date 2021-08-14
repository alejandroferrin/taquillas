package taquillas.smartcard;

import java.math.BigInteger;
import java.util.List;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import taquillas.controller.WithdrawalController;

@Component
public class CardReader {

	private int counter = 0;

	public String getRead() {
		System.out.println("#####_Lector de tarjetas activo...");
		while (true) {
			try {
				// Display the list of terminals
				TerminalFactory factory = TerminalFactory.getDefault();
				List<CardTerminal> terminals = factory.terminals().list();
				//System.out.println("Terminals: " + terminals);

				// Use the first terminal
				CardTerminal terminal = terminals.get(0);

				// Connect wit hthe card
				if (terminal.isCardPresent()) {
					Card card = terminal.connect("*");
					//System.out.println("Card: " + card);
					CardChannel channel = card.getBasicChannel();

					// Send test command
					ResponseAPDU response = channel.transmit(new CommandAPDU(new byte[]{
						(byte) 0xFF,
						(byte) 0xCA,
						(byte) 0x00,
						(byte) 0x00,
						(byte) 0x00}));
					//System.out.println("Response: " + response.toString());

					if (response.getSW1() == 0x63 && response.getSW2() == 0x00) {
						//System.out.println("Failed");
					} else {
						//System.out.println("UID: " + bin2hex(response.getData()));
						BigInteger decimal = new BigInteger(bin2hex(response.getData()), 16);
						//System.out.println(decimal);
						card.disconnect(false);
						System.out.println(decimal.toString());
						return decimal.toString();
					}

				}

			} catch (Exception e) {
				//System.out.println("Ouch: " + e.toString());

			}
		}
	}

	private String bin2hex(byte[] data) {
		return String.format("%0" + (data.length * 2) + "X", new BigInteger(1, data));
	}

}
