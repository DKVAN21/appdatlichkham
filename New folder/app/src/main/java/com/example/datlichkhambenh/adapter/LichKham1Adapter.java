package com.example.datlichkhambenh.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.BSChiTietPhieuActivity;
import com.example.datlichkhambenh.R;
import com.example.datlichkhambenh.model.PhieuKham;

import java.util.List;

public class LichKham1Adapter extends RecyclerView.Adapter<LichKham1Adapter.LichSuViewHolder>{
    private Context context;
    private List<PhieuKham> phieuKhamList;

    public LichKham1Adapter(Context context, List<PhieuKham> phieuKhamList) {
        this.context = context;
        this.phieuKhamList = phieuKhamList;
    }

    @NonNull
    @Override
    public LichSuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieu_kham1, parent, false);
        return new LichSuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuViewHolder holder, int position) {
        PhieuKham obj = phieuKhamList.get(position);
        holder.tvTenbn.setText(obj.getTenBn());
        holder.tvTime.setText(obj.getDate() + " lúc " + obj.getTime());
        holder.tvStatus.setText(obj.getStatus());
        if(obj.getStatus().equalsIgnoreCase("Hoàn thành")){
            holder.tvStatus.setTextColor(context.getResources().getColor(android.R.color.holo_green_light));
        }
        holder.btnChitiet.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), BSChiTietPhieuActivity.class);
              intent.putExtra("ID", phieuKhamList.get(position).getId());
              intent.putExtra("IDBN", phieuKhamList.get(position).getIdBn());
              intent.putExtra("IDBS", phieuKhamList.get(position).getIdBs());
              intent.putExtra("TENBN", phieuKhamList.get(position).getTenBn());
              intent.putExtra("TUOI", phieuKhamList.get(position).getTuoi());
              intent.putExtra("TIENSU", phieuKhamList.get(position).getTiensu());
              intent.putExtra("MAU", phieuKhamList.get(position).getMau());
              intent.putExtra("NOTE", phieuKhamList.get(position).getNote());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return phieuKhamList.size();
    }

    public static class LichSuViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMa, tvTenbn, tvStatus, tvTime;
        private Button btnChitiet;
        public LichSuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStatus = itemView.findViewById(R.id.tvTrangThai_PhieuKham);
            tvTime = itemView.findViewById(R.id.tvThoiGian);
            tvMa = itemView.findViewById(R.id.tvMaPhieuKham);
            tvTenbn= itemView.findViewById(R.id.tvTenBn_PhieuKham);
            btnChitiet= itemView.findViewById(R.id.btn_Chitietphieu);
        }
    }
}