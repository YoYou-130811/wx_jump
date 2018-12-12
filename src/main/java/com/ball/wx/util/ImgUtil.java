package com.ball.wx.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

@Component
public class ImgUtil {

    public static String downLoadImage(String imageUrl, String savePath,String imageName) throws Exception {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        File imageFile = new File(savePath+File.separator + imageName);
        OutputStream os = new FileOutputStream(imageFile);
        if(!imageFile.exists()){
            imageFile.mkdirs();
        }
        while((len = is.read(buffer)) != -1){
            os.write(buffer, 0, len);
        }
        os.close();
        is.close();
        return savePath + imageName;
    }


}
