package com.example.sep4android.client.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sep4android.R;

import com.example.sep4android.client.model.Measurements;
import com.example.sep4android.client.viewModel.CurrentDataViewModel;
import com.example.sep4android.client.viewModel.MainViewModel;
import com.example.sep4android.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

   ActivityMainBinding binding;
   MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_CurrentData, R.id.navigation_History, R.id.navigation_Account,R.id.navigation_SetAlert)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        mainViewModel= new ViewModelProvider(this).get(MainViewModel.class);
        binding.setLifecycleOwner(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}