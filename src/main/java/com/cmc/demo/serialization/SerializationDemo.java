package com.cmc.demo.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对象序列化和反序列化Demo
 * @author Thomas Lee
 * @version 2017年4月5日 上午11:57:27
 */
public class SerializationDemo {

    private static final Logger LOG = LoggerFactory.getLogger(SerializationDemo.class);

    public static void main(String[] args) {
        // 将Person对象序列化
        serializePerson();
        LOG.info("the result of deserialization: " + deserializePerson().toString());
    }

    /**
     * 序列化Person对象，将其存储到 E:/hello.txt文件中
     * @author Thomas Lee
     * @version 2017年4月5日 上午11:57:38
     */
    private static void serializePerson() {
        Person person = new Person();
        person.setAge(30);
        person.setName("SerializePerson");
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("E:/hello.txt"))) {
            outputStream.writeObject(person);
            LOG.info("序列化成功。");
        } catch (FileNotFoundException e) {
            LOG.error(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * 执行反序列化过程生产Person对象
     * @return 反序列化之后获取的对象
     * @author Thomas Lee
     * @version 2017年4月5日 上午11:58:51
     */
    private static Person deserializePerson() {
        Person person = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("E:/hello.txt"))) {
            try {
                person = (Person) inputStream.readObject();
                LOG.info("执行反序列化过程成功。");
            } catch (ClassNotFoundException e) {
                LOG.error(e.getMessage(), e);
            }
        } catch (FileNotFoundException e) {
            LOG.error(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return person;
    }

}