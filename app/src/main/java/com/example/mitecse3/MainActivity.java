package com.example.mitecse3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private EditText email;
    private EditText password;
    private Button login;
    private Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email =(EditText)findViewById(R.id.email);
        password =(EditText) findViewById(R.id.password);
        login =(Button) findViewById(R.id.Login);
        signup =(Button) findViewById(R.id.button2);
    mAuth = FirebaseAuth.getInstance();
    database = FirebaseDatabase.getInstance();
    myRef = database.getReference("message");
    myRef.setValue("Hello sunil");

    login.setOnClickListener(new View.OnClickListener() {
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        @Override
        public void onClick(View v) {
            String emailstring=email.getText().toString();
            String pwd = password.getText().toString();
            if(emailstring!=null)
                mAuth.signInWithEmailAndPassword(emailstring,pwd);
            startActivity(intent);
        }
    });
    signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String newuseremail=email.getText().toString();
            String newuserpwd=password.getText().toString();
            mAuth.createUserWithEmailAndPassword(newuseremail,newuserpwd);
        }
    });
    }
}

