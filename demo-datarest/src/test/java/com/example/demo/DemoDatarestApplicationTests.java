package com.example.demo;

import com.example.demo.datarest.repository.PersonRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoDatarestApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void deleteAllBeforeTests() throws Exception {
        personRepository.deleteAll();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void shouldReturnRepositoryIndex() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
                MockMvcResultMatchers.jsonPath("$._links.people").exists());
    }

    @Test
    public void shouldRetrieveEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/people").content(
                "{\"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
                MockMvcResultMatchers.status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");
        mockMvc.perform(MockMvcRequestBuilders.get(location)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
                MockMvcResultMatchers.jsonPath("$.firstName").value("Frodo")).andExpect(
                MockMvcResultMatchers.jsonPath("$.lastName").value("Baggins"));
    }

    @Test
    public void shouldCreateEntity() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/people").content(
                "{\"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
                MockMvcResultMatchers.status().isCreated()).andExpect(
                MockMvcResultMatchers.header().string("Location", Matchers.containsString("people/")));
    }

    @Test
    public void shouldQueryEntity() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/people").content(
                "{ \"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
                MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/people/search/findByLastName?name={name}", "Baggins")).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(
                MockMvcResultMatchers.jsonPath("$._embedded.people[0].firstName").value(
                        "Frodo"));
    }

    @Test
    public void shouldUpdateEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/people").content(
                "{\"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
                MockMvcResultMatchers.status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(MockMvcRequestBuilders.put(location).content(
                "{\"firstName\": \"Bilbo\", \"lastName\":\"Baggins\"}")).andExpect(
                MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get(location)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
                MockMvcResultMatchers.jsonPath("$.firstName").value("Bilbo")).andExpect(
                MockMvcResultMatchers.jsonPath("$.lastName").value("Baggins"));
    }

    @Test
    public void shouldPartiallyUpdateEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/people").content(
                "{\"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
                MockMvcResultMatchers.status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(
                MockMvcRequestBuilders.patch(location).content("{\"firstName\": \"Bilbo Jr.\"}")).andExpect(
                MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get(location)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
                MockMvcResultMatchers.jsonPath("$.firstName").value("Bilbo Jr.")).andExpect(
                MockMvcResultMatchers.jsonPath("$.lastName").value("Baggins"));
    }

    @Test
    public void shouldDeleteEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/people").content(
                "{ \"firstName\": \"Bilbo\", \"lastName\":\"Baggins\"}")).andExpect(
                MockMvcResultMatchers.status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");
        mockMvc.perform(MockMvcRequestBuilders.delete(location)).andExpect(MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get(location)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
