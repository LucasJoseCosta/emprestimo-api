package com.emprestimo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emprestimo.config.JwtServiceGenerator;
import com.emprestimo.model.Login;
import com.emprestimo.model.Usuario;
import com.emprestimo.repository.LoginRepository;

@Service
public class LoginServiceImp implements LoginService {
	@Autowired
	private LoginRepository repository;
	@Autowired
	private JwtServiceGenerator jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public Usuario logar(Login login) {
	    authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())
	    );

	    Usuario user = repository.findByUsername(login.getUsername())
	        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

	    String jwtToken = jwtService.generateToken(user);
	    user.setToken(jwtToken);

	    return user;
	}

}
