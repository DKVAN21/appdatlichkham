package com.example.datlichkhambenh.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.R;
import com.example.datlichkhambenh.model.Toathuoc;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class BStoathuocAdapter extends RecyclerView.Adapter<BStoathuocAdapter.DatLichViewHolder>{
    private List<Toathuoc> mList;
    private DatabaseReference ref;

    public BStoathuocAdapter(List<Toathuoc> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public DatLichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thuoc_bs, parent, false);
        return new DatLichViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatLichViewHolder holder, int position) {
        Toathuoc a = mList.get(position);
        holder.tenthuoc.setText(mList.get(position).getTenTh());
        holder.soluong.setText(mList.get(position).getsL());
        holder.songay.setText(mList.get(position).getNgay());
        holder.btnXoa.setOnClickListener(v -> {
            mList.remove(holder.getAdapterPosition());
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.tenthuoc.getContext());
            builder.setTitle("Bạn có muốn xóa?");
            builder.setMessage("Bỏ loại thuốc này");

            builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseDatabase.getInstance().getReference().child("Toathuoc")
                            .child(a.getId())
                            .child(a.getTenTh()).removeValue();
                }
            });
            builder.setNegativeButton("Trở lại", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class DatLichViewHolder extends RecyclerView.ViewHolder {
        private TextView tenthuoc, soluong, songay;
        private Button btnXoa;

        public DatLichViewHolder(@NonNull View itemView) {
            super(itemView);
            tenthuoc = itemView.findViewById(R.id.tvTen_Thuoc);
            soluong = itemView.findViewById(R.id.tvSL_Thuoc);
            songay = itemView.findViewById(R.id.tvNgay_Thuoc);
            btnXoa = itemView.findViewById(R.id.btnXoa_Thuoc);
        }
    }
}
