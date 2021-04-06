package com.example.keuangan.paketku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keuangan.R;

import java.util.List;

public class AdapterTransaksiku extends RecyclerView.Adapter<AdapterTransaksiku.ViewHolder>{

    Context context;
    List<Transaksiku> list;

    onCallBack OnCallBack;

    public void setOnCallBack(onCallBack onCallBack) {
        OnCallBack = onCallBack;
    }

    public AdapterTransaksiku(Context context, List<Transaksiku> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.teksData.setText(list.get(position).getIsi());

        holder.tblHapus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void  onClick(View view) {
            onCallBack.onTblHapus(list.get(position));
        }
        });

        holder.tblEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCallBack.onTblEdit(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView teksData;
        ImageButton tblHapus,tblEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            teksData = itemView.findViewById(R.id.teks_data);
            tblHapus = itemView.findViewById(R.id.tbl_hapus);
            tblEdit = itemView.findViewById(R.id.tbl_edit);
        }
    }

    public interface onCallBack{
        void onTblHapus(Transaksiku transaksiku);
        void onTblEdit(Transaksiku transaksiku);

    }
}
