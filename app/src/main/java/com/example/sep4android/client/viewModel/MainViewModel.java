package com.example.sep4android.client.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4android.client.model.Measurements;
import com.example.sep4android.client.repository.MeasurementRepository;
import com.example.sep4android.networking.ClientRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {


    private MeasurementRepository measurementRepository;

    private ClientRepository clientRepository;

    private MutableLiveData<Measurements> measurementsMutableLiveData= new MutableLiveData<>();




    public MainViewModel(@NonNull Application application) {
        super(application);
        measurementRepository= MeasurementRepository.getInstance(application);
        clientRepository= ClientRepository.getInstance();
    }


    public void getMeasurementsMutableLiveDataFromServer(String location){
        clientRepository.getMeasurementsFromServer(location);
        measurementsMutableLiveData= clientRepository.getMeasurementsMutableLiveData();
    }

    public MutableLiveData<Measurements> getMeasurementsMutableLiveData() {
        return measurementsMutableLiveData;
    }
}
