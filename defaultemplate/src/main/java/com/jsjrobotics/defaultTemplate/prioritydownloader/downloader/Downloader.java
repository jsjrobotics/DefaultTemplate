package com.jsjrobotics.defaultTemplate.prioritydownloader.downloader;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * A downloader that uses @{link DownloadRequest} to queue downloads
 */
public class Downloader<T> {
    private static final String TAG = "Downloader";
    private static final String CHARSET = "UTF-8";
    private final ConnectivityManager connMgr;

    public Downloader(ConnectivityManager manager) {
        this.connMgr = manager;
    }


    private boolean isOnline(){
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn = networkInfo.isConnected();
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo.isConnected();
        return isMobileConn || isWifiConn;
    }

    public String downloadAsString(String url,  Map<String, String> parameters, Map<String, String> headers, UrlDownloadMode mode){
        HttpURLConnection urlConnection = downloadAsInputStream(url, parameters, headers, mode);
        InputStreamToObject<String> stringConverter = InputStreamToObjectConverters.getStringConverter();
        String result = null;
        try {
            result = stringConverter.convertInputStreamToObject(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeUrlConnection(urlConnection);
        return result;
    }

    public T downloadAndConvertInputStream(String url, Map<String, String> parameters, Map<String, String> headers,UrlDownloadMode mode, InputStreamToObject<T> converter){
        if(converter == null){
            Log.e(TAG, "Invalid parameters in downloadAndConvertInputStream");
            return null;
        }
        HttpURLConnection urlConnection = downloadAsInputStream(url, parameters, headers, mode);
        T result = null;
        try {
            if (urlConnection != null) {
                result = converter.convertInputStreamToObject(urlConnection.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeUrlConnection(urlConnection);
        return result;
    }

    public HttpURLConnection downloadAsInputStream(
            String urlToDownload,
            byte[] entity,
            Map<String,String> headers,
            UrlDownloadMode mode){
        if(!isOnline()){
            return null;
        }
        InputStream inputStream = null;
        try {
            URL url = new URL(urlToDownload);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod(mode.name());
            conn.setDoInput(true);
            if(!headers.isEmpty()){
                for( String key : headers.keySet()){
                    String value = headers.get(key);
                    if(!isEmpty(value) && !isEmpty(key)){
                        conn.setRequestProperty(key, value);
                    }
                }
            }
            if(entity != null){
                String crlf = "\r\n";
                String twoHyphens = "--";
                String boundary =  "*****";
                conn.setDoOutput(true);
                conn.setFixedLengthStreamingMode(entity.length);
                DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream());
                outputStream.writeBytes(twoHyphens + boundary + crlf);
                outputStream.writeBytes("Content-Disposition: form-data; name=\"" +
                        "userProfileImage" + "\";filename=\"" +
                        "userProfileImage.jpg" + "\"" + crlf);
                outputStream.writeBytes(crlf);
                outputStream.write(entity);
                outputStream.writeBytes(crlf);
                outputStream.writeBytes(twoHyphens + boundary + twoHyphens + crlf);
                outputStream.flush();
                outputStream.close();
            }
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(TAG, "The response is: " + response);
            return conn;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(TAG,"Malformed url exception");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,"IOException");
        }
        return null;
    }


    public HttpURLConnection downloadAsInputStream(
            String urlToDownload,
            Map<String,String> parameters,
            Map<String,String> headers,
            UrlDownloadMode mode){
        String query = buildQuery(parameters);
        return downloadAsInputStream(urlToDownload+query, (byte[])null, headers, mode);
    }

    private String buildQuery(Map<String, String> parameters) {
        if(parameters == null || parameters.isEmpty()){
            return "";
        }

        boolean firstParam = true;
        StringBuilder builder = new StringBuilder();
        for(String key : parameters.keySet()){
            if(firstParam){
                firstParam = false;
            } else {
                builder.append("&");
            }
            String urlKey;
            String urlValue;
            try {
                if(key == null){
                    Log.e(TAG,"Invalid key: "+key);
                    continue;
                }
                urlKey = URLEncoder.encode(key, CHARSET);
                urlValue = URLEncoder.encode(parameters.get(key), CHARSET);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.e(TAG,"Failed to encode key:"+key+" - value:"+parameters.get(key));
                continue;
            }
            builder.append(urlKey).append("=").append(urlValue);

        }
        return builder.toString();
    }

    public static void closeUrlConnection(HttpURLConnection urlConnection) {
        if(urlConnection == null){
            return;
        }
        try {
            InputStream is = urlConnection.getInputStream();
            if(is != null){
                is.close();
            }
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,"Failed to close input stream");
        }
    }

    public static boolean isEmpty(String input) {
        return input == null || input.isEmpty();
    }


}
