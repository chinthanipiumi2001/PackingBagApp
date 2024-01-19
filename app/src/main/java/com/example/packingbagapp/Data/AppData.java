package com.example.packingbagapp.Data;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.example.packingbagapp.Constance.MyConstants;
import com.example.packingbagapp.Database.RoomDB;
import com.example.packingbagapp.Models.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppData extends Application {

    RoomDB database;
    String category;
    Context context;

    public static final String LAST_VERSION = "LAST_VERSION";
    public static final int NEW_VERSION = 1;

    public AppData (RoomDB database){

        this.database = database;
    }
    public AppData(RoomDB database,Context context){
        this.database = database;
        this.context =context;
    }
    public List<Items>getBasicData(){
        category ="Basic Needs";
        List<Items>basicItem =new ArrayList<>();
        basicItem.clear();
        basicItem.add(new Items("Visa",category,false));
        basicItem.add(new Items("Passport",category,false));
        basicItem.add(new Items("Tickets",category,false));
        basicItem.add(new Items("wallet",category,false));
        basicItem.add(new Items("Driving License",category,false));
        basicItem.add(new Items("Currency",category,false));
        basicItem.add(new Items("House key",category,false));
        basicItem.add(new Items("Book",category,false));
        basicItem.add(new Items("Travel Pillow",category,false));
        basicItem.add(new Items("Umbrella",category,false));
        basicItem.add(new Items("Note Book",category,false));
        return basicItem;
    }
    public List<Items>getPersonalCareData(){
        String[]data={"Tooth-drush","Tooth-paste","floos","Mouthwash","Brandle soap","Hair Diyer"};
        return prepareItemsList(MyConstants.BABY_NEEDS_CAMEL_CASE,data);
    }
    public List<Items>getClothingData(){
        String[]data={"Sports ware","Tooth-paste","floos","Mouthwash","Brandle soap","Hair Diyer"};
        return prepareItemsList(MyConstants.CLOTHING_CAMEL_CASE,data);
    }
    public List<Items>getBabyNeedsData(){
        String[]data={"Tooth-drush","Tooth-paste","floos","Mouthwash","Brandle soap","Hair Diyer"};
        return prepareItemsList(MyConstants.BASIC_NEEDS_CAMEL_CASE,data);
    }
    public List<Items>getFoodData(){
        String[]data={"Tooth-drush","Tooth-paste","floos","Mouthwash","Brandle soap","Hair Diyer"};
        return prepareItemsList(MyConstants.FOOD_CAMEL_CASE,data);
    }
    public List<Items>getTechnoloData(){
        String[]data={"Tooth-drush","Tooth-paste","floos","Mouthwash","Brandle soap","Hair Diyer"};
        return prepareItemsList(MyConstants.TECHNOLOGY_CAMEL_CASE,data);
    }
    public List<Items>getHealthData(){
        String[]data={"Tooth-drush","Tooth-paste","floos","Mouthwash","Brandle soap","Hair Diyer"};
        return prepareItemsList(MyConstants.HEALTH_CAMEL_CASE,data);
    }
    public List<Items>getBeachSuppliseData(){
        String[]data={"Tooth-drush","Tooth-paste","floos","Mouthwash","Brandle soap","Hair Diyer"};
        return prepareItemsList(MyConstants.BEACH_SUPPLIES_CAMEL_CASE,data);
    }
    public List<Items>getNeedsData(){
        String[]data={"Tooth-drush","Tooth-paste","floos","Mouthwash","Brandle soap","Hair Diyer"};
        return prepareItemsList(MyConstants.NEEDS_CAMEL_CASE,data);
    }

    public List<Items>prepareItemsList(String category,String[]data){
        List<String>list = Arrays.asList(data);
        List<Items> dataList = new ArrayList<>();
        dataList.clear();

        for (int i=0;i<list.size();i++){
            dataList.add(new Items(list.get(i),category,false));
        }
        return dataList;
    }
    public List<List<Items>>getAllData(){
        List<List<Items>>listofAllItems = new ArrayList<>();
        listofAllItems.clear();
        listofAllItems.add(getBasicData());
        listofAllItems.add(getClothingData());
        listofAllItems.add(getPersonalCareData());
        listofAllItems.add(getBabyNeedsData());
        listofAllItems.add(getHealthData());
        listofAllItems.add(getTechnoloData());
        listofAllItems.add(getFoodData());
        listofAllItems.add(getBeachSuppliseData());
        // listofAllItems.add(getNeedsData());
        return listofAllItems;
    }
    public void persistAllData(){
        List<List<Items>>listofAllItems = getAllData();
        for(List<Items>list:listofAllItems){
            for(Items items:list){
                database.mainDao().saveItem(items);
            }
        }
        System.out.println("Data added.");
    }

     public void persistDataByCategory(String category,Boolean onlyDelete){
        try{

        }catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(this, "Something went worning", Toast.LENGTH_SHORT).show();
        }
     }

    private List<Items>deleteAndGetListByCatergory(String category,Boolean onlyDelete){
        if(onlyDelete){
            database.mainDao().deleteAllByCatergoryAndAddBy(category ,MyConstants.SYSTEM_SMALL);
        }else{
            database.mainDao().deleteAllByCatergory(category);
        }
        switch (category){
            case MyConstants.BASIC_NEEDS_CAMEL_CASE:
                return getBasicData();

            case MyConstants.CLOTHING_CAMEL_CASE:
                return getClothingData();

            case MyConstants.BABY_NEEDS_CAMEL_CASE:
                return getBabyNeedsData();

            case MyConstants.PERSONAL_CARE_CAMEL_CASE:
                return getPersonalCareData();

            case MyConstants.HEALTH_CAMEL_CASE:
                return getHealthData();

            case MyConstants.TECHNOLOGY_CAMEL_CASE:
                return getTechnoloData();

            case MyConstants.BEACH_SUPPLIES_CAMEL_CASE:
                return getBeachSuppliseData();

            case MyConstants.CAR_SUPPLIES_CAMEL_CASE:
                return getPersonalCareData();

            case MyConstants.NEEDS_CAMEL_CASE:
                return getNeedsData();

            default:
                return new ArrayList<>();
        }
    }
}
