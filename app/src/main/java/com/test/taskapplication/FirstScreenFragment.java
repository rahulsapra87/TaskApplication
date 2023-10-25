package com.test.taskapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.test.taskapplication.databinding.FragmentFirstScreenBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstScreenFragment extends Fragment {

    private FragmentFirstScreenBinding binding;
    private SharedViewModel sharedViewModel;
    public FirstScreenFragment() {
        // Required empty public constructor
    }
    private static final String TAG = "FIRST_SCREEN";
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment FirstScreenFragment.
     */

    public static FirstScreenFragment newInstance() {
        return new FirstScreenFragment();
    }

    private AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            getMessageFromViewModel();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFirstScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.cityWeathers.observe(getViewLifecycleOwner(), cityWeathers -> {
            Log.d(TAG,"new city weather data added by user");
            inflateViews(cityWeathers);
        });

        inflateViews(new ArrayList<CityWeather>());
        binding.btnSettings.setOnClickListener(view1 -> sharedViewModel.settingsButtonClicked());
        binding.spCitySelection.setOnItemSelectedListener(spinnerListener);
        binding.spCityTypeSelection.setOnItemSelectedListener(spinnerListener);
        binding.spSeasonSelection.setOnItemSelectedListener(spinnerListener);

        sharedViewModel.messageToDisplay.observe(getViewLifecycleOwner(), message -> {
            binding.tvMessageView.setText(message);
        });
    }

    private void getMessageFromViewModel(){
        String citySelected = (String) binding.spCitySelection.getSelectedItem();
        String cityTypeSelected = (String) binding.spCityTypeSelection.getSelectedItem();
        String seasonSelected = (String) binding.spSeasonSelection.getSelectedItem();
        sharedViewModel.getMessageToDisplay(citySelected,cityTypeSelected, seasonSelected);
    }

    private void inflateViews(List<CityWeather> weatherList){

        List<String> cities = sharedViewModel.getCities(weatherList);
        List<String> cityTypes = sharedViewModel.getCityTypes(weatherList);
        List<String> seasons = sharedViewModel.getSeasons(weatherList);

        binding.spCitySelection.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, cities));
        binding.spCityTypeSelection.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, cityTypes));
        binding.spSeasonSelection.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, seasons));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}