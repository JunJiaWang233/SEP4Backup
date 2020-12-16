package com.example.sep4android.client.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4android.R;
import com.example.sep4android.client.viewModel.CurrentDataViewModel;
import com.example.sep4android.client.viewModel.MainViewModel;
import com.example.sep4android.databinding.FragmentCurrentdataBinding;

import java.util.ArrayList;
import java.util.List;

public class CurrentDataFragment extends Fragment {

    private FragmentCurrentdataBinding binding;
    private Spinner spinner;
    private ArrayAdapter<String>adapter;
    private List<String> currents;
    private MainViewModel mainViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_currentdata, container, false);
        View root= binding.getRoot();
        mainViewModel= new ViewModelProvider(getActivity()).get(MainViewModel.class);
        binding.setMainViewModel(mainViewModel);
        binding.setLifecycleOwner(getActivity());
        spinner = (Spinner)root.findViewById(R.id.location);
        initSpinner();


        return root;
    }

    public void initSpinner(){

        currents = new ArrayList<String>();
        currents.add("front");
        currents.add("back");

        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,currents);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String location= adapter.getItem(position);
                Toast.makeText(getContext(), "The selected location is"+location, Toast.LENGTH_SHORT).show();
                mainViewModel.getMeasurementsMutableLiveDataFromServer(location);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }




}