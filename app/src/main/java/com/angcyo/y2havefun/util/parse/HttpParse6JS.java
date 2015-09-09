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
public class HttpParse6JS {
    public static String URL_6JS = "http://m.6jianshi.com/";//手机版和电脑版,得到的页面元素不一样
    public static int currentPage = 1;//当前获取到的页面

    /**
     * 获取6贱事 的页面内容
     *
     * @param url the url
     * @return the 6 jS
     */
    public static List<ContentItem> get(@NonNull String url) {
        ArrayList<ContentItem> items = new ArrayList<>();
        try {
            Elements contents = HttpDoc.getDoc(url).body().select(".content");
            String msg = "", img = "";
            Element itemContent;
            for (Element item : contents) {
                try {
                    itemContent = item.select("> tbody > tr").get(1);
                    try {
                        img = itemContent.select("img").first().attr("src");
                        msg = itemContent.select("a").first().text();
                    } catch (Exception e) {
                        e.printStackTrace();
                        img = "";
                        msg = itemContent.select("span").first().text();
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
//        http://m.6jianshi.com/index.php?hot=0&pic=0&page_id=2
        return "http://m.6jianshi.com/index.php?hot=0&pic=0&page_id=" + (currentPage + 1);
    }


    /**
     * Increment page.
     */
    public static void incrementPage() {
        ++currentPage;
    }
}
