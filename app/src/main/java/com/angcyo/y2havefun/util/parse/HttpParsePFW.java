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
public class HttpParsePFW {
    public static String URL_PFW = "http://www.pengfu.com/";
    public static int currentPage = 1;//当前获取到的页面

    public static List<ContentItem> get(@NonNull String url) {
        ArrayList<ContentItem> items = new ArrayList<>();
        try {
            Elements contents = HttpDoc.getDoc(url).body().select(".tieziBox");
            String msg = "", img = "";
            Element itemContent;
            for (Element item : contents) {
                try {
                    itemContent = item.select(".imgbox").first().select(".humordatacontent").first();
                    try {
                        img = itemContent.select("img").first().attr("src");
                    } catch (Exception e) {//如果只有图片的情况
                        e.printStackTrace();
                        img = "";
                    }
                    try {
                        msg = itemContent.text().replaceAll(itemContent.select("> div").first().text(), "");
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
//        http://www.pengfu.com/index_2.html
        return "http://www.pengfu.com/index_" + (currentPage + 1) + ".html";
    }


    /**
     * Increment page.
     */
    public static void incrementPage() {
        ++currentPage;
    }
}
