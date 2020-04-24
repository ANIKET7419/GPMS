package com.example.anp;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class guard_acc_notacc_adapter extends RecyclerView.Adapter<guard_acc_notacc_adapter.holder>
{
    String name[],address[],type[],type_number[],entry[],mobile[];
    int length;
    public  guard_acc_notacc_adapter(String name[],String address[],String type[],String type_number[],String entry[],String mobile[],int length)
    {
        this.name=name;
        this.address=address;
        this.type=type;
        this.type_number=type_number;
        this.entry=entry;
        this.mobile=mobile;
        this.length=length;

    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.guard_acc_not_card,parent,false);
        holder h=new holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        holder.mobile1.setTextColor(Color.YELLOW);
        holder.address1.setTextColor(Color.CYAN);
        holder.entry1.setTextColor(Color.YELLOW);
        holder.type_number1.setTextColor(Color.CYAN);
        holder.name1.setTextColor(Color.CYAN);
        holder.mobile1.setText(mobile[position]);
        holder.address1.setText(address[position]);
        holder.entry1.setText(entry[position]);
        holder.type_number1.setText(type[position]+" "+type_number[position]);
        holder.name1.setText(name[position]);
    }

    @Override
    public int getItemCount() {
        return length;
    }

    public  class holder extends RecyclerView.ViewHolder
    {
        TextView name1,address1,entry1,mobile1,type_number1;


        public holder(@NonNull View itemView) {
            super(itemView);
            name1=itemView.findViewById(R.id.guard_an_name);
            address1=itemView.findViewById(R.id.guard_an_address);
            type_number1=itemView.findViewById(R.id.guard_an_typenumber);
            entry1=itemView.findViewById(R.id.guard_an_entry);
            mobile1=itemView.findViewById(R.id.guard_an_mobile);
        }
    }
}
