package com.space.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.space.config.MyWebAppInit;
import com.space.config.WebConfig;
import com.space.controller.utils.ShipInfoTest;
import com.space.controller.utils.TestDataSourceConfig;
import com.space.controller.utils.TestsHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfig.class, MyWebAppInit.class, WebConfig.class})
@WebAppConfiguration
@Sql(scripts = "classpath:test.sql", config = @SqlConfig(encoding = "UTF-8"))
public class GetShipTest {

    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //test1
    @Test
    public void getShipByIdEqualZeroTest() throws Exception {
        mockMvc.perform(get("/rest/ships/0")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    //test2
    @Test
    public void getShipByIdNotNumberTest() throws Exception {
        mockMvc.perform(get("/rest/ships/test")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    //test3
    @Test
    public void getShipByIdNotExistTest() throws Exception {
        mockMvc.perform(get("/rest/ships/410")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    //test4
    @Test
    public void getShipByIdTest() throws Exception {
        ShipInfoTest expected = new TestsHelper().getShipInfosById(14);

        ResultActions resultActions = mockMvc.perform(get("/rest/ships/14")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        ShipInfoTest actual = mapper.readValue(contentAsString, ShipInfoTest.class);
        assertTrue("Вернулся неправильный объект при запросе GET /rest/ships/{id}", actual.equals(expected));
    }

    @Autowired
    public void setContext(WebApplicationContext context) {
        this.context = context;
    }
}