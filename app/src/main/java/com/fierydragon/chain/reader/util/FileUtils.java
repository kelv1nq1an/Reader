package com.fierydragon.chain.reader.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Copyright KelvinQian
 */
public class FileUtils {
    private static final String TAG = "FileUtils";

    public static String readFileFromAssets(Context context, String fileName) throws IOException {
        if (null == context || null == fileName) {
            return null;
        }
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open(fileName);
        int length = inputStream.available();
        byte[] buffer = new byte[length];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = 0;
        while ((len = inputStream.read(buffer)) > 0) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        byteArrayOutputStream.close();
        inputStream.close();
        return byteArrayOutputStream.toString();
    }
}
