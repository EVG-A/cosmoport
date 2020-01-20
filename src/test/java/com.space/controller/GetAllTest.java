package com.space.controller;

import com.fasterxml.jackson.core.type.TypeReference;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfig.class, MyWebAppInit.class, WebConfig.class})
@WebAppConfiguration
@Sql(scripts = "classpath:test.sql", config = @SqlConfig(encoding = "UTF-8"))
public class GetAllTest {

    private WebApplicationContext context;
    private MockMvc mockMvc;

    private TestsHelper testsHelper = new TestsHelper();
    private ObjectMapper mapper = new ObjectMapper();
    private TypeReference<List<ShipInfoTest>> typeReference = new TypeReference<List<ShipInfoTest>>() {
    };

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //test1
    @Test
    public void getAllWithoutFiltersReturnAllShips() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/rest/ships")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        List<ShipInfoTest> actual = mapper.readValue(contentAsString, typeReference);
        List<ShipInfoTest> expected = testsHelper.getShipInfosByPage(0, 3,
                testsHelper.getAllShips());
        assertTrue("Возвращается не правильный результат при запросе GET /rest/ships.", actual.equals(expected));
    }

    //test2
    @Test
    public void getAllWithFiltersNamePageNumber() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/rest/ships?name=ra&pageNumber=1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<ShipInfoTest> actual = mapper.readValue(contentAsString, typeReference);
        List<ShipInfoTest> expected = testsHelper.getShipInfosByPage(1, 3,
                testsHelper.getShipInfosByName("ra",
                        testsHelper.getAllShips()));

        assertTrue("Возвращается не правильный результат при запросе GET /rest/ships с параметрами name и pageNumber.", actual.equals(expected));
    }

    //test3
    @Test
    public void getAllWithFiltersPlanetPageSize() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/rest/ships?planet=ur&pageSize=4")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        List<ShipInfoTest> actual = mapper.readValue(contentAsString, typeReference);
        List<ShipInfoTest> expected = testsHelper.getShipInfosByPage(0, 4,
                testsHelper.getShipInfosByPlanet("ur", testsHelper.getAllShips()));

        assertTrue("Возвращается не правильный результат при запросе GET /rest/ships с параметрами planet и pageSize.", actual.equals(expected));
    }

    //test4
    @Test
    public void getAllWithFiltersShipTypeAfterBefore() throws Exception {
        //after 00:00 01.01.3000
        //before 00:00 01.01.3011
        ResultActions resultActions = mockMvc.perform(get("/rest/ships?shipType=MILITARY&after=32503672800000&before=32850741600000")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        List<ShipInfoTest> actual = mapper.readValue(contentAsString, typeReference);
        List<ShipInfoTest> expected = testsHelper.getShipInfosByPage(0, 3,
                testsHelper.getShipInfosByShipType(ShipType.MILITARY,
                        testsHelper.getShipInfosByAfter(32503672800000L,
                                testsHelper.getShipInfosByBefore(32850741600000L,
                                        testsHelper.getAllShips()))));

        assertTrue("Возвращается не правильный результат при запросе GET /rest/ships с параметрами shipType, after и before.", actual.equals(expected));
    }

    //test5
    @Test
    public void getAllWithFiltersShipTypeMinSpeedMaxSpeed() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/rest/ships?shipType=TRANSPORT&minSpeed=0.3&maxSpeed=0.6")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        List<ShipInfoTest> actual = mapper.readValue(contentAsString, typeReference);
        List<ShipInfoTest> expected = testsHelper.getShipInfosByPage(0, 3,
                testsHelper.getShipInfosByShipType(ShipType.TRANSPORT,
                        testsHelper.getShipInfosByMinSpeed(0.3,
                                testsHelper.getShipInfosByMaxSpeed(0.6,
                                        testsHelper.getAllShips()))));

        assertTrue("Возвращается не правильный результат при запросе GET /rest/ships с параметрами shipType, minSpeed и maxSpeed.", actual.equals(expected));
    }

    //test6
    @Test
    public void getAllWithFiltersShipTypeMinCrewSizeMaxCrewSize() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/rest/ships?shipType=MERCHANT&minCrewSize=10&maxCrewSize=1000")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        List<ShipInfoTest> actual = mapper.readValue(contentAsString, typeReference);
        List<ShipInfoTest> expected = testsHelper.getShipInfosByPage(0, 3,
                testsHelper.getShipInfosByShipType(ShipType.MERCHANT,
                        testsHelper.getShipInfosByMinCrewSize(10,
                                testsHelper.getShipInfosByMaxCrewSize(1000,
                                        testsHelper.getAllShips()))));

        assertTrue("Возвращается не правильный результат при запросе GET /rest/ships с параметрами shipType, minCrewSize и maxCrewSize.", actual.equals(expected));
    }

    //test7
    @Test
    public void getAllWithFiltersIsUsedMinMaxRating() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/rest/ships?isUsed=true&minRating=2&maxRating=4")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        List<ShipInfoTest> actual = mapper.readValue(contentAsString, typeReference);
        List<ShipInfoTest> expected = testsHelper.getShipInfosByPage(0, 3,
                testsHelper.getShipInfosByIsUsed(true,
                        testsHelper.getShipInfosByMinRating(2.,
                                testsHelper.getShipInfosByMaxRating(4.,
                                        testsHelper.getAllShips()))));

        assertTrue("Возвращается не правильный результат при запросе GET /rest/ships с параметрами isUsed, minRating и maxRating.", actual.equals(expected));
    }

    //test8
    @Test
    public void getAllWithFiltersIsUsedMaxSpeedMaxRating() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/rest/ships?isUsed=false&maxSpeed=0.6&maxRating=7")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        List<ShipInfoTest> actual = mapper.readValue(contentAsString, typeReference);
        List<ShipInfoTest> expected = testsHelper.getShipInfosByPage(0, 3,
                testsHelper.getShipInfosByIsUsed(false,
                        testsHelper.getShipInfosByMaxSpeed(0.6,
                                testsHelper.getShipInfosByMaxRating(7.,
                                        testsHelper.getAllShips()))));

        assertTrue("Возвращается не правильный результат при запросе GET /rest/ships с параметрами isUsed, maxSpeed и maxRating.", actual.equals(expected));
    }

    //test9
    @Test
    public void getAllWithFiltersNameOrderSpeed() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/rest/ships?name=ca&order=SPEED")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        List<ShipInfoTest> ships = mapper.readValue(contentAsString, typeReference);
        List<ShipInfoTest> expected = testsHelper.getShipInfosByPage(0, 3,
                testsHelper.getShipInfosByOrder(ShipOrder.SPEED,
                        testsHelper.getShipInfosByName("ca",
                                testsHelper.getAllShips())));

        assertTrue("Возвращается не правильный результат при запросе GET /rest/ships с параметрами name и order.", ships.equals(expected));
    }

    //test10
    @Test
    public void getAllWithFiltersAfterBeforeMinCrewMaxCrew() throws Exception {
        //after 00:00 01.01.2996
        //before 00:00 01.01.3009
        ResultActions resultActions = mockMvc.perform(get("/rest/ships?after=32377442400000&before=32787669600000&minCrewSize=20&maxCrewSize=1500&pageNumber=1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        List<ShipInfoTest> actual = mapper.readValue(contentAsString, typeReference);
        List<ShipInfoTest> expected = testsHelper.getShipInfosByPage(1, 3,
                testsHelper.getShipInfosByAfter(32377442400000L,
                        testsHelper.getShipInfosByBefore(32787669600000L,
                                testsHelper.getShipInfosByMinCrewSize(20,
                                        testsHelper.getShipInfosByMaxCrewSize(1500,
                                                testsHelper.getAllShips())))));

        assertTrue("Возвращается не правильный результат при запросе GET /rest/ships с параметрами after, before, minCrewSize и maxCrewSize.", actual.equals(expected));
    }

    @Autowired
    public void setContext(WebApplicationContext context) {
        this.context = context;
    }
}