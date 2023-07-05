package com.myspring.pro28.ex03;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {
	
//	mail-context.xml 에서 설정한 빈을 자동으로 주입합니다.
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SimpleMailMessage preConfiguredMessage;
	
	@Async
	public void sendMail(String to, String subject, String body) {
//		MimeMessage 타입 객체를 생성
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper =
					new MimeMessageHelper(message,true,"utf-8");
//			messageHelper.setCc("####@naver.com");
			messageHelper.setFrom("spr3133@gmail.com","임성채"); // 송신자의 메일과 이름
			messageHelper.setSubject(subject);
			messageHelper.setTo(to);
			messageHelper.setText(body);
			mailSender.send(message);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	동기화 식으로 해서 글의 내용을 수정
	@Async
	public void sendPreConfiguredMail(String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
		mailMessage.setText(message);
		mailSender.send(mailMessage);
	}
}
