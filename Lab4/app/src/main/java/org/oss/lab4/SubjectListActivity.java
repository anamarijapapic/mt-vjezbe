package org.oss.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SubjectListActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button logout;
    TextView textViewUserEmail;
    FirebaseDatabase db;
    DatabaseReference ref;
    ArrayList<Subject> subjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);

        mAuth = FirebaseAuth.getInstance();
        textViewUserEmail = findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText(mAuth.getCurrentUser().getEmail());

        CustomAdapter customAdapter = new CustomAdapter(subjects);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        customAdapter.setOnClickListener((position, model) -> {
            Intent intent = new Intent(SubjectListActivity.this, SubjectDetailsActivity.class);
            intent.putExtra("uid", model.uid);
            intent.putExtra("name", model.getName());
            intent.putExtra("lecturer", model.getLecturer());
            intent.putExtra("year", model.getYear());
            startActivity(intent);
        });

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("predmeti");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        Subject subject = child.getValue(Subject.class);
                        assert subject != null;
                        subject.uid = child.getKey();
                        subjects.add(subject);
                    }
                }

                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        logout = findViewById(R.id.buttonLogout);
        logout.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(SubjectListActivity.this, MainActivity.class));
        });
    }
}
