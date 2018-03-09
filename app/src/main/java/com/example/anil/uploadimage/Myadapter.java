package com.example.anil.uploadimage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anil.uploadimage.model.ReadData;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Anil on 3/8/2018.
 */

class Myadapter extends RecyclerView.Adapter<Myadapter.Viewholder> {
    Context ct;
    List<ReadData> images;
    public Myadapter(DisplayImage getImageFromServer, List<ReadData> imagesdata) {
        this.ct=getImageFromServer;
        this.images=imagesdata;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.tv.setText(images.get(position).getName());
        Picasso.with(ct).load(images.get(position).getPath()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;
        public Viewholder(View itemView) {
            super(itemView);
            iv=(ImageView)itemView.findViewById(R.id.iv);
            tv=(TextView)itemView.findViewById(R.id.tv);
        }
    }
}
