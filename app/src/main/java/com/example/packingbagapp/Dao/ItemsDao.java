package com.example.packingbagapp.Dao;
import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.packingbagapp.Models.Items;

import java.util.List;

@Dao
public interface ItemsDao {
    @Insert(onConflict = REPLACE)
    void saveItem(Items items);

    List<Items>getAll(String category);
}
