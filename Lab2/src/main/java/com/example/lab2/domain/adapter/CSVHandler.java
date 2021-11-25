package com.example.lab2.domain.adapter;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

public abstract class CSVHandler {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file){
        return TYPE.equals(file.getContentType());
    }

    public abstract List<?> parseCSV(InputStream is);

}
