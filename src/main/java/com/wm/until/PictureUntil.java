package com.wm.until;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author wm
 */

public class PictureUntil {

    private static String descPath = "D:" + File.separator + "java-pro"+File.separator+"upload"+File.separator+"picture"+File.separator;


    public static void upload(MultipartFile picture,String pictureName) throws IOException {
        File descFile = new File(descPath+pictureName);

        if (!descFile.getParentFile().exists()) {
            descFile.getParentFile().mkdirs();
        }
        if (!picture.isEmpty()) {
            picture.transferTo(descFile);
        }
    }

    public static void delete(String fileName) {
        File descFile = new File(descPath+fileName);
        if (descFile.exists()){
            descFile.delete();
        }
    }
}
