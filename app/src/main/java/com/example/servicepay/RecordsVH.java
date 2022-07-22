package com.example.servicepay;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecordsVH extends RecyclerView.ViewHolder {
    public TextView txt_name, txt_mobilenum, txt_emailadd, clickoption;
    public RecordsVH(@NonNull View itemView) {
        super(itemView);
        txt_name = itemView.findViewById(R.id.dispname);
        txt_mobilenum = itemView.findViewById(R.id.dispmobile);
        txt_emailadd = itemView.findViewById(R.id.dispemail);
        clickoption = itemView.findViewById(R.id.option);
    }
}
