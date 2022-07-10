package com.guo.controller;

import com.guo.utils.GetAccessIp;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class ModifierController {
    private HashSet<String> data = new HashSet<String>();
    private HashMap<String, String> datas = new HashMap<String, String>();
    public static  StringBuilder nums=new StringBuilder("所有功能IP地址汇总如下: "+"\n");
    private static StringBuilder font=new StringBuilder("搜索功能IP地址汇总如下: "+"\n");
    @RequestMapping(value = "/modifier", method = RequestMethod.POST)

    public StringBuilder modifier(@RequestParam("str[]") String[] str) throws IOException {
        StringBuilder result = new StringBuilder("");
        LinkedHashMap<String, String> stringStringLinkedHashMap = new LinkedHashMap<>();
     //   HashSet<String> strs = new HashSet<>();



        String item = "";
        int book;
        int k;
        for (int j = 0; j < str.length; j++) {
            book=0;
            k=0;
            if (str[j].trim().length() < 11 || !str[j].contains("1") || str[j].length() - str[j].indexOf("1") < 11) {
                str[j] = "";
            } else {
                if (NumberUtils.isDigits(str[j].substring(str[j].indexOf("1"), str[j].indexOf("1") + 11))) {
                    stringStringLinkedHashMap.put(str[j].substring(str[j].indexOf("1"), str[j].indexOf("1") + 11),str[j].substring(str[j].indexOf("1"), str[j].indexOf("1") + 11));
                    if(str[j].length() - (str[j].indexOf("1") + 11) > 10 && str[j].substring(str[j].indexOf("1")+11, str[j].length()).contains("1")){
                        str[j]=str[j].substring(str[j].indexOf("1")+11,str[j].length());
                    }else{
                        book=1;
                    }

                } if(book==0) {
                    if (str[j].length() - (str[j].indexOf("1") + 1) > 10) {
                        str[j] = str[j].substring(str[j].indexOf("1") , str[j].length());
                        while (str[j].contains("1") && str[j].length() - str[j].indexOf("1") > 10) {
                            if (NumberUtils.isDigits(str[j].substring(str[j].indexOf("1"), str[j].indexOf("1") + 11))) {
                                stringStringLinkedHashMap.put(str[j].substring(str[j].indexOf("1"), str[j].indexOf("1") + 11),str[j].substring(str[j].indexOf("1"), str[j].indexOf("1") + 11));
                                if(str[j].length() - (str[j].indexOf("1") + 11) > 10 && str[j].substring(str[j].indexOf("1")+11, str[j].length()).contains("1")){
                                    str[j]=str[j].substring(str[j].indexOf("1")+11,str[j].length());
                                    k=1;
                                }else {
                                    break;
                                }
                            } else {
                               if(str[j].length() - (str[j].indexOf("1") + 1) > 10){
                                   if(k!=1){
                                       str[j]=str[j].substring(str[j].indexOf("1") + 1, str[j].length());
                                   }
                                   if(str[j].contains("1")){
                                       str[j]=str[j].substring(str[j].indexOf("1"),str[j].length());
                                   }
                                   k=0;
                               }else{
                                   break;
                               }
                            }
                        }
                    }

                }

            }
        }

        for (Map.Entry<String, String> next : stringStringLinkedHashMap.entrySet()) {
            if (data.contains(next.getKey())) {
                result.append(datas.get(next.getKey()));
            } else {
                System.out.println(next.getValue()+"查询成功");
                item = HttpClientHelper.sendPost("phone=" + next.getValue());
                result.append(item);
                data.add(next.getKey());
                datas.put(next.getKey(), item);
            }

        }

        return result;
    }
    @RequestMapping(value = "/fontNum",method= RequestMethod.GET)
    public StringBuilder fontNum() throws IOException {
        return font;
    }
    @RequestMapping(value = "/findNum",method= RequestMethod.GET)
    public StringBuilder findNum() throws IOException {
        return nums;
    }
    @RequestMapping(value = "/add",method= RequestMethod.GET)
    public String add(HttpServletRequest request) throws IOException {

        String date = DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINA).format(new Date());
        String time = DateFormat.getTimeInstance(DateFormat.LONG, Locale.CHINA).format(new Date());
        String ipAddress = GetAccessIp.getIpAddr(request);
        font.append("ip地址: "+ipAddress+" 访问时间: "+date+""+ time+"\n");
        nums.append("ip地址: "+ipAddress+" 访问时间: "+date+""+ time+" 使用功能: 查绑"+"\n");
      return "";
    }

}
