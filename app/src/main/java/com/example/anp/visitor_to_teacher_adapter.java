package com.example.anp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Statement;


public class visitor_to_teacher_adapter extends RecyclerView.Adapter<visitor_to_teacher_adapter.holder>
{
    String name[],address[],purpose[],entry[],pn[],type[],type_number[],email[];
    int length;
    int id[];
    int admin_id;
    Context c;
    public  visitor_to_teacher_adapter(String name[],String address[],String purpose[],String entry[],String pn[],String type[],String type_number[],String email[],int id[],int admin_id,Context c,int length)
    {
        this.c=c;
        this.name=name;
        this.address=address;
        this.purpose=purpose;
        this.entry=entry;
        this.pn=pn;
        this.type=type;
        this.type_number=type_number;
        this.email=email;
        this.id=id;
        this.length=length;
        this.admin_id=admin_id;
    }
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.visitor_to_teacher_card, parent, false);
        holder holder=new holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        holder.name1.setTextColor(Color.CYAN);
        holder.pn1.setTextColor(Color.GREEN);
        holder.address1.setTextColor(Color.YELLOW);
        holder.type_number1.setTextColor(Color.CYAN);
        holder.entry1.setTextColor(Color.GREEN);
        holder.email1.setTextColor(Color.GREEN);
        holder.name1.setText(name[position]);
    holder.entry1.setText(entry[position]);
    holder.purpose1.setText(purpose[position]);
    holder.type_number1.setText(type[position]+" "+type_number[position]);
    holder.address1.setText(address[position]);
    holder.email1.setText(email[position]);
    holder.pn1.setText(pn[position]);
    }

    @Override
    public int getItemCount() {
        return length;
    }

    public  class holder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name1,address1,type_number1,pn1,email1,purpose1,entry1;
        Button accept;

        public holder(@NonNull View itemView) {
            super(itemView);
            name1=itemView.findViewById(R.id.vtot_name);
            address1=itemView.findViewById(R.id.vtot_address);
            type_number1=itemView.findViewById(R.id.vtot_type_number);
            pn1=itemView.findViewById(R.id.vtot_pn);
            email1=itemView.findViewById(R.id.vtot_email);
            purpose1=itemView.findViewById(R.id.vtot_purpose);
            entry1=itemView.findViewById(R.id.vott_entry);
            accept=itemView.findViewById(R.id.vtot_accept);
            accept.setOnClickListener(this);
        }
        @Override
        public  void onClick(View v)
        {
            int position=getAdapterPosition();
            connection c1=new connection();
            Statement statement=c1.get();
            try{

                statement.execute("update VISITOR SET HASACCEPTED ='TRUE' where ID="+id[position]);
                AlertDialog.Builder builder=new AlertDialog.Builder(c);
                builder.setTitle("-------Message-------");
                builder.setMessage("Done");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
            catch (Exception e)
            {
                AlertDialog.Builder builder=new AlertDialog.Builder(c);
                builder.setTitle("Error");
                builder.setMessage(e.toString());
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        }

    }
}
