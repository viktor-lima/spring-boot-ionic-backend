package com.vkl.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.vkl.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
