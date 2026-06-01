package com.example.mydictionary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WordListActivity extends AppCompatActivity implements WordAdapter.OnWordActionListener {
    private RecyclerView rvWords;
    private WordAdapter adapter;
    private final List<Word> words = new ArrayList<>();
    private DictionaryDbHelper dbHelper;
    private TextView tvEmpty;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_word_list);

        dbHelper = new DictionaryDbHelper(this);
        rvWords = findViewById(R.id.rvWords);
        tvEmpty = findViewById(R.id.tvEmpty);
        searchView = findViewById(R.id.searchView);
        Button btnBack = findViewById(R.id.btnBack);

        adapter = new WordAdapter(words, this);
        rvWords.setLayoutManager(new LinearLayoutManager(this));
        rvWords.setAdapter(adapter);

        btnBack.setOnClickListener(v -> finish());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadWords(newText);
                return true;
            }
        });

        loadWords(null);
    }

    @Override
    public void onEdit(Word word) {
        Intent intent = new Intent(this, EditDataActivity.class);
        intent.putExtra("ID", word.getId());
        intent.putExtra("ENGLISH", word.getEnglish());
        intent.putExtra("INDONESIA", word.getIndonesia());
        startActivity(intent);
    }

    @Override
    public void onDelete(Word word) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Entry")
                .setMessage("Are you sure you want to delete '" + word.getEnglish() + "'?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    int deleted = dbHelper.deleteById(word.id);
                    if (deleted > 0) {
                        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                        loadWords(searchView.getQuery().toString());
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWords(searchView.getQuery().toString());
    }

    private void loadWords(String query) {
        if (dbHelper == null) return;
        List<Word> list = dbHelper.searchEntries(query);
        words.clear();
        if (list != null) {
            words.addAll(list);
        }
        adapter.notifyDataSetChanged();
        updateEmptyView();
    }

    private void updateEmptyView() {
        if (tvEmpty == null) return;
        tvEmpty.setVisibility(words.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) dbHelper.close();
    }
}