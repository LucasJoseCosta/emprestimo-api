package com.emprestimo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emprestimo.model.Login;
import com.emprestimo.model.Usuario;
import com.emprestimo.service.LoginServiceImp;

@RestController
@RequestMapping("/auth")
public class LoginController {
	@Autowired
	private LoginServiceImp loginService;

	@PostMapping("/login")
	public ResponseEntity<Usuario> logar(@RequestBody Login login) {
		try {
			return ResponseEntity.ok(loginService.logar(login));
		}catch(AuthenticationException ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
