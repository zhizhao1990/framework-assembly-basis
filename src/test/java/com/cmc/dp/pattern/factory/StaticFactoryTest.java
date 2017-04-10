package com.cmc.dp.pattern.factory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cmc.user.facade.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:config/*.xml" })
public class StaticFactoryTest {

    @Autowired
    private int random;

    @Autowired
    private int random1;

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        System.out.println(random);
        System.out.println(random1);
        System.out.println("random ==================================== " + userService.getCommon() + "**** " + userService.getArg1());
    }

}