package com.example.servicepay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<Records> list = new ArrayList<>();
    public RecyclerAdapter(Context ctx){
        this.context = ctx;
    }
    public void setItems(ArrayList<Records> rec) {
        list.addAll(rec);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new RecordsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecordsVH vh = (RecordsVH) holder;
        Records rec = list.get(position);
        vh.txt_name.setText(rec.getFullname());
        vh.txt_mobilenum.setText(rec.getMobilenumber());
        vh.txt_emailadd.setText(rec.getEmailaddress());
        vh.clickoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, vh.clickoption);
                popupMenu.inflate(R.menu.menu_vh      );
                popupMenu.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.item1:
                            Intent intent = new Intent(context, PersonalInfoActivity.class);
                            intent.putExtra("update", (Serializable) rec);
                            context.startActivity(intent);
                            break;
                        case R.id.item2:
                            DbFunctions dbFunctions = new DbFunctions();
                            dbFunctions.remove(rec.getKey()).addOnSuccessListener(suc-> {
                                Toast.makeText(context, "Record is removed", Toast.LENGTH_SHORT).show();
                                notifyItemRemoved(position);

                            }).addOnFailureListener(er->{
                                Toast.makeText(context, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                            });

                            break;
                    }
                    return false;
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
