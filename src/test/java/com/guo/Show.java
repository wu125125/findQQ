package com.guo;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Show {
    @org.junit.jupiter.api.Test
    public void test() throws IOException {
        String a="12a3";
        String b="1a23";
        String c="123a";
        String d="123";


        System.out.println(a.substring(1,1));
        System.out.println(NumberUtils.isDigits( b));
        System.out.println(NumberUtils.isDigits( c));
        System.out.println(NumberUtils.isDigits( d));


    }
}
