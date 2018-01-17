package com.mahui.sa.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by minghui on 2018/1/17.
 */

public class Nav {
    public static void  nav(Activity currentActivity, Class targetActivity , Bundle bundle){
        Intent intent = new Intent(currentActivity,targetActivity);
        if(bundle != null){
            intent.putExtra("param",bundle);
        }
        currentActivity.startActivity(intent);

    }
}
