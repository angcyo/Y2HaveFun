package com.angcyo.y2havefun.util.parse;

import android.support.annotation.NonNull;

import com.angcyo.y2havefun.mode.ContentItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by angcyo on 15-08-27-027.
 */
public class HttpParseJYQ {
    //    public static String URL_JYQ = "http://m.juyouqu.com/page/2";
    public static String URL_JYQ = "http://www.juyouqu.com/tease";  //"http://m.juyouqu.com/";
    public static int currentPage = 1;//当前获取到的页面

    public static Document getDoc(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        return Jsoup.parse(connection.getInputStream(), "utf-8", "www.juyouqu.com");
    }

    public static List<ContentItem> get(@NonNull String url) {
        ArrayList<ContentItem> items = new ArrayList<>();
        try {
            Elements contents = getDoc(url).body().select(".article-item");
            Element itemContent;
            for (Element item : contents) {
                String msg = "", img = "";
                try {
                    itemContent = item;
                    try {
                        img = itemContent.select("a").get(1).select("img").first().attr("src");
                    } catch (Exception e) {//如果只有图片的情况
                        e.printStackTrace();
                        img = "";
                    }
                    try {
                        msg = itemContent.select("a").first().text();
                    } catch (Exception e) {
                        e.printStackTrace();
                        msg = "";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                items.add(new ContentItem(msg, img));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * 获取6贱事 下一页 的网址
     *
     * @return the next page
     */
    public static String getNextPage() {
//        http://m.juyouqu.com/page/1  http://www.juyouqu.com/tease/page/2
        return "http://www.juyouqu.com/tease/page/" + (currentPage + 1);
    }

    /**
     * Increment page.
     */
    public static void incrementPage() {
        ++currentPage;
    }

    public static void resetPage() {
        currentPage = 1;
    }

}
