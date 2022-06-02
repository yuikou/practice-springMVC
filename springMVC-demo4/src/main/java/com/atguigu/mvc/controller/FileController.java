package com.atguigu.mvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.UUID;

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
        headers.add("Content-Disposition", "attachment;filename=testpic.jpg");
//      創建ResponseEntity, 建構子(response body內容, response Headers,Http狀態碼)
        ResponseEntity<byte[]> re = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
//        關閉input stream
        input.close();

        return re;
    }

    @RequestMapping("/testUp")
    public String testUp(MultipartFile photo, HttpSession session) throws IOException {
//        獲取上傳的文件的文件名
        String fileName = photo.getOriginalFilename();
//        取得文件副檔名
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
//        將UUID作為上傳文件的檔名
        String uuid = UUID.randomUUID().toString();
//        將UUUID加上後綴名, 拼接為最終檔名
        fileName = uuid + fileSuffix;
//        通過ServletContext獲取到server中"photo"(存放上傳file)目錄的路徑
        ServletContext context = session.getServletContext();
        String photoPath = context.getRealPath("photo");
        File file = new File(photoPath);

//        判斷photoPath所對應路徑之資料夾是否存在
        if (!file.exists()) {
            file.mkdir();
        }
//        文件最終上傳路徑
        String finalPath = photoPath + File.separator + fileName;
        photo.transferTo(new File(finalPath));

        return "success";
    }
}
