package com.example.keuangan;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.keuangan.paketku.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.keuangan.paketku.SharedClass.CUSTOMER_PATH;
import static com.example.keuangan.paketku.SharedClass.ROOT_UID;
import static com.example.keuangan.paketku.SharedClass.user;


public class NavApp extends AppCompatActivity {

    public void getUserInfo(){

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(CUSTOMER_PATH).child(ROOT_UID);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.child("customer_info").getValue(User.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.w("MAIN", "Failed to read value", error.toException());
            }
        });


    }

}


