package taquillas.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	public void login(String user, String pass) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, pass);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
