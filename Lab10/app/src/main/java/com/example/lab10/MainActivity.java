package com.example.lab10;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager2 viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText("Страница " + (position + 1)));
        tabLayoutMediator.attach();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_fragment1) {
                viewPager.setCurrentItem(0);
                return true;
            } else if (itemId == R.id.nav_fragment2) {
                viewPager.setCurrentItem(1);
                return true;
            } else if (itemId == R.id.nav_fragment3) {
                viewPager.setCurrentItem(2);
                return true;
            }
            return false;
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.nav_fragment1);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.nav_fragment2);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.nav_fragment3);
                        break;
                }
            }
        });
    }
}

