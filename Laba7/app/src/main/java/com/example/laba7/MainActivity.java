package com.example.laba7;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void buttonClicked(View view) {
        Toast myToast = Toast.makeText(getApplicationContext(), "Ура, вы тапнули на кнопку!",
                Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.TOP, 0, 0);
        myToast.show();
    }

    public void closeApp(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder
                .setTitle("Выход из приложения")
                .setIcon(R.drawable.icon_close)
                .setMessage("Вы уверенны, что хотите закрыть приложение?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void selectIngredients(View view) {
        String[] ingredients = {"Сырок Б.Ю. Александров", "Чипсы Pringles", "Налистники"};
        final boolean[] nSelectedIngredients = {false, false, false};

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder
                .setTitle("Выбери лучший продукт")
                .setCancelable(false)
                .setMultiChoiceItems(ingredients, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        nSelectedIngredients[which] = isChecked;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}