package com.test.taskapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class provides various utility methods
 */
public class Utility {

    private Utility(){

    }

    /**
     * static class that will create instance lazily when first time being accessed and will hold the same instance.
     */
    private static final class InstanceHolder {
        static final Utility INSTANCE = new Utility();
    }

    /**
     * static method to get instance of Utility
     * @return single instance of Utility
     */
    public static Utility getInstance(){
        return InstanceHolder.INSTANCE;
    }

    /**
     * this will provide list of cities from all city weather data entered by user
     * @param cityWeathers list of CityWeather data
     * @return list of cities
     */
    public List<String> getCities(List<CityWeather> cityWeathers){
        if(cityWeathers == null || cityWeathers.isEmpty()) return new ArrayList<>();
        return cityWeathers.stream().map(map -> map.city).distinct().collect(Collectors.toList());
    }

    /**
     * this will filter out city types from all CityWeather data
     * @param cityWeathers list of CityWeather data
     * @return list of city types
     */

    public List<String> getCityTypes(List<CityWeather> cityWeathers){
        if(cityWeathers == null || cityWeathers.isEmpty()) return new ArrayList<>();
        return cityWeathers.stream().map(map -> map.cityType).distinct().collect(Collectors.toList());
    }

    /**
     * this will return list of seasons from CityWeather data
     * @param cityWeathers list of CityWeather data
     * @return list of seasons
     */
    public List<String> getSeasons(List<CityWeather> cityWeathers){
        if(cityWeathers == null || cityWeathers.isEmpty()) return new ArrayList<>();
        return cityWeathers.stream().map(map -> map.season).distinct().collect(Collectors.toList());
    }

    /**
     * to get message to be displayed to user
     * @param city cityname
     * @param cityType city type
     * @param season season type
     * @param value weather data list
     * @return message to be displayed to user
     */
    public String getMessageToDisplay(String city, String cityType, String season, List<CityWeather> value) {
        if(value == null || value.isEmpty()) return "";
        CityWeather cityWeatherObject =  value.stream().filter(cityWeather -> cityWeather.cityType.equals(cityType) && cityWeather.city.equals(city)
        && cityWeather.season.equals(season)).findFirst().orElse(null);
        StringBuilder sb = null;
        if(cityWeatherObject  != null){
            sb = new StringBuilder();
            sb.append(city).append(", type-").append(cityType).append(", temp for season ").append(season)
                    .append(" = ").append(cityWeatherObject.averageTemp);
            return sb.toString();
        }
        return "";
    }

}
