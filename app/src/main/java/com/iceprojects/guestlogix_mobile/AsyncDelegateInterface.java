package com.iceprojects.guestlogix_mobile;

import org.json.JSONArray;

public interface AsyncDelegateInterface {

    void processFinishedData(JSONArray jo);
    void nextPageURL(String url);
    void prevPageURL(String url);

}
