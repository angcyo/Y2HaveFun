package com.angcyo.y2havefun.mode;

import com.angcyo.y2havefun.components.RDataService;
import com.angcyo.y2havefun.util.parse.HttpParse6JS;

/**
 * Created by angcyo on 15-09-07-007.
 */
public class HandlerTask {
    public int taskType = RDataService.DATA_TYPE_0;
    public int taskLoadType = RDataService.DATA_UPDATE;
    public String url = HttpParse6JS.URL_6JS;

    @Override
    public boolean equals(Object o) {
        HandlerTask task = ((HandlerTask) o);
        if (task.taskType == this.taskType
                && task.taskLoadType == this.taskLoadType
                && task.url.equalsIgnoreCase(this.url)) {
            return true;
        }
        return false;
    }
}
