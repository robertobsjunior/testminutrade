package com.catalog.minut.minutecatalog.util;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.catalog.minut.minutecatalog.R;
import com.catalog.minut.minutecatalog.http.HttpUtils;
import com.catalog.minut.minutecatalog.interfaces.IBaseActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Created by robertosampaio on 18/09/15.
 */
public abstract class AppActivity extends AppCompatActivity implements IBaseActivity {



    private CharSequence mTitle;
    private Gson gson;
    private String[] retornoJson;
    private int contChamaContinua = 0;


    public static void showToastMensagersAux(String msg, Activity activity) {

        Toast.makeText(activity,msg, Toast.LENGTH_LONG).show();

    }



    public static void showToastMensagers(String codeMsg, Activity activity) {
        if((codeMsg.equals("-1"))){
            Toast.makeText(activity, activity.getString(R.string.msg_sem_conexao), Toast.LENGTH_LONG).show();
        }else if((codeMsg.equals("404"))){
            Toast.makeText(activity, activity.getString(R.string.msg_registro_nao_encontrado), Toast.LENGTH_LONG).show();
        }else if((codeMsg.equals("500"))){
            Toast.makeText(activity, activity.getString(R.string.msg_erro_padrao), Toast.LENGTH_LONG).show();
        }else if((codeMsg.equals("200") || codeMsg.equals("201"))){
            Toast.makeText(activity, activity.getString(R.string.msg_gravado_sucesso), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        GsonBuilder b = new GsonBuilder();
        Gson gson = b.create();
        setGson(gson);


    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public String[] getRetornoJson() {
        return retornoJson;
    }

    public void setRetornoJson(String[] retornoJson) {
        this.retornoJson = retornoJson;
    }

    public int getContChamaContinua() {
        return contChamaContinua;
    }

    public void setContChamaContinua(int contChamaContinua) {
        this.contChamaContinua = contChamaContinua;
    }



    public   class ClientRestCustomer extends AsyncTask<String, Void, String> {
        // private ProgressDialog barraProgresso;
        private Activity objActivity;
        private String tipoRequisicao;
        private String urlService;
        private Context context;

        public ClientRestCustomer(Activity objActivity, String urlService, String tipoRequisicao) {
            this.setObjActivity(objActivity);
            this.setUrlService(urlService);
            this.setTipoRequisicao(tipoRequisicao);
            this.setContext(objActivity.getBaseContext());
        }


        @Override
        protected void onPreExecute() {
            setGson(new Gson());
            //callLoad();
            super.onPreExecute();
        }




        @Override
        protected String doInBackground(String... params) {
            try {
                if (getTipoRequisicao().equals("post")) {
                    setRetornoJson(HttpUtils.acessServicePost(this.getUrlService(), params[0], this.getObjActivity()));
                } else if (getTipoRequisicao().equals("get")) {
                    setRetornoJson(HttpUtils.acessServiceGet(this.getUrlService(), this.getContext()));
                }
                getResultJson(params);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return "sucess";
        }


        @Override
        protected void onPostExecute(final String success) {
            getResultService();

            closeLoad();
            /**if(getBarraProgresso() != null && getBarraProgresso().isShowing()) {
             getBarraProgresso().dismiss();
             setBarraProgresso(null);
             }*/
        }

        @Override
        protected void onCancelled() {

        }



        public Activity getObjActivity() {
            return objActivity;
        }

        public void setObjActivity(Activity objActivity) {
            this.objActivity = objActivity;
        }

        public String getTipoRequisicao() {
            return tipoRequisicao;
        }

        public void setTipoRequisicao(String tipoRequisicao) {
            this.tipoRequisicao = tipoRequisicao;
        }

        public String getUrlService() {
            return urlService;
        }

        public void setUrlService(String urlService) {
            this.urlService = urlService;
        }

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }
    }





}
