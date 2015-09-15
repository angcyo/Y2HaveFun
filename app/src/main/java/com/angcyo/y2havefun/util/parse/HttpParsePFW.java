package com.angcyo.y2havefun.util.parse;

import android.support.annotation.NonNull;

import com.angcyo.y2havefun.mode.ContentItem;
import com.angcyo.y2havefun.util.Util;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angcyo on 15-08-27-027.
 */
public class HttpParsePFW {
    public static String URL_PFW = "http://pengfu.tiexue.net"; //"http://www.pengfu.com/";//http://pengfu.tiexue.net/index_1.html 域名更换
    public static int currentPage = 1;//当前获取到的页面

    public static List<ContentItem> get(@NonNull String url) {
        ArrayList<ContentItem> items = new ArrayList<>();
        try {
            Elements contents = HttpDoc.getDoc(url).body().select(".tieziBox");
            String msg = "", img = "", title = "";
            Element itemContent;
            for (Element item : contents) {
                try {
                    itemContent = item.select("> .contFont").first();
                    try {
                        img = itemContent.select("> .imgbox").first().select("a > img").first().attr("src");
                    } catch (Exception e) {//没有图片
                        e.printStackTrace();
                    }
                    try {
                        title = itemContent.select("> .tieTitle > a").first().text();
                    } catch (Exception e) {//没有标题,
                        e.printStackTrace();
                    }
                    try {
                        msg = itemContent.select("> .imgbox > div").first().text().replaceAll(itemContent.select("> .imgbox > span").first().text(), "");
                    } catch (Exception e) {//没有内容
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (Util.isEmpty(msg) && Util.isEmpty(title)) {
                    title += "\n" + msg;
                } else {
                    title += msg;
                }
                items.add(new ContentItem(title, img));
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
        return "http://pengfu.tiexue.net/index_" + (currentPage + 1) + ".html";
    }


    /**
     * Increment page.
     */
    public static void incrementPage() {
        ++currentPage;
    }
}
