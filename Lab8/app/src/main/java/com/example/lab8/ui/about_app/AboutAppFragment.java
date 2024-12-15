package com.example.lab8.ui.about_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab8.databinding.FragmentAboutAppBinding;
import com.example.lab8.databinding.FragmentCallsBinding;

public class AboutAppFragment extends Fragment {

    private FragmentAboutAppBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AboutAppViewModel createChannelViewModel =
                new ViewModelProvider(this).get(AboutAppViewModel.class);

        binding = FragmentAboutAppBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}