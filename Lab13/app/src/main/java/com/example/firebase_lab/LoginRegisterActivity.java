package com.example.firebase_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class LoginRegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEditText, passwordEditText, nameEditText;
    private Button buttonAction, switchButton;
    private boolean isLoginMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        mAuth = FirebaseAuth.getInstance();

        // Инициализация элементов интерфейса
        emailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        nameEditText = findViewById(R.id.editTextName);
        buttonAction = findViewById(R.id.buttonAction);
        switchButton = findViewById(R.id.switchButton);

        updateUI();

        // Кнопка входа или регистрации
        buttonAction.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Заполните email и пароль", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isLoginMode) {
                // Вход в аккаунт
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(this, "Вход выполнен", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(this, WelcomeActivity.class));
                                finish();
                            } else {
                                Toast.makeText(this, "Ошибка входа", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                // Регистрация аккаунта
                String name = nameEditText.getText().toString().trim();
                if (name.isEmpty()) {
                    Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(name)
                                            .build();

                                    user.updateProfile(profileUpdates).addOnCompleteListener(profileTask -> {
                                        if (profileTask.isSuccessful()) {
                                            Toast.makeText(this, "Регистрация успешна!", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(this, WelcomeActivity.class));
                                            finish();
                                        }
                                    });
                                }
                            } else {
                                Toast.makeText(this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // Переключение между входом и регистрацией
        switchButton.setOnClickListener(v -> {
            isLoginMode = !isLoginMode;
            updateUI();
        });
    }

    private void updateUI() {
        if (isLoginMode) {
            nameEditText.setVisibility(View.GONE);
            buttonAction.setText("Войти");
            switchButton.setText("Перейти к регистрации");
        } else {
            nameEditText.setVisibility(View.VISIBLE);
            buttonAction.setText("Зарегистрироваться");
            switchButton.setText("Перейти к входу");
        }
    }
}