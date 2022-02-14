package com.example.storetodoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    Button regd;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    EditText name,phone,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.mail);
        password=findViewById(R.id.pass);


        regd=findViewById(R.id.regd);


        regd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase=FirebaseDatabase.getInstance();
                reference=firebaseDatabase.getReference("userdata");
                String name_s=name.getText().toString();
                String phone_s=phone.getText().toString();
                String email_s=email.getText().toString();
                String password_s=password.getText().toString();

                storingdata storingdatass= new storingdata(name_s,phone_s,email_s,password_s);

                reference.child(name_s).setValue(storingdatass);
                Toast.makeText(getApplicationContext(), "sucessfully register", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(register.this,MainActivity.class);
                startActivity(intent);
                finish();



            }
        });

    }
}