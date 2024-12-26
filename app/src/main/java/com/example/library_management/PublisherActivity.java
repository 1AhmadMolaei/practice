package com.example.library_management;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PublisherActivity extends AppCompatActivity {

    EditText editTextPublisherName, editTextPublisherAddress, editTextPublisherPhone;
    Button buttonAddPublisher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_publisher);

        editTextPublisherName = findViewById(R.id.editTextAuthorName);
        editTextPublisherAddress = findViewById(R.id.editTextPublisherAddress);
        editTextPublisherPhone = findViewById(R.id.editTextPublisherPhone);
        buttonAddPublisher = findViewById(R.id.buttonAddPublisher);

        // Implement functionality to add a publisher to the database
        buttonAddPublisher.setOnClickListener(v -> {
            String publisherName = editTextPublisherName.getText().toString();
            String publisherAddress = editTextPublisherAddress.getText().toString();
            String publisherPhone = editTextPublisherPhone.getText().toString();

            Publisher publisher = new Publisher(publisherName, publisherAddress, publisherPhone);
            Database_Helper databaseHelper = new Database_Helper(PublisherActivity.this);
            databaseHelper.addPublisher(publisher);

            finish();
        });
    }

}
