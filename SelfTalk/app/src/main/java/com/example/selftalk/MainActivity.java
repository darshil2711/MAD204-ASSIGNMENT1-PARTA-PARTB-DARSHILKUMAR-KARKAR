/**
 * Course: MAD204 – Assignment 1 Part B
 * Student: Darshilkumar Karkar (A00203357)
 * Date: OCT 2025
 * Description: MainActivity for SelfTalk app – collects user input, validates it,
 *              and displays an introduction message or error.
 */

package com.example.selftalk;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editName, editAge, editProgram;
    private Button btnIntroduce;
    private TextView textOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect UI widgets
        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editProgram = findViewById(R.id.editProgram);
        btnIntroduce = findViewById(R.id.btnIntroduce);
        textOutput = findViewById(R.id.textOutput);

        // Button click listener
        btnIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleIntroduction();
            }
        });
    }

    /** Handles input validation and message display. */
    private void handleIntroduction() {
        String name = editName.getText().toString().trim();
        String ageText = editAge.getText().toString().trim();
        String program = editProgram.getText().toString().trim();

        // Validate
        if (name.isEmpty() || ageText.isEmpty() || program.isEmpty()) {
            textOutput.setText("❌ Please fill out all fields.");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
            if (age <= 0) {
                textOutput.setText("⚠️ Age must be greater than 0.");
                return;
            }
        } catch (NumberFormatException e) {
            textOutput.setText("⚠️ Invalid age format.");
            return;
        }

        // Display introduction
        String message = "👋 Hi! My name is " + name + ". I am " + age +
                " years old and currently enrolled in the " + program + " program.";
        textOutput.setText(message);
    }
}
