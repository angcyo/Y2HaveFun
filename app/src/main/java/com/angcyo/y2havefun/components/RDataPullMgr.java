package com.angcyo.y2havefun.components;

import android.content.Context;

import com.angcyo.y2havefun.mode.ContentItem;
import com.angcyo.y2havefun.mode.event.LoadedEvent;
import com.angcyo.y2havefun.mode.realm.Realm3JY;
import com.angcyo.y2havefun.mode.realm.Realm6JS;
import com.angcyo.y2havefun.mode.realm.RealmJYQ;
import com.angcyo.y2havefun.mode.realm.RealmLFD;
import com.angcyo.y2havefun.mode.realm.RealmPFW;
import com.angcyo.y2havefun.mode.realm.RealmWEG;
import com.angcyo.y2havefun.util.Logger;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * 用于管理数据拉取的组件
 * Created by angcyo on 15-09-06-006.
 */
public class RDataPullMgr {

    //{"6贱事", "叽歪笑话", "捧腹网", "巨有趣", "来福岛", "我恶搞"};
    private static List<ContentItem> list6JS = new ArrayList<>();
    private static List<ContentItem> list3JY = new ArrayList<>();
    private static List<ContentItem> listPFW = new ArrayList<>();
    private static List<ContentItem> listJYQ = new ArrayList<>();
    private static List<ContentItem> listLFD = new ArrayList<>();
    private static List<ContentItem> listWEG = new ArrayList<>();

    private static Thread thread;
    private static Realm realm;

    public static void initDataAsync(Context context) {
        //从本地数据库装载历史数据
        if (thread == null) {
            thread = new Thread(new LoadRunnable(context));
            thread.start();
        }
    }

    public static void saveDataAsync(Context context, int type, List<ContentItem> lists) {
        //保存数据到本地
        if (thread == null) {
            thread = new Thread(new SaveRunnable(context, type, lists));
            thread.start();
        }
    }

    //{"6贱事", "叽歪笑话", "捧腹网", "巨有趣", "来福岛", "我恶搞"};
    public static void loadFromDB(Context context) {
        realm = getRealm(context);
        RealmResults<Realm6JS> results6JS = realm.allObjects(Realm6JS.class);
        RealmResults<Realm3JY> results3JY = realm.allObjects(Realm3JY.class);
        RealmResults<RealmPFW> resultsPFW = realm.allObjects(RealmPFW.class);
        RealmResults<RealmJYQ> resultsJYQ = realm.allObjects(RealmJYQ.class);
        RealmResults<RealmLFD> resultsLFD = realm.allObjects(RealmLFD.class);
        RealmResults<RealmWEG> resultsWEG = realm.allObjects(RealmWEG.class);


        for (Realm6JS item : results6JS) {
            list6JS.add(new ContentItem(item.getContent(), item.getImgUrl()));
        }
        for (Realm3JY item : results3JY) {
            list3JY.add(new ContentItem(item.getContent(), item.getImgUrl()));
        }
        for (RealmPFW item : resultsPFW) {
            listPFW.add(new ContentItem(item.getContent(), item.getImgUrl()));
        }
        for (RealmJYQ item : resultsJYQ) {
            listJYQ.add(new ContentItem(item.getContent(), item.getImgUrl()));
        }
        for (RealmLFD item : resultsLFD) {
            listLFD.add(new ContentItem(item.getContent(), item.getImgUrl()));
        }
        for (RealmWEG item : resultsWEG) {
            listWEG.add(new ContentItem(item.getContent(), item.getImgUrl()));
        }

        Logger.e("results6JS --> " + results6JS.size());
        Logger.e("results3JY --> " + results3JY.size());
        Logger.e("resultsPFW --> " + resultsPFW.size());
        Logger.e("resultsJYQ --> " + resultsJYQ.size());
        Logger.e("resultsLFD --> " + resultsLFD.size());
        Logger.e("resultsWEG --> " + resultsWEG.size());

        realm.close();
        EventBus.getDefault().post(new LoadedEvent(LoadedEvent.CODE_OK));
    }

