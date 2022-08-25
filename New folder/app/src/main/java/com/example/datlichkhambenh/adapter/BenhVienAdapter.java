package com.example.datlichkhambenh.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.ListKhoaActivity;
import com.example.datlichkhambenh.R;
import com.example.datlichkhambenh.model.Benhvien;

import java.util.List;

public class BenhVienAdapter extends RecyclerView.Adapter<BenhVienAdapter.DatLichViewHolder>{
    private List<Benhvien> mList;

    public BenhVienAdapter(List<Benhvien> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public DatLichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_benh_vien, parent, false);
        return new DatLichViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatLichViewHolder holder, int position) {
      holder.benhvien.setText(mList.get(position).getTenBV());
        holder.address.setText(mList.get(position).getAddress());
            holder.btnDatLich.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), ListKhoaActivity.class);
                intent.putExtra("IDBV", mList.get(position).getIdBV());
                v.getContext().startActivity(intent);
            });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class DatLichViewHolder extends RecyclerView.ViewHolder {
        private TextView benhvien, address;
        private Button btnDatLich;

        public DatLichViewHolder(@NonNull View itemView) {
            super(itemView);
            benhvien = itemView.findViewById(R.id.tvBenhvien_DatL);
            address = itemView.findViewById(R.id.tvAddress_DatLich);
            btnDatLich = itemView.findViewById(R.id.btnDatLichKham_DatL);
        }
    }


}
