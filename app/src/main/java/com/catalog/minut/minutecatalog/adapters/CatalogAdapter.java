package com.catalog.minut.minutecatalog.adapters;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.catalog.minut.minutecatalog.MyItenDialogFragment;
import com.catalog.minut.minutecatalog.R;
import com.catalog.minut.minutecatalog.entidades.Product;
import com.catalog.minut.minutecatalog.util.BaseConstantes;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by robertosampaio on 17/04/17.
 */

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder> {
    private  View  view;
    private List<Product> listaItens;
    private Context context;
    private FragmentManager frgmaFragmentManager;

    public CatalogAdapter(List<Product> listaItens, Context context, FragmentManager frgmaFragmentManager){
        this.listaItens = listaItens;
        this.context =context;
        this.frgmaFragmentManager = frgmaFragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_product_catalog, parent, false);
        ViewHolder vHolder = new ViewHolder(
                ((ImageView) view.findViewById(R.id.imgProd)),
                ((TextView) view.findViewById(R.id.nomeProd)),
                ((CardView) view.findViewById(R.id.card_view)),
                view
        );



        return vHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtTitulo.setText(listaItens.get(position).getNameProduct());
        Picasso.with(context)
                .load(BaseConstantes.URL_IMG+listaItens.get(position).getImage().getUrl())
                .into(holder.imgItem);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyItenDialogFragment newFragment = MyItenDialogFragment.newInstance(listaItens.get(position));
                newFragment.show(getFrgmaFragmentManager(), "dialog");
            }
        });

    }



    @Override
    public int getItemCount() {
        return listaItens.size();
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<Product> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<Product> listaItens) {
        this.listaItens = listaItens;
    }

    public FragmentManager getFrgmaFragmentManager() {
        return frgmaFragmentManager;
    }

    public void setFrgmaFragmentManager(FragmentManager frgmaFragmentManager) {
        this.frgmaFragmentManager = frgmaFragmentManager;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgItem;
        public TextView txtTitulo;
        public CardView cardView;

        public ViewHolder(
                ImageView imgItem,
                TextView txtTitulo,
                CardView cardView,
                View v) {
            super(v);

            this.imgItem = imgItem;
            this.txtTitulo = txtTitulo;
            this.cardView = cardView;
        }
    }


}
