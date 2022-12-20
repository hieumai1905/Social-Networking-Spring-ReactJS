package com.codegym.service.appUserService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class AppUserDTOService {
    private final String FILE_PATH = "src\\pitnik-MXH-views\\pitnik-MXH\\images\\avatar\\";
    public boolean saveImg(MultipartFile img, long time){
        if (img == null) return true;
        File file = new File(FILE_PATH,time+"-"+img.getOriginalFilename());
        long start = System.currentTimeMillis();
        System.out.println(start);
        try (OutputStream out = new FileOutputStream(file.getPath())) {
            out.write(img.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println((System.currentTimeMillis() - start)*1000);

        while (file.length() < img.getSize()){
            System.out.println(file.exists());
        }
        System.out.println(file.exists());
        return false;
    }
}
