package com.example.myapplication1.activities;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;
import com.example.myapplication1.data.Animal;
import com.example.myapplication1.data.AnimalDAO;
import com.example.myapplication1.data.AnimalDB;
import com.example.myapplication1.data.Converter;

import java.util.List;

public class AnimalRecycler extends RecyclerView.Adapter<AnimalView> {

    List<Animal> animalList;
    private AnimalDAO database;
    private Activity context;

    public AnimalRecycler(List<Animal> animals, Activity context) {
        animalList = animals;
        this.context = context;
    }

    @NonNull
    @Override
    public AnimalView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.animal_view_activity,
                parent,
                false
        );
        return new AnimalView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalView holder, int position) {
        Animal animal = animalList.get(position);
        database = AnimalDB.getDBInstance(context).animalDAO();

        if (animal.getImage() != null) {
            holder.imageView.setImageBitmap(Converter.convertByteArrayToImage(animal.getImage()));
        }

        if (animal.getName() != null) {
            holder.name.setText(animal.getName());
        }

        holder.delete.setOnClickListener(view -> {
            database.deleteAnimal(animal);
            // slettet i Liten
            animalList.remove(position);
            this.notifyDataSetChanged();

            Toast.makeText(context, "Slettet", Toast.LENGTH_SHORT).show();
            // context.finish();
            // context.startActivity(context.getIntent());
        });
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

}
