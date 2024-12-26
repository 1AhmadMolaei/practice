package com.example.library_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Database_Helper db;

    public void insertBook(String title, String Author, String Publisher){
        long id = db.addBook(new Book(title, Publisher));
        BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setBookId(id);
        bookAuthor.setAuthorName(Author);
        db.addBookAuthor(bookAuthor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database_Helper(this);

        insertBook("The Great Gatsby", "Scribner", "F. Scott Fitzgerald");
        insertBook("To Kill a Mockingbird", "J.B. Lippincott & Co.", "Harper Lee");
        insertBook("1984", "Secker & Warburg", "George Orwell");
        insertBook("Pride and Prejudice", "T. Egerton", "Jane Austen");
        insertBook("The Catcher in the Rye", "Little, Brown and Company", "J.D. Salinger");
        insertBook("The Hobbit", "Allen & Unwin", "J.R.R. Tolkien");
        insertBook("The Lord of the Rings", "Allen & Unwin", "J.R.R. Tolkien");
        insertBook("Animal Farm", "Secker & Warburg", "George Orwell");
        insertBook("Brave New World", "Chatto & Windus", "Aldous Huxley");

        Button buttonAddBook = findViewById(R.id.buttonAddBook);
        Button buttonViewBooks = findViewById(R.id.buttonViewBooks);
        Button buttonAddMember = findViewById(R.id.buttonAddMember);
        Button buttonAddPublisher = findViewById(R.id.buttonAddPublisher);

        Button buttonAddBranch = findViewById(R.id.buttonAddBranch);

        buttonAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BookAddActivity.class));
            }
        });

        buttonViewBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BookViewActivity.class));
            }
        });

        buttonAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MemberAddActivity.class));
            }
        });

        buttonAddPublisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PublisherActivity.class));
            }
        });



        buttonAddBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BranchAddActivity.class));
            }
        });
    }
}
