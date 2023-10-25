package com.test.taskapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import com.test.taskapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private SharedViewModel sharedViewModel;

    @Override
    public void  onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        sharedViewModel.screenLoad.observe(this, this::launchScreen);
    }

    /**
     * this method will launch provided fragment/screen
     * @param fragmentClassToLoad fragment to be launched
     */
    public void launchScreen(Class fragmentClassToLoad){
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, fragmentClassToLoad, null)
                .commit();
    }

}