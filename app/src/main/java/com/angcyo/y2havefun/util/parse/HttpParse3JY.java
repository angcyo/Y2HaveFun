package com.angcyo.y2havefun.util.parse;

import android.support.annotation.NonNull;

import com.angcyo.y2havefun.mode.ContentItem;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angcyo on 15-08-27-027.
 */
public class HttpParse3JY {
    public static String URL_3JY = "http://m.3jy.com/index.html";
    public static int currentPage = 1;//当前获取到的页面

    public static List<ContentItem> get(@NonNull String url) {
        ArrayList<ContentItem> items = new ArrayList<>();
        try {
            Elements contents = HttpDoc.getDoc(url).body().select(".listbox");
            Element itemContent;
            for (Element item : contents) {
                String msg = "", img = "";
                try {
                    itemContent = item.select(".main_text").first();
                    try {
                        img = itemContent.select("img").first().attr("src");
                        msg = itemContent.select("p").get(1).text();
                    } catch (Exception e) {//如果只有图片的情况
                        e.printStackTrace();
                        img = "";
                        msg = itemContent.select("a").first().text();
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
//        http://m.3jy.com/index/2.html
        return "http://m.3jy.com/index/" + (currentPage + 1) + ".html";
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
