package com.angcyo.y2havefun.mode.realm;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by angcyo on 15-09-07-007.
 */
public class RealmLFD extends RealmObject {
    @Ignore
    private long index;
    private String content;
    private String imgUrl;

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}