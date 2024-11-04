package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //    private FrameLayout photoFrame, projectsFrame, achievementsFrame;
    private GridLayout[] grids;
    private Button[] tabs;

    final int[] myImages = {R.drawable.me_1, R.drawable.me_2, R.drawable.me_3, R.drawable.me_4, R.drawable.me_5, R.drawable.me_6, R.drawable.me_7, R.drawable.me_8, R.drawable.me_9};

    final int[] achievementsImages = {R.drawable.achievement_1, R.drawable.achievement_2, R.drawable.achievement_3, R.drawable.achievement_4, R.drawable.achievement_5, R.drawable.achievement_6, R.drawable.achievement_7, R.drawable.achievement_8, R.drawable.achievement_9};

    final int[] projectImages = {R.drawable.project_1, R.drawable.project_2, R.drawable.project_3, R.drawable.project_4, R.drawable.project_5, R.drawable.project_6, R.drawable.project_7, R.drawable.project_8, R.drawable.project_9};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView ageTextView = findViewById(R.id.ageTextView);
        TextView specTextView = findViewById(R.id.specTextView);

        setBioText(nameTextView, ageTextView, specTextView);


        // Инициализация UI элементов
        initUI();

        // Установка слушателей кликов
        setClickListeners();

        showContent(0, ProfileTabs.photo);
    }

    private void setBioText(TextView nameTextView, TextView ageTextView, TextView specTextView) {
        if (isEnglish()) {
            addToTheEndTextViewValue(nameTextView, "Michael");
            addToTheEndTextViewValue(ageTextView, "13");
            addToTheEndTextViewValue(specTextView, "Programmer");
        } else {
            addToTheEndTextViewValue(nameTextView, "Мишель");
            addToTheEndTextViewValue(ageTextView, "13");
            addToTheEndTextViewValue(specTextView, "Программист");
        }
    }

    private @NonNull Boolean isEnglish() {
        final Locale currentLocale = getResources().getConfiguration().getLocales().get(0);
        return currentLocale.getLanguage().equals("en");
    }


    private void initUI() {
        grids = new GridLayout[]{findViewById(R.id.meGridLayout), findViewById(R.id.projectGridLayout), findViewById(R.id.achievementGridLayout)};

        tabs = new Button[]{findViewById(R.id.photoTab), findViewById(R.id.projectsTab), findViewById(R.id.achievementsTab)};
    }


    private void setClickListeners() {
        for (Button tab : tabs) {
            tab.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        int frameIndex;
        if (v.getId() == R.id.projectsTab) {
            frameIndex = 1;
        } else if (v.getId() == R.id.achievementsTab) {
            frameIndex = 2;
        } else {
            frameIndex = 0;
        }
        showContent(frameIndex, ProfileTabs.values()[frameIndex]);
    }

    private void showContent(int frameIndex, ProfileTabs tab) {
        // Скрыть все фреймы и показать только видимый
        for (int i = 0; i < grids.length; i++) {
            if (i != frameIndex) {
                grids[i].setVisibility(View.GONE);
            }
        }
        grids[frameIndex].setVisibility(View.VISIBLE);

        // Скрыть все табы и показать только выбранную
        for (Button button : tabs) {
            button.setSelected(false);
        }
        tabs[frameIndex].setSelected(true);


        // Очистить все картинки и показать только нужные для табы картинки
        grids[frameIndex].removeAllViews();

        final int[] images = getImageViewResourceIds(tab);

        for (int imageId : images) {
            ImageView imageView = getImageView(imageId);
            grids[frameIndex].addView(imageView);
        }
    }

    private @NonNull ImageView getImageView(int imageId) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(imageId);

        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        int width = screenWidth / 7 * 2;
        int height = screenWidth / 7 * 2;

        imageView.setLayoutParams(new ViewGroup.LayoutParams(width, height));

        return imageView;
    }

    private int[] getImageViewResourceIds(ProfileTabs tab) {
        switch (tab) {
            case photo:
                return myImages;
            case projects:
                return projectImages;
            case achievements:
                return achievementsImages;
            default:
                throw new IllegalArgumentException("Unknown tab type");
        }
    }


    private void addToTheEndTextViewValue(TextView textView, String endValue) {
        String oldValue = textView.getText().toString();
        String newValue = oldValue + " " + endValue;
        textView.setText(newValue);

    }
}

enum ProfileTabs {
    photo, projects, achievements
}
