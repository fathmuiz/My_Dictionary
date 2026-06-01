package com.example.mydictionary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class EditDataActivity extends AppCompatActivity {
    private EditText etEnglish, etIndonesia;
    private TextView tvMessage;
    private DictionaryDbHelper dbHelper;
    private int wordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_data);

        etEnglish = findViewById(R.id.etEditEnglish);
        etIndonesia = findViewById(R.id.etEditIndonesia);
        tvMessage = findViewById(R.id.tvEditMessage);
        dbHelper = new DictionaryDbHelper(this);

        wordId = getIntent().getIntExtra("ID", -1);
        etEnglish.setText(getIntent().getStringExtra("ENGLISH"));
        etIndonesia.setText(getIntent().getStringExtra("INDONESIA"));

        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnCancel = findViewById(R.id.btnCancel);

        btnUpdate.setOnClickListener(v -> updateData());
        btnCancel.setOnClickListener(v -> finish());
    }

    private void updateData() {
        String english = etEnglish.getText().toString().trim();
        String indonesia = etIndonesia.getText().toString().trim();

        if (english.isEmpty() || indonesia.isEmpty()) {
            tvMessage.setText("Fields cannot be empty");
            tvMessage.setVisibility(View.VISIBLE);
            return;
        }

        int rows = dbHelper.updateEntry(wordId, english, indonesia);
        if (rows > 0) {
            Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            tvMessage.setText("Update failed");
            tvMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) dbHelper.close();
    }
}