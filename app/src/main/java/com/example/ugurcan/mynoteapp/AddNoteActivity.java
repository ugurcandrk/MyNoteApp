package com.example.ugurcan.mynoteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {
    private Date c;
    private SimpleDateFormat df;
    private String formattedDate;
    private EditText editTextTitle,editTextNote;
    private Toolbar toolbar;
    private FirebaseDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        c = Calendar.getInstance().getTime();
        df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c);

        editTextTitle=findViewById(R.id.editTextTitle);
        editTextNote=findViewById(R.id.editTextNote);

        db = FirebaseDatabase.getInstance();

    }



    public void btnSaveNote(View view) {
        String noteTitle=editTextTitle.getText().toString().trim();
        String noteNote=editTextNote.getText().toString().trim();
        String noteDate =formattedDate;

        Notes notes = new Notes("",noteTitle,noteNote,noteDate);
        DatabaseReference dbRef = db.getReference("NOTLAR");
        String key = dbRef.push().getKey();
        DatabaseReference dbRefYeni=db.getReference("NOTLAR/"+key);
        dbRefYeni.setValue(notes);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
