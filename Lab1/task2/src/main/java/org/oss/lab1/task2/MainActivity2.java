package org.oss.lab1.task2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText num1Input, num2Input;
    private String operatorStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Spinner spinner = (Spinner) findViewById(R.id.operators_spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.operators_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        num1Input = findViewById(R.id.num1);
        num2Input = findViewById(R.id.num2);

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

        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        intent.putExtra(
                "resultOutput",
                String.format(
                        "Result:\n%s",
                        new DecimalFormat("#.##").format(result)
                )
        );
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        operatorStr = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}