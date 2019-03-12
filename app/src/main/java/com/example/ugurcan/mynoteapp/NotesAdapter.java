package com.example.ugurcan.mynoteapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.HolderNoteCardDesign> {
    private Context context;
    private List<Notes> notesList;

    public NotesAdapter(Context context, List<Notes> notesList) {
        this.context = context;
        this.notesList = notesList;
    }



    @Override
    public void onBindViewHolder(@NonNull HolderNoteCardDesign holder, int i) {

       final Notes notes = notesList.get(i);
        holder.textViewTitle.setText(notes.getNoteTitle());
        holder.textViewDate.setText(String.valueOf(notes.getNoteDate()));
        holder.note_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateDeleteActivity.class);
                intent.putExtra("object",notes);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class  HolderNoteCardDesign extends RecyclerView.ViewHolder{
        private CardView note_card;
        private TextView textViewTitle;
        private TextView textViewDate;

        public HolderNoteCardDesign(@NonNull View itemView) {
            super(itemView);
            note_card=itemView.findViewById(R.id.note_card);
            textViewTitle=itemView.findViewById(R.id.textViewTitleCard);
            textViewDate=itemView.findViewById(R.id.textViewDateCard);
        }
    }
    @NonNull
    @Override
    public HolderNoteCardDesign onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_card_design,viewGroup,false);
        return new HolderNoteCardDesign(view);
    }

}
