package com.example.mitecse3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private Button signout;
    private FirebaseAuth mAuth1;
    private FirebaseUser muser1;
    private Intent intent1;
    private FirebaseDatabase db1;
    private DatabaseReference myRef1;
    private ListView ld1;
    private ArrayList<String> arrayList= new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        signout = (Button) findViewById(R.id.signout);
        ld1 = (ListView) findViewById(R.id.db_list);
         db1= FirebaseDatabase.getInstance();

        myRef1 = db1.getReference("Notices1");
        mAuth1 = FirebaseAuth.getInstance();
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        arrayList.add("test10");
        arrayList.add("test11");
        myRef1.push().setValue("N4");
        ld1.setAdapter(arrayAdapter);
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                    arrayList.add(snapshot.getValue().toString());

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        intent1 = new Intent(Main2Activity.this,MainActivity.class);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth1.signOut();
                startActivity(intent1);
                 //muser1= mAuth1.getCurrentUser();
                //Toast.makeText(Main2Activity.this,muser1,Toast.LENGTH_LONG).show();
            }
        });

    }
}
