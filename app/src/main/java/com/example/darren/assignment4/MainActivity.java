package com.example.darren.assignment4;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        final Button findSynonym = findViewById(R.id.find_synonym);
        final Button enterValues = findViewById(R.id.enter_values);
        final EditText wordInput = findViewById(R.id.input_word);
        findSynonym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = wordInput.getText().toString().trim();
                wordInput.setText("");
                wordInput.clearFocus();
                Intent intent = new Intent(getApplicationContext(), SynonymActivity.class);
                intent.putExtra("word", word);
                startActivity(intent);
            }
        });
        enterValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = wordInput.getText().toString().trim();
                wordInput.setText("");
                wordInput.clearFocus();
                Intent intent = new Intent(getApplicationContext(), EnterValuesActivity.class);
                intent.putExtra("word", word);
                startActivity(intent);
            }
        });
    }
}
