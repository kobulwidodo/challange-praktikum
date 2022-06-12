package com.example.praktikumchallange.dao;

import com.example.praktikumchallange.model.Article;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ArticleDAO {
    private DatabaseReference databaseReference;

    public ArticleDAO() {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://project-akhir-pam-ccf6f-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = db.getReference(Article.class.getSimpleName());
    }

    public Task<Void> insert(Article med) {
        return databaseReference.push().setValue(med);
    }

    public Query get() {
        return databaseReference.orderByKey();
    }

    public Task<DataSnapshot> getByKey(String key) {
        return databaseReference.child(key).get();
    }

    public Task<Void> update (String key, Article article) {
        return databaseReference.child(key).setValue(article);
    }

    public Task<Void> delete (String key) {
        return databaseReference.child(key).removeValue();
    }
}
