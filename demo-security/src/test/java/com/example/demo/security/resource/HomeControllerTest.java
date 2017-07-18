package com.example.demo.security.resource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.TestSecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.security.Principal;


/**
 * Created by liyuhong on 2017/7/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class HomeControllerTest {

    @Autowired
    private HomeController homeController;
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    @WithMockUser(username="admin",password="password",roles={"ADMIN"})
    public void home() throws Exception {
        String s= homeController.home();
         assert(s=="hello world");
    }

    @Test(expected =AccessDeniedException.class )
    @WithMockUser(username="user",password="password",roles={"USER"})
    public void homeAccessDeniedException() throws Exception {
        //expectedEx.expect(AccessDeniedException.class);
         homeController.home();

    }

    @Test
    @WithMockUser(username="admin",password="password",roles={"ADMIN"})
    public void user() throws Exception {

         homeController.user(TestSecurityContextHolder.getContext().getAuthentication());
    }

}