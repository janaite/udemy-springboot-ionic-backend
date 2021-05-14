package net.janaite.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendMail(SimpleMailMessage msg) {
		LOG.info("simulando o envio de e-mail");
		LOG.info(msg.toString());
		LOG.info("e-mail enviado");
	}

}
