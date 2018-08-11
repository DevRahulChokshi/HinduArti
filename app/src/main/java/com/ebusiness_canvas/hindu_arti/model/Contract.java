package com.ebusiness_canvas.hindu_arti.model;

import android.net.Uri;
import android.util.Base64;

/**
 * Created by EBC003 on 10/28/2017.
 */

public class Contract  {
    public static final String USER_ID="_ID";
    public static final String USER_EMAIL="user_email";
    public static final String USER_DISPLAY_NAME="user_display_name";

    Uri  uri;

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
