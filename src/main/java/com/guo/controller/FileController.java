package com.guo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


@RestController
public class FileController {
    public static StringBuilder filess=new StringBuilder("文件识别搜索IP地址汇总如下: "+"\n");
    @RequestMapping(value = "/upload",method= RequestMethod.POST)

    public StringBuilder upload(@RequestParam("uploadFile") MultipartFile uploadFile) throws IOException {

        InputStream is = uploadFile.getInputStream();

        InputStreamReader isReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isReader);
        //循环逐行读取
        StringBuilder data= new StringBuilder("");
        while (br.ready()) {
            data.append(br.readLine()).append("\n");
        }
        //关闭流，讲究
        br.close();
      return data;
    }
    @RequestMapping(value = "/test/{id}",method= RequestMethod.GET)
    public StringBuilder test(@PathVariable("id")  int id ) throws IOException {
        return new StringBuilder("success");
    }
    @RequestMapping(value = "/fileNum",method= RequestMethod.GET)
    public StringBuilder fileNum() throws IOException {

        return filess;
    }
}

