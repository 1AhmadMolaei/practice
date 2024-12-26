package com.example.library_management;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BranchAddActivity extends AppCompatActivity {

    EditText editTextBranchName, editTextBranchAddress;
    Button buttonAddBranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);

        editTextBranchName = findViewById(R.id.editTextBranchName);
        editTextBranchAddress = findViewById(R.id.editTextBranchAddress);
        buttonAddBranch = findViewById(R.id.buttonAddBranch);

        // Implement functionality to add a branch to the database
        buttonAddBranch.setOnClickListener(v -> {
            String branchName = editTextBranchName.getText().toString();
            String branchAddress = editTextBranchAddress.getText().toString();

            Branch branch = new Branch(branchName, branchAddress);
            Database_Helper databaseHelper = new Database_Helper(BranchAddActivity.this);
            databaseHelper.addBranch(branch);

            Toast.makeText(this, "Branch added successfully", Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}
