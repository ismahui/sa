package com.mahui.sa.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.mahui.sa.business.sms.model.MessageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minghui on 2018/1/16.
 */

public class SmsUtil {
    public static List<MessageModel> getMessage(Context context){
        List<MessageModel> smsList= new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(Uri.parse(Path.SMS_PATH), new String[]{"address", "date", "body", "type"},
                null, null, null);
        while(cursor.moveToNext()){
            MessageModel sms = new MessageModel();
            sms.address = cursor.getString(0);
            sms.date = cursor.getLong(1);
            sms.body = cursor.getString(2);
            sms.type = cursor.getString(3);
            smsList.add(sms);
        }
        return smsList;
    }
}
