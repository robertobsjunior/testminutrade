package com.catalog.minut.minutecatalog;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.catalog.minut.minutecatalog.entidades.Product;
import com.catalog.minut.minutecatalog.util.BaseConstantes;
import com.facebook.CallbackManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by robertosampaio on 18/04/17.
 */

public class MyItenDialogFragment extends DialogFragment {


    private static final String ARG_PARAM1 = "param1";
    private ImageView imgItem;
    private TextView txtTitulo;
    private EditText edtComentario;
    private Button btn;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;
    private Product product;

    public static MyItenDialogFragment newInstance(Product product) {
        MyItenDialogFragment fragment = new MyItenDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,product);
        fragment.setArguments(args);
        return fragment;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_detail_product_catalog, container, false);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        if (getArguments() != null) {
            product = (Product) getArguments().getSerializable(ARG_PARAM1);
            imgItem = ((ImageView) v.findViewById(R.id.imgProd));
            txtTitulo = ((TextView) v.findViewById(R.id.nomeProd));
            edtComentario = ((EditText) v.findViewById(R.id.edtComentario));
            btn = ((Button) v.findViewById(R.id.btnShare));

            txtTitulo.setText(product.getNameProduct());
            Picasso.with(this.getActivity())
                    .load(BaseConstantes.URL_IMG+product.getImage().getUrl())
                    .into(imgItem);



            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    /**
                     * A imagem para aparece no compartilhamento deve ser jpg, mas neste servico que criei eu não tenho
                     */
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle(product.getNameProduct())
                            .setImageUrl(Uri.parse(BaseConstantes.URL_IMG+product.getImage().getUrl()))
                            .setContentDescription(edtComentario.getText().toString())
                            .setContentUrl(Uri.parse(getString(R.string.url)))
                            .build();


                    shareDialog.show(linkContent);
                }


            });



        }

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout((ViewGroup.LayoutParams.MATCH_PARENT), ViewGroup.LayoutParams.MATCH_PARENT);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }


    public ImageView getImgItem() {
        return imgItem;
    }

    public void setImgItem(ImageView imgItem) {
        this.imgItem = imgItem;
    }

    public TextView getTxtTitulo() {
        return txtTitulo;
    }

    public void setTxtTitulo(TextView txtTitulo) {
        this.txtTitulo = txtTitulo;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}