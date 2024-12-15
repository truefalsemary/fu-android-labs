package com.example.lab8.ui.calls;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab8.databinding.FragmentCallsBinding;
import com.example.lab8.databinding.FragmentContactsBinding;

public class CallsFragment extends Fragment {

    private FragmentCallsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CallsViewModel createChannelViewModel =
                new ViewModelProvider(this).get(CallsViewModel.class);

        binding = FragmentCallsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}