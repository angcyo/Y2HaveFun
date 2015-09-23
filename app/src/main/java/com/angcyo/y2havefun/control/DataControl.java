package com.angcyo.y2havefun.control;

import com.angcyo.y2havefun.components.RDataPullMgr;
import com.angcyo.y2havefun.components.RDataService;
import com.angcyo.y2havefun.mode.ContentItem;
import com.angcyo.y2havefun.util.parse.HttpParse3JY;
import com.angcyo.y2havefun.util.parse.HttpParse6JS;
import com.angcyo.y2havefun.util.parse.HttpParseJYQ;
import com.angcyo.y2havefun.util.parse.HttpParseLFD;
import com.angcyo.y2havefun.util.parse.HttpParsePFW;
import com.angcyo.y2havefun.util.parse.HttpParseWEG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angcyo on 15-09-08-008.
 */
public class DataControl {
    /**
     * 获取不同网站的数据,从本地获取的;并非网络上
     *
     * @param position the position
     * @return the data
     */
    public static List<ContentItem> getData(int position) {
        //{"6贱事", "叽歪笑话", "捧腹网", "巨有趣", "来福岛", "我恶搞"};
        switch (position) {
            case RDataService.DATA_TYPE_0:
                return RDataPullMgr.getList6JS();
            case RDataService.DATA_TYPE_1:
                return RDataPullMgr.getList3JY();
            case RDataService.DATA_TYPE_2:
                return RDataPullMgr.getListPFW();
            case RDataService.DATA_TYPE_3:
                return RDataPullMgr.getListJYQ();
            case RDataService.DATA_TYPE_4:
                return RDataPullMgr.getListLFD();
            case RDataService.DATA_TYPE_5:
                return RDataPullMgr.getListWEG();
            default:
                return new ArrayList<>();
        }
    }

    /**
     * 下一页...计数器
     *
     * @param position the position
     */
    public static void increment(int position) {
        //{"6贱事", "叽歪笑话", "捧腹网", "巨有趣", "来福岛", "我恶搞"};
        switch (position) {
            case RDataService.DATA_TYPE_0:
                HttpParse6JS.incrementPage();
                break;
            case RDataService.DATA_TYPE_1:
                HttpParse3JY.incrementPage();
                break;
            case RDataService.DATA_TYPE_2:
                HttpParsePFW.incrementPage();
                break;
            case RDataService.DATA_TYPE_3:
                HttpParseJYQ.incrementPage();
                break;
            case RDataService.DATA_TYPE_4:
                HttpParseLFD.incrementPage();
                break;
            case RDataService.DATA_TYPE_5:
                HttpParseWEG.incrementPage();
                break;
            default:
                break;
        }
    }

    /**
     * 获取不同网站 需要更新和加载更多的服务器地址
     *
     * @param taskLoadType the task load type
     * @param position     the position
     * @return the url
     */
    public static String getUrl(int taskLoadType, int position) {
        //{"6贱事", "叽歪笑话", "捧腹网", "巨有趣", "来福岛", "我恶搞"};
        String url = "";
        switch (position) {
            case RDataService.DATA_TYPE_0:
                if (taskLoadType == RDataService.DATA_UPDATE) {
                    url = HttpParse6JS.URL_6JS;
                } else {
                    url = HttpParse6JS.getNextPage();
                }
                break;
            case RDataService.DATA_TYPE_1:
                if (taskLoadType == RDataService.DATA_UPDATE) {
                    url = HttpParse3JY.URL_3JY;
                } else {
                    url = HttpParse3JY.getNextPage();
                }
                break;
            case RDataService.DATA_TYPE_2:
                if (taskLoadType == RDataService.DATA_UPDATE) {
                    url = HttpParsePFW.URL_PFW;
                } else {
                    url = HttpParsePFW.getNextPage();
                }
                break;
            case RDataService.DATA_TYPE_3:
                if (taskLoadType == RDataService.DATA_UPDATE) {
                    url = HttpParseJYQ.URL_JYQ;
                } else {
                    url = HttpParseJYQ.getNextPage();
                }
                break;
            case RDataService.DATA_TYPE_4:
                if (taskLoadType == RDataService.DATA_UPDATE) {
                    url = HttpParseLFD.URL_LFD;
                } else {
                    url = HttpParseLFD.getNextPage();
                }
                break;
            case RDataService.DATA_TYPE_5:
                if (taskLoadType == RDataService.DATA_UPDATE) {
                    url = HttpParseWEG.URL_WEG;
                } else {
                    url = HttpParseWEG.getNextPage();
                }
                break;
            default:
                break;
        }
        return url;
    }

    /**
     * 根据url, 获取不同网站的数据
     *
     * @param position the position
     * @param url      the url
     * @return the list
     */
    public static List<ContentItem> get(int position, String url) {
        List<ContentItem> datas;
        switch (position) {
            case RDataService.DATA_TYPE_0:
                datas = HttpParse6JS.get(url);
                break;
            case RDataService.DATA_TYPE_1:
                datas = HttpParse3JY.get(url);
                break;
            case RDataService.DATA_TYPE_2:
                datas = HttpParsePFW.get(url);
                break;
            case RDataService.DATA_TYPE_3:
                datas = HttpParseJYQ.get(url);
                break;
            case RDataService.DATA_TYPE_4:
                datas = HttpParseLFD.get(url);
                break;
            case RDataService.DATA_TYPE_5:
                datas = HttpParseWEG.get(url);
                break;
            default:
                datas = new ArrayList<>();
                break;
        }
        return datas;
    }

    public static void resetPaget(int position) {
        switch (position) {
            case RDataService.DATA_TYPE_0:
                HttpParse6JS.resetPage();
                break;
            case RDataService.DATA_TYPE_1:
                HttpParse3JY.resetPage();
                break;
            case RDataService.DATA_TYPE_2:
                HttpParsePFW.resetPage();
                break;
            case RDataService.DATA_TYPE_3:
                HttpParseJYQ.resetPage();
                break;
            case RDataService.DATA_TYPE_4:
                HttpParseLFD.resetPage();
                break;
            case RDataService.DATA_TYPE_5:
                HttpParseWEG.resetPage();
                break;
            default:
                break;
        }
    }
}
