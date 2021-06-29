package com.example.carterangkotapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carterangkotapps.R;
import com.example.carterangkotapps.UpdateActivity;
import com.example.carterangkotapps.model.Data;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Data> drivers;

    public RecyclerViewAdapter(Context context , List<Data> drivers) {
        this.drivers = drivers;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate( R.layout.list_view, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder , int position) {

        Data driver = drivers.get( position );
        holder.textViewId.setText(driver.getId_driver());
        holder.textViewNama.setText(driver.getNama_driver());
        holder.textViewSim.setText(driver.getNo_sim());
        holder.textViewAlamat.setText(driver.getAlamat());
        holder.textViewUmur.setText(driver.getUmur());
        holder.textViewTlp.setText(driver.getNo_tlp());
        holder.textViewTipe.setText(driver.getTipe_mobil());
        holder.textViewJurusan.setText(driver.getJurusan_mobil());
        holder.textViewPlat.setText(driver.getPlat_mobil());

    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textIdDriver)
        TextView textViewId;
        @BindView(R.id.textNama)TextView textViewNama;
        @BindView(R.id.textSim)TextView textViewSim;
        @BindView(R.id.textAlamat)TextView textViewAlamat;
        @BindView(R.id.textUmur)TextView textViewUmur;
        @BindView(R.id.textNo_telepon) TextView textViewTlp;
        @BindView(R.id.textTipeMobil)TextView textViewTipe;
        @BindView(R.id.textJurusan)TextView textViewJurusan;
        @BindView(R.id.textNo_polisi)TextView textViewPlat;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            ButterKnife.bind( this, itemView );
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id_driver = textViewId.getText().toString();
                    String nama_driver = textViewNama.getText().toString();
                    String no_sim = textViewSim.getText().toString();
                    String alamat = textViewAlamat.getText().toString();
                    String umur = textViewUmur.getText().toString();
                    String no_tlp = textViewTlp.getText().toString();
                    String tipe_mobil = textViewTipe.getText().toString();
                    String jurusan = textViewJurusan.getText().toString();
                    String plat_mobil = textViewPlat.getText().toString();

                    Intent i = new Intent(v.getContext(), UpdateActivity.class);
                    i.putExtra("id_driver",id_driver);
                    i.putExtra("nama_driver",nama_driver);
                    i.putExtra("no_sim",no_sim);
                    i.putExtra("alamat",alamat);
                    i.putExtra( "umur", umur );
                    i.putExtra("no_tlp",no_tlp);
                    i.putExtra("tipe_mobil",tipe_mobil);
                    i.putExtra("jurusan_mobil",jurusan);
                    i.putExtra("plat_mobil",plat_mobil);
                    v.getContext().startActivity(i);
/*
                    Intent p = new Intent(v.getContext(), SMSActivity.class);
                    p.putExtra("id_driver",id_driver);
                    p.putExtra("nama_driver",nama_driver);
                    p.putExtra("no_sim",no_sim);
                    p.putExtra("alamat",alamat);
                    p.putExtra( "umur", umur );
                    p.putExtra("no_tlp",no_tlp);
                    p.putExtra("tipe_mobil",tipe_mobil);
                    p.putExtra("jurusan_mobil",jurusan);
                    p.putExtra("plat_mobil",plat_mobil);
                    v.getContext().startActivity(p);



                    Intent o = new Intent(v.getContext(), WhatsappActivity.class);
                    o.putExtra("id_driver",id_driver);
                    o.putExtra("nama_driver",nama_driver);
                    o.putExtra("no_sim",no_sim);
                    o.putExtra("alamat",alamat);
                    o.putExtra( "umur", umur );
                    o.putExtra("no_tlp",no_tlp);
                    o.putExtra("tipe_mobil",tipe_mobil);
                    o.putExtra("jurusan_mobil",jurusan);
                    o.putExtra("plat_mobil",plat_mobil);
                    v.getContext().startActivity(o);


 */
                }
            } );




        }
    }
}
