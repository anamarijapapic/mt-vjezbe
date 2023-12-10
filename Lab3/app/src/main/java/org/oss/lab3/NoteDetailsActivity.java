package org.oss.lab3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class NoteDetailsActivity extends AppCompatActivity {

    private EditText titleInput, contentInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        titleInput = findViewById(R.id.titleInput);
        contentInput = findViewById(R.id.contentInput);

        ImageButton closeBtn = findViewById(R.id.close);
        closeBtn.setOnClickListener(view -> {
            Intent intent = new Intent(NoteDetailsActivity.this, MainActivity.class);
            startActivity(intent);
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("uid") && intent.hasExtra("title") && intent.hasExtra("content")) {
            // int uid = intent.getIntExtra("uid", -1);
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");

            titleInput.setText(title);
            contentInput.setText(content);
        }

        Button saveBtn = findViewById(R.id.save);
        Button deleteBtn = findViewById(R.id.delete);

        // Save/update note method
        View.OnClickListener saveClickListener = view -> {
            assert intent != null;
            int uid = intent.getIntExtra("uid", -1);

            // Update the note if uid is valid
            if (uid != -1) {
                updateNote(uid, titleInput.getText().toString(), contentInput.getText().toString());
            } else {
                // Insert a new note if there is no uid
                saveNote();
            }

            Intent backIntent = new Intent(NoteDetailsActivity.this, MainActivity.class);
            startActivity(backIntent);
        };

        // Delete note method
        View.OnClickListener deleteClickListener = view -> showDeleteConfirmationDialog();

        saveBtn.setOnClickListener(saveClickListener);
        deleteBtn.setOnClickListener(deleteClickListener);
    }

    private void saveNote() {
        AsyncTask.execute(() -> {
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            NoteDao noteDao = db.noteDao();
            noteDao.insertAll(new Note(
                    titleInput.getText().toString(),
                    contentInput.getText().toString()
            ));
        });
    }

    private void updateNote(int uid, String title, String content) {
        AsyncTask.execute(() -> {
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            NoteDao noteDao = db.noteDao();
            noteDao.update(uid, title, content);
        });
    }

    private void deleteNote(int uid) {
        AsyncTask.execute(() -> {
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            NoteDao noteDao = db.noteDao();
            noteDao.deleteById(uid);
        });
    }

    private void deleteNoteWithConfirmation(int uid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure you want to delete this note?");

        builder.setPositiveButton("Yes", (dialog, id) -> {
            // Delete the note if the user confirms
            deleteNote(uid);
            Intent backIntent = new Intent(NoteDetailsActivity.this, MainActivity.class);
            startActivity(backIntent);
        });

        builder.setNegativeButton("No", (dialog, id) -> {
            // User cancelled the dialog
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDeleteConfirmationDialog() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("uid")) {
            int uid = intent.getIntExtra("uid", -1);

            // Show the confirmation dialog
            if (uid != -1) {
                deleteNoteWithConfirmation(uid);
            }
        }
    }
}
