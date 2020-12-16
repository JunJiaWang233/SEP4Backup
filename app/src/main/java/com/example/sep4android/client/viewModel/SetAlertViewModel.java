package com.example.sep4android.client.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.client.model.AlertValue;
import com.example.sep4android.client.repository.AlertRepository;
import com.example.sep4android.databinding.FragmentSetAlertBindingImpl;

public class SetAlertViewModel extends AndroidViewModel {


    private AlertRepository alertRepository;



    public SetAlertViewModel(@NonNull Application application) {
        super(application);

        alertRepository= AlertRepository.getInstance(application);
    }

    public void setAlertValue(AlertValue alertValue) {
        long id= alertValue.getUserId();
        AlertValue a= alertRepository.getAlertValueLiveData(id).getValue();
        if (a==null){
            alertRepository.insert(alertValue);
        }else {
            alertRepository.update(alertValue);
        }
    }

    public void insertAlert(AlertValue... alertValues){
        alertRepository.insert(alertValues);
    }

    public void updateAlert(AlertValue... alertValues){
        alertRepository.update(alertValues);
    }

    public LiveData<AlertValue> getAlert(long userId){
        return alertRepository.getAlertValueLiveData(userId);
    }


}
