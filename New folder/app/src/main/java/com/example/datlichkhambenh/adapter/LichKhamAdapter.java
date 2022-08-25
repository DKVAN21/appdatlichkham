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
import com.example.datlichkhambenh.VideocallActivity;
import com.example.datlichkhambenh.model.PhieuKham;

import java.util.List;

public class LichKhamAdapter extends RecyclerView.Adapter<LichKhamAdapter.LichSuViewHolder>{
    private Context context;
    private List<PhieuKham> phieuKhamList;

    public LichKhamAdapter(Context context, List<PhieuKham> phieuKhamList) {
        this.context = context;
        this.phieuKhamList = phieuKhamList;
    }

    @NonNull
    @Override
    public LichSuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieu_kham, parent, false);
        return new LichSuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuViewHolder holder, int position) {
        PhieuKham obj = phieuKhamList.get(position);
        holder.tvTenbn.setText(obj.getTenBn());
        holder.tvTime.setText(obj.getDate() + " lúc " + obj.getTime());
        holder.tvStatus.setText(obj.getStatus());
        if(obj.getStatus().equalsIgnoreCase("Đang chờ")){
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.red));
        }
        holder.btnCall.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), VideocallActivity.class);
            intent.putExtra("IDBN", phieuKhamList.get(position).getIdBn());
            intent.putExtra("IDBS", phieuKhamList.get(position).getIdBs());
            intent.putExtra("TENBN", phieuKhamList.get(position).getTenBn());
            v.getContext().startActivity(intent);

        });
        holder.btnChitiet.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), BSChiTietPhieuActivity.class);
              intent.putExtra("ID", phieuKhamList.get(position).getId());
              intent.putExtra("IDBN", phieuKhamList.get(position).getIdBn());
              intent.putExtra("IDBS", phieuKhamList.get(position).getIdBs());
              intent.putExtra("TENBN", phieuKhamList.get(position).getTenBn());
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
        private Button btnCall, btnChitiet;
        public LichSuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStatus = itemView.findViewById(R.id.tvTrangThai_PhieuKham);
            tvTime = itemView.findViewById(R.id.tvThoiGian);
            tvMa = itemView.findViewById(R.id.tvMaPhieuKham);
            tvTenbn= itemView.findViewById(R.id.tvTenBn_PhieuKham);
            btnCall = itemView.findViewById(R.id.btn_Videocall);
            btnChitiet= itemView.findViewById(R.id.btn_Chitietphieu);
        }
    }
}