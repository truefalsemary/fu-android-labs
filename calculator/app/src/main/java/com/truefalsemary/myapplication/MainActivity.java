package com.truefalsemary.myapplication;

import java.util.Random;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView textViewFeedbackIncorrectAnswer = findViewById(R.id.textViewFeedbackIncorrectAnswer);
        TextView textViewFeedbackCorrectAnswer = findViewById(R.id.textViewFeedbackCorrectAnswer);
        TextView textViewMathExample = findViewById(R.id.textViewMathExample);


        final MathExample[] mathExample = {new MathExample()};

        textViewMathExample.setText(mathExample[0].getMathExample());


        Button buttonAnswer = findViewById(R.id.buttonAnswer);


        buttonAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.editTextNumber);
                String userAnswer = editText.getText().toString();

                if (userAnswer.equals(String.valueOf(mathExample[0].getAnswer()))) {
                    cleanFeedback(textViewFeedbackCorrectAnswer, textViewFeedbackIncorrectAnswer);
                    mathExample[0] = generateMathExample(textViewMathExample);
                } else {
                    textViewFeedbackCorrectAnswer.setVisibility(View.GONE);
                    textViewFeedbackIncorrectAnswer.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    void cleanFeedback(TextView textViewFeedbackCorrectAnswer, TextView textViewFeedbackIncorrectAnswer) {
        textViewFeedbackCorrectAnswer.setVisibility(View.GONE);
        textViewFeedbackIncorrectAnswer.setVisibility(View.GONE);
    }


    MathExample generateMathExample(TextView textViewMathExample) {
        final MathExample mathExample = new MathExample();
        textViewMathExample.setText(mathExample.getMathExample());
        return mathExample;
    }

}

class MathExample {

    MathExample() {
        Random random = new Random();
        firstNumber = random.nextInt(100);
        secondNumber = random.nextInt(firstNumber);
        operation = MathOperation.getRandomOperation();
        answer = calculate(firstNumber, secondNumber, operation);
        mathExample = generateMathExample();
    }

    final private int firstNumber;
    final private int secondNumber;
    final private int answer;
    final private String mathExample;
    final private MathOperation operation;

    public String getMathExample() {
        return mathExample;
    }

    public int getAnswer() {
        return this.answer;
    }

    @SuppressLint("DefaultLocale")
    private String generateMathExample() {
        return String.format("%d %s %d = ?", firstNumber, operation.toString(), secondNumber);
    }

    private int calculate(int firstNumber, int secondNumber, MathOperation operation) {
        switch (operation) {
            case PLUS:
                return firstNumber + secondNumber;
            case MINUS:
                return firstNumber - secondNumber;
            default:
                return firstNumber * secondNumber;
        }
    }


}

enum MathOperation {
    PLUS,
    MINUS,
    MULTIPLY;

    @NonNull
    @Override
    public String toString() {
        switch (this) {
            case PLUS:
                return "+";
            case MINUS:
                return "-";
            default:
                return "*";
        }
    }

    private static final MathOperation[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static MathOperation getRandomOperation() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}