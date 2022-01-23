package com.example.roomdatabase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.roomdatabase.R;
import com.example.roomdatabase.db.Kategori;

import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder> {
   private List<Kategori> kategoriList;
   private Context context;
   private click click;

    public KategoriAdapter(Context context,click click) {
        this.context=context;
        this.click=click;
    }

    public void setKategoriAdapter(List<Kategori>kategoriList) {
        this.kategoriList=kategoriList;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public KategoriAdapter.KategoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);
        return new KategoriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriAdapter.KategoriViewHolder holder, int position) {
            holder.kategoriadi.setText(kategoriList.get(position).kategoriadi);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                click.kategoriClik(kategoriList.get(position));
                }
            });

            holder.silimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                click.sil(kategoriList.get(position));
                }
            });

            holder.duzenleimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                click.duzenle(kategoriList.get(position));
                }
            });

    }

    @Override
    public int getItemCount() {
        if(kategoriList==null || kategoriList.size()==0)
            return 0;
        else
            return kategoriList.size();
    }
    public class KategoriViewHolder extends RecyclerView.ViewHolder{
        ImageView silimg,duzenleimg;
        TextView kategoriadi;
        public KategoriViewHolder(@NonNull View itemView) {
            super(itemView);
            silimg=itemView.findViewById(R.id.sil);
            duzenleimg=itemView.findViewById(R.id.duzenle);
            kategoriadi=itemView.findViewById(R.id.kategoriadi);
        }
    }
    public interface click{
        void kategoriClik(Kategori kategori);
        void sil(Kategori kategori);
        void duzenle(Kategori kategori);
    }
}
