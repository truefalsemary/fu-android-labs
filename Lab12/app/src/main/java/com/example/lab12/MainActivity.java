package com.example.lab12;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button loadDbButton = findViewById(R.id.load_db_button);

        loadDbButton.setOnClickListener(v -> {
            databaseHelper.clearTable();
            databaseHelper.fillTable();
            updateRecyclerView();
        });

        Button clearDbButton = findViewById(R.id.clear_database_button);
        clearDbButton.setOnClickListener(v -> {
            databaseHelper.clearTable();
            updateRecyclerView();
        });



    }

    private void updateRecyclerView() {
        List<Item> items = databaseHelper.getAllItems();
        itemAdapter = new ItemAdapter(items);
        recyclerView.setAdapter(itemAdapter);
    }
}