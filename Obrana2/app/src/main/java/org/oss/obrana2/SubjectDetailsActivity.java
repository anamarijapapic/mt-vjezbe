package org.oss.obrana2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SubjectDetailsActivity extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference ref;
    EditText name, lecturer, year;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("predmeti");

        name = findViewById(R.id.editTextName);
        lecturer = findViewById(R.id.editTextLecturer);
        year = findViewById(R.id.editTextYear);

        Intent intent = getIntent();
        if (intent != null) {
            key = intent.getStringExtra("uid");
            name.setText(intent.getStringExtra("name"));
            lecturer.setText(intent.getStringExtra("lecturer"));
            year.setText(String.valueOf(intent.getLongExtra("year", 0)));
        }

        ImageButton backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(view -> {
            Intent backIntent = new Intent(SubjectDetailsActivity.this, SubjectListActivity.class);
            startActivity(backIntent);
        });

        Button saveBtn = findViewById(R.id.saveButton);
        saveBtn.setOnClickListener(view -> {
            updateSubject(key, name.getText().toString(), lecturer.getText().toString(), Long.parseLong(year.getText().toString()));

            Intent saveIntent = new Intent(SubjectDetailsActivity.this, SubjectListActivity.class);
            startActivity(saveIntent);
        });

        Button removeBtn = findViewById(R.id.removeButton);
        removeBtn.setOnClickListener(view -> {
            removeSubject(key);

            Intent removeIntent = new Intent(SubjectDetailsActivity.this, SubjectListActivity.class);
            startActivity(removeIntent);
        });
    }

    private void updateSubject(String key, String name, String lecturer, Long year) {
        Map<String, Object> subjectValues = new HashMap<>();
        subjectValues.put("ime", name);
        subjectValues.put("predavac", lecturer);
        subjectValues.put("godina", year);

        ref.child(key).updateChildren(subjectValues)
                .addOnSuccessListener(m -> Toast.makeText(
                        SubjectDetailsActivity.this,
                        "Subject updated successfully",
                        Toast.LENGTH_SHORT).show()
                )
                .addOnFailureListener(e -> Toast.makeText(
                        SubjectDetailsActivity.this,
                        "Subject update failed",
                        Toast.LENGTH_SHORT).show()
                );
    }

    private void removeSubject(String key) {
        ref.child(key).removeValue()
                .addOnSuccessListener(m -> Toast.makeText(
                        SubjectDetailsActivity.this,
                        "Subject removed successfully",
                        Toast.LENGTH_SHORT).show()
                )
                .addOnFailureListener(e -> Toast.makeText(
                        SubjectDetailsActivity.this,
                        "Subject removal failed",
                        Toast.LENGTH_SHORT).show()
                );
    }
}
