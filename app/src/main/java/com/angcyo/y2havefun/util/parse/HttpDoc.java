package com.angcyo.y2havefun.util.parse;

import android.support.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

/**
 * Created by angcyo on 15-08-27-027.
 */
public class HttpDoc {
    public static Document getDoc(@NonNull String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        return document;
    }

    public static Document getDoc(File file, String charsetName)
            throws IOException {
        return Jsoup.parse(file, charsetName);
    }
}
