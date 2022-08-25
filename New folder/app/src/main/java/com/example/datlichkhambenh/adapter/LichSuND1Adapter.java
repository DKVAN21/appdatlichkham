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

import com.example.datlichkhambenh.NDChiTietPhieuActivity;
import com.example.datlichkhambenh.R;
import com.example.datlichkhambenh.model.PhieuKham;

import java.util.List;

public class LichSuND1Adapter extends RecyclerView.Adapter<LichSuND1Adapter.LichSuViewHolder>{
    private Context context;
    private List<PhieuKham> phieuKhamList;

    public LichSuND1Adapter(Context context, List<PhieuKham> phieuKhamList) {
        this.context = context;
        this.phieuKhamList = phieuKhamList;
    }

    @NonNull
    @Override
    public LichSuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lich_su_kham1, parent, false);
        return new LichSuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuViewHolder holder, int position) {
        PhieuKham obj = phieuKhamList.get(position);
        holder.tvTen.setText(obj.getTenBs());
        holder.tvTime.setText(obj.getDate() + " lúc " + obj.getTime());
        holder.tvStatus.setText(obj.getStatus());
        if(obj.getStatus().equalsIgnoreCase("Đã hủy")){
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.red));
        }
        holder.chitiet.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), NDChiTietPhieuActivity.class);
            intent.putExtra("ID", phieuKhamList.get(position).getId());
            intent.putExtra("TENBS", phieuKhamList.get(position).getTenBs());
            intent.putExtra("MONEY", phieuKhamList.get(position).getMoney());
            intent.putExtra("STATUS", phieuKhamList.get(position).getStatus());
            intent.putExtra("PAY", phieuKhamList.get(position).getPay());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return phieuKhamList.size();
    }

    public static class LichSuViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTen, tvTime, tvStatus, tvPay;
        private Button chitiet;
        public LichSuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStatus = itemView.findViewById(R.id.tvStatus_lichSuKham);
            tvTime = itemView.findViewById(R.id.tv_time_lichSuKham);
            tvTen = itemView.findViewById(R.id.tv_ten_lichSuKham);
            chitiet = itemView.findViewById(R.id.btn_Chitietphieukham);
        }
    }
}
