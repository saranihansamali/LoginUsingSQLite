package com.intpro.sqllitelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {

    EditText id, pass;
    Button sign;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        dbHelper = new DBHelper(this);

        id = findViewById(R.id.index);
        pass = findViewById(R.id.password);
        sign = findViewById(R.id.signIn);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String index = id.getText().toString().trim();
                String password = pass.getText().toString().trim();
                Boolean res = dbHelper.checkUser(index, password);

                if(res == true)
                {
                    Toast.makeText(SignIn.this, "Sign-in Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignIn.this, Home.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(SignIn.this, "Sign-in Unsuccessful", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
