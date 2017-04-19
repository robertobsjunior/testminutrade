package com.catalog.minut.minutecatalog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.catalog.minut.minutecatalog.adapters.CatalogAdapter;
import com.catalog.minut.minutecatalog.entidades.Product;
import com.catalog.minut.minutecatalog.util.AppActivity;
import com.catalog.minut.minutecatalog.util.BaseConstantes;
import com.catalog.minut.minutecatalog.util.GridSpacingItemDecoration;
import com.catalog.minut.minutecatalog.util.SavedData;
import com.facebook.login.LoginManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Product> itens;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setmRecyclerView((RecyclerView) findViewById(R.id.my_recycler_view));

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        getmRecyclerView().setHasFixedSize(true);

              setmLayoutManager(new LinearLayoutManager(this));
        getmRecyclerView().setLayoutManager(getmLayoutManager());
        setItens(new ArrayList<Product>());




        ClientRestCustomer task = new ClientRestCustomer(this,
                BaseConstantes.urlProduct, BaseConstantes.GET);
        task.execute(getGson().toJson(null));




    }

    /**
     * Converting dp to pixel
     */
    public int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            LoginManager.getInstance().logOut();
            SavedData.setUserApp(this,null);
            startActivity(new Intent(CatalogActivity.this,LoginActivity.class));
            finish();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }


    @Override
    public String[] getResultService() {
        try {
            closeLoad();
            if (getRetornoJson()[0].equals("200")) {
                JsonParser parser = new JsonParser();
                JsonArray array = parser.parse(getRetornoJson()[1]).getAsJsonArray();
                for (int i = 0; i < array.size()-1; i++) {
                    Product prd = getGson().fromJson(array.get(i), Product.class);
                    getItens().add(prd);
                }
                setmAdapter(new CatalogAdapter(getItens(),this,this.getFragmentManager()));
                getmRecyclerView().setAdapter(getmAdapter());
                getmRecyclerView().addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(3), true,this));


            }else {
                showToastMensagers(getRetornoJson()[0],this);
            }


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, getResources().getString(R.string.msg_sem_conexao), Toast.LENGTH_SHORT).show();
        }
        return new String[0];

    }

    @Override
    public void getResultJson(String[] rst) {
        // specify an adapter (see also next example)



    }

    @Override
    public void callLoad() {

    }

    @Override
    public void closeLoad() {

    }

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    public void setmRecyclerView(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }

    public RecyclerView.Adapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(RecyclerView.Adapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public RecyclerView.LayoutManager getmLayoutManager() {
        return mLayoutManager;
    }

    public void setmLayoutManager(RecyclerView.LayoutManager mLayoutManager) {
        this.mLayoutManager = mLayoutManager;
    }

    public List<Product> getItens() {
        return itens;
    }

    public void setItens(List<Product> itens) {
        this.itens = itens;
    }


}
