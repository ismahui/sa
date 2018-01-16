package com.mahui.sa.business.sms.model;

import java.io.Serializable;

/**
 * Created by minghui on 2018/1/16.
 */

public class MessageModel implements Serializable {
    public String address;
    public long date;
    public String body;
    public String type;
}
