package org.oss.lab1.task2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText num1Input, num2Input;
    private TextView operatorInput, resultOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1Input = findViewById(R.id.num1);
        num2Input = findViewById(R.id.num2);
        operatorInput = findViewById(R.id.operator);
        resultOutput = findViewById(R.id.result);

        Button calculateButton = findViewById(R.id.button);
        calculateButton.setOnClickListener(v -> calculate());
    }

    private void calculate() {
        String num1Str = num1Input.getText().toString();
        String num2Str = num2Input.getText().toString();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please enter both numbers!",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        String operatorStr = operatorInput.getText().toString();

        if (operatorStr.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please enter operator!",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        double num1 = Double.parseDouble(num1Str);
        double num2 = Double.parseDouble(num2Str);
        char operator = operatorStr.charAt(0);
        double result;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    Toast.makeText(
                            this,
                            "Division by zero is not allowed!",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }
                break;
            default:
                Toast.makeText(
                        this,
                        "Unsupported operator!",
                        Toast.LENGTH_SHORT
                ).show();
                return;
        }

        resultOutput.setText(
                String.format(
                        "Result:\n%s",
                        new DecimalFormat("#.##").format(result)
                )
        );
    }
}