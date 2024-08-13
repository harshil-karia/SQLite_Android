package com.example.db_example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText name, loc, designation;
    Button saveBtn,viewBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        name = findViewById(R.id.txtName);
        loc = findViewById(R.id.txtLocation);
        designation = findViewById(R.id.txtDesignation);
        saveBtn = findViewById(R.id.btnSave);
        viewBtn = findViewById(R.id.btnView);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString()+"\n";
                String location = loc.getText().toString();
                String strDesignation = designation.getText().toString();
                try {
                    DbHandler dbHandler = new DbHandler(MainActivity.this);
                    dbHandler.insertUserDetails(username,location,strDesignation);
                } catch (Exception exception){
                    Toast.makeText(MainActivity.this, "DB Error", Toast.LENGTH_SHORT).show();
                }
                intent = new Intent(MainActivity.this,Details_Activity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Details Inserted Successfully",Toast.LENGTH_SHORT).show();
            }
        });
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,Details_Activity.class);
                startActivity(intent);
            }
        });
    }
}