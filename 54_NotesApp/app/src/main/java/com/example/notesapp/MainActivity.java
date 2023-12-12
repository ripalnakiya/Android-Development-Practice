package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.notesapp.repository.room.Note;
import com.example.notesapp.viewmodel.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity.this";
    private NoteViewModel viewModel;
    MyDialogManager myDialogManager = MyDialogManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton faButton = findViewById(R.id.faButton);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        viewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
                Log.d(TAG, "onChanged: Notes Loaded");
            }
        });

        faButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = myDialogManager.getAddDialog(MainActivity.this);
                dialog.show();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.deleteAllMenu) {
                    viewModel.deleteAll();
                    Toast.makeText(MainActivity.this, "All Notes Deleted", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    OnNoteListener onNoteListener = new OnNoteListener() {
        @Override
        public void onNoteAdd(Note note) {
            viewModel.insert(note);
            Toast.makeText(MainActivity.this, "Note Added", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onNoteDelete(Note note) {
            viewModel.delete(note);
            Toast.makeText(MainActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onNoteUpdate(Note note) {
            viewModel.update(note);
            Toast.makeText(MainActivity.this, "Note Updated", Toast.LENGTH_SHORT).show();
        }
    };
}






