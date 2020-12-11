package com.example.demo.utils;



import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUploadUtils {
    public static List<Object> getUploderFile(MultipartFile file){
        String filename = file.getOriginalFilename();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(new Date());
        File folder = new File("e://up/"+time);
        if (!folder.exists()) {
            folder.mkdir();
        }
        //修改图片名称
        filename = IDUtils.genImageName() + filename.substring(filename.lastIndexOf("."));
        List<Object> list = new ArrayList<Object>();
        list.add(time);
        list.add(folder);
        list.add(filename);
        return list;
    }
}
