package com.katzdan.currencyconverter;

import android.content.Context;
import android.content.res.Resources;

public class Utils {
    public static String getStringByIdName(Context context, String idName) {
        Resources res = context.getResources();
        return res.getString(res.getIdentifier(idName, "string", context.getPackageName()));
    }
}
