package com.example.library_management;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

public class Database_Helper extends SQLiteOpenHelper {

    // Database name and version
    private static final String DATABASE_NAME = "library.db";
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public Database_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        db.execSQL("CREATE TABLE Book (BOOK_ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE VARCHAR(40), PUBLISHER_NAME VARCHAR(40))");
        db.execSQL("CREATE TABLE Publisher (NAME VARCHAR(20), ADDRESS VARCHAR(30), PHONE VARCHAR(10), PRIMARY KEY (NAME))");
        db.execSQL("CREATE TABLE Branch (BRANCH_ID INTEGER PRIMARY KEY AUTOINCREMENT, BRANCH_NAME VARCHAR(20), ADDRESS VARCHAR(30))");
        db.execSQL("CREATE TABLE Member (CARD_NO INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(20), ADDRESS VARCHAR(30), PHONE VARCHAR(10), UNPAID_DUES REAL)");
        db.execSQL("CREATE TABLE Book_Author (BOOK_ID VARCHAR(13), AUTHOR_NAME VARCHAR(20), PRIMARY KEY(BOOK_ID, AUTHOR_NAME), FOREIGN KEY(BOOK_ID) REFERENCES Book)");
        db.execSQL("CREATE TABLE Book_Copy (BOOK_ID VARCHAR(13), BRANCH_ID VARCHAR(5), ACCESS_NO VARCHAR(5), PRIMARY KEY(ACCESS_NO, BRANCH_ID), FOREIGN KEY(BOOK_ID) REFERENCES Book, FOREIGN KEY(BRANCH_ID) REFERENCES Branch)");
        db.execSQL("CREATE TABLE Book_Loan (ACCESS_NO VARCHAR(5), BRANCH_ID VARCHAR(5), CARD_NO VARCHAR(10), DATE_OUT DATE, DATE_DUE DATE, DATE_RETURNED DATE, PRIMARY KEY(ACCESS_NO, BRANCH_ID, CARD_NO, DATE_OUT), FOREIGN KEY(ACCESS_NO, BRANCH_ID) REFERENCES Book_Copy, FOREIGN KEY(CARD_NO) REFERENCES Member, FOREIGN KEY(BRANCH_ID) REFERENCES Branch)");


