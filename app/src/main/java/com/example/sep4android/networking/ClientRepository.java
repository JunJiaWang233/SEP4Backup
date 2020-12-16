package com.example.sep4android.networking;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.client.model.Measurements;
import com.example.sep4android.client.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientRepository {
    private static ClientRepository instance;
    private static Client client;
    private  MutableLiveData<User> userMutableLiveData;
    private  MutableLiveData<Measurements> measurementsMutableLiveData;

    private static List<Measurements> location1;
    private static List<Measurements> location2;



    private ClientRepository(){

        measurementsMutableLiveData= new MutableLiveData<>();
    }

    public static synchronized ClientRepository getInstance(){
        if (instance==null){
            instance= new ClientRepository();
            client= ServerGenerator.getClient();
        }
        return instance;
    }

    public void signUpAccount(String username, String password){

    }

    public void loginAccount(String username, String password){
        Call<User> userCall= client.getUser(username, password);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void getMeasurementsFromServer(String location){
        Call<Measurements> call= client.getMeasurements(location);
        call.enqueue(new Callback<Measurements>() {
            @Override
            public void onResponse(Call<Measurements> call, Response<Measurements> response) {
                measurementsMutableLiveData.setValue(response.body());
                Log.e("measurement", response.body().toString());
            }

            @Override
            public void onFailure(Call<Measurements> call, Throwable t) {
                Log.e("measurement", "error");
            }
        });
    }

    public MutableLiveData<Measurements> getMeasurementsMutableLiveData() {
        return measurementsMutableLiveData;
    }
}
