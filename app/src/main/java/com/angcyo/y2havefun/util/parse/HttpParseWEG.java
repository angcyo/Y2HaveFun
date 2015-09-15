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
public class HttpParseWEG {
    public static String URL_WEG = "http://www.woegao.com/zuihaoxiao_30"; //"http://www.woegao.com/e/action/ListInfo/?classid=3";
    public static int currentPage = 1;//当前获取到的页面
    private static String URL_BASE = "http://www.woegao.com";

    public static List<ContentItem> get(@NonNull String url) {
        ArrayList<ContentItem> items = new ArrayList<>();
        try {
            Elements contents = HttpDoc.getDoc(url).body().select(".post");
            String msg = "", img = "";
            Element itemContent;
            for (Element item : contents) {
                try {
                    itemContent = item.select("> .mixed").first();
                    try {
                        img = URL_BASE + itemContent.select("> .pic_text").first().select("img").first().attr("src");
                    } catch (Exception e) {//如果只有图片的情况
                        e.printStackTrace();
                        img = "";
                    }
                    try {
                        msg = itemContent.select("> h4").first().text();
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
        //ttp://www.woegao.com/e/action/ListInfo/index.php?page=2&classid=3&totalnum=915
        //http://www.woegao.com/e/action/ListInfo/index.php?page=9&classid=3&totalnum=915
        //http://www.woegao.com/zuihaoxiao_30/index_2.html
        return "http://www.woegao.com/zuihaoxiao_30/index_" + (currentPage + 1) + ".html";
    }


    /**
     * Increment page.
     */
    public static void incrementPage() {
        ++currentPage;
    }
}
