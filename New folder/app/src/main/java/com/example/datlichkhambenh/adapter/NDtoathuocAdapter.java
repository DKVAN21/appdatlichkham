package com.example.datlichkhambenh.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.R;
import com.example.datlichkhambenh.model.Toathuoc;

import java.util.List;

public class NDtoathuocAdapter extends RecyclerView.Adapter<NDtoathuocAdapter.DatLichViewHolder>{
    private List<Toathuoc> mList;

    public NDtoathuocAdapter(List<Toathuoc> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public DatLichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thuoc_nd, parent, false);
        return new DatLichViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatLichViewHolder holder, int position) {
      holder.tenthuoc.setText(mList.get(position).getTenTh());
        holder.soluong.setText(mList.get(position).getsL());
        holder.songay.setText(mList.get(position).getNgay());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class DatLichViewHolder extends RecyclerView.ViewHolder {
        private TextView tenthuoc, soluong, songay;

        public DatLichViewHolder(@NonNull View itemView) {
            super(itemView);
            tenthuoc = itemView.findViewById(R.id.tvTen_Thuoc);
            soluong = itemView.findViewById(R.id.tvSL_Thuoc);
            songay = itemView.findViewById(R.id.tvNgay_Thuoc);
        }
    }
}