        // Insert sample data
//
    }



    // Method called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing tables and recreate them
        db.execSQL("DROP TABLE IF EXISTS Book_Loan");
        db.execSQL("DROP TABLE IF EXISTS Book_Copy");
        db.execSQL("DROP TABLE IF EXISTS Book_Author");
        db.execSQL("DROP TABLE IF EXISTS Member");
        db.execSQL("DROP TABLE IF EXISTS Branch");
        db.execSQL("DROP TABLE IF EXISTS Publisher");
        db.execSQL("DROP TABLE IF EXISTS Book");
        onCreate(db);
    }

    // CRUD operations for Book table

    // Add a new book to the database
    // Add a new book to the database and return the new book's ID
    public long addBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TITLE", book.getTitle());
        values.put("PUBLISHER_NAME", book.getPublisherName());
        long id = db.insert("Book", null, values);
        db.close();
        return id;
    }

    // Retrieve all books from the database
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String selectQuery = "SELECT * FROM Book";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setId(cursor.getString(0));
                book.setTitle(cursor.getString(1));
                book.setPublisherName(cursor.getString(2));
                books.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return books;
    }

    // Retrieve a specific book by its ID
    public Book getBookById(String bookId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("Book", new String[]{"BOOK_ID", "TITLE", "PUBLISHER_NAME"}, "BOOK_ID = ?",
                new String[]{bookId}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Book book = new Book(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return book;
    }

    // Update book details
    public int updateBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TITLE", book.getTitle());
        values.put("PUBLISHER_NAME", book.getPublisherName());
        return db.update("Book", values, "BOOK_ID = ?", new String[]{book.getId()});
    }

    // Delete a book
    public void deleteBook(String bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Book", "BOOK_ID = ?", new String[]{bookId});
        db.close();
    }

    public List<Book> getBooks(){
        List<Book> books = new ArrayList<>();
        String selectQuery = "SELECT * FROM Book";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setId(cursor.getString(0));
                book.setTitle(cursor.getString(1));
                book.setPublisherName(cursor.getString(2));
                books.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return books;
    }
    // CRUD operations for Publisher table

    // Add a new publisher to the database
    public void addPublisher(Publisher publisher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME", publisher.getName());
        values.put("ADDRESS", publisher.getAddress());
        values.put("PHONE", publisher.getPhone());
        db.insert("Publisher", null, values);
        db.close();
    }

    // Retrieve all publishers from the database
    public List<Publisher> getAllPublishers() {
        List<Publisher> publishers = new ArrayList<>();
        String selectQuery = "SELECT * FROM Publisher";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Publisher publisher = new Publisher();
                publisher.setName(cursor.getString(0));
                publisher.setAddress(cursor.getString(1));
                publisher.setPhone(cursor.getString(2));
                publishers.add(publisher);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return publishers;
    }

    // Retrieve a specific publisher by its name
    public Publisher getPublisherByName(String publisherName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("Publisher", new String[]{"NAME", "ADDRESS", "PHONE"}, "NAME = ?",
                new String[]{publisherName}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Publisher publisher = new Publisher(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return publisher;
    }

    // Update publisher details
    public int updatePublisher(Publisher publisher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ADDRESS", publisher.getAddress());
        values.put("PHONE", publisher.getPhone());
        return db.update("Publisher", values, "NAME = ?", new String[]{publisher.getName()});
    }

    // Delete a publisher
    public void deletePublisher(String publisherName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Publisher", "NAME = ?", new String[]{publisherName});
        db.close();
    }

    // CRUD operations for Member table

    // Add a new member to the database
    public void addMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME", member.getName());
        values.put("ADDRESS", member.getAddress());
        values.put("PHONE", member.getPhone());
//        values.put("UNPAID_DUES", member.getUnpaidDues());
        db.insert("Member", null, values);
        db.close();
    }

    // Retrieve all members from the database
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String selectQuery = "SELECT * FROM Member";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Member member = new Member();

                member.setName(cursor.getString(0));
                member.setAddress(cursor.getString(1));
                member.setPhone(cursor.getString(2));

                members.add(member);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return members;
    }

    // Retrieve a specific member by their card number
    public Member getMemberByCardNo(String cardNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("Member", new String[]{"CARD_NO", "NAME", "ADDRESS", "PHONE", "UNPAID_DUES"}, "CARD_NO = ?",
                new String[]{cardNo}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Member member = new Member(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return member;
    }

    // Update member details
    public int updateMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME", member.getName());
        values.put("ADDRESS", member.getAddress());
        values.put("PHONE", member.getPhone());
        values.put("UNPAID_DUES", member.getUnpaidDues());
        return 0;
    }

    // Delete a member
    public void deleteMember(String cardNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Member", "CARD_NO = ?", new String[]{cardNo});
        db.close();
    }

// CRUD operations for Author table

    // Add a new author to the database
    public void addAuthor(Author author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("AUTHOR_NAME", author.getName());
        db.insert("Author", null, values);
        db.close();
    }

    // Retrieve all authors from the database
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        String selectQuery = "SELECT * FROM Author";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Author author = new Author();
                author.setName(cursor.getString(0));
                authors.add(author);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return authors;
    }

    // Retrieve a specific author by their name
    public Author getAuthorByName(String authorName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("Author", new String[]{"AUTHOR_NAME"}, "AUTHOR_NAME = ?",
                new String[]{authorName}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Author author = new Author(cursor.getString(0));
        cursor.close();
        db.close();
        return author;
    }

    // Delete an author
    public void deleteAuthor(String authorName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Author", "AUTHOR_NAME = ?", new String[]{authorName});
        db.close();
    }

// CRUD operations for Branch table

    // Add a new branch to the database
    public void addBranch(Branch branch) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("BRANCH_ID", branch.getId());
        values.put("BRANCH_NAME", branch.getName());
        values.put("ADDRESS", branch.getAddress());
        db.insert("Branch", null, values);
        db.close();
    }

    // Retrieve all branches from the database
    public List<Branch> getAllBranches() {
        List<Branch> branches = new ArrayList<>();
        String selectQuery = "SELECT * FROM Branch";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Branch branch = new Branch();
                branch.setId(cursor.getString(0));
                branch.setName(cursor.getString(1));
                branch.setAddress(cursor.getString(2));
                branches.add(branch);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return branches;
    }

    // Retrieve a specific branch by its ID
    public Branch getBranchById(String branchId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("Branch", new String[]{"BRANCH_ID", "BRANCH_NAME", "ADDRESS"}, "BRANCH_ID = ?",
                new String[]{branchId}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Branch branch = new Branch(cursor.getString(0), cursor.getString(1));
        cursor.close();
        db.close();
        return branch;
    }

    // Update branch details
    public int updateBranch(Branch branch) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("BRANCH_NAME", branch.getName());
        values.put("ADDRESS", branch.getAddress());
        return db.update("Branch", values, "BRANCH_ID = ?", new String[]{branch.getId()});
    }

    // Delete a branch
    public void deleteBranch(String branchId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Branch", "BRANCH_ID = ?", new String[]{branchId});
        db.close();
    }

// CRUD operations for Book_Author table

    // Add a new book author to the database
    public void addBookAuthor(BookAuthor bookAuthor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("BOOK_ID", bookAuthor.getBookId());
        values.put("AUTHOR_NAME", bookAuthor.getAuthorName());
        db.insert("Book_Author", null, values);
        db.close();
    }

    // Retrieve all book authors from the database
    public List<BookAuthor> getAllBookAuthors() {
        List<BookAuthor> bookAuthors = new ArrayList<>();
        String selectQuery = "SELECT * FROM Book_Author";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                BookAuthor bookAuthor = new BookAuthor();
                bookAuthor.setBookId(cursor.getLong(0));
                bookAuthor.setAuthorName(cursor.getString(1));
                bookAuthors.add(bookAuthor);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bookAuthors;
    }

    // Delete a book author
    public void deleteBookAuthor(String bookId, String authorName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Book_Author", "BOOK_ID = ? AND AUTHOR_NAME = ?", new String[]{bookId, authorName});
        db.close();
    }

// CRUD operations for Book_Copy table

    // Add a new book copy to the database
    public void addBookCopy(BookCopy bookCopy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("BOOK_ID", bookCopy.getBookId());
        values.put("BRANCH_ID", bookCopy.getBranchId());
        values.put("ACCESS_NO", bookCopy.getAccessNo());
        db.insert("Book_Copy", null, values);
        db.close();
    }

    // Retrieve all book copies from the database
    public List<BookCopy> getAllBookCopies() {
        List<BookCopy> bookCopies = new ArrayList<>();
        String selectQuery = "SELECT * FROM Book_Copy";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                BookCopy bookCopy = new BookCopy();
                bookCopy.setBookId(cursor.getString(0));
                bookCopy.setBranchId(cursor.getString(1));
                bookCopy.setAccessNo(cursor.getString(2));
                bookCopies.add(bookCopy);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bookCopies;
    }

    // Delete a book copy
    public void deleteBookCopy(String accessNo, String branchId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Book_Copy", "ACCESS_NO = ? AND BRANCH_ID = ?", new String[]{accessNo, branchId});
        db.close();
    }
}

// Implementing similar methods for these tables.
