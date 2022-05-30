package com.atguigu.mvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileController {

    @RequestMapping("/testDown")
    public ResponseEntity<byte[]> testResponseEntiy(HttpSession session) throws IOException {

//        獲取ServletContext對象
        ServletContext context = session.getServletContext();
//        獲取Server中文件的真實路徑
        String realPath = context.getRealPath("/static/img/testpic.jpg");
//        創建InputStream
        InputStream input = new FileInputStream(realPath);
//        創建byte陣列
        byte[] bytes = new byte[input.available()];
//        將stream讀到array裡面
        input.read(bytes);
//        創建http header設置 response header
        MultiValueMap<String, String> headers = new HttpHeaders();
//        設置下載方式(附件 attachment)及文件檔名
        headers.add("Content-Disposition","attachment;filename=testpic.jpg");
//      創建ResponseEntity, 建構子(response body內容, response Headers,Http狀態碼)
        ResponseEntity<byte[]> re = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
//        關閉input stream
        input.close();

        return re;
    }
}
