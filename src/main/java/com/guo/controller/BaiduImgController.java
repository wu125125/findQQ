package com.guo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guo.utils.Base64Util;
import com.guo.utils.FileUtil;
import com.guo.utils.GetAccessIp;
import com.guo.utils.HttpUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import static com.guo.controller.FileController.filess;
import static com.guo.controller.ModifierController.nums;

/**
 * 通用文字识别
 */
@RestController
public class BaiduImgController {
    private static StringBuilder imgs=new StringBuilder("图像识别搜索IP地址汇总如下: "+"\n");
    private  String token="24.9e833126e9ac5debd615363f33858270.2592000.1652545213.282335-25976509";
    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    @RequestMapping("/transfer")
    public StringBuilder transfer(@RequestParam("base") String base) throws Exception  {
        StringBuilder results=new StringBuilder("");
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
        try {
            // 本地文件路径
           // String filePath = "[本地文件路径]";
           // byte[] imgData = FileUtil.readFileByBytes(filePath);
            //String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(base, "UTF-8");
            String param = "image=" + imgParam;
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
//            String accessToken = GetAccept.getAuth();
//            System.out.println(accessToken);
            token= GetAccept.getAuth();

            String result = HttpUtil.post(url, token, param);
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray words_result = JSONArray.parseArray(jsonObject.get("words_result").toString());
            Object[] objects = words_result.toArray();
            for (int i = 0; i < objects.length; i++) {
                results.append(JSONObject.parseObject(objects[i].toString()).get("words") + "\n");

            }
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/imageNum",method= RequestMethod.GET)
    public StringBuilder imageNum() throws IOException {
        return imgs;
    }
    @RequestMapping(value = "/upimg/{id}",method= RequestMethod.GET)
    public void add(@PathVariable("id")  int id,HttpServletRequest request) throws IOException {
        String date = DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINA).format(new Date());
        String time = DateFormat.getTimeInstance(DateFormat.LONG, Locale.CHINA).format(new Date());
        String ipAddress = GetAccessIp.getIpAddr(request);

        if(id==1){
            imgs.append("ip地址: "+ipAddress+" 访问时间: "+date+""+ time+"\n");
            nums.append("ip地址: "+ipAddress+" 访问时间: "+date+""+ time+" 使用功能: 图像识别搜索"+"\n");
        }
        else if(id==2){
            filess.append("ip地址: "+ipAddress+" 访问时间: "+date+""+ time+"\n");
            nums.append("ip地址: "+ipAddress+" 访问时间: "+date+""+ time+" 使用功能: 文件识别搜索"+"\n");
        }
    }


}