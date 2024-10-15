package com.example.usingrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class NationAdapter extends RecyclerView.Adapter<NationAdapter.NationViewHolder> {

    private List<Nation> nations;
    private Context context;

    // Hàm khởi tạo
    public NationAdapter(List<Nation> nations, Context context) {
        this.nations = nations;
        this.context = context;
    }

    // Phương thức onCreateViewHolder
    @NonNull
    @Override
    public NationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nation, parent, false);
        return new NationViewHolder(view);
    }

    // Phương thức onBindViewHolder
    @Override
    public void onBindViewHolder(@NonNull NationViewHolder holder, int position) {
        Nation nation = nations.get(position);
        holder.txtNationName.setText(nation.getName());
        holder.txtCapital.setText(nation.getCapital());
        holder.imgFlag.setImageResource(nation.getFlagResource());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, NationDetailActivity.class);
            intent.putExtra("nation", nation);
            context.startActivity(intent);
        });
    }

    // Phương thức getItemCount
    @Override
    public int getItemCount() {
        return nations.size();
    }

    // Lớp ViewHolder
    public static class NationViewHolder extends RecyclerView.ViewHolder {
        TextView txtNationName, txtCapital;
        ImageView imgFlag;

        public NationViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNationName = itemView.findViewById(R.id.txtNationName);
            txtCapital = itemView.findViewById(R.id.txtCapital);
            imgFlag = itemView.findViewById(R.id.imgFlag);
        }
    }
}
