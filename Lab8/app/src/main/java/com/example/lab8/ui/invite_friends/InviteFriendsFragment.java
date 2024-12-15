package com.example.lab8.ui.invite_friends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab8.databinding.FragmentCallsBinding;
import com.example.lab8.databinding.FragmentInviteFriendsBinding;

public class InviteFriendsFragment extends Fragment {

    private FragmentInviteFriendsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InviteFriendsViewModel createChannelViewModel =
                new ViewModelProvider(this).get(InviteFriendsViewModel.class);

        binding = FragmentInviteFriendsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}