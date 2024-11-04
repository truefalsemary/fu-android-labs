package com.example.laba6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView operation, result;
    private EditText number1, number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonPow = findViewById(R.id.buttonPow);

        Button buttonClean = findViewById(R.id.buttonClean);

        operation = findViewById(R.id.operation);
        result = findViewById(R.id.result);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);

        buttonAdd.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonPow.setOnClickListener(this);
        buttonClean.setOnClickListener(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {
        double num1 = 0;
        double num2;
        try {
            num1 = Double.parseDouble(number1.getText().toString());
            num2 = Double.parseDouble(number2.getText().toString());
        } catch (NumberFormatException e) {
            return;
        }


        final int operationId = v.getId();
        double res = 0;
        if (operationId == R.id.buttonAdd) {
            operation.setText("+");
            res = num1 + num2;
        } else if (operationId == R.id.buttonSubtract) {
            operation.setText("-");
            res = num1 - num2;
        } else if (operationId == R.id.buttonMultiply) {
            operation.setText("*");
            res = num1 * num2;
        } else if (operationId == R.id.buttonDivide) {
            operation.setText("/");
            if (num2 == 0) {
                result.setText("None");
                return;
            }
            res = num1 / num2;
        } else if (operationId == R.id.buttonPow) {
            operation.setText("^");
            res = Math.pow(num1, num2);
        } else {
            number1.setText("");
            operation.setText("");
            number2.setText("");
            result.setText("");
        }

        result.setText(res + "");


    }
}