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
public class HttpParseLFD {
    public static String URL_LFD = "http://www.laifudao.com/";
    public static int currentPage = 1;//当前获取到的页面

    public static List<ContentItem> get(@NonNull String url) {
        ArrayList<ContentItem> items = new ArrayList<>();
        try {
            Elements contents = HttpDoc.getDoc(url).body().select(".post");
            String msg = "", img = "", title = "";
            Element itemContent;
            for (Element item : contents) {
                try {
                    try {
                        img = item.select("div").first().select("> .pic-content").first().select("img").first().attr("src");
                        msg = "";
                    } catch (Exception e) {//如果只有图片的情况
                        e.printStackTrace();
                        img = "";
                        msg = item.select("div").first().select("> .article-content").first().text();
                    }
                    try {
                        title = item.select("a").first().text();
                    } catch (Exception e) {
                        e.printStackTrace();
                        title = "";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                items.add(new ContentItem(title + "\n" + msg, img));
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
//      http://www.laifudao.com/index_2.htm
        return "http://www.laifudao.com/index_" + (currentPage + 1) + ".html";
    }

    /**
     * Increment page.
     */
    public static void incrementPage() {
        ++currentPage;
    }
}
