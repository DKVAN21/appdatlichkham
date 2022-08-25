package com.example.datlichkhambenh.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.model.Doctor;
import com.example.datlichkhambenh.DatLichActivity;
import com.example.datlichkhambenh.R;

import java.util.List;

public class BacSi1Adapter extends RecyclerView.Adapter<BacSi1Adapter.DatLichViewHolder>{
    private List<Doctor> mList;

    public BacSi1Adapter(List<Doctor> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public DatLichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bac_si1, parent, false);
        return new DatLichViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatLichViewHolder holder, int position) {
        holder.tenBS.setText(mList.get(position).getFullName());
        holder.btnDatLich.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DatLichActivity.class);
            intent.putExtra("IDBS", mList.get(position).getUserName());
            intent.putExtra("TENBS", mList.get(position).getFullName());
            intent.putExtra("INTRODUCE", mList.get(position).getIntroduce());
            intent.putExtra("MONEY", mList.get(position).getMoney());
            intent.putExtra("TIMELV", mList.get(position).getTimeLV());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class DatLichViewHolder extends RecyclerView.ViewHolder {
        private TextView tenBS;
        private Button btnDatLich;
        public DatLichViewHolder(@NonNull View itemView) {
            super(itemView);
            tenBS = itemView.findViewById(R.id.tvNameBs_DatLich);
            btnDatLich = itemView.findViewById(R.id.btnDatLichKham_DatLich);
        }
    }


}
