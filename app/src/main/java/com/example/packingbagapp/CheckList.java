package com.example.packingbagapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

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
        show = intent.getStringExtra(MyConstants.SHOW_SMALL);

        getSupportActionBar().setTitle(header);

        textAdd = findViewById(R.id.txtAdd);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);
        linearLayout = findViewById(R.id.linerarLayout);

        database = RoomDB.getInstance(this);

        if(MyConstants.FALSE_STRING.equals(show)){
            linearLayout.setVisibility(View.GONE);
            itemsList = database.mainDao().getAllSelected(true);
        }else{
            itemsList = database.mainDao().getAll(header);
        }

        updateRecycler(itemsList.toString());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = textAdd.getText().toString();
                if(itemName!=null && !itemName.isEmpty()){
                    addNewItems(itemName);
                    Toast.makeText(CheckList.this, "item Added", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CheckList.this,"Empty can't be added ",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void addNewItems(String itemName){
        Items item = new Items();
        item.setChecked(false);
        item.setCategory(header);
        item.setItemname(itemName);
        item.setAddedby(MyConstants.USER_SMALL);
        database.mainDao().saveItem(item);
        itemsList = database.mainDao().getAll(header);
        updateRecycler(itemsList.toString());
        recyclerView.scrollToPosition(checkListAdapter.getItemCount() -1);
        textAdd.setText("");
    }
    private void updateRecycler(String itemsList){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,LinearLayout.VERTICAL));
        checkListAdapter = new CheckListAdapter(CheckList.this,itemsList,database, show);
        recyclerView.setAdapter(checkListAdapter);

    }
}