package com.example.library_management;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnPxWidthModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


public class BookViewActivity extends AppCompatActivity {

    TextView textViewBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        textViewBooks = findViewById(R.id.textViewBooks);

        // Implement functionality to view books from the database
        // Get a reference to the TableView
        TableView<String[]> tableView = findViewById(R.id.tableView);
        tableView.setColumnCount(3);

        // set table column weight
        TableColumnPxWidthModel columnModel = new TableColumnPxWidthModel(4, 350);
        columnModel.setColumnWidth(0, 200);
        columnModel.setColumnWidth(1, 550);
        columnModel.setColumnWidth(2, 350);
        tableView.setColumnModel(columnModel);

        // set up the header adapter
        String[] headers = {"Id", "Name", "Publisher"};
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, headers));


        Database_Helper db = new Database_Helper(this);
        List<Book> books = db.getBooks();

        List<String[]> data = new ArrayList<>();
        for (Book book : books) {
            data.add(new String[]{book.getId(), book.getTitle(), book.getPublisherName()});
        }
        tableView.setDataAdapter(new SimpleTableDataAdapter(this, data));

    }
}
