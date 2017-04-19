package com.catalog.minut.minutecatalog.entidades;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Image
  implements Serializable
{


  @SerializedName("urlImgBandeira")
  private String url;


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
