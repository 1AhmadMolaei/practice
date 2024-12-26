package com.example.library_management;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MemberAddActivity extends AppCompatActivity {

    EditText editTextMemberName, editTextMemberAddress, editTextMemberPhone;
    Button buttonAddMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        editTextMemberName = findViewById(R.id.editTextMemberName);
        editTextMemberAddress = findViewById(R.id.editTextMemberAddress);
        editTextMemberPhone = findViewById(R.id.editTextMemberPhone);
        buttonAddMember = findViewById(R.id.buttonAddMember);

        // Implement functionality to add a member to the database
        buttonAddMember.setOnClickListener(v -> {
            String memberName = editTextMemberName.getText().toString();
            String memberAddress = editTextMemberAddress.getText().toString();
            String memberPhone = editTextMemberPhone.getText().toString();

            Member member = new Member(memberName, memberAddress, memberPhone);
            Database_Helper databaseHelper = new Database_Helper(MemberAddActivity.this);
            databaseHelper.addMember(member);

            Toast.makeText(this, "Member added successfully", Toast.LENGTH_SHORT).show();
            finish();
        });

    }
}
