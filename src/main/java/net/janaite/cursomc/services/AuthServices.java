package net.janaite.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.janaite.cursomc.domain.Cliente;
import net.janaite.cursomc.repositories.ClienteRepository;
import net.janaite.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthServices {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email not found!");
		}
		
		String newPass = newPassword();
		cliente.setSenha(encoder.encode(newPass));
		clienteRepository.save(cliente);

		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3); // numbers between 0 and 2

		switch (opt) {
		case 0:
			return (char) (rand.nextInt(10) + 48); // 0..9

		case 1:
			return (char) (rand.nextInt(26) + 65); // A..Z

		default:
			return (char) (rand.nextInt(26) + 97); // a..z
		}
	}

}
