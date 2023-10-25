package com.test.taskapplication;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilityTest {
    private static Utility utility;
    private static List<CityWeather> dummyData ;

    private static void dummyWeatherData(){
        dummyData = new ArrayList<>();
        CityWeather data = new CityWeather();
        data.city = "New York";
        data.season = "Autumn";
        data.cityType = "Large";
        data.averageTemp = 20.0f;
        dummyData.add(data);

        data = new CityWeather();
        data.city = "New York";
        data.season = "Summer";
        data.cityType = "Medium";
        data.averageTemp = 25.0f;
        dummyData.add(data);

        data = new CityWeather();
        data.city = "Chicago";
        data.season = "Spring";
        data.cityType = "Small";
        data.averageTemp = 32.0f;
        dummyData.add(data);
    }

    @BeforeClass
    public static void setUp() throws Exception {
        utility = Utility.getInstance();
        dummyWeatherData();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        utility = null;
        dummyData = null;
    }

    @Test
    public void checkSameUtilityInstanceReturned(){
        //Utility.getInstance()
        Assert.assertEquals("same instance is returned", utility, Utility.getInstance());
    }

    @Test
    public void checkValidCitiesReturned() {
        List<String> cityList = utility.getCities(dummyData);
        Assert.assertNotNull("list of cities is not null", cityList);
        Assert.assertTrue("Cities list contains New York", cityList.contains("New York"));
        Assert.assertEquals(2, cityList.size());
    }

    @Test
    public void checkValidCityTypesReturned() {
        List<String> cityTypes = utility.getCityTypes(dummyData);
        Assert.assertNotNull("list of cities is not null", cityTypes);
        Assert.assertTrue("City types list contains Large", cityTypes.contains("Large"));
        Assert.assertEquals(3, cityTypes.size());

    }

    @Test
    public void getSeasons() {
    }

    @Test
    public void getMessageToDisplay() {
    }
}