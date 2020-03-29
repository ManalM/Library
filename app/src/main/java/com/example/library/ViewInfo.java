package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class ViewInfo extends AppCompatActivity implements  PopupMenu.OnMenuItemClickListener {
    String[] data;
    TextView name, email, phone, section;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info);
        //-----------------toolbar and menu----
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        TextView toolbarText = findViewById(R.id.toolbar_title);

        toolbar.setTitle("");
        toolbarText.setText("View Information");
        ImageView menu = findViewById(R.id.menu_btn);
        menu.setVisibility(View.VISIBLE);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });

        //-------------------------------------------
        email = findViewById(R.id.view_email);
        name = findViewById(R.id.view_name);

        phone = findViewById(R.id.view_phone);

        section = findViewById(R.id.view_section);

        //-------------------------------------------
        data = getIntent().getStringArrayExtra("Data");
        if (data != null) {
            name.setText(data[1]);
            section.setText(data[2]);
            phone.setText(data[3]);
            email.setText(data[0]);


        } else {
            Toast.makeText(ViewInfo.this, "no data for the user", Toast.LENGTH_SHORT).show();
        }

    }

    public void showPopup(View v) {
      PopupMenu popup = new PopupMenu(ViewInfo.this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());
        popup.setOnMenuItemClickListener(this);

        popup.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(ViewInfo.this, EditInfo.class);
                intent.putExtra("email", data[0]);
                startActivity(intent);
                return true;

            case R.id.log_out:
                Intent intent1 = new Intent(ViewInfo.this, MainActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }


}
