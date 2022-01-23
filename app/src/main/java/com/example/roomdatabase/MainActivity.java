package com.example.roomdatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdatabase.adapter.KategoriAdapter;
import com.example.roomdatabase.databinding.ActivityMainBinding;
import com.example.roomdatabase.db.Kategori;
import com.example.roomdatabase.viewmodel.KatergoriViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements  KategoriAdapter.click{
    private ActivityMainBinding binding;
    private KatergoriViewModel viewmodel;
    private KategoriAdapter adapter;
    private Kategori kategori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
       binding.add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialogGoster(false);
           }
       });
        initModel();
        initRecycler();
        viewmodel.getAllCategory();
    }
    public void dialogGoster(boolean forEdit){
        AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        View dialog=getLayoutInflater().inflate(R.layout.dialog_layout,null);

        EditText kategoriAdi=dialog.findViewById(R.id.kategoriAdi);
        TextView cıkıs=dialog.findViewById(R.id.cıkıs);
        TextView olustur=dialog.findViewById(R.id.olustur);

        if(forEdit){
            olustur.setText("Guncelle");
            kategoriAdi.setText(kategori.kategoriadi);
        }

        olustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kadi = kategoriAdi.getText().toString();
                if (TextUtils.isEmpty(kadi)) {
                    Toast.makeText(MainActivity.this, "Kategorı Adı Boş", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(forEdit){
                    kategori.kategoriadi=kadi;
                    viewmodel.updateKategori(kategori);
                }
                else{
                    viewmodel.insertKategori(kadi);
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(dialog);
        alertDialog.show();


        cıkıs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    private void initRecycler(){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new KategoriAdapter(this,this);
        binding.recyclerView.setAdapter(adapter);
    }
    private  void initModel(){
        viewmodel=new ViewModelProvider(this).get(KatergoriViewModel.class);
        viewmodel.getListMutableLiveKategori().observe(this, new Observer<List<Kategori>>() {
            @Override
            public void onChanged(List<Kategori> kategoris) {
                if(kategoris==null){
                    binding.recyclerView.setVisibility(View.GONE);
                }else{
                    //recycler da gösterme
                    adapter.setKategoriAdapter(kategoris);
                }
            }
        });
    }

    @Override
    public void kategoriClik(Kategori kategori) {

    }

    @Override
    public void sil(Kategori kategori) {
        viewmodel.deleteKategori(kategori);
    }

    @Override
    public void duzenle(Kategori kategori) {
            this.kategori=kategori;
            dialogGoster(true);
    }
}