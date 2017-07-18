package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by liyuhong on 2017/7/16.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoSecurityApplication.class)
public class Swagger2MarkupTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
   @Before
    public void setUp(){
       this.mockMvc= MockMvcBuilders.webAppContextSetup(this.context).build();
   }

   @Test
    public void createSpringfoxSwaggerJson()throws Exception{
        String outputDir ="target/swagger";
       MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/v2/api-docs")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andReturn();

       MockHttpServletResponse response = mvcResult.getResponse();
       String swaggerJson = response.getContentAsString();
       Files.createDirectories(Paths.get(outputDir));
       try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputDir, "swagger.json"), StandardCharsets.UTF_8)) {
           writer.write(swaggerJson);
       }

   }
}
