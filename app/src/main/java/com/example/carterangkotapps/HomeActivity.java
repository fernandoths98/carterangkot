package com.example.carterangkotapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.carterangkotapps.adapter.RecyclerViewAdapter;
import com.example.carterangkotapps.api.RequestAPI;
import com.example.carterangkotapps.model.Data;
import com.example.carterangkotapps.model.JsonResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    public static final String Url = "http://192.168.16.12/driver/listdriver.php";
    private List<Data> drivers = new ArrayList<>(  );
    private RecyclerViewAdapter viewAdapter;
    String no_polisi, nama, umur, alamat, no_tlp;
    Toolbar toolbar;
    @BindView( R.id.recyclerView )
    RecyclerView recyclerView;
    @BindView( R.id.progress_bar )
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind( this );
        viewAdapter = new RecyclerViewAdapter(this, drivers);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        recyclerView.setAdapter( viewAdapter );

        loadDataDrivers();
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadDataDrivers();
    }

    private void loadDataDrivers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( Url )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        RequestAPI api = retrofit.create( RequestAPI.class );
        Call<JsonResponse> call = api.view();

        call.enqueue( new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call , Response<JsonResponse> response) {
                String value = response.body().getValue();
                progressBar.setVisibility( View.GONE );
                if (value.equals( "1" )){
                    drivers = response.body().getResult();
                    viewAdapter = new RecyclerViewAdapter( HomeActivity.this, drivers );
                    recyclerView.setAdapter( viewAdapter );
                }

            }

            @Override
            public void onFailure(Call<JsonResponse> call , Throwable t) {
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem menuItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Cari Tipe Mobil");
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        recyclerView.setVisibility( View.GONE );
        progressBar.setVisibility( View.VISIBLE );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( Url )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        RequestAPI api = retrofit.create( RequestAPI.class );
        Call<JsonResponse> call = api.search(newText);

        call.enqueue( new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call , Response<JsonResponse> response) {
                String value = response.body().getValue();
                progressBar.setVisibility( View.GONE );
                recyclerView.setVisibility( View.VISIBLE );
                if (value.equals( "1" )) {
                    drivers = response.body().getResult();
                    viewAdapter = new RecyclerViewAdapter( HomeActivity.this , drivers );
                    recyclerView.setAdapter( viewAdapter );
                }

            }

            @Override
            public void onFailure(Call<JsonResponse> call , Throwable t) {
                progressBar.setVisibility( View.GONE );
            }
        } );

        return true;
    }

    public void onBackPressed() {
        Intent startMain = new Intent(HomeActivity.this, DashboardActivity.class);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }
}