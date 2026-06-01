package com.example.mydictionary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private List<Word> words;
    private OnWordActionListener listener;

    public interface OnWordActionListener {
        void onEdit(Word word);
        void onDelete(Word word);
    }

    public WordAdapter(List<Word> words, OnWordActionListener listener) {
        this.words = words;
        this.listener = listener;
    }

    public void setWords(List<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = words.get(position);
        holder.tvEnglish.setText(word.getEnglish());
        holder.tvIndonesia.setText(word.getIndonesia());
        
        holder.btnEdit.setOnClickListener(v -> {
            if (listener != null) listener.onEdit(word);
        });
        
        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) listener.onDelete(word);
        });
    }

    @Override
    public int getItemCount() {
        return words != null ? words.size() : 0;
    }

    static class WordViewHolder extends RecyclerView.ViewHolder {
        TextView tvEnglish, tvIndonesia;
        ImageButton btnEdit, btnDelete;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEnglish = itemView.findViewById(R.id.tvEnglish);
            tvIndonesia = itemView.findViewById(R.id.tvIndonesia);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}