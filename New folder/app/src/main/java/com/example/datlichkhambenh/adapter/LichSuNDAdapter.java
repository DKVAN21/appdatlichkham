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

import com.example.datlichkhambenh.HoadonActivity;
import com.example.datlichkhambenh.NDChiTietPhieuActivity;
import com.example.datlichkhambenh.R;
import com.example.datlichkhambenh.ThanhtoanActivity;
import com.example.datlichkhambenh.model.PhieuKham;

import java.util.List;

public class LichSuNDAdapter extends RecyclerView.Adapter<LichSuNDAdapter.LichSuViewHolder>{
    private Context context;
    private List<PhieuKham> phieuKhamList;

    public LichSuNDAdapter(Context context, List<PhieuKham> phieuKhamList) {
        this.context = context;
        this.phieuKhamList = phieuKhamList;
    }

    @NonNull
    @Override
    public LichSuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lich_su_kham, parent, false);
        return new LichSuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuViewHolder holder, int position) {
        PhieuKham obj = phieuKhamList.get(position);
        holder.tvTen.setText(obj.getTenBs());
        holder.tvTime.setText(obj.getDate() + " lúc " + obj.getTime());
        holder.tvStatus.setText(obj.getStatus());
        holder.tvPay.setText(obj.getPay());
        if(obj.getStatus().equalsIgnoreCase("Đã hủy")){
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.red));
        }
        if(obj.getPay().equalsIgnoreCase("Chưa thanh toán")){
            holder.tvPay.setTextColor(context.getResources().getColor(R.color.red));
        }
        holder.Hoadon.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), HoadonActivity.class);
            intent.putExtra("ID", phieuKhamList.get(position).getId());
            intent.putExtra("TENBS", phieuKhamList.get(position).getTenBs());
            intent.putExtra("TENBN", phieuKhamList.get(position).getTenBn());
            intent.putExtra("MONEY", phieuKhamList.get(position).getMoney());
            intent.putExtra("PAY", phieuKhamList.get(position).getPay());
            v.getContext().startActivity(intent);
        });

        holder.Thanhtoan.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ThanhtoanActivity.class);
            intent.putExtra("ID", phieuKhamList.get(position).getId());
            intent.putExtra("TENBN", phieuKhamList.get(position).getTenBn());
            intent.putExtra("MONEY", phieuKhamList.get(position).getMoney());
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
        private Button Hoadon, Thanhtoan;
        public LichSuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStatus = itemView.findViewById(R.id.tvStatus_lichSuKham);
            tvTime = itemView.findViewById(R.id.tv_time_lichSuKham);
            tvTen = itemView.findViewById(R.id.tv_ten_lichSuKham);
            tvPay = itemView.findViewById(R.id.tvPay_lichSuKham);
            Thanhtoan = itemView.findViewById(R.id.btn_Thanhtoan);
            Hoadon = itemView.findViewById(R.id.btn_Hoadon);
        }
    }
}
