package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.DB.DBAdapter;

public class EditInfo extends AppCompatActivity {
    DBAdapter adapter;
    EditText  name, pass,phone ;
    private RadioGroup section;
    private int selectedSection;
    String emailFromIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        //-----------------toolbar----
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        TextView toolbarText = findViewById(R.id.toolbar_title);

        toolbar.setTitle("");
        toolbarText.setText("Edit information");
//-----------init------
        name = findViewById(R.id.edit_username);
        pass = findViewById(R.id.edit_password);
        phone =findViewById(R.id.edit_phone);

        section = findViewById(R.id.edit_radio);
        selectedSection = section.getCheckedRadioButtonId();
        ///--------database--------------
        emailFromIntent = getIntent().getStringExtra("email");

        adapter = new DBAdapter(this);
    }

    public void Edit(View view) {
        String nameInput = name.getText().toString();
        String phoneInput= phone.getText().toString();
        String passInput = pass.getText().toString();
        String ChozenSection = "";

        switch (selectedSection){
            case R.id.novel:
                ChozenSection = "novel";
                break;

            case R.id.science:
                ChozenSection = "science";
                break;
            case R.id.diaries:
                ChozenSection = "diaries";
                break;
            case R.id.Literary:
                ChozenSection = "Literary";
                break;
        }
        if (nameInput.isEmpty() && phoneInput.isEmpty() &&  passInput.isEmpty() ) {
            Toast.makeText(this, "Enter the values for all fields", Toast.LENGTH_SHORT).show();
        } else {
            adapter.updateData(emailFromIntent, nameInput, passInput,phoneInput,ChozenSection);

                Toast.makeText(this, "Insertion successful", Toast.LENGTH_SHORT).show();
                viewData(view);


        }
    }

    public void viewData(View view) {
        String[] data = adapter.getData( emailFromIntent);
        Intent intent = new Intent(this, ViewInfo.class);
        intent.putExtra("Data", data);
        startActivity(intent);
    }
}
