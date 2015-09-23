package com.angcyo.y2havefun.components;

import android.app.IntentService;
import android.content.Intent;

import com.angcyo.y2havefun.control.DataControl;
import com.angcyo.y2havefun.mode.HandlerTask;
import com.angcyo.y2havefun.mode.event.BaseEvent;
import com.angcyo.y2havefun.mode.event.EventUpdate;

import java.util.Vector;

import de.greenrobot.event.EventBus;

/**
 * Created by angcyo on 15-09-06-006.
 */
public class RDataService extends IntentService {
    //{"6贱事", "叽歪笑话", "捧腹网", "巨有趣", "来福岛", "我恶搞"};
    public static final int DATA_TYPE_0 = 0;
    public static final int DATA_TYPE_1 = 1;
    public static final int DATA_TYPE_2 = 2;
    public static final int DATA_TYPE_3 = 3;
    public static final int DATA_TYPE_4 = 4;
    public static final int DATA_TYPE_5 = 5;
    public static final int DATA_UPDATE = 1;//刷新
    public static final int DATA_LOAD_MORE = 2;//加载更多

    public static String KEY_DATA_TYPE = "data_type";//网站类型
    public static String KEY_DATA_UPDATE_OR_LOADING = "update_or_loading";//刷新,还是 加载更多
    public static String KEY_DATA_URL = "data_url";//链接

    public static int curPullType = DATA_TYPE_0;//当前正在拉取数据的网站
    public static int curUpdateOrLoadinng = DATA_UPDATE;//当前拉取数据的方式, 刷新还是加载更多
    public static String curDataUrl = "";//当前拉取数据的网站

    private static Vector<HandlerTask> tasks = new Vector<>();//当前需要执行的任务
    private static Vector<HandlerTask> loadedTasks = new Vector<>();//保存加载过的数据网站

    public RDataService() {
        super("RDataService");
    }

    public static void addTask(HandlerTask task) {
        if (!tasks.contains(task)) {
            tasks.add(task);
        }
    }

    public static void removeTask(HandlerTask task) {
        tasks.remove(task);
    }

    public static boolean isExistTask(HandlerTask task) {
        return tasks.contains(task);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
//        Logger.e("onHandleIntent " + intent.getIntExtra(KEY_DATA_TYPE, DATA_TYPE_0) + " " + Thread.currentThread().getId());
        curPullType = intent.getIntExtra(KEY_DATA_TYPE, DATA_TYPE_0);
        curUpdateOrLoadinng = intent.getIntExtra(KEY_DATA_UPDATE_OR_LOADING, DATA_UPDATE);
        curDataUrl = intent.getStringExtra(KEY_DATA_URL);

        BaseEvent event;
        boolean isAppend;//是否是加载更多的数据,决定获取到的数据,是在list 前面,还是后面
        while (tasks.size() > 0) {
//            Logger.e("请求任务数:" + tasks.size());
            HandlerTask task = tasks.remove(0);
//            Logger.e("请求网页数据:" + task.url);

            event = new EventUpdate();
            event.loadType = task.taskLoadType;
            event.type = task.taskType;
            event.datas = DataControl.get(task.taskType, task.url);
            if (task.taskLoadType == DATA_UPDATE) {
                isAppend = false;
                if (event.datas.size() > 0) {
                    DataControl.resetPaget(task.taskType);
                }
            } else {
                isAppend = true;
            }
            event.datas = RDataPullMgr.updateAndSaveData(RDataService.this, task.taskType, event.datas, isAppend);
            loadedTasks.add(task);
            if (event != null) {
                EventBus.getDefault().post(event);
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
