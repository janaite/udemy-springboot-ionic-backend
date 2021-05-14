package net.janaite.cursomc.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import net.janaite.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendMail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido Confirmado! Código: " + obj.getId());
		//sm.setSentDate(Date.valueOf(LocalDate.now()));
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		
		return sm;
	}
}
