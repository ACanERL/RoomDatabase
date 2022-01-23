package com.example.roomdatabase.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.roomdatabase.db.AppDatabase;
import com.example.roomdatabase.db.Kategori;

import java.util.List;

public class KatergoriViewModel extends AndroidViewModel {
    private MutableLiveData<List<Kategori>>listMutableLiveKategori;
    private AppDatabase appDatabase;

    public KatergoriViewModel(@NonNull Application application){
       super(application);
        listMutableLiveKategori=new MutableLiveData<>();
        appDatabase=AppDatabase.getDatabaseInstance(application.getApplicationContext());
    }

    public MutableLiveData<List<Kategori>>getListMutableLiveKategori(){
        return listMutableLiveKategori;
    }
    public void getAllCategory(){
        List<Kategori>kategoriList=appDatabase.appDao().getAllKategori();
        if(kategoriList.size()>0){
            listMutableLiveKategori.postValue(kategoriList);
        }else {
            listMutableLiveKategori.postValue(null);
        }
    }

    public  void insertKategori(String kategoriAdi){
        Kategori kategori=new Kategori();
        kategori.kategoriadi=kategoriAdi;
        appDatabase.appDao().insertKategori(kategori);
        getAllCategory();
    }
    public  void updateKategori(Kategori kategori){
            appDatabase.appDao().updateKategori(kategori);
             getAllCategory();
    }

    public  void deleteKategori(Kategori kategori){
        appDatabase.appDao().deleteKategori(kategori);
        getAllCategory();
    }
}
