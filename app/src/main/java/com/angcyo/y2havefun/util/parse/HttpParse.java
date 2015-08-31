package com.angcyo.y2havefun.util.parse;

import android.support.annotation.NonNull;

import com.angcyo.y2havefun.mode.ContentItem;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by angcyo on 15-08-27-027.
 */
public class HttpParse {
    public static String URL_6JS = "http://m.6jianshi.com/";//手机版和电脑版,得到的页面元素不一样

    public static Element getContentWrap(@NonNull String url, @NonNull String cssQuery) throws IOException {
        Element element = HttpDoc.getDoc(url).select(cssQuery).first();
        return element;
    }


    /**
     * 获取6贱事 的页面内容
     *
     * @param url the url
     * @return the 6 jS
     */
    public static List<ContentItem> get6JS(@NonNull String url) {
        ArrayList<ContentItem> items = new ArrayList<>();
        try {
            Elements contents = HttpDoc.getDoc(url).body().select(".content");
            String msg, img;
            Element itemContent;
            for (Element item : contents) {
                itemContent = item.select("> tbody > tr").get(1);
                msg = itemContent.select("a").first().text();
                img = itemContent.select("img").first().attr("src");

                items.add(new ContentItem(msg, img));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
}
