package com.example.noteproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteproject.OnItem;
import com.example.noteproject.R;
import com.example.noteproject.main.NotesAdapter;
import com.example.noteproject.model.NoteModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotesFragment extends Fragment implements OnItem {
    private RecyclerView rvNotes;
    private NotesAdapter adapter;
    private FloatingActionButton btnOpedAddActivity;

    public NotesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NotesAdapter(getContext(), this);
    }

    private void initViews(View view) {
        btnOpedAddActivity = view.findViewById(R.id.btn_open_add_activity);
        btnOpedAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new AddNoteFragment());
                transaction.addToBackStack("AddNoteFragment");
                transaction.commit();
            }
        });
    }

    private void initNoteRecycler(View view) {
        rvNotes = view.findViewById(R.id.rv_notes);
        rvNotes.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notesfragment, container, false);
        initViews(view);
        initNoteRecycler(view);
        listenNote();

        return view;
    }

    private void listenNote() {
        getActivity().getSupportFragmentManager().setFragmentResultListener("note", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                    NoteModel model = new NoteModel(result.getString("title"), result.getString("description"), "");
                    adapter.addNote(model);
                }
        });
        getActivity().getSupportFragmentManager().setFragmentResultListener("changeNote", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                NoteModel model = new NoteModel(result.getString("changeTitle"),result.getString("changeDescription"), "");
                adapter.changeNote(result.getInt("position"), model);
            }
        });
    }

    @Override
    public void click(int position) {
        NoteModel model = adapter.getList().get(position);
        Bundle bundle = new Bundle();
        bundle.putString("changeTitle", model.getTitle());
        bundle.putString("changeDescription", model.getDescription());
        bundle.putInt("position", position);
        AddNoteFragment addNoteFragment = new AddNoteFragment();
        addNoteFragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, addNoteFragment);
        transaction.addToBackStack("AddNoteFragment");
        transaction.commit();
    }
}
