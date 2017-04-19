package com.catalog.minut.minutecatalog.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpUtils {

	public static String[] acessServicePut(String endereco, String parametros, Context context)
			throws URISyntaxException {
		String[] result = new String[2];
		if (!verificaConexao(context)) {
			result[0] = "-1";
			return result;
		}
		HttpPut put = new HttpPut(new URI(endereco));
		put.setHeader("Content-type", "application/json");

		StringEntity sEntity;
		try {
			sEntity = new StringEntity(parametros, "UTF-8");
			put.setEntity(sEntity);
			HttpResponse response = HttpClientSingleton.getHttpClientInstace().execute(put);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				result[0] = String.valueOf(response.getStatusLine().getStatusCode());
				InputStream instream = entity.getContent();
				result[1] = retornaStringDoResponse(instream);
				instream.close();
				Log.i("Put", "Result from put JsonPut : " + result[0] + " : " + result[1]);
			} else {
				result[0] = "-1";
				result[1] = "Falha de rede! ";
			}
		} catch (UnsupportedEncodingException e) {
			Log.e("NGVL", "Falha ao acessar Web service", e);
			result[0] = "-1";
			result[1] = "Falha de rede! " + e.getMessage();
		} catch (ClientProtocolException e) {
			Log.e("NGVL", "Falha ao acessar Web service", e);
			result[0] = "-1";
			result[1] = "Falha de rede! " + e.getMessage();
		} catch (IOException e) {
			Log.e("NGVL", "Falha ao acessar Web service", e);
			result[0] = "-1";
			result[1] = "Falha de rede! " + e.getMessage();
		}

		return result;
	}

	public static String[] acessServicePost(String endereco, String parametros, Context context)
			throws URISyntaxException {
		String[] result = new String[2];
		if (!verificaConexao(context)) {
			result[0] = "-1";
			return result;
		}
		HttpPost post = new HttpPost(new URI(endereco));
		post.setHeader("Content-type", "application/json");

		StringEntity sEntity;
		try {
			sEntity = new StringEntity(parametros, "UTF-8");
			post.setEntity(sEntity);
			HttpResponse response = HttpClientSingleton.getHttpClientInstace().execute(post);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				result[0] = String.valueOf(response.getStatusLine().getStatusCode());
				InputStream instream = entity.getContent();
				result[1] = retornaStringDoResponse(instream);
				instream.close();
				Log.i("Post", "Result from post JsonPost : " + result[0] + " : " + result[1]);
			} else {
				result[0] = "-1";
				result[1] = "Falha de rede! ";
			}
		} catch (UnsupportedEncodingException e) {
			Log.e("NGVL", "Falha ao acessar Web service", e);
			result[0] = "-1";
			result[1] = "Falha de rede! " + e.getMessage();
		} catch (ClientProtocolException e) {
			Log.e("NGVL", "Falha ao acessar Web service", e);
			result[0] = "-1";
			result[1] = "Falha de rede! " + e.getMessage();
		} catch (IOException e) {
			Log.e("NGVL", "Falha ao acessar Web service", e);
			result[0] = "-1";
			result[1] = "Falha de rede! " + e.getMessage();
		}

		return result;
	}

	public static String[] acessServiceGet(String url, Context context) throws IOException {
		String[] result = new String[2];
		if (!verificaConexao(context)) {
			result[0] = "-1";
			return result;
		}
		HttpGet httpget = new HttpGet(url);
		HttpResponse response;

		try {
			response = HttpClientSingleton.getHttpClientInstace().execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				result[0] = String.valueOf(response.getStatusLine().getStatusCode());
				InputStream instream = entity.getContent();
				result[1] = retornaStringDoResponse(instream);
				instream.close();
				Log.i("Get", "Result from get JsonGet: " + result[0] + " : " + result[1]);
			}
		} catch (Exception e) {
			Log.e("NGVL", "Falha ao acessar Web service", e);
			result[0] = "-1";
			result[1] = "Falha de rede! " + e.getMessage();
		}
		return result;
	}

	public static InputStream httpGet(String urlString) throws IOException {
		HttpGet httpget = new HttpGet(urlString);
		HttpResponse response;

		try {
			response = HttpClientSingleton.getHttpClientInstace().execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				instream.close();
				return instream;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("NGVL", "Falha ao acessar Web service", e);
		}

		return null;
	}

	private static String retornaStringDoResponse(InputStream input) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(input, "utf-8"));
		String retorno = null;
		StringBuilder sb = new StringBuilder();

		while ((retorno = buffer.readLine()) != null) {
			sb.append(retorno);
		}

		input.close();
		return sb.toString();
	}

	/*
	 * Função para verificar existência de conexão com a internet
	 */
	public static boolean verificaConexao(Context context) {
		boolean conectado;
		ConnectivityManager conectivtyManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conectivtyManager.getActiveNetworkInfo() != null
				&& conectivtyManager.getActiveNetworkInfo().isAvailable()
				&& conectivtyManager.getActiveNetworkInfo().isConnected()) {
			conectado = true;
		} else {
			conectado = false;
		}
		return conectado;
	}

}
