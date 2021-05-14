package net.janaite.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import net.janaite.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	void sendMail(SimpleMailMessage msg);
}
