package com.example.lab8.ui.create_channel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab8.databinding.FragmentCreateChannelBinding;
import com.example.lab8.databinding.FragmentHomeBinding;

public class CreateChannelFragment extends Fragment {

    private FragmentCreateChannelBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CreateChannelViewModel createChannelViewModel =
                new ViewModelProvider(this).get(CreateChannelViewModel.class);

        binding = FragmentCreateChannelBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}