package com.example.praktikumchallange.data;

import com.example.praktikumchallange.R;
import com.example.praktikumchallange.model.Shoes;

import java.util.ArrayList;

public class DataSource {
    public ArrayList<Shoes> shoes = new ArrayList<>();

    public DataSource() {
        this.shoes.add(new Shoes(R.drawable.shoes1, R.drawable.shoes1_detail,"Hiking", "Terrex Urban", "$143,67", "Unpaved trails and mixed surfaces are easy when you have the traction and support you need. Casual enough for the daily commute"));
        this.shoes.add(new Shoes(R.drawable.shoes2, R.drawable.shoes2_detail,"Hiking", "Court Vision 2.0", "$58,67", "Unpaved trails and mixed surfaces are easy when you have the traction and support you need. Casual enough for the daily commute"));
        this.shoes.add(new Shoes(R.drawable.shoes3, R.drawable.shoes3_detail,"Training", "SL 72 Shoes", "$115,19", "Unpaved trails and mixed surfaces are easy when you have the traction and support you need. Casual enough for the daily commute"));
    }
}