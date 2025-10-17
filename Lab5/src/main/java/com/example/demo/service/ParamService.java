package com.example.demo.service;

import java.io.File;
import java.util.*;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ParamService {
    @Autowired
    HttpServletRequest request;

    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return value != null ? value : defaultValue;
    }

    public int getInt(String name, int defaultValue) {
        try {
            return Integer.parseInt(getString(name, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public double getDouble(String name, double defaultValue) {
        try {
            return Double.parseDouble(getString(name, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        return Boolean.parseBoolean(getString(name, String.valueOf(defaultValue)));
    }

    public Date getDate(String name, String pattern) {
        try {
            String dateStr = getString(name, "");
            if (dateStr.isEmpty()) return null;
            return new SimpleDateFormat(pattern).parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public File save(MultipartFile file, String path) {
        if (file.isEmpty()) {
            return null;
        }

        // Lấy đường dẫn thật (real path) tới thư mục cần lưu
        String realPath = request.getServletContext().getRealPath(path);

        // Tạo đối tượng File trỏ đến thư mục đích
        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdirs();  // 🔥 Tạo thư mục nếu chưa có
        }

        try {
            File savedFile = new File(dir, file.getOriginalFilename());
            file.transferTo(savedFile);  // Ghi file xuống ổ đĩa
            return savedFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

