package com.myntra.myntraskintone.util;

/**
 * Created by viswanath.sandu on 19/12/14.
 */
public class UrlUtils {

    public static String getUrlFor(int position) {
        return String.format("http://skintone.yolasite.com/resources/skintone/%s.jpg", position);
    }

    public static String getSkinlessUrlFor(int position) {
        return String.format("http://skintone.yolasite.com/resources/skintone/%s.png", position);
    }

    public static String getThumbnailUrlFor(int position) {
        return String.format("http://skintone.yolasite.com/resources/skintone/thumbnails/%s.jpg", position);
    }

}
