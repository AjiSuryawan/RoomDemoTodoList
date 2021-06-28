package com.ashish.roomdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.ashish.roomdemo.R;
import com.ashish.roomdemo.constants.Constants;
import com.ashish.roomdemo.database.AppDatabase;
import com.ashish.roomdemo.database.AppExecutors;
import com.ashish.roomdemo.model.NoteModel;

public class EditActivity extends AppCompatActivity {
    EditText name, note;
    Button button;
    int mNoteId;
    Intent intent;
    private AppDatabase mDb;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initViews();
        mDb = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Note_Id)) {
            button.setText("Update");

            mNoteId = intent.getIntExtra(Constants.UPDATE_Note_Id, -1);

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    NoteModel noteModel = mDb.personDao().loadNoteById(mNoteId);
                    populateUI(noteModel);
                }
            });


        }

    }

    private void populateUI(NoteModel noteModel) {

        if (noteModel == null) {
            return;
        }

        name.setText(noteModel.getName());
        note.setText(noteModel.getEmail());
    }

    private void initViews() {
        name = findViewById(R.id.edit_name);
        note = findViewById(R.id.edit_note);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveButtonClicked();
            }
        });
    }

    public void onSaveButtonClicked() {
        final NoteModel note = new NoteModel(
                name.getText().toString(),
                this.note.getText().toString());

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (!intent.hasExtra(Constants.UPDATE_Note_Id)) {
                    mDb.personDao().insertNote(note);
                } else {
                    note.setId(mNoteId);
                    mDb.personDao().updateNote(note);
                }
                finish();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
