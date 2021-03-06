package com.nit.saif.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {
	
	@Autowired
	JavaMailSender emailSender;

	public void createEmail(String to,String subject,String text) {		
		SimpleMailMessage message = new SimpleMailMessage();	
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		
		emailSender.send(message);
	}
}
