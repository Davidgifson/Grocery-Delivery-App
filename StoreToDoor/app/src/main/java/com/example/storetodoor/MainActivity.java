package com.example.storetodoor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText id ,password;
    Button login,register;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register=findViewById(R.id.reg);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String id_s=id.getText().toString();
                final String pass_s=password.getText().toString();
//                firebaseDatabase= FirebaseDatabase.getInstance();
//                reference=firebaseDatabase.getReference("userdata");
                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("userdata");

                Query check_id=databaseReference.orderByChild("name").equalTo(id_s);
                check_id.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
//                            id.setError(null);
//                            id.setEnabled(false);
                            String passcheck=dataSnapshot.child(id_s).child("password").getValue(String.class);
                            if (passcheck.equals(pass_s))
                            {
                                Toast.makeText(getApplicationContext(), "login sucessfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, store.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), "Id doesnot exist ", Toast.LENGTH_SHORT).show();


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                //if (id.getText().toString().equals("Biren") && password.getText().toString().equals("biren12345")) {


                }



        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, com.example.storetodoor.register.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
