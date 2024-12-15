package com.example.lab8;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lab8.ui.about_app.AboutAppFragment;
import com.example.lab8.ui.calls.CallsFragment;
import com.example.lab8.ui.contacts.ContactsFragment;
import com.example.lab8.ui.create_channel.CreateChannelFragment;
import com.example.lab8.ui.favorites.FavoritesFragment;
import com.example.lab8.ui.invite_friends.InviteFriendsFragment;
import com.example.lab8.ui.new_group.NewGroupFragment;
import com.example.lab8.ui.rate_app.RateAppFragment;
import com.example.lab8.ui.settings.SettingsFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab8.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.lab8.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).setAnchorView(R.id.fab).show());
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.new_group).setOpenableLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            Class fragmentClass = getClass(item);

            try {
                assert fragmentClass != null;
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            assert fragment != null;
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
            item.setChecked(true);
            setTitle(item.getTitle());

            return true;
        });
    }

    private static @Nullable Class getClass(MenuItem item) {
        Class fragmentClass = null;
        int id = item.getItemId();

        if (id == R.id.new_group) {
            fragmentClass = NewGroupFragment.class;
        } else if (id == R.id.create_chanel) {
            fragmentClass = CreateChannelFragment.class;
        } else if (id == R.id.contacts) {
            fragmentClass = ContactsFragment.class;
        } else if (id == R.id.calls) {
            fragmentClass = CallsFragment.class;
        } else if (id == R.id.favorites) {
            fragmentClass = FavoritesFragment.class;
        } else if (id == R.id.invite) {
            fragmentClass = InviteFriendsFragment.class;
        } else if (id == R.id.settings) {
            fragmentClass = SettingsFragment.class;
        } else if (id == R.id.about_app) {
            fragmentClass = AboutAppFragment.class;
        } else if (id == R.id.rate_app) {
            fragmentClass = RateAppFragment.class;
        }
        return fragmentClass;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        int id = item.getItemId();

        if (id == R.id.settings) {
            navController.navigate(R.id.settings);
            return true;
        } else if (id == R.id.about_app) {
            navController.navigate(R.id.about_app);
            return true;
        } else if (id == R.id.rate_app) {
            navController.navigate(R.id.rate_app);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        Fragment fragment = null;
//        Class fragmentClass = getClass(item);
//
//        try {
//            assert fragmentClass != null;
//            fragment = (Fragment) fragmentClass.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        assert fragment != null;
//        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
//        item.setChecked(true);
//        setTitle(item.getTitle());
//
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

}