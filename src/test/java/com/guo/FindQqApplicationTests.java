package com.guo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
@RestController
class FindQqApplicationTests {

    @Test
    void contextLoads() throws IOException {
        Path path = Paths.get("F:\\查绑代码findPhone项目");
        byte[] data = Files.readAllBytes(path);
        String result = new String(data, StandardCharsets.UTF_8);
        System.out.println(result);

    }
    @Value("${text:kk}")
    private String name;
    @RequestMapping("/tests")
    public void test(){
        System.out.println(name);
    }

}
