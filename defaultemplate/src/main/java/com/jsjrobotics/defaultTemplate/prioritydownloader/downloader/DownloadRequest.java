package com.jsjrobotics.defaultTemplate.prioritydownloader.downloader;

import android.os.Handler;

import com.jsjrobotics.defaultTemplate.lifecycle.functional.Receiver;
import com.jsjrobotics.defaultTemplate.prioritydownloader.Priorities;

import java.util.Collections;
import java.util.Map;

/**
 * A DownloadRequest that can return downloaded data as a raw stream, or an object on a
 * different thread through a handler
 */
public class DownloadRequest<T> {
    private final UrlDownloadMode urlDownloadMode;
    private final String url;
    private final InputStreamReceiver inputStreamReceiver;
    private final Handler handler;
    private final int messageWhat;
    private final Priorities priority;
    private final String requestName;
    private final InputStreamToObject<T> converter;
    private final Map<String, String> parameters;
    private final DownloadRequestMode downloadRequestMode;
    private final Receiver<T> mObjectReceiver;
    private final Map<String, String> mHeaders;
    private final boolean mHasBody;
    private final byte[] mBody;


    /**
     * Build a download request that will call the inputStreamReader upon download completion
     * @param inputStreamReceiver Receives input stream on success, null on failure
     * @param url Url to download
     * @param priority Priority of this request
     * @param requestName Caller supplied request name
     */
    public DownloadRequest(InputStreamReceiver inputStreamReceiver, String url, Priorities priority, String requestName){
        this.priority = priority;
        this.requestName = requestName;
        this.urlDownloadMode = UrlDownloadMode.GET;
        this.parameters = Collections.EMPTY_MAP;
        this.url = url;
        this.inputStreamReceiver = inputStreamReceiver;
        handler = null;
        messageWhat = -1;
        converter = null;
        this.downloadRequestMode = DownloadRequestMode.DOWNLOAD_AS_INPUTSTREAM;
        mObjectReceiver = null;
        mHeaders = Collections.EMPTY_MAP;
        mHasBody = false;
        mBody = null;
    }

    public DownloadRequest(InputStreamReceiver inputStreamReceiver, String url,
                           Priorities priority,
                           String requestName,
                           UrlDownloadMode mode,
                           Map<String, String> parameters,
                           Map<String, String> headers,
                           DownloadRequestMode requestMode){
        this.priority = priority;
        this.requestName = requestName;
        this.urlDownloadMode = mode;
        this.parameters = parameters;
        this.url = url;
        this.inputStreamReceiver = inputStreamReceiver;
        this.downloadRequestMode = requestMode;
        handler = null;
        messageWhat = -1;
        converter = null;
        mObjectReceiver = null;
        mHeaders = headers;
        mHasBody = false;
        mBody = null;
    }

    /**
     * Build a download request that will send a caller specefied object as the message object to the receiving
     * handler upon download completion
     * @param receivingHandler The handler that will receive the download complete method
     * @param messageWhat The what of the received message
     * @param converter An inputstream to object converter to build the message object
     * @param url url to download
     * @param priority Download request priority
     * @param requestName Caller supplied request name
     */
    public DownloadRequest(Handler receivingHandler, int messageWhat, InputStreamToObject<T> converter, String url, Priorities priority, String requestName){
        this.priority = priority;
        this.requestName = requestName;
        this.urlDownloadMode = UrlDownloadMode.GET;
        this.parameters = Collections.EMPTY_MAP;
        this.url = url;
        this.inputStreamReceiver = null;
        downloadRequestMode = DownloadRequestMode.DOWNLOAD_AS_HANDLER_MESSAGE;
        handler = receivingHandler;
        this.messageWhat = messageWhat;
        this.converter = converter;
        mObjectReceiver = null;
        mHeaders = Collections.EMPTY_MAP;
        mHasBody = false;
        mBody = null;
    }

    public DownloadRequest(
            Receiver<T> receiver,
            InputStreamToObject<T> converter,
            String url,
            Map<String, String> parameters,
            Map<String, String> headers,
            Priorities priority,
            String requestName){
        this.priority = priority;
        this.requestName = requestName;
        this.urlDownloadMode = UrlDownloadMode.GET;
        this.parameters = parameters;
        this.url = url;
        this.inputStreamReceiver = null;
        downloadRequestMode = DownloadRequestMode.DOWNLOAD_AS_TYPED_OBJECT;
        handler = null;
        mObjectReceiver = receiver;
        this.messageWhat = -1;
        this.converter = converter;
        mHeaders = headers;
        mHasBody = false;
        mBody = null;
    }

    public DownloadRequest(
            Receiver<T> receiver,
            InputStreamToObject<T> converter,
            String url,
            byte[] body,
            Map<String, String> headers,
            Priorities priority,
            String requestName){
        this.priority = priority;
        this.requestName = requestName;
        this.urlDownloadMode = UrlDownloadMode.GET;
        this.parameters = Collections.EMPTY_MAP;
        this.url = url;
        this.inputStreamReceiver = null;
        downloadRequestMode = DownloadRequestMode.DOWNLOAD_AS_TYPED_OBJECT;
        handler = null;
        mObjectReceiver = receiver;
        this.messageWhat = -1;
        this.converter = converter;
        mHeaders = headers;
        mHasBody = true;
        mBody = body;
    }

    public UrlDownloadMode getUrlDownloadMode() {
        return urlDownloadMode;
    }

    public DownloadRequestMode getDownloadRequestMode() {
        return downloadRequestMode;
    }

    public String getUrl() {
        return url;
    }

    public InputStreamReceiver getInputStreamReceiver() {
        return inputStreamReceiver;
    }

    public Handler getHandler() {
        return handler;
    }

    public int getMessageWhat() {
        return messageWhat;
    }

    public Priorities getPriority() {
        return priority;
    }

    public String getRequestName() {
        return requestName;
    }

    public InputStreamToObject<T> getConverter() {
        return converter;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public Receiver<T> getObjectReceiver() {
        return mObjectReceiver;
    }

    public Map<String, String> getHeaders() {
        return mHeaders;
    }

    public boolean hasBinaryOutput() {
        return mHasBody;
    }

    public byte[] getBinaryBody() {
        return mBody;
    }
}
