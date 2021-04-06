package com.example.keuangan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keuangan.paketku.AdapterTransaksiku;
import com.example.keuangan.paketku.Transaksiku;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton tbhData;
    RecyclerView recyclerView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Transaksiku");
    List<Transaksiku> list = new ArrayList<>();
    AdapterTransaksiku adapterTransaksiku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbhData = findViewById(R.id.tbl_data);
        recyclerView = findViewById(R.id.resikel_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tbhData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogTambahData();
            }
        });

        bacaData();
    }

    private void bacaData() {
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Transaksiku value = snapshot.getValue(Transaksiku.class);
                    list.add(value);
//
                }
                adapterTransaksiku = new AdapterTransaksiku(MainActivity.this,list);
                recyclerView.setAdapter(adapterTransaksiku);
//                recyclerView.setAdapter(new AdapterTransaksiku(MainActivity.this,list));

                setClick();
                
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

    }

    private void setClick() {
        adapterTransaksiku.setOnCallBack(new AdapterTransaksiku.onCallBack() {
            @Override
            public void onTblHapus(Transaksiku transaksiku) {
                hapusTransaksi(transaksiku);
            }

            @Override
            public void onTblEdit(Transaksiku transaksiku) {
                showDialogEditData(transaksiku);
            }
        });
    }

    private void showDialogEditData(final Transaksiku transaksiku){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_tambah);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        ImageButton tblKeluar = dialog.findViewById(R.id.tbl_keluar);
        tblKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

         final EditText txtTambah = dialog.findViewById(R.id.txt_tambah);
         final Button tblTambah = dialog.findViewById(R.id.tbl_tambah);
         TextView tlTambah = dialog.findViewById(R.id.tl_tambah);

        txtTambah.setText(transaksiku.getIsi());
        tblTambah.setText("Update");
        tlTambah.setText("Edit Data");

        tblTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txtTambah.getText())) {
                    tblTambah.setError("Silahkan Isi Saldo");
                } else {
                    editTransaksi(transaksiku, txtTambah.getText().toString());
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    private void editTransaksi(Transaksiku transaksiku, String baru){
        myRef.child(transaksiku.getKunci()).child("isi").setValue(baru).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(getApplicationContext(),"Update Berhasil",Toast.LENGTH_SHORT).show();

            }
        });
    }



    private void hapusTransaksi(final Transaksiku transaksiku){
        myRef.child(transaksiku.getKunci()).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(getApplication(), transaksiku.getIsi()+"telah dihapus",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialogTambahData() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_tambah);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        ImageButton tblKeluar = dialog.findViewById(R.id.tbl_keluar);
        tblKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        final EditText txtTambah = dialog.findViewById(R.id.txt_tambah);
        final Button tblTambah = dialog.findViewById(R.id.tbl_tambah);


        tblTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txtTambah.getText())) {
                    tblTambah.setError("Silahkan Isi Saldo");
                } else {
                    simpanData(txtTambah.getText().toString());
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }


    private void simpanData(String s) {

        String kunci = myRef.push().getKey();
        Transaksiku transaksiku = new Transaksiku(kunci,s);

        myRef.child(kunci).setValue(transaksiku).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_SHORT).show();

            }
        });
    }
}