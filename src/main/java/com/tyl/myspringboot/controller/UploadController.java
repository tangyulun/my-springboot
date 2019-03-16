package com.tyl.myspringboot.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class UploadController {
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "E://temp//";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/file")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        //请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        try {
            //文件转换base64字符串
            String fileStr = Base64.getEncoder().encodeToString(file.getBytes());
            Map<String, String> params =  new LinkedHashMap<>();
            params.put("fileStr", fileStr);
            params.put("fileName", file.getOriginalFilename());
            //转成json字符串
            String jsonString = JSON.toJSONString(params);
            System.out.println(jsonString);
            //添加请求对象(请求实体，请求头)
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonString, httpHeaders);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://192.168.1.102:8085/portal/log/fileUpload\n", requestEntity, String.class);
            System.out.println(responseEntity.getBody());

        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        try {
//            // Get the file and save it somewhere
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//            Files.write(path, bytes);
//
//            redirectAttributes.addFlashAttribute("message",
//                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}