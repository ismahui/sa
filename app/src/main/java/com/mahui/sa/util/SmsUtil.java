package com.mahui.sa.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.mahui.sa.business.sms.model.MessageModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by minghui on 2018/1/16.
 */

public class SmsUtil {
    public static List<MessageModel> readMessage(Context context ,int currentOffset){
        List<MessageModel> smsList= new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(Uri.parse(Path.SMS_PATH), new String[]{"address", "date", "body", "type"},
                null, null, ContactsContract.Contacts._ID + " limit " + PageConfig.PAGE_SIZE + " offset " + currentOffset);
        if (cursor != null){
            while(cursor.moveToNext()){
                MessageModel sms = new MessageModel();
                sms.address = cursor.getString(cursor.getColumnIndex("address"));
                sms.date = cursor.getLong(cursor.getColumnIndex("date"));
                sms.body = cursor.getString(cursor.getColumnIndex("body"));
                sms.type = cursor.getString(cursor.getColumnIndex("type"));
                smsList.add(sms);
            }
            cursor.close();
        }
        return smsList;
    }

    public static void writeMessage(Context context,List<MessageModel> list){
        ContentResolver cr = context.getContentResolver();
        for (MessageModel messageModel : list){
            ContentValues values = new ContentValues();
            values.put("address", "+"+messageModel.address);
            values.put("type", 1);
            values.put("date", DateToUnix(String.valueOf(messageModel.date)));
            values.put("body", messageModel.body);
            cr.insert(Uri.parse(Path.SMS_PATH), values);
        }
    }

    public static List<MessageModel> fillData(){
        List<MessageModel> list = new ArrayList<>();
        for(int i=0;i<5;i++){
            MessageModel messageModel = new MessageModel();
            messageModel.date=201108;
            messageModel.address="10086"+i;
            messageModel.body="这是测试数据"+i;
            list.add(messageModel);
        }
        return list;
    }

    public static String UnixToDate(long unix){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM--dd");
        return simpleDateFormat.format(unix);
    }

    public static long DateToUnix(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM--dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}
