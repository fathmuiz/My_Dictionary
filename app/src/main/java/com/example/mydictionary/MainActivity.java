package com.example.mydictionary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etEnglish;
    private EditText etIndonesia;
    private TextView tvMessage;
    private DictionaryDbHelper dbHelper;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        etEnglish = findViewById(R.id.etEnglish);
        etIndonesia = findViewById(R.id.etIndonesia);
        tvMessage = findViewById(R.id.tvMessage);
        dbHelper = new DictionaryDbHelper(this);
    }
    public void translate(View view) {
// Clear previous message
        if (tvMessage != null) tvMessage.setVisibility(View.GONE);
        String english = "";
        if (etEnglish != null && etEnglish.getText() != null) {
            english = etEnglish.getText().toString().trim();
        }
        String indonesia = null;
        if (dbHelper != null) {
            indonesia = dbHelper.queryTranslation(english);
        }
        if (indonesia != null) {
            if (etIndonesia != null) etIndonesia.setText(indonesia);
        } else {
            if (etIndonesia != null) etIndonesia.setText("");
            if (tvMessage != null) {
                tvMessage.setText("Data not found");
                tvMessage.setVisibility(View.VISIBLE);
            }
        }
    }
    public void addData(View view) {
        Intent intent = new Intent(this, AddDataActivity.class);
        startActivity(intent);
    }
    public void viewAll(View view) {
        Intent intent = new Intent(this, WordListActivity.class);
        startActivity(intent);
    }
@Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}