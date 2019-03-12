package com.example.ugurcan.mynoteapp;

import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UpdateDeleteActivity extends AppCompatActivity {
    private Date c;
    private SimpleDateFormat df;
    private String formattedDate;
    private Toolbar toolbar;
    private EditText editTextTitle,editTextNote;
    private Notes notes;
    private FirebaseDatabase db;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        c = Calendar.getInstance().getTime();
        df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c);

        toolbar=findViewById(R.id.toolbar);
        editTextTitle=findViewById(R.id.editTextTitleUpdateDelete);
        editTextNote=findViewById(R.id.editTextNoteUpdateDelete);

        db = FirebaseDatabase.getInstance();
        myRef = db.getReference("NOTLAR");


        toolbar.setTitle("Not İşlemleri");
        setSupportActionBar(toolbar);
        if (getIntent().getSerializableExtra("object")!=null){
            notes=(Notes)getIntent().getSerializableExtra("object");
        }
        editTextTitle.setText(notes.getNoteTitle());
        editTextNote.setText(notes.getNote());
    }

    // Tool bar menü için
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Tool bar menü için
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_update:

                String noteTitle=editTextTitle.getText().toString().trim();
                String noteNote=editTextNote.getText().toString().trim();
                String noteDate =formattedDate;
                Map<String,Object> infoMap = new HashMap<>();
                infoMap.put("noteTitle",noteTitle);
                infoMap.put("note",noteNote);
                infoMap.put("noteDate",noteDate);
                myRef.child(notes.getNoteId()).updateChildren(infoMap);
                Toast.makeText(getApplicationContext(),"Güncellendi",Toast.LENGTH_SHORT).show();



                startActivity(new Intent(UpdateDeleteActivity.this,MainActivity.class));
                finish();
            case R.id.action_delete:
                    Snackbar.make(toolbar,"Notu Silmek İstediğinize Eminmisiniz ?", BaseTransientBottomBar.LENGTH_LONG)
                            .setAction("Evet", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    myRef.child(notes.getNoteId()).removeValue();
                                    startActivity(new Intent(UpdateDeleteActivity.this,MainActivity.class));
                                    Toast.makeText(getApplicationContext(),"Silindi",Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }).show();
                   return true;
            default:
                    return false;
        }

    }
}
