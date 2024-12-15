package com.example.lab8.ui.new_group;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab8.databinding.FragmentHomeBinding;
import com.example.lab8.databinding.FragmentNewGroupBinding;

public class NewGroupFragment extends Fragment {

    private FragmentNewGroupBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NewGroupViewModel viewModel =
                new ViewModelProvider(this).get(NewGroupViewModel.class);

        binding = FragmentNewGroupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textView2;
        viewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}