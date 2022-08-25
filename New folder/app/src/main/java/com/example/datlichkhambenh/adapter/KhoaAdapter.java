package com.example.datlichkhambenh.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.DatLichNhanhActivity;
import com.example.datlichkhambenh.ListBacSiActivity;
import com.example.datlichkhambenh.R;
import com.example.datlichkhambenh.model.Khoa;

import java.util.List;

public class KhoaAdapter extends RecyclerView.Adapter<KhoaAdapter.DatLichViewHolder>{
    private List<Khoa> mList;

    public KhoaAdapter(List<Khoa> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public DatLichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoa, parent, false);
        return new DatLichViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatLichViewHolder holder, int position) {
      holder.khoa.setText(mList.get(position).getTenKhoa());
            holder.btnDatLich.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), ListBacSiActivity.class);
                intent.putExtra("idBV", mList.get(position).getIdBV());
                intent.putExtra("IDKHOA", mList.get(position).getIdKhoa());
              //  intent.putExtra("TENKHOA", mList.get(position).getTenKhoa());
             //   intent.putExtra("MONEY", mList.get(position).getMoney());
                v.getContext().startActivity(intent);
            });
            holder.btnDatLichNhanh.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), DatLichNhanhActivity.class);
                intent.putExtra("IDBV", mList.get(position).getIdBV());
                intent.putExtra("IDKHOA", mList.get(position).getIdKhoa());
                v.getContext().startActivity(intent);
            });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class DatLichViewHolder extends RecyclerView.ViewHolder {
        private TextView khoa;
        private Button btnDatLich, btnDatLichNhanh;

        public DatLichViewHolder(@NonNull View itemView) {
            super(itemView);
            khoa = itemView.findViewById(R.id.tvKhoa_DatLich);
            btnDatLich = itemView.findViewById(R.id.btnDatLichKham_DatLich);
            btnDatLichNhanh = itemView.findViewById(R.id.btnDatLichnhanh_DatLich);
        }
    }


}
