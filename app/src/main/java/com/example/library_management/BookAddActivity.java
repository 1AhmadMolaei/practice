package com.example.library_management;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookAddActivity extends AppCompatActivity {

    EditText editTextBookTitle, editTextPublisherName, editTextAuthorName;
    Button buttonAddBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        editTextBookTitle = findViewById(R.id.editTextBookTitle);
        editTextPublisherName = findViewById(R.id.editTextAuthorName);
        editTextAuthorName = findViewById(R.id.editTextAuthorName);

        buttonAddBook = findViewById(R.id.buttonAddBook);

        buttonAddBook.setOnClickListener(v -> addBookToDatabase());
    }

    private void addBookToDatabase() {
        String title = editTextBookTitle.getText().toString();
        String publisherName = editTextPublisherName.getText().toString();
        String authorName = editTextAuthorName.getText().toString();

        // Create a new Book object
        Book book = new Book(title, publisherName);

        // Add the book to the database using your Database_Helper class
        Database_Helper dbHelper = new Database_Helper(this);
        long bookId = dbHelper.addBook(book);

        BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setBookId(bookId);
        bookAuthor.setAuthorName(authorName);
        dbHelper.addBookAuthor(bookAuthor);
        // Optionally, you can display a message indicating that the book has been added
        Toast.makeText(this, "Book added successfully", Toast.LENGTH_SHORT).show();

        // Finish the activity
        finish();
    }
}
