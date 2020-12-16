package com.example.sep4android.client.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sep4android.R;
import com.example.sep4android.client.model.Measurements;
import com.example.sep4android.client.viewModel.HistroyViewModel;
import com.example.sep4android.databinding.FragmentHistroyBinding;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private HistroyViewModel dashboardViewModel;
    private FragmentHistroyBinding binding;

    private Spinner spinner;
    private List<String> historyRecord;
    private ArrayAdapter<String> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(HistroyViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_histroy, container, false);
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_histroy, container, false);
        View root= binding.getRoot();

        binding.setLifecycleOwner(this);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));


        List<Measurements> measurementsList= new ArrayList<>();
        measurementsList.add(new Measurements(1,1,1));
        measurementsList.add(new Measurements(2,2,2));
        measurementsList.add(new Measurements(3,3,3));
        measurementsList.add(new Measurements(4,4,4));
        measurementsList.add(new Measurements(5,5,5));
        MeasurementsAdapter adapter= new MeasurementsAdapter(measurementsList);
        binding.recycleView.setAdapter(adapter);
        spinner = (Spinner)root.findViewById(R.id.locationInHistory);
        initSpinner();

        return root;
    }

    public void initSpinner(){

        historyRecord = new ArrayList<String>();
        historyRecord.add("front");
        historyRecord.add("Student Village");

        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,historyRecord);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//
            }
        });

    }
}