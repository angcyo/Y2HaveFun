package com.angcyo.y2havefun.control;

import android.os.AsyncTask;

import com.angcyo.y2havefun.mode.ContentItem;
import com.angcyo.y2havefun.util.parse.HttpParse6JS;

import java.util.List;

/**
 * Created by angcyo on 15-08-27-027.
 */
public abstract class HttpAsync extends AsyncTask<String, Void, List<ContentItem>> {

    @Override
    protected List<ContentItem> doInBackground(String... params) {
        return HttpParse6JS.get(params[0]);
    }

    @Override
    protected void onPostExecute(List<ContentItem> contentItems) {
        super.onPostExecute(contentItems);
        onPostExe(contentItems);
    }

    public abstract void onPostExe(List<ContentItem> contentItems);

}
