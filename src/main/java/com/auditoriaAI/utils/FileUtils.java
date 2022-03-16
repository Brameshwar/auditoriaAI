package com.auditoriaAI.utils;

import com.rtfparserkit.converter.text.StringTextConverter;
import com.rtfparserkit.parser.RtfStreamSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
@Slf4j
public class FileUtils {

    private static final ThreadLocal<List<String>> filesListInDir = new InheritableThreadLocal<>();

    public boolean zipDirectory(String path, String zipDirName) throws IOException {

        filesListInDir.set(new ArrayList<>());
        File directoryPath = new File(path);

        if(! directoryPath.isDirectory())
            return false;

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(zipDirName);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

            populateFilesList(directoryPath);

            for(String filePath : filesListInDir.get()){
                log.info("Zipping {}",filePath);
                ZipEntry zipEntry = new ZipEntry(filePath.substring(directoryPath.getAbsolutePath().length()+1, filePath.length()));
                zipOutputStream.putNextEntry(zipEntry);
                FileInputStream fileInputStream = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
                zipOutputStream.closeEntry();
                fileInputStream.close();
            }
            zipOutputStream.close();
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void populateFilesList(File dir) throws IOException {
        File[] files = dir.listFiles();
        for(File file : files){
            if(file.isFile()) {
                if (!(file.getAbsolutePath().contains("target")
                        || file.getAbsolutePath().contains(".idea")
                        || file.getAbsolutePath().contains(".git")))
                    filesListInDir.get().add(file.getAbsolutePath());
            }
            else
                populateFilesList(file);
        }
    }

    public String parseRtfFile(String rtf) throws IOException {

        InputStream inputStream = new FileInputStream(rtf);

        StringTextConverter converter = new StringTextConverter();
        converter.convert(new RtfStreamSource(inputStream));
        String extractedText = converter.getText();

        return extractedText;

    }


    public boolean writeDataToFile(String data, String filePath) throws IOException {
        if(new File(filePath).createNewFile())
            log.info("File {} created",filePath);
        else
            log.info("File {} exits",filePath);

        BufferedWriter fWriter
                = new BufferedWriter(new FileWriter(
                filePath));
        try {
            fWriter.write(data);
            fWriter.close();
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
            fWriter.close();
            return false;
        }
    }
}
