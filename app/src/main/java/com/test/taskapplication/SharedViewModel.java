package com.test.taskapplication;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ViewModel which is being shared among both screens. Handles user interaction with UI and update observers in order to update UI.
 */
public class SharedViewModel extends ViewModel {

     private final MutableLiveData<Class<?>> _screenLoader = new MutableLiveData<>();

     public LiveData<Class<?>> screenLoad = _screenLoader;

     private final MutableLiveData<List<CityWeather>> _cityWeathers = new MutableLiveData<>();
     public LiveData<List<CityWeather>> cityWeathers = _cityWeathers;

     private MutableLiveData<String> _messageToDisplay = new MutableLiveData<>();

     public LiveData<String> messageToDisplay = _messageToDisplay;

     /**
      * to save city weather data entered by user
      * @param city cityname
      * @param cityType city type {small, medium, large}
      * @param season season {Autumn, Spring, Summer, Winter}
      * @param monthTemp1 temp for month 1
      * @param monthTemp2 temp for month 2
      * @param monthTemp3 temp for month 3
      */
     public void submitWeatherData(String city, String cityType, String season, float monthTemp1, float monthTemp2, float monthTemp3){
          float avgTemp = (monthTemp1 + monthTemp2 + monthTemp3)/3;
          CityWeather cityWeather = new CityWeather();
          cityWeather.averageTemp = avgTemp;
          cityWeather.city = city;
          cityWeather.cityType = cityType;
          cityWeather.season = season;

          List<CityWeather> list = _cityWeathers.getValue();

          if (list == null) list = new ArrayList<>();

          list.add(cityWeather);
          Log.d("ViewModel", "list size:"+ list.size());
          _cityWeathers.setValue(list);
     }

     public CityWeather dummyWeather(){
          CityWeather cityWeather = new CityWeather();
          cityWeather.averageTemp = 20;
          cityWeather.city = "ABC";
          cityWeather.cityType = "ABCType";
          cityWeather.season = "season";
          return cityWeather;
     }

     /**
      *  this will launch provided fragment
      * @param fragmentToLaunch screen launch
      */
     private void launchScreen(Class<?> fragmentToLaunch){
          _screenLoader.setValue(fragmentToLaunch);
     }

     /**
         invoked when user taps settings screen
      */
     public void settingsButtonClicked(){
         launchScreen(SecondScreenFragment.class);
     }

     /**
         invoked when user submits city weather data
     */
     public void submitButtonClicked(){
          launchScreen(FirstScreenFragment.class);
     }

     /**
      * this will provide list of cities from all city weather data entered by user
      * @param weatherData list of CityWeather data
      * @return list of cities
      */
     public List<String> getCities(List<CityWeather> weatherData){
          return  Utility.getInstance().getCities(weatherData);
     }

     /**
      * this will provide list of city types from all city weather data entered by user
      * @param weatherData list of CityWeather data
      * @return list of city types
      */
     public List<String> getCityTypes(List<CityWeather> weatherData){
          return  Utility.getInstance().getCityTypes(weatherData);
     }

     /**
      * this will provide list of cities from all city weather data entered by user
      * @param weatherData list of CityWeather data
      * @return list of seasons
      */
     public List<String> getSeasons(List<CityWeather> weatherData){
          return  Utility.getInstance().getSeasons(weatherData);
     }

     /**
      * to get message to display
      * @param city city selected by user
      * @param cityType citytype
      * @param season season
      */
     public void getMessageToDisplay(String city, String cityType, String season){
          _messageToDisplay.setValue(Utility.getInstance().getMessageToDisplay(city, cityType, season, _cityWeathers.getValue()));
     }
}
