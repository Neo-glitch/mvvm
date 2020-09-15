package com.neo.mvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    // constants
    public static final String EXTRA_ID = "com.neo.mvvm.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.neo.mvvm.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.neo.mvvm.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.neo.mvvm.EXTRA_PRIORITY";


    private EditText editTextTitle, editTextDescription;
    private NumberPicker numberPickerPriority;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);

        // sets min and max of number picker
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(11);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else{
            setTitle("Add Note");                                   // used to set the label of this activity in actionBar
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_add_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();

        // trim removes whitespaces at beginning and end of value and ret the value
        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "please insert a title and description", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_DESCRIPTION, description);
        intent.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        // true only for update cases
        if(id != -1){
            intent.putExtra(EXTRA_ID, id);
        }

        // sets the result code to be RESULT_OK only when we call this method
        setResult(RESULT_OK, intent);
        finish();
    }
}