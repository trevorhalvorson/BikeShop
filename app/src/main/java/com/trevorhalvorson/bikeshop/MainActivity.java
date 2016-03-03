package com.trevorhalvorson.bikeshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = (EditText) findViewById(R.id.username_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);

        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidLogin()) {
                    Intent intent = new Intent(MainActivity.this, BikeListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a username and password.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean isValidLogin() {
        return (!this.usernameEditText.getText().toString().isEmpty() &&
                !this.passwordEditText.getText().toString().isEmpty());
    }
}
