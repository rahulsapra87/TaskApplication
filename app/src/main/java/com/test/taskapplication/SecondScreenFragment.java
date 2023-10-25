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
import android.widget.ArrayAdapter;

import com.test.taskapplication.databinding.FragmentSecondScreenBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondScreenFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private FragmentSecondScreenBinding binding;
    private static final String TAG = "SETTINGS_SCREEN";
    public SecondScreenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment SecondScreenFragment.
     */

    public static SecondScreenFragment newInstance(String param1, String param2) {
        return new SecondScreenFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSecondScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        inflateViews();
        binding.btnSubmit.setOnClickListener(view1 -> {
            String selectedCity = (String)binding.spCitySelection.getSelectedItem();
            String selectedCityType = (String)binding.spCityTypeSelection.getSelectedItem();
            String selectedSeason = (String)binding.spSeasonSelection.getSelectedItem();

            Log.d(TAG, "City :"+ selectedCity +" type: "+selectedCityType +" season:"+selectedSeason);
            // TODO added hardcoded temp
            sharedViewModel.submitWeatherData(selectedCity, selectedCityType,selectedSeason, 22,24,26);
            sharedViewModel.submitButtonClicked();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void inflateViews(){
        String[] cityList = getResources().getStringArray(R.array.city_list);
        String[] cityType = getResources().getStringArray(R.array.city_type);
        String[] seasons = getResources().getStringArray(R.array.seasons);
        binding.spCitySelection.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, cityList));
        binding.spCityTypeSelection.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, cityType));
        binding.spSeasonSelection.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, seasons));
    }
}