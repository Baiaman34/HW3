package com.example.noteproject.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteproject.OnItem;
import com.example.noteproject.R;
import com.example.noteproject.fragment.AddNoteFragment;
import com.example.noteproject.model.NoteModel;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    private LayoutInflater inflater;
    private List<NoteModel> list = new ArrayList<>();
    private OnItem onItem;

    public NotesAdapter(Context context, OnItem onItem) {
        this.inflater = LayoutInflater.from(context);
        this.onItem = onItem;
    }

    public void addNote(NoteModel model) {
        list.add(model);
        notifyDataSetChanged();
    }

    public List<NoteModel> getList() {
        return list;
    }

    public void changeNote(int position, NoteModel model) {
        list.set(position, model);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_notes, parent, false);
        return new NotesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {
        holder.txtTitle.setText(list.get(position).getTitle());
        holder.txtDescription.setText(list.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = holder.txtTitle.getText().toString();
                String description = holder.txtDescription.getText().toString();
                onItem.click(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDescription;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_item_title);
            txtDescription = itemView.findViewById(R.id.txt_item_description);
        }
    }
}
