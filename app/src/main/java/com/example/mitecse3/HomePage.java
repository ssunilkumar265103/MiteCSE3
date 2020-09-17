package com.example.mitecse3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomePage extends AppCompatActivity {

    private Button add_msg;
    private Button view_msg;
    private EditText cirular_msg;
    private FirebaseDatabase db2;
    private DatabaseReference myRef2;
    private Intent intent_view_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        add_msg =(Button) findViewById(R.id.Add_Msg);
        view_msg = (Button) findViewById(R.id.View_Msg);
        cirular_msg =(EditText) findViewById(R.id.Circular_Msg_Editbox);
        db2 = FirebaseDatabase.getInstance();
        myRef2 = db2.getReference("Notices3");
        intent_view_msg = new Intent(HomePage.this,ViewCircularMsg.class);
        add_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = cirular_msg.getText().toString();
                myRef2.push().setValue(msg);
                Toast.makeText(HomePage.this,"Added",Toast.LENGTH_LONG).show();
            }
        });
        view_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_view_msg);

            }
        });
    }
}
