package org.oss.lab1.task2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView resultOutput = findViewById(R.id.result);

        Intent intent = getIntent();
        String result = intent.getStringExtra("resultOutput");
        resultOutput.setText(result);

        Button backButton = findViewById(R.id.button);
        backButton.setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity3.this, MainActivity2.class);
            startActivity(intent1);
        });
    }
}