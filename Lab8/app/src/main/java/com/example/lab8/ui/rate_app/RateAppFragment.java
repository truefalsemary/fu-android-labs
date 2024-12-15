package com.example.lab8.ui.rate_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab8.databinding.FragmentCallsBinding;
import com.example.lab8.databinding.FragmentRateAppBinding;

public class RateAppFragment extends Fragment {

    private FragmentRateAppBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RateAppViewModel createChannelViewModel =
                new ViewModelProvider(this).get(RateAppViewModel.class);

        binding = FragmentRateAppBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}