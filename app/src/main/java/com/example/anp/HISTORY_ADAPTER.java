package com.example.anp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HISTORY_ADAPTER extends RecyclerView.Adapter<HISTORY_ADAPTER.holder> {


    String name[];
    String address[];
    String email[];
    String mobile[];
    String hasaccepted[];
    String hasmeet[];
    String entry[];
    String exit[];
    String teacher[];
    String department[];
    String purpose[];
    String type[];
    String type_number[];
    int length;
    Context context;

    public HISTORY_ADAPTER(String name[], String address[], String email[], String mobile[], String hasaccepted[], String hasmeet[], String entry[], String exit[], String teacher[], String department[], String purpose[], String type[], String type_number[], int length, Context context) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
        this.hasaccepted = hasaccepted;
        this.hasmeet = hasmeet;
        this.entry = entry;
        this.exit = exit;
        this.teacher = teacher;
        this.department = department;
        this.purpose = purpose;
        this.type = type;
        this.type_number = type_number;
        this.length = length;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_card, parent, false);
        holder h = new holder(view);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        holder.email1.setText(email[position]);
        holder.address1.setText(address[position]);
        holder.name1.setText(name[position]);
        holder.entry1.setText("En. " + entry[position]);
        holder.exit1.setText("Ex. " + exit[position]);
        holder.department1.setText(department[position].substring(0, 4) + ".");
        holder.hasmeet1.setText("Met ? " + hasmeet[position]);
        holder.hasaccepted1.setText("Accepted ? " + hasaccepted[position]);
        holder.mobile1.setText(mobile[position]);
        holder.teacher1.setText("T- " + teacher[position]);
        holder.purpose1.setText("Purpose: " + purpose[position]);
        holder.type_number1.setText(type[position] + " : " + type_number[position]);
    }

    @Override
    public int getItemCount() {
        return length;
    }

    public class holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name1, address1, email1, mobile1, entry1, exit1, hasmeet1, hasaccepted1, teacher1, department1, type_number1, purpose1;
        Button call;
        Button mail;

        public holder(@NonNull View itemView) {
            super(itemView);
            name1 = itemView.findViewById(R.id.history_name);
            address1 = itemView.findViewById(R.id.history_address);
            email1 = itemView.findViewById(R.id.history_email);
            mobile1 = itemView.findViewById(R.id.history_mobile);
            entry1 = itemView.findViewById(R.id.history_entry);
            exit1 = itemView.findViewById(R.id.history_exit);
            hasmeet1 = itemView.findViewById(R.id.history_hasmeet);
            hasaccepted1 = itemView.findViewById(R.id.history_hasaccepted);
            teacher1 = itemView.findViewById(R.id.history_teacher);
            department1 = itemView.findViewById(R.id.history_department);
            type_number1 = itemView.findViewById(R.id.history_type_number);
            call = itemView.findViewById(R.id.history_call);
            mail = itemView.findViewById(R.id.history_gmail);
            purpose1 = itemView.findViewById(R.id.history_purpose);
            mail.setOnClickListener(this);
            call.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (v == call) {
                if (!mobile[position].equals("")) {

                    Intent i = new Intent(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:" + mobile[position]));
                    context.startActivity(i);
                }
                else
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setTitle("Notice...");
                    builder.setMessage("Can't Call");
                    builder.setPositiveButton("OKay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
            }
            else
            {
                if(!email[position].equals("")&&!email[position].equals("null")) {
                    Intent i = new Intent(Intent.ACTION_SENDTO);
                    i.setType("text/plain");
                   i.setData(Uri.parse("mailto:"+email[position]));
                   Intent j= Intent.createChooser(i,"Choose One ");
                    context.startActivity(j);
                }
                else
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setTitle("Notice...");
                    builder.setMessage("Can't Mail");
                    builder.setPositiveButton("OKay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
            }
        }
    }
}
