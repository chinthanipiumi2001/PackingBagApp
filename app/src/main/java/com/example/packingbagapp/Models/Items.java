package com.example.packingbagapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "items")
public class Items implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int ID =0;


    @ColumnInfo(name = "itemname")
    String itemname;

    @ColumnInfo(name="category")
    String category;

    @ColumnInfo(name = "addedby")
    String addedby;

    @ColumnInfo(name = "checked")
    Boolean checked = false;

    public Items(){

    }
    public Items(String itemname,String category,String addedby,Boolean checked){
        this.itemname = itemname;
        this.category = category;
        this.addedby = addedby;
        this.checked = checked;
    }
}
