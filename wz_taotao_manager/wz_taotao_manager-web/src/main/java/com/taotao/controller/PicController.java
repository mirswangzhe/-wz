package com.taotao.controller;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.taotao.common.JsonUtils;

@Controller
public class PicController {
	@RequestMapping(value = "/pic/upload", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
    @ResponseBody
    public String uploadPic1(MultipartFile uploadFile) throws Exception {
            /**
             * 华东 Zone.zone0() 华北 Zone.zone1() 华南 Zone.zone2() 北美 Zone.zoneNa0()
             */
            Configuration cfg = new Configuration(Zone.zone0());
            // ...其他参数参考类注释
            UploadManager uploadManager = new UploadManager(cfg);
            // ...生成上传凭证，然后准备上传
            String accessKey = "Tdkzlm_sDRgPEPEjZiATAZatxLKwUDmO8H0olVO6";
            String secretKey = "V5pnNc99O0vEpgC3ss4N-RB82f9XiZwS6RAFi-Un";
            String bucket = "wz-2018";
            // 默认不指定key的情况下，以文件内容的hash值作为文件名
            String key = null;
            try {
                byte[] uploadBytes = uploadFile.getBytes();
                Auth auth = Auth.create(accessKey, secretKey);
                String upToken = auth.uploadToken(bucket);
                try {
                    Response response = uploadManager.put(uploadBytes, key, upToken);
                    // 解析上传成功的结果
                    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                    System.out.println(putRet.key);
                    System.out.println(putRet.hash);
                    // 上传成功返回map
                    Map<String, Object> result = new HashMap<String, Object>();
                    result.put("error", 0);
                    result.put("url","idv1cjn.qiniudns.com"+putRet.key );
                    return JsonUtils.objectToJson(result);
                } catch (QiniuException ex) {
                    Response r = ex.response;
                    System.err.println(r.toString());
                    try {
                        System.err.println(r.bodyString());
                    } catch (QiniuException ex2) {
                        // ignore
                    }
                    ex.printStackTrace();
                    // 5、返回map
                    Map<String, String> result = new HashMap<String, String>();
                    result.put("error", "1");
                    result.put("message", "图片上传失败");
                    System.out.println("上传失败!!!!");
                    return JsonUtils.objectToJson(result);
                }
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
                // 5、返回map
                Map<String, String> result = new HashMap<String, String>();
                result.put("error", "1");
                result.put("message", "图片上传失败");
                System.out.println("上传失败!!!!");
                
                return JsonUtils.objectToJson(result);
            }
 
             
 
    }

}
