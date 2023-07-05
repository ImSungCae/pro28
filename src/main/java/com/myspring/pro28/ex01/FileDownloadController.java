package com.myspring.pro28.ex01;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller
public class FileDownloadController {
//	파일 저장 위치 지정
	private static String CURR_IMAGE_REPO_PATH = "c:\\spring\\image_repo";
	
	@RequestMapping("/download")   // 다운르도할 이미지 파일 이름을 전달
	protected void download(@RequestParam("imageFileName") String imageFileName,
			HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String downFile = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		File file = new File(downFile); // 다운로드할 파일 객체를 생성합니다.
		response.setHeader("Cache-control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName);
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024 * 8];
		while(true) { // 버퍼를 이용해 한번에 8Kbyte씩 브라우저로 전송
			int count = in.read(buffer);
			if(count == -1) break;
			out.write(buffer,0,count);
		}
		in.close();
		out.close();
	}
}
