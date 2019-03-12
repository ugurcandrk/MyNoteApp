package com.example.ugurcan.mynoteapp;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button buttonAddNote;
    private ArrayList<Notes> notesArrayList;
    private NotesAdapter notesAdapter;
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance();


        recyclerView = findViewById(R.id.recyclerView);
        buttonAddNote = findViewById(R.id.btnAddNote);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notesArrayList = new ArrayList<>();
        notesAdapter = new NotesAdapter(this, notesArrayList);
        recyclerView.setAdapter(notesAdapter);

       


        bringAllNotes();
    }

    public void AddNoteActivity(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
        finish();

    }

    public void bringAllNotes() {
        DatabaseReference myRef = db.getReference("NOTLAR");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                notesArrayList.clear();

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Notes not = d.getValue(Notes.class);
                    not.setNoteId(d.getKey());
                    notesArrayList.add(not);
                }
                notesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("d", "hata", databaseError.toException());
            }
        });
    }
}
