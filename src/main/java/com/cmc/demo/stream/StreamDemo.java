package com.cmc.demo.stream;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.LinkedList;

/**
 * Java中流（字节流和字符流）DEMO
 * 
 * @author Thomas Lee
 * @version 2017年2月6日 下午1:56:09
 */
public class StreamDemo {
    private static final String FILE_INPUT_PATH = "F:" + File.separator + "individual" + File.separator + "workspace" + File.separator + "SSM" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "tmp" + File.separator + "file-for-stream_in_test";
    private static final String FILE_OUTPUT_PATH = "F:" + File.separator + "individual" + File.separator + "workspace" + File.separator + "SSM" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "tmp" + File.separator + "file-for-stream_out_test";

    public static void main(String[] args) {
        new LinkedList<String>().addFirst("First");
        new LinkedList<String>().addLast("Lash");
        final StringBuilder sb = new StringBuilder("fda");
        sb.append("fdas");
        System.out.println(sb.toString());
    }

    /**
     * FileInputStream和FileOutputStream实例
     * 
     * @author Thomas Lee
     * @version 2017年2月6日 下午5:15:26
     */
    public void demoFileInputStreamAndFileOutputStream() throws IOException {
        int data = 0;
        FileInputStream fileInputStream = new FileInputStream(FILE_INPUT_PATH);
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_OUTPUT_PATH);
        while (-1 != (data = fileInputStream.read())) {
            fileOutputStream.write(data);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    /**
     * FileReader和FileWriter实例
     * 
     * @author Thomas Lee
     * @version 2017年2月6日 下午5:19:25
     */
    public void demoFileReaderAndFileWriter() throws IOException {
        FileReader fileReader = new FileReader(FILE_INPUT_PATH);
        FileWriter fileWriter = new FileWriter(FILE_OUTPUT_PATH);
        int data = 0;
        while (-1 != (data = fileReader.read())) {
            fileWriter.write(data);
        }
        fileReader.close();
        fileWriter.close();
    }

    /**
     * BufferedInputStream和BufferedOutputStream实例
     * 
     * @author Thomas Lee
     * @version 2017年2月6日 下午5:22:43
     * @throws IOException 
     */
    public void demoBufferedInputStreamAndBufferedOutputStream() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(FILE_INPUT_PATH);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        int data = 0;
        while (-1 != (data = bufferedInputStream.read())) {
            System.out.println((char) data);
        }
        bufferedInputStream.close();
        fileInputStream.close();
    }

    /**
     * BufferedReader和BufferedWriter实例
     * 
     * @author Thomas Lee
     * @version 2017年2月7日 上午11:03:39
     */
    public void demoBufferedReaderAndBufferedWriter() throws IOException {
        FileReader fileReader = new FileReader(FILE_INPUT_PATH);
        FileWriter fileWriter = new FileWriter(FILE_OUTPUT_PATH);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < 100; i++) {
            bufferedWriter.write(String.valueOf(Math.random()));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        String str = null;
        while (null != (str = bufferedReader.readLine())) {
            System.out.println(str);
        }
        bufferedReader.close();
        bufferedWriter.close();
        fileReader.close();
        fileWriter.close();
    }

    /**
     * PrintStream和PrintWriter实例
     * 
     * @author Thomas Lee
     * @version 2017年2月7日 下午1:23:12
     */
    public void demoPrintStreamAndPrintWriter() throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_OUTPUT_PATH, true);
        PrintStream printStream = new PrintStream(fileOutputStream);
        System.setOut(printStream);
        for (int i = 0; i < 100; i++) {
            System.out.println(String.valueOf(Math.random()));
        }
    }

    /**
     * NIO实例
     * 
     * @author Thomas Lee
     * @version 2017年2月8日 下午5:22:47
     */
    public void testNIO() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(FILE_INPUT_PATH);
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // Reads a sequence of bytes from this channel into the given buffer.
        fileChannel.read(byteBuffer);

        for (int i = 0; i < 100; i++) {
            byteBuffer.put((byte) i);
        }

        byteBuffer.flip();
        // Writes a sequence of bytes to this channel from the given buffer.
        fileChannel.write(byteBuffer);

        fileChannel.close();
        fileInputStream.close();
    }

}