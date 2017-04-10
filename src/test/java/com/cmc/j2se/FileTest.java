package com.cmc.j2se;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTest {

    private static final Logger LOG = LoggerFactory.getLogger(FileTest.class);

    public void copyFile() {
        try {
            File inputFile = new File("C:\\Users\\ucmed\\Desktop\\inputfile.txt");
            FileInputStream fileInputStream = new FileInputStream(inputFile);
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\ucmed\\Desktop\\outputfile.txt");
            byte[] buffer = new byte[(int) inputFile.length()];
            int length = 0;
            while (-1 != (length = fileInputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, length);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            this.logError(e);
        } catch (IOException e) {
            this.logError(e);
        }
    }

    public void logError(Exception e) {
        LOG.error("", e);
    }

    public static void main(String[] args) {
        new FileTest().copyFile();
    }

}