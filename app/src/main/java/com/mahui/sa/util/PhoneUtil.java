package com.mahui.sa.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.mahui.sa.business.phone.model.PhoneModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minghui on 2018/1/23.
 */

public class PhoneUtil {

    public static List<PhoneModel> readPhoneNumberFromLocal(Context context) {
        List<PhoneModel> phoneModels = new ArrayList<>();
        String[] projection = new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };
        //根据Uri查询相应的ContentProvider，cursor为获取到的数据集
        Cursor cursor = context.getContentResolver().query(Uri.parse(Path.PHONE_PATH), projection, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int i = 0;
            do {
                Long id = cursor.getLong(0);
                //获取姓名
                String name = cursor.getString(1);
                PhoneModel phoneModel = new PhoneModel();
                //指定获取NUMBER这一列数据
                String[] phoneProjection = new String[]{
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                };
                phoneModel.nickname = name;

                //根据联系人的ID获取此人的电话号码
                Cursor phonesCusor = context.getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        phoneProjection,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id,
                        null,
                        null);

                //因为每个联系人可能有多个电话号码，所以需要遍历
                List<String> phoneList = new ArrayList<>();
                if (phonesCusor != null && phonesCusor.moveToFirst()) {
                    do {
                        String num = phonesCusor.getString(0);
                        phoneList.add(num);
                    } while (phonesCusor.moveToNext());
                }
                phoneModel.phonenumber = phoneList;
                phoneModels.add(phoneModel);
                i++;
            } while (cursor.moveToNext());
            cursor.close();
        }
        return phoneModels;
    }
}
