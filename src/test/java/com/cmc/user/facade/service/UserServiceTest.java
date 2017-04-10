package com.cmc.user.facade.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cmc.common.BizException;
import com.cmc.common.constants.Sex;
import com.cmc.common.utils.ModelDataObjectUtil;
import com.cmc.common.utils.PaginationResult;
import com.cmc.user.facade.entity.User;
import com.cmc.user.facade.model.UserModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:config/spring/*.xml" })
public class UserServiceTest {

    private static final Logger logger = Logger.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Test
    public void testAdd() throws InterruptedException, BizException {
        for (int i = 0; i < 50; i++) {
            UserModel dto = new UserModel();
            dto.setId(UUID.randomUUID().toString());
            dto.setName("lcb" + i);
            dto.setSex(Sex.MALE.getCode());
            dto.setAge(18 + i);
            dto.setCreateTime(new Date());
            dto.setUpdateTime(new Date());
            userService.add(dto);
            Thread.sleep(1000L);
        }
    }

    @Test
    public void testList() {
        PaginationResult<UserModel> pageDTO = userService.list(1L, 10L);
        List<User> users = ModelDataObjectUtil.modelList2doList(pageDTO.getList(), User.class);
        for (User user : users) {
            logger.info(user);
        }
    }

    @Test
    public void testGet() {
        PaginationResult<UserModel> pageDTO = userService.list(1L, 10L);
        for (UserModel dto : pageDTO.getList()) {
            logger.info(userService.get(dto));
        }
    }

    @Test
    public void testUpdate() {
        String id = "066ed471-014a-45e3-a291-fca8f737d449";
        UserModel dto = new UserModel();
        dto.setId(id);
        dto.setName("lcbxxx");
        dto.setSex(Sex.FEMALE.getCode());
        dto.setAge(18);
        userService.update(dto);
    }

    @Test
    public void testDelete() {
        String id = "066ed471-014a-45e3-a291-fca8f737d449";
        UserModel dto = new UserModel();
        dto.setId(id);
        userService.delete(dto);
    }

}