package com.example.packingbagapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.packingbagapp.Adapter.CheckListAdapter;
import com.example.packingbagapp.Constance.MyConstants;
import com.example.packingbagapp.Database.RoomDB;
import com.example.packingbagapp.Models.Items;

import java.util.ArrayList;
import java.util.List;

public class CheckList extends AppCompatActivity {

    RecyclerView recyclerView;
    CheckListAdapter checkListAdapter;
    RoomDB database;
    List<Items> itemsList = new ArrayList<>();
    String header, show;

    EditText textAdd;
    Button btnAdd;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);


        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        header = intent.getStringExtra(MyConstants.HEADER_SMALL);
    }
    private void addNewItems(String itemsList){
        Items item = new Items();
        item.setChecked(false);
        item.setCategory(header);
        item.setItemname(itemsList);
        item.setAddedby(MyConstants.USER_SMALL);
        database.mainDao().saveItem(item);
        updateRecycler(itemsList);
        recyclerView.scrollToPosition(checkListAdapter.getItemCount()-1);
        textAdd.setText("");
    }
    private void updateRecycler(String itemsList){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,LinearLayout.VERTICAL));
        checkListAdapter = new CheckListAdapter(CheckList.this,itemsList,database, show);
        recyclerView.setAdapter(checkListAdapter);

    }
}