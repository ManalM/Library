package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.DB.DBAdapter;

public class Register extends AppCompatActivity {
    DBAdapter adapter;
    EditText email, name, pass, confirmPass, phone ;
    private RadioGroup section;
    private int selectedSection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //-----------------toolbar----
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        TextView toolbarText = findViewById(R.id.toolbar_title);

        toolbar.setTitle("");
        toolbarText.setText("Register");
//-----------init------
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        pass = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirm_password);
        phone =findViewById(R.id.phone);

        section = findViewById(R.id.radio);
        selectedSection = section.getCheckedRadioButtonId();
       // selectedGender = (RadioButton) findViewById(selectedSection);
        ///--------database--------------

        adapter = new DBAdapter(this);
    }

    public void Register(View view) {
        String nameInput = name.getText().toString();
        String emailInput = email.getText().toString();
        String phoneInput= phone.getText().toString();
        String passInput = pass.getText().toString();
        String confirmPassInput = confirmPass.getText().toString();
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
        if (nameInput.isEmpty() && emailInput.isEmpty() && phoneInput.isEmpty() &&  passInput.isEmpty() && confirmPassInput.isEmpty()) {
            Toast.makeText(this, "Enter the values for all fields", Toast.LENGTH_SHORT).show();
        } else {
            // long id =
            if (passInput.equals(confirmPassInput)) {
                adapter.insertData(nameInput, emailInput, passInput,phoneInput,ChozenSection);

                Toast.makeText(this, "Insertion successful", Toast.LENGTH_SHORT).show();
                viewData(view);

            } else {
                Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void viewData(View view) {
        String[] data = adapter.getData( email.getText().toString());
        Intent intent = new Intent(this, ViewInfo.class);
        intent.putExtra("Data", data);
        startActivity(intent);
    }
}
