package com.example.firebase_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView textWelcome = findViewById(R.id.textWelcome);
        Button buttonLogout = findViewById(R.id.buttonLogout);

        // Получение имени пользователя
        String userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        textWelcome.setText("Добро пожаловать, " + (userName != null ? userName : "Пользователь"));

        // Обработчик кнопки "Выйти"
        buttonLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut(); // Выход из аккаунта
            Intent intent = new Intent(WelcomeActivity.this, LoginRegisterActivity.class); // Переход на экран входа
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Закрыть текущую активность
        });
    }
}