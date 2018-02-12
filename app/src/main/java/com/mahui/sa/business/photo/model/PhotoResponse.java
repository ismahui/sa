package com.mahui.sa.business.photo.model;

import java.io.Serializable;

/**
 * Created by minghui on 2018/1/17.
 */

public class PhotoResponse implements Serializable {
    public String imageUrl;
    public boolean isShowCheckBox;//是否展示checkbox
    public boolean isChecked;//是否选中
    public boolean isNative = true;//图片是本地还是服务端
}
