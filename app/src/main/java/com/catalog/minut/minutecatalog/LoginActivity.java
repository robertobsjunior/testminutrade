package com.catalog.minut.minutecatalog;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.catalog.minut.minutecatalog.entidades.Usuario;
import com.catalog.minut.minutecatalog.util.SavedData;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LoginActivity extends SSOGoogleActivity implements FacebookLoginFragment.OnFragmentInteractionListener {



    private Usuario usuario;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(SavedData.getUserAPP(this) != null){
            startActivity(new Intent(LoginActivity.this,CatalogActivity.class));
            finish();
        }else{
            configAutenticacaoGoogle();
            ((SignInButton) findViewById(R.id.sign_in_button)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signIn();
                }
            });

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FacebookLoginFragment fragment = FacebookLoginFragment.newInstance(this);
            fragmentTransaction.add(R.id.fragmentFacebook,fragment);
            fragmentTransaction.commit();

            // GsonBuilder b = new GsonBuilder();
            // Gson gson = b.create();
            setGson(new Gson());

        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == getRcSignIn()) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("TAG", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            setAcct(result.getSignInAccount());
            setUsuario(new Usuario());
            usuario.setUrlFotoGoogle(getAcct().getPhotoUrl().toString());
            usuario.setIdGoole(getAcct().getId());
            usuario.setNomeUsuario(getAcct().getDisplayName());
            SavedData.setUserApp(this,getGson().toJson(usuario));

            startActivity(new Intent(LoginActivity.this,CatalogActivity.class));
            finish();

        }
    }

    @Override
    public void onFragmentInteraction(Usuario usr) {
        SavedData.setUserApp(this,getGson().toJson(usr));
        startActivity(new Intent(LoginActivity.this,CatalogActivity.class));
        finish();

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
