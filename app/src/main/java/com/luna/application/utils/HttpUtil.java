package com.luna.application.utils;

import android.app.Activity;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

	/**
	 * GET请求网络数据
	 * @param activity
	 * @param url 接口获取的地址
	 * @param onHttpRepsonLinstener 接口回调，获取完成的数据通过它来传递到页面
	 */
	public static void getUrl2Net(final Activity activity, final String url, final OnHttpRepsonLinstener onHttpRepsonLinstener){
		Log.e("url:",url);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				getUrlConnectJson(activity, url, "", onHttpRepsonLinstener);
			}
		}).start();
	}


	/**
	 * POST请求方式
	 * @param activity
	 * @param url
	 * @param action 方式post请求参数用的
	 * @param onHttpRepsonLinstener
	 */
	public static void getUrl2NetPost(final Activity activity, final String url, final String action, final OnHttpRepsonLinstener onHttpRepsonLinstener){
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				getPostUrlConnectJson(activity, url, action, onHttpRepsonLinstener);
			}
		}).start();
	}


	public static void getPostUrlConnectJson(Activity aactivity,
											 final String urlString, String action,
											 final OnHttpRepsonLinstener onHttpRepsonLinstener) {
		String json = "";
		try {
			final URL url = new URL(urlString);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setRequestProperty("accept", "*/*");
			urlConnection.setRequestProperty("connection", "Keep-Alive");
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			urlConnection.setRequestProperty("charset", "utf-8");
			urlConnection.setUseCaches(false);
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);

			OutputStream outputStream = urlConnection.getOutputStream();
			String data = action;
			outputStream.write(data.getBytes());
			outputStream.flush();

			int code = urlConnection.getResponseCode();
			if (code == 200) {
				InputStream inputStream = urlConnection.getInputStream();

				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				int len = -1;
				byte[] buf = new byte[128];
				while ((len = inputStream.read(buf)) != -1) {
					byteArrayOutputStream.write(buf, 0, len);
				}
				byteArrayOutputStream.flush();
				final String jsonString = byteArrayOutputStream.toString();
				Log.e("TAG", "user:" + jsonString);
				aactivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						onHttpRepsonLinstener.onGetString(jsonString);
					}
				});

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/*
		get请求方式
	 */
	public static void getUrlConnectJson(Activity aactivity,
										 final String urlString, String action,
										 final OnHttpRepsonLinstener onHttpRepsonLinstener) {
		String json = "";
		try {
			final URL url = new URL(urlString);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setRequestProperty("accept", "*/*");
			urlConnection.setRequestProperty("connection", "Keep-Alive");
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			urlConnection.setRequestProperty("charset", "utf-8");
			urlConnection.setUseCaches(false);
			int code = urlConnection.getResponseCode();
			if (code == 200) {
				InputStream inputStream = urlConnection.getInputStream();

				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				int len = -1;
				byte[] buf = new byte[128];
				while ((len = inputStream.read(buf)) != -1) {
					byteArrayOutputStream.write(buf, 0, len);
				}
				byteArrayOutputStream.flush();
				final String jsonString = byteArrayOutputStream.toString();
				Log.e("TAG", "user:" + jsonString);
				aactivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						onHttpRepsonLinstener.onGetString(jsonString);
					}
				});

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public interface OnHttpRepsonLinstener {
		void onGetString(String json);
	}
}
