package com.example.myapplication1.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.R;
import com.example.myapplication1.data.Animal;
import com.example.myapplication1.data.AnimalDAO;
import com.example.myapplication1.data.AnimalDB;
import com.example.myapplication1.data.Converter;



public class AddAnimalActivity extends AppCompatActivity {

    ImageView imageView;
    EditText name;
    Bitmap bitmapImage;

    AnimalDAO animalDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_animal_activity);

        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.nameInput);
        bitmapImage = null;

        animalDAO = AnimalDB.getDBInstance(this).animalDAO();
    }

    ActivityResultLauncher<Intent> takePictureResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Intent data = result.getData();

                        bitmapImage = (Bitmap) data.getExtras().get("data");
                        imageView.setImageBitmap(bitmapImage);
                    }
                }
            });

    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureResultLauncher.launch(intent);
    }

    public void saveAnimalPic(View view) {

        String animalName = name.getText().toString().trim();

        if (TextUtils.isEmpty(animalName) || bitmapImage == null) {
            Toast.makeText(this, "Missing data", Toast.LENGTH_SHORT).show();
            return;
        }

        Animal animal = new Animal(animalName, Converter.convertImageToByte(bitmapImage));
        animalDAO.insertAnimal(animal);
        Toast.makeText(this, "Animal saved to DB", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AddAnimalActivity.this, StartActivity.class));
    }


}
