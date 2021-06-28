package com.ashish.roomdemo.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "NoteModel")
public class NoteModel {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String email;


    @Ignore
    public NoteModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public NoteModel(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
