package com.jsjrobotics.defaultTemplate.prioritydownloader.downloader;

import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.jsjrobotics.defaultTemplate.lifecycle.functional.Receiver;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * A Thread to service a @{link DownloadRequest}
 */
public class DownloadThread<T> extends Thread {
    private static final String TAG = "DownloadThread";
    private DownloadRequest request;
    private final ConnectivityManager connMgr;

    public DownloadThread(String name, ConnectivityManager connMgr){
        super(name);
        this.connMgr = connMgr;
        request = null;
    }

    public DownloadThread(DownloadRequest request, ConnectivityManager connMgr){
        super(request.getRequestName());
        this.request = request;
        this.connMgr = connMgr;
    }

    public DownloadThread(String threadName, DownloadRequest request, ConnectivityManager connMgr){
        super(threadName);
        this.request = request;
        this.connMgr = connMgr;
    }

    public void setRequest(DownloadRequest request){
        if(!isAlive()){
            this.request = request;
            setName(request.getRequestName());
        }
    }

    @Override
    public void run(){
        Log.d(TAG, "Executing: " + request.getRequestName());
        Downloader<T> downloader = new Downloader<T>(connMgr);
        switch (request.getDownloadRequestMode()){
            case DOWNLOAD_AS_HANDLER_MESSAGE:
                Object result = downloader.downloadAndConvertInputStream(request.getUrl(), request.getParameters(), request.getHeaders(), request.getUrlDownloadMode(), request.getConverter());
                Handler h = request.getHandler();
                Message msg = h.obtainMessage(request.getMessageWhat(),result);
                h.sendMessage(msg);
                return;
            case DOWNLOAD_AS_INPUTSTREAM:
                HttpURLConnection urlConnection = downloader.downloadAsInputStream(request.getUrl(), request.getParameters(), request.getHeaders(), request.getUrlDownloadMode());
                InputStreamReceiver receiver = request.getInputStreamReceiver();
                if(receiver != null){
                    try {
                        receiver.receiveInputStream(urlConnection.getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Downloader.closeUrlConnection(urlConnection);
                }
                return;
            case DOWNLOAD_AS_TYPED_OBJECT:
                T value = (T) downloader.downloadAndConvertInputStream(request.getUrl(), request.getParameters(), request.getHeaders(), request.getUrlDownloadMode(), request.getConverter());
                Receiver<T> callback = request.getObjectReceiver();
                if(callback != null){
                    callback.accept(value);
                }
                return;
            default:
                throw new IllegalArgumentException("Unhandled download request mode");
        }
    }
}
