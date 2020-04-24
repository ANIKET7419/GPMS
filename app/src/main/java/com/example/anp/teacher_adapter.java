package com.example.anp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
public class teacher_adapter extends RecyclerView.Adapter<teacher_adapter.holder> {

    String data[][];
    int length1;
    String callfor;
    Context context;
     public  teacher_adapter(Context c,String callfor,String array[][],int length)
     {
         context=c;
         this.callfor=callfor;
         length1=length;
         data=array;
     }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.teacher_detail_card, parent, false);
        holder viewHolder = new holder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( holder holder, int position) {
         holder.username.setText(data[position][0]);
         holder.name.setText(data[position][1]);

    }

    @Override
    public int getItemCount()
    {
        return length1;
    }

    class holder extends RecyclerView.ViewHolder implements  View.OnClickListener
    {
        Button button;
        TextView username,name;
        public holder(View item)
        {
            super(item);
            button=item.findViewById(R.id.message_button);
            username=item.findViewById(R.id.user_name);
            name=item.findViewById(R.id.name);
            button.setOnClickListener(this);
        }
        @Override
        public void onClick(View view)
        {
            try {

                int position = this.getAdapterPosition();
                String username = data[position][0];
                Intent i = new Intent(context, Information.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("callfor", callfor);
                i.putExtra("username", username);
                context.startActivity(i);
            }
            catch (Exception e)
            {
                Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }
}
