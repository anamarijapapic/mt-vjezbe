package org.oss.obrana2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button login, register;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);

        login = findViewById(R.id.buttonLogin);
        login.setOnClickListener(view -> loginUser(email.getText().toString(), password.getText().toString()));

        register = findViewById(R.id.buttonRegister);
        register.setOnClickListener(view -> createAccount(email.getText().toString(), password.getText().toString()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Toast.makeText(MainActivity.this, "Welcome " + user.getEmail(), Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, SubjectListActivity.class));
        }
    }

    void createAccount(String userEmail, String userPassword) {
        mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    assert user != null;

                    Toast.makeText(MainActivity.this, user.toString(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Authentication failed",Toast.LENGTH_SHORT).show();
                }
            });
    }

    void loginUser(String userEmail, String userPassword) {
        mAuth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    assert user != null;

                    Intent intent = new Intent(MainActivity.this, SubjectListActivity.class);
                    startActivity(intent);

                    Toast.makeText(MainActivity.this, "Successful login", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                }
            });
    }
}