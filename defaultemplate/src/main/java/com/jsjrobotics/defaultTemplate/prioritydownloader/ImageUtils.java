package com.jsjrobotics.defaultTemplate.prioritydownloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;

import com.jsjrobotics.defaultTemplate.lifecycle.appCompat.DefaultAppCompatLifecycleFragment;
import com.jsjrobotics.defaultTemplate.lifecycle.functional.Receiver;
import com.jsjrobotics.defaultTemplate.lifecycle.functional.WeakReferenceSupplier;
import com.jsjrobotics.defaultTemplate.prioritydownloader.downloader.DownloadRequest;
import com.jsjrobotics.defaultTemplate.prioritydownloader.downloader.InputStreamReceiver;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ImageUtils {
    private static final int BUFFER_SIZE = 1024;
    private static final int FILE_IMAGE_HEIGHT_PX = 1200;
    private static final int FILE_IMAGE_WIDTH_PX = 1600;
    private static final int TWENTY_MB = 20 * 1024 * 1024;
    private static final String TAG = "ImageUtils";

    public static void downloadAndDisplayImage(
            final WeakReferenceSupplier<Fragment> fragment,
            final PriorityDownloader downloader,
            final WeakReferenceSupplier<ImageView> photoSupplier,
            final String url,
            final int downloadTagKey
    ) {
        final DownloadRequest<String> request= new DownloadRequest<>(
                new InputStreamReceiver() {
                    @Override
                    public void receiveInputStream(InputStream stream) {
                        if (stream == null) {
                            return;
                        }
                        final BufferedInputStream input = new BufferedInputStream(stream);
                        final BitmapFactory.Options sourceAttributes = readImageAttributes(input);
                        if (sourceAttributes == null){
                            return;
                        }

                        int inSampleSize = 2;
                        if (photoSupplier.isPresent()) {
                            int height = photoSupplier.value().getHeight();
                            int width = photoSupplier.value().getWidth();
                            if(height == 0 || width == 0){
                                Log.e("DownloadImage", "Can't downscale image because image size not set");
                            } else {
                                inSampleSize = calculateInSampleSize(sourceAttributes, width, height);

                            }
                        }
                        final Bitmap bitmap = decodeBitmapFromStream(sourceAttributes, inSampleSize, input);
                        if(bitmap != null ){
                            DefaultAppCompatLifecycleFragment.runOnUiThread(fragment, new Runnable() {
                                @Override
                                public void run() {
                                    photoSupplier.ifPresent(new Receiver<ImageView>() {
                                        @Override
                                        public void accept(ImageView photo) {
                                            if (photo.getTag(downloadTagKey).equals(url)) {
                                                photo.setImageBitmap(bitmap);
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    }

                },
                url,
                Priorities.MEDIUM,
                url
        );
        photoSupplier.get().ifPresent(new Receiver<ImageView>() {
            @Override
            public void accept(final ImageView photo) {
                photo.post(new Runnable() {
                    @Override
                    public void run() {
                        photo.setTag(downloadTagKey, url);
                        photo.setImageDrawable(null);
                        downloader.queueRequest(request);
                    }
                });
            }
        });
    }



    public static void downloadAndDisplayImage(
            final Receiver<Bitmap> receiver,
            final PriorityDownloader downloader,
            final WeakReferenceSupplier<ImageView> photoSupplier,
            final String url) {
        final DownloadRequest request= new DownloadRequest<>(
                new InputStreamReceiver() {
                    @Override
                    public void receiveInputStream(InputStream stream) {
                        if (stream == null) {
                            return;
                        }
                        BufferedInputStream input = new BufferedInputStream(stream);
                        BitmapFactory.Options sourceAttributes = readImageAttributes(input);
                        if (sourceAttributes == null){
                            return;
                        }
                        int inSampleSize = 2;
                        if (photoSupplier.get().isPresent()) {
                            int height = photoSupplier.value().getHeight();
                            int width = photoSupplier.value().getWidth();
                            if(height == 0 || width == 0){
                                Log.e("DownloadImage", "Can't downscale image because image size not set");
                            } else {
                                inSampleSize = calculateInSampleSize(sourceAttributes, width, height);

                            }
                        }

                        final Bitmap bitmap = decodeBitmapFromStream(sourceAttributes, inSampleSize, stream);
                        if(bitmap != null){
                            receiver.accept(bitmap);
                        }
                    }
                },
                url,
                Priorities.MEDIUM,
                url
        );
        photoSupplier.ifPresent(new Receiver<ImageView>() {
            @Override
            public void accept(final ImageView imageView) {
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(null);
                        downloader.queueRequest(request);
                    }
                });
            }
        });
    }

    public static void downloadImage(final WeakReferenceSupplier<Fragment> fragment, final Receiver<Bitmap> receiver, final PriorityDownloader downloader, final int desiredWidth, final int desiredHeight, String url) {
        final DownloadRequest request= new DownloadRequest<>(
                new InputStreamReceiver() {
                    @Override
                    public void receiveInputStream(InputStream stream) {
                        BufferedInputStream input = new BufferedInputStream(stream);
                        BitmapFactory.Options sourceAttributes = readImageAttributes(input);
                        if (sourceAttributes == null){
                            return;
                        }
                        int inSampleSize = 2;
                        if(desiredWidth == 0 || desiredHeight == 0){
                            Log.e("DownloadImage", "Can't downscale image because image size not set");
                        } else {
                            inSampleSize = calculateInSampleSize(sourceAttributes, desiredWidth, desiredHeight);

                        }
                        final Bitmap bitmap = decodeBitmapFromStream(sourceAttributes, inSampleSize, stream);
                        if(bitmap != null && fragment.isPresent()){
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


    private static Bitmap decodeBitmapFromByteArray(BitmapFactory.Options sourceAttributes, int inSampleSize, byte[] data) {
        sourceAttributes.inSampleSize = inSampleSize;
        sourceAttributes.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data,0,data.length, sourceAttributes);
    }

    private static Bitmap decodeBitmapFromStream(BitmapFactory.Options sourceAttributes, int inSampleSize, InputStream data) {
        sourceAttributes.inSampleSize = inSampleSize;
        sourceAttributes.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeStream(data,null, sourceAttributes);
        } catch (OutOfMemoryError error){
            Log.e(TAG,"Out of memory decodeBitmapFromStream");
            return null;
        }
    }

    private static BitmapFactory.Options readImageAttributes(BufferedInputStream stream) {
        stream.mark(TWENTY_MB);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(stream, null, options);
        try {
            stream.reset();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,"Failed to decode image");
            return null;
        }
        return options;
    }

    private static byte[] inputStreamToByteArray(InputStream stream) {
        if(stream == null){
            return null;
        }
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

    public static Bitmap inflateImageFromFile(Context context, String filePath) {
        InputStream stream;
        try {
            stream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        byte[] data = inputStreamToByteArray(stream);
        BitmapFactory.Options sourceAttributes = readImageAttributes(data);
        int inSampleSize = calculateInSampleSize(sourceAttributes, FILE_IMAGE_WIDTH_PX, FILE_IMAGE_HEIGHT_PX);
        return decodeBitmapFromByteArray(sourceAttributes, inSampleSize, data);
    }

    private static float dpToPixels(Context context, int valueInDp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp , context.getResources().getDisplayMetrics());
    }

}
