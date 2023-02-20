package com.example.myapplication1.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication1.R;

@Database(
        entities = Animal.class,
        version = 1,
        exportSchema = false
)
public abstract class AnimalDB extends RoomDatabase{

    private static AnimalDB animalDB = null;
    public abstract AnimalDAO animalDAO();

    public static synchronized AnimalDB getDBInstance(Context context) {
        if (animalDB == null) {
            animalDB = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AnimalDB.class,
                    "MyApplication1"
            ).allowMainThreadQueries().build();

            addAnimalDB(context, "Cat", R.drawable.cat);
            addAnimalDB(context, "Dog", R.drawable.dog);
            addAnimalDB(context, "Rabbit", R.drawable.rabbit);
        }
        return animalDB;
    }

    private static void addAnimalDB(Context context, String name, int imageResourceId) {
        Animal animal = new Animal();
        animal.setName(name);

        Bitmap image = BitmapFactory.decodeResource(context.getResources(), imageResourceId);
        if (image != null) {
            animal.setImage(Converter.convertImageToByte(image));
        }

        animalDB.animalDAO().insertAnimal(animal);
    }

}
