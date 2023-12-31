package com.myspring.pro28.ex03;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
//@EnableAsync
public class MailController {
	
	@Autowired
	private MailService mailService;
	
//	@RequestMapping(value = "/sendMail.do", method = RequestMethod.GET)
	public void sendSimpleMail(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
//		1 : 수신자, 2: 수신제목, 3: 수신내용
		mailService.sendMail("lschmhj@naver.com", "테스트 메일", "안녕하세요. 테스트 메일입니다.");
		mailService.sendPreConfiguredMail("테스트 메일입니다.123");
		out.print("메일을 보냈습니다!!");
	}
}
