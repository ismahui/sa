package com.mahui.sa.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.mahui.sa.R;
import com.mahui.sa.business.photo.model.PhotoResponse;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minghui on 2018/1/17.
 */

public class PhotoUtil {

    public static Bitmap getBitmap(String imageUrl){
        Bitmap bitmap = BitmapFactory.decodeFile(imageUrl);
        return bitmap;
    }

    public static void loadLocalImage(ImageView targetView , String imageUrl){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.drawable_loading)
                .showImageOnFail(R.drawable.icon_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(imageUrl),targetView,options);
    }


    public static List<PhotoResponse> getPhotoFromLocal(Context context){
        List<PhotoResponse> list = new ArrayList<>();
        Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projImage = { MediaStore.Images.Media._ID
                , MediaStore.Images.Media.DATA
                ,MediaStore.Images.Media.SIZE
                ,MediaStore.Images.Media.DISPLAY_NAME};
        Cursor mCursor = context.getContentResolver().query(mImageUri,
                projImage,
                MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                new String[]{"image/jpeg", "image/png"},
                MediaStore.Images.Media.DATE_MODIFIED+" desc");

        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                PhotoResponse photoResponse = new PhotoResponse();
                // 获取图片的路径
                photoResponse.imageUrl = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                list.add(photoResponse);
            }
            mCursor.close();
        }
        return list;
    }
}
