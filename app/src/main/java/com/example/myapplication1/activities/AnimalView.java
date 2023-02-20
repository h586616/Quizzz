package com.example.myapplication1.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;

public class AnimalView extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView name;
    ImageView delete;

    public AnimalView(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.cardImage);
        name = itemView.findViewById(R.id.cardName);
        delete = itemView.findViewById(R.id.delete);
    }
}
