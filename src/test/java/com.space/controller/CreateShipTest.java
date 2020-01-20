package com.space.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.space.config.MyWebAppInit;
import com.space.config.WebConfig;
import com.space.controller.utils.ShipInfoTest;
import com.space.controller.utils.TestDataSourceConfig;
import com.space.controller.utils.TestsHelper;
import com.space.model.ShipType;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfig.class, MyWebAppInit.class, WebConfig.class})
@WebAppConfiguration
@Sql(scripts = "classpath:test.sql", config = @SqlConfig(encoding = "UTF-8"))
public class CreateShipTest {

    private WebApplicationContext context;
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();
    private ShipInfoTest expected;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        expected = new ShipInfoTest(41L, "123456789", "Earth", ShipType.MILITARY, 32998274577071L, true, 0.8, 14, 6.4);
    }

    //test1
    @Test
    public void createShipEmptyBodyTest() throws Exception {
        mockMvc.perform(post("/rest/ships/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content("{}")
        )
                .andExpect(status().isBadRequest());
    }

    //test2
    @Test
    public void createShipNoSpeedTest() throws Exception {
        mockMvc.perform(post("/rest/ships/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(TestsHelper.NO_SPEED_JSON))
                .andExpect(status().isBadRequest());
    }

    //test3
    @Test
    public void createShipEmptyNameTest() throws Exception {
        mockMvc.perform(post("/rest/ships/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(TestsHelper.EMPTY_NAME_JSON))
                .andExpect(status().isBadRequest());
    }

    //test4
    @Test
    public void createShipProdDateNegativeTest() throws Exception {
        mockMvc.perform(post("/rest/ships/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(TestsHelper.NEGATIVE_PROD_DATE_JSON))
                .andExpect(status().isBadRequest());
    }

    //test5
    @Test
    public void createShipCrewSizeTooBigTest() throws Exception {
        mockMvc.perform(post("/rest/ships/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(TestsHelper.TOO_BIG_CREW_SIZE_JSON))
                .andExpect(status().isBadRequest());
    }

    //test6
    @Test
    public void createShipPlanetLengthTooBigTest() throws Exception {
        mockMvc.perform(post("/rest/ships/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(TestsHelper.TOO_BIG_PLANET_LENGTH_JSON))
                .andExpect(status().isBadRequest());
    }

    //test7
    @Test
    public void createShipIsUsedAbsentTest() throws Exception {
        expected.isUsed = false;
        expected.rating = 12.8;

        ResultActions resultActions = mockMvc.perform(post("/rest/ships/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(TestsHelper.NO_IS_USED_JSON))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        ShipInfoTest actual = mapper.readValue(contentAsString, ShipInfoTest.class);
        assertTrue("Возвращается не правильный результат при запросе создания корабля без параметра isUsed.", actual.equals(expected));
    }

    //test8
    @Test
    public void createShipIsUsedTrueTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/rest/ships/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(TestsHelper.IS_USED_TRUE_JSON))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        ShipInfoTest actual = mapper.readValue(contentAsString, ShipInfoTest.class);
        assertTrue("Возвращается не правильный результат при запросе создания корабля с параметром isUsed.", actual.equals(expected));
    }

    //test9
    @Test
    public void createShipIsUsedFalseTest() throws Exception {
        expected.isUsed = false;
        expected.rating = 12.8;

        ResultActions resultActions = mockMvc.perform(post("/rest/ships/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(TestsHelper.IS_USED_FALSE_JSON))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        ShipInfoTest actual = mapper.readValue(contentAsString, ShipInfoTest.class);
        assertTrue("Возвращается не правильный результат при запросе создания корабля с параметром isUsed.", actual.equals(expected));
    }

    @Autowired
    public void setContext(WebApplicationContext context) {
        this.context = context;
    }
}