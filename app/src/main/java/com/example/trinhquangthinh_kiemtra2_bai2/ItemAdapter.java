package com.example.trinhquangthinh_kiemtra2_bai2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
    private final List<Virus> virusList;

    public ItemAdapter(List<Virus> virusList) {
        this.virusList = virusList;
    }

    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout,parent,false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  ItemHolder holder, int position) {
        Virus virus = this.virusList.get(position);
        holder.getId().setText("Id :"+String.valueOf(virus.getId()));
        holder.getName().setText("Tên: "+virus.getName());
        String s = "";
        if(virus.isArn())s+="arn ";
        if (virus.isProteinN())s+="proteinN ";
        if(virus.isProteinS()) s+="proteinS ";
        holder.getStruct().setText("Cấu trúc: "+s);
        holder.getDate().setText("Ngày phát hiện: "+virus.getDate().toLocaleString());
        holder.getVaxcin().setText("vacxin: "+(virus.isVaxcin()?"Có":"Không"));
    }

    @Override
    public int getItemCount() {
        return this.virusList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        private final TextView id,name,struct,date,vaxcin;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            this.id = itemView.findViewById(R.id.itemId);
            this.name= itemView.findViewById(R.id.itemName);
            this.struct = itemView.findViewById(R.id.itemStruct);
            this.date = itemView.findViewById(R.id.itemDate);
            this.vaxcin= itemView.findViewById(R.id.itemVacine);
        }

        public TextView getId() {
            return id;
        }

        public TextView getName() {
            return name;
        }

        public TextView getStruct() {
            return struct;
        }

        public TextView getDate() {
            return date;
        }

        public TextView getVaxcin() {
            return vaxcin;
        }
    }
}
