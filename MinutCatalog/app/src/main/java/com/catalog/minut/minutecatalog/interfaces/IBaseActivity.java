package com.catalog.minut.minutecatalog.interfaces;

import java.io.Serializable;

/**
 * Created by btps000230 on 19/01/2016.
 */
public interface IBaseActivity extends Serializable {
    String GET = "get";
    String POST = "post";



    String[] getResultService();
    void getResultJson(String[] rst);
    void callLoad();
    void closeLoad();



}