    public static List<ContentItem> updateAndSaveData(Context context, int type, List<ContentItem> lists, boolean isAppend) {
        realm = getRealm(context);
        realm.beginTransaction();
        List<ContentItem> saveList = new ArrayList<>();
        switch (type) {
            case RDataService.DATA_TYPE_0:
                list6JS = updateList(context, lists, list6JS, saveList, isAppend);
                for (ContentItem item : saveList) {
                    Realm6JS realmItem = realm.createObject(Realm6JS.class);
                    realmItem.setContent(item.getMsgContent());
                    realmItem.setImgUrl(item.getImgUrl());
                }
                break;
            case RDataService.DATA_TYPE_1:
                list3JY = updateList(context, lists, list3JY, saveList, isAppend);
                for (ContentItem item : saveList) {
                    Realm3JY realmItem = realm.createObject(Realm3JY.class);
                    realmItem.setContent(item.getMsgContent());
                    realmItem.setImgUrl(item.getImgUrl());
                }
                break;
            case RDataService.DATA_TYPE_2:
                listPFW = updateList(context, lists, listPFW, saveList, isAppend);
                for (ContentItem item : saveList) {
                    RealmPFW realmItem = realm.createObject(RealmPFW.class);
                    realmItem.setContent(item.getMsgContent());
                    realmItem.setImgUrl(item.getImgUrl());
                }
                break;
            case RDataService.DATA_TYPE_3:
                listJYQ = updateList(context, lists, listJYQ, saveList, isAppend);
                for (ContentItem item : saveList) {
                    RealmJYQ realmItem = realm.createObject(RealmJYQ.class);
                    realmItem.setContent(item.getMsgContent());
                    realmItem.setImgUrl(item.getImgUrl());
                }
                break;
            case RDataService.DATA_TYPE_4:
                listLFD = updateList(context, lists, listLFD, saveList, isAppend);
                for (ContentItem item : saveList) {
                    RealmLFD realmItem = realm.createObject(RealmLFD.class);
                    realmItem.setContent(item.getMsgContent());
                    realmItem.setImgUrl(item.getImgUrl());
                }
                break;
            case RDataService.DATA_TYPE_5:
                listWEG = updateList(context, lists, listWEG, saveList, isAppend);
                for (ContentItem item : saveList) {
                    RealmWEG realmItem = realm.createObject(RealmWEG.class);
                    realmItem.setContent(item.getMsgContent());
                    realmItem.setImgUrl(item.getImgUrl());
                }
                break;
            default:
                break;
        }
        realm.commitTransaction();
        realm.close();
        return saveList;
    }

    public static List<ContentItem> updateList(Context context, List<ContentItem> updateList,
                                               List<ContentItem> targetList, List<ContentItem> needList, boolean isAppend) {//将新的数据, 放在旧的数据之前,并保存在 数据库中
        List<ContentItem> tempList = new ArrayList<>();
        if (isAppend) {
            tempList.addAll(targetList);
        }
        for (ContentItem item : updateList) {
            if (!targetList.contains(item)) {
                needList.add(item);
                tempList.add(item);
            }
        }
        if (!isAppend) {
            tempList.addAll(targetList);
        }

        return tempList;
    }

    public static List<ContentItem> getList6JS() {
        return list6JS;
    }

    public static void addList6JS(List<ContentItem> list6JS) {
        RDataPullMgr.list6JS.addAll(list6JS);
    }

    public static List<ContentItem> getList3JY() {
        return list3JY;
    }

    public static void addList3JY(List<ContentItem> list3JY) {
        RDataPullMgr.list3JY.addAll(list3JY);
    }

    public static List<ContentItem> getListPFW() {
        return listPFW;
    }

    public static void addListPFW(List<ContentItem> listPFW) {
        RDataPullMgr.listPFW.addAll(listPFW);
    }

    public static List<ContentItem> getListJYQ() {
        return listJYQ;
    }

    public static void addListJYQ(List<ContentItem> listJYQ) {
        RDataPullMgr.listJYQ.addAll(listJYQ);
    }

    public static List<ContentItem> getListLFD() {
        return listLFD;
    }

    public static void addListLFD(List<ContentItem> listLFD) {
        RDataPullMgr.listLFD.addAll(listLFD);
    }

    public static List<ContentItem> getListWEG() {
        return listWEG;
    }

    public static void addListWEG(List<ContentItem> listWEG) {
        RDataPullMgr.listWEG.addAll(listWEG);
    }

    private static void end() {
        thread = null;
        realm.close();
    }

    public static Realm getRealm(Context context) {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(context).build();
//        Realm.deleteRealm(realmConfiguration);//删除realm
        return Realm.getInstance(realmConfiguration);
//        return Realm.getDefaultInstance();//多线程中,使用默认,会抛出异常RealmMigrationNeededException
    }

    private static class LoadRunnable implements Runnable {

        Context context;

        public LoadRunnable(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            loadFromDB(context);
            end();
        }
    }

    private static class SaveRunnable implements Runnable {

        Context context;
        int type;
        List<ContentItem> lists;

        public SaveRunnable(Context context, int type, List<ContentItem> lists) {
            this.context = context;
            this.type = type;
            this.lists = lists;
        }

        @Override
        public void run() {
            updateAndSaveData(context, type, lists, true);
            end();
        }
    }
}
