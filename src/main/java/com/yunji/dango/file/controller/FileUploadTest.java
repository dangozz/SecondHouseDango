package com.yunji.dango.file.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yunji.dango.shiro.uti.LOG;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping({"/file"})
public class FileUploadTest{
    @RequestMapping({"/test1.do"})
    @ResponseBody
    public Map test1(HttpServletRequest request){
        String test = request.getParameter("test");
        System.out.println("test1----------------------------");
        Map<String, Object> map = new HashMap<>();
        map.put("status", 400);
        map.put("message", "上传失败");
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            Iterator iterator = multiRequest.getFileNames();
            while (iterator.hasNext())
            {
                MultipartFile file = multiRequest.getFile(iterator.next().toString());
                if ((file != null) && (!"".equals(file.getOriginalFilename())))
                {
                    String path = "E:/logs/filetest/" + UUID.randomUUID() + "__" + file.getOriginalFilename();
                    File f = new File(path);
                    File fileParent = f.getParentFile();
                    if (!fileParent.exists()) {
                        fileParent.mkdirs();
                    }
                    try
                    {
                        file.transferTo(f);
                        map.put("status", 200);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                        map.put("status", 300);
                        LOG.logger.error(e.getMessage());
                    }
                    map.put("message", "上传成功");
                }
            }
        }
        return map;
    }

    @RequestMapping({"/test2.do"})
    @ResponseBody
    public Map test2(@RequestParam("from2_1") MultipartFile[] multipartFiles, HttpServletRequest request){
        System.out.println("test2----------------------------");
        System.out.println(request.getParameter("test2"));
        Map<String, Object> map = new HashMap<>();
        map.put("status", 400);
        map.put("message", "lalala");
        if (multipartFiles != null) {
            for (MultipartFile multipartFile : multipartFiles) {
                if (!"".equals(multipartFile.getOriginalFilename()))
                {
                    String path = "E:/logs/filetest/" + UUID.randomUUID() + "__" + multipartFile.getOriginalFilename();
                    System.out.println(multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")));
                    File f = new File(path);
                    File fileParent = f.getParentFile();
                    if (!fileParent.exists()) {
                        fileParent.mkdirs();
                    }
                    try
                    {
                        multipartFile.transferTo(f);
                        map.put("status", 200);
                    }
                    catch (IOException e)
                    {
                        map.put("status", 300);
                        e.printStackTrace();
                        LOG.logger.error(e.getMessage());
                    }
                    map.put("message", "上传成功");
                }
            }
        }
        return map;
    }
}
