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
public class UpdateShipTest {

    private WebApplicationContext context;
    private MockMvc mockMvc;

    private TestsHelper testsHelper = new TestsHelper();
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //test1
    @Test
    public void updateShipIdZeroTest() throws Exception {
        mockMvc.perform(post("/rest/ships/0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(TestsHelper.NORMAL_JSON))
                .andExpect(status().isBadRequest());
    }

    //test2
    @Test
    public void updateShipNotExistTest() throws Exception {
        mockMvc.perform(post("/rest/ships/415")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(TestsHelper.NORMAL_JSON))
                .andExpect(status().isNotFound());
    }

    //test3
    @Test
    public void updateShipInvalidNameTest() throws Exception {
        ShipInfoTest shipInfoTest = testsHelper.getShipInfosById(1);

        mockMvc.perform(post("/rest/ships/" + shipInfoTest.id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(TestsHelper.EMPTY_NAME_JSON))
                .andExpect(status().isBadRequest());
    }

    //test4
    @Test
    public void updateShipInvalidProdDateTest() throws Exception {
        ShipInfoTest shipInfoTest = testsHelper.getShipInfosById(1);

        mockMvc.perform(post("/rest/ships/" + shipInfoTest.id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(TestsHelper.NEGATIVE_PROD_DATE_JSON))
                .andExpect(status().isBadRequest());
    }

    //test5
    @Test
    public void updateShipInvalidCrewSizeTest() throws Exception {
        ShipInfoTest shipInfoTest = testsHelper.getShipInfosById(1);

        mockMvc.perform(post("/rest/ships/" + shipInfoTest.id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(TestsHelper.TOO_BIG_CREW_SIZE_JSON))
                .andExpect(status().isBadRequest());
    }

    //test6
    @Test
    public void updateShipInvalidCrewSizeTest2() throws Exception {
        ShipInfoTest shipInfoTest = testsHelper.getShipInfosById(1);

        mockMvc.perform(post("/rest/ships/" + shipInfoTest.id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(TestsHelper.NEGATIVE_CREW_SIZE_JSON))
                .andExpect(status().isBadRequest());
    }

    //test7
    @Test
    public void updateShipWithIdTest() throws Exception {
        ShipInfoTest expected = mapper.readValue(String.format(TestsHelper.NORMAL_JSON_WITH_ID, 5), ShipInfoTest.class);
        expected.rating = 12.8;

        ResultActions resultActions = mockMvc.perform(post("/rest/ships/5")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(String.format(TestsHelper.NORMAL_JSON_WITH_ID, 8L)))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        ShipInfoTest actual = mapper.readValue(contentAsString, ShipInfoTest.class);

        assertTrue("При запросе POST /rest/ships/{id} поле id не должно обновляться.", actual.id != 8);
        assertTrue("При запросе POST /rest/ships/{id} с id в теле запроса, должны быть обновлены поля, кроме поля id", actual.equals(expected));
    }

    //test8
    @Test
    public void updateShipEmptyBodyTest() throws Exception {
        ShipInfoTest expected = testsHelper.getShipInfosById(17);

        ResultActions resultActions = mockMvc.perform(post("/rest/ships/17")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content("{}"))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        ShipInfoTest actual = mapper.readValue(contentAsString, ShipInfoTest.class);

        assertTrue("При запросе POST /rest/ships/{id} с пустым телом запроса, корабль не должен изменяться", actual.equals(expected));
    }

    //test9
    @Test
    public void updateShipRatingTest() throws Exception {
        ShipInfoTest expected = mapper.readValue(String.format(TestsHelper.NORMAL_JSON_WITH_ID, 23), ShipInfoTest.class);
        expected.rating = 12.8;

        ResultActions resultActions = mockMvc.perform(post("/rest/ships/23")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(String.format(TestsHelper.NORMAL_JSON_WITH_RATING, "9")))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        ShipInfoTest actual = mapper.readValue(contentAsString, ShipInfoTest.class);

        assertTrue("При запросе POST /rest/ships/{id} с rating в теле запроса, должны быть обновлены поля, кроме поля rating", actual.equals(expected));
    }

    //test10
    @Test
    public void updateShipWithDataTest1() throws Exception {
        ShipInfoTest shipInfoTest = testsHelper.getShipInfosById(14);

        String newName = "TestName";
        boolean newIsUsed = false;
        double newSpeed = 0.5;
        int newCrewSize = 2500;

        ShipInfoTest expected = new ShipInfoTest(shipInfoTest.id, newName, shipInfoTest.planet, shipInfoTest.shipType, shipInfoTest.prodDate,
                newIsUsed, newSpeed, newCrewSize, 6.67);

        ResultActions resultActions = mockMvc.perform(post("/rest/ships/" + shipInfoTest.id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(String.format(TestsHelper.JSON_SKELETON, newName, String.valueOf(newIsUsed), String.valueOf(newSpeed), String.valueOf(newCrewSize))))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        ShipInfoTest actual = mapper.readValue(contentAsString, ShipInfoTest.class);

        assertTrue("При запросе POST /rest/ships/{id} корабль должен обновляться и рейтинг пересчитываться", actual.equals(expected));
    }

    //test11
    @Test
    public void updateShipWithDataTest2() throws Exception {
        ShipInfoTest shipInfoTest = testsHelper.getShipInfosById(32);

        String newPlanet = "TestName";
        ShipType newShipType = ShipType.MILITARY;
        long newProdDate = 32556844329665L;

        ShipInfoTest expected = new ShipInfoTest(shipInfoTest.id, shipInfoTest.name, newPlanet, ShipType.MILITARY, 32556844329665L,
                shipInfoTest.isUsed, shipInfoTest.speed, shipInfoTest.crewSize, 2.48);

        ResultActions resultActions = mockMvc.perform(post("/rest/ships/" + shipInfoTest.id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(String.format(TestsHelper.JSON_SKELETON_2, newPlanet, String.valueOf(newShipType), String.valueOf(newProdDate))))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        ShipInfoTest actual = mapper.readValue(contentAsString, ShipInfoTest.class);

        assertTrue("При запросе POST /rest/ships/{id} корабль должен обновляться и рейтинг пересчитываться", actual.equals(expected));
    }


    @Autowired
    public void setContext(WebApplicationContext context) {
        this.context = context;
    }
}