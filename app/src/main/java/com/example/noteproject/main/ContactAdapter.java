package com.example.noteproject.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteproject.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private LayoutInflater inflater;
    private List<ContactModel> list;

    public ContactAdapter(Context context, List<ContactModel> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_contacts, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, int position) {
        holder.txtContactName.setText(list.get(position).getContactName());
        holder.txtContactPhone.setText(list.get(position).getContactPhone());
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", list.get(position).getContactPhone(), null));
                v.getContext().startActivity(intent);
            }
        });
        holder.btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.whatsapp.com/send?phone=" + list.get(position).getContactPhone();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView txtContactName, txtContactPhone;
        Button btnCall, btnWhatsApp;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            txtContactName = itemView.findViewById(R.id.txt_item_contact_name);
            txtContactPhone = itemView.findViewById(R.id.txt_item_contact_phone);
            btnCall = itemView.findViewById(R.id.btn_item_call);
            btnWhatsApp = itemView.findViewById(R.id.btn_item_whatsapp);
        }
    }
}
