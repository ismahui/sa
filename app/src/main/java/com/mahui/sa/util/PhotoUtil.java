package com.mahui.sa.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import com.mahui.sa.business.photo.model.PhotoModel;

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


    public static List<PhotoModel> getPhotoFromLocal(Context context){
        List<PhotoModel> list = new ArrayList<>();
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
                PhotoModel photoModel = new PhotoModel();
                // 获取图片的路径
                photoModel.imageUrl = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                list.add(photoModel);
            }
            mCursor.close();
        }
        return list;
    }
}
