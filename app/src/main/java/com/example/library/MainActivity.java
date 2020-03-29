package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.DB.DBAdapter;

public class MainActivity extends AppCompatActivity {

    DBAdapter adapter;
    private EditText username, pass;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--------------toolbar--------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        TextView toolbarText = findViewById(R.id.toolbar_title);

        toolbar.setTitle("");
        toolbarText.setText("Log in");

        //---------------------database---------

        adapter = new DBAdapter(this);
        //-------------init------------------
        username = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Register.class));

            }
        });
    }

    public void logIn(View view) {
        String[] data = adapter.getData( username.getText().toString());
        String password = data[4];
        if (pass.getText().toString().equals(password)){
            Intent intent = new Intent(this, ViewInfo.class);
            intent.putExtra("Data", data);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Wrong password, try again", Toast.LENGTH_SHORT).show();
        }
    }
}
