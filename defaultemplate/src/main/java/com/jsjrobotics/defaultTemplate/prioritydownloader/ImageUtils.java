package com.jsjrobotics.defaultTemplate.prioritydownloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;

import com.jsjrobotics.defaultTemplate.lifecycle.functional.Receiver;
import com.jsjrobotics.defaultTemplate.prioritydownloader.downloader.DownloadRequest;
import com.jsjrobotics.defaultTemplate.prioritydownloader.downloader.InputStreamReceiver;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ImageUtils {
    private static final int BUFFER_SIZE = 1024;

    public static void downloadAndDisplayImage(final Fragment fragment, final PriorityDownloader downloader, final ImageView photo, String url) {
        final DownloadRequest request= new DownloadRequest<>(
                new InputStreamReceiver() {
                    @Override
                    public void receiveInputStream(InputStream stream) {
                        byte[] data = inputStreamToByteArray(stream);
                        BitmapFactory.Options sourceAttributes = readImageAttributes(data);
                        int inSampleSize = 2;
                        if(photo.getHeight() == 0 || photo.getWidth() == 0){
                            Log.e("DownloadImage", "Can't downscale image because image size not set");
                        } else {
                            inSampleSize = calculateInSampleSize(sourceAttributes, photo.getWidth(), photo.getHeight());

                        }
                        final Bitmap bitmap = decodeBitmapFromStream(sourceAttributes, inSampleSize, data);
                        if(bitmap != null && fragment.getActivity() != null){
                            fragment.getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    photo.setImageBitmap(bitmap);
                                }
                            });
                        }
                    }
                },
                url,
                Priorities.MEDIUM,
                url
        );
        photo.post(new Runnable() {
            @Override
            public void run() {
                downloader.queueRequest(request);
            }
        });
    }

    public static void downloadImage(final Fragment fragment, final Receiver<Bitmap> receiver, final PriorityDownloader downloader, final int desiredWidth, final int desiredHeight, String url) {
        final DownloadRequest request= new DownloadRequest<>(
                new InputStreamReceiver() {
                    @Override
                    public void receiveInputStream(InputStream stream) {
                        byte[] data = inputStreamToByteArray(stream);
                        BitmapFactory.Options sourceAttributes = readImageAttributes(data);
                        int inSampleSize = 2;
                        if(desiredWidth == 0 || desiredHeight == 0){
                            Log.e("DownloadImage", "Can't downscale image because image size not set");
                        } else {
                            inSampleSize = calculateInSampleSize(sourceAttributes, desiredWidth, desiredHeight);

                        }
                        final Bitmap bitmap = decodeBitmapFromStream(sourceAttributes, inSampleSize, data);
                        if(bitmap != null && fragment.getActivity() != null){
                            receiver.accept(bitmap);
                        }
                    }
                },
                url,
                Priorities.MEDIUM,
                url
        );
        downloader.queueRequest(request);
    }


    private static Bitmap decodeBitmapFromStream(BitmapFactory.Options sourceAttributes, int inSampleSize, byte[] data) {
        sourceAttributes.inSampleSize = inSampleSize;
        sourceAttributes.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data,0,data.length, sourceAttributes);
    }

    private static byte[] inputStreamToByteArray(InputStream stream) {
        byte[] buffer = new byte[BUFFER_SIZE];
        byte[] result = new byte[0];
        ArrayList<Byte> data = new ArrayList<>();
        try {
            int bytesRead = stream.read(buffer);
            while(bytesRead != -1){
                for(int index =0; index < bytesRead; index++){
                    data.add(buffer[index]);
                }
                bytesRead = stream.read(buffer);
            }
            result = new byte[data.size()];
            for(int index = 0; index < result.length; index++){
                result[index] = data.get(index);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static BitmapFactory.Options readImageAttributes(byte[] data){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        return options;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options,
            int reqWidth,
            int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}
