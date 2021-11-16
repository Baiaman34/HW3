package com.example.noteproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.noteproject.OnItem;
import com.example.noteproject.R;

public class AddNoteFragment extends Fragment {

    private EditText etTitle, etDescription;
    private Button btnSave;
    private OnItem item;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AddNoteFragment() {

    }

    public static AddNoteFragment newInstance(String param1, String param2) {
        AddNoteFragment fragment = new AddNoteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void chekIsEdit() {
        Bundle bundle = new Bundle();
        if (getArguments() != null) {
                etTitle.setText(getArguments().getString("changeTitle"));
                etDescription.setText(getArguments().getString("changeDescription"));
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String changeTitle = etTitle.getText().toString();
                        String changeDescription = etDescription.getText().toString();
                        int position = getArguments().getInt("position");
                        bundle.putString("changeTitle", changeTitle);
                        bundle.putString("changeDescription", changeDescription);
                        bundle.putInt("position", position);
                        getActivity().getSupportFragmentManager().setFragmentResult("changeNote", bundle);
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                });
        } else {
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = etTitle.getText().toString();
                    String description = etDescription.getText().toString();
                    bundle.putString("title", title);
                    bundle.putString("description", description);
                    getActivity().getSupportFragmentManager().setFragmentResult("note", bundle);
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            });
        }
    }

    public void initViews(View view) {
        etTitle = view.findViewById(R.id.et_title);
        etDescription = view.findViewById(R.id.et_description);
        btnSave = view.findViewById(R.id.btn_save);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);
        initViews(view);
        chekIsEdit();
        return view;
    }
}