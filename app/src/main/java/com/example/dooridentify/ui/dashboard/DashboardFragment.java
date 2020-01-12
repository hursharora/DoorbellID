package com.example.dooridentify.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.dooridentify.ProfileAdapter;
import com.example.dooridentify.R;
import com.example.dooridentify.model.Profile;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profiles, container, false);

        //final TextView textView = root.findViewById(R.id.text_dashboard);

        ArrayList<Profile> profiles = new ArrayList<>();

        Profile test1 = new Profile("Test", "Test 2", "https://www.ehotelsasia.com/wp-content/uploads/2018/10/Black-Background-DX58.jpg");
        Profile test2 = new Profile("Test1", "Test 3", "https://www.ehotelsasia.com/wp-content/uploads/2018/10/Black-Background-DX58.jpg");
        Profile test3 = new Profile("Test2", "Test 4", "https://www.ehotelsasia.com/wp-content/uploads/2018/10/Black-Background-DX58.jpg");

        profiles.add(test1);
        profiles.add(test2);
        profiles.add(test3);

        ListView profilesList =(ListView) root.findViewById(R.id.list);

        ProfileAdapter profileAdapter = new ProfileAdapter(getActivity(), profiles);

        profilesList.setAdapter(profileAdapter);

//        dashboardViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}