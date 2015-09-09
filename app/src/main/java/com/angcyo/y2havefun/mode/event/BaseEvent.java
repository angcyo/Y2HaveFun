package com.angcyo.y2havefun.mode.event;

import com.angcyo.y2havefun.mode.ContentItem;

import java.util.List;

/**
 * Created by angcyo on 15-09-06-006.
 */
public abstract class BaseEvent {
    public List<ContentItem> datas;
    public int type;//任务类型
    public int loadType;//加载类型, 刷新还是加载更多
    public int code;//成功 或者 失败
}
