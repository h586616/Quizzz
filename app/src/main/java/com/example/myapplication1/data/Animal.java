package com.example.myapplication1.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Animals")
public class Animal {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte [] image;


    public Animal() {
    }

    public Animal(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public byte [] getImage() {

        return image;
    }

    public void setImage(byte[] image) {

        this.image = image;
    }

}
