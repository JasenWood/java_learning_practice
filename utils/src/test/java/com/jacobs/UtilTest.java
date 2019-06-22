package com.jacobs;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;

import org.junit.Test;

/**
 * Created by lichao on 2017/4/12.
 */
public class UtilTest {

    private com.jacobs.CommonHttpUtil commonHttpUtil = com.jacobs.CommonHttpUtil.builder().build();
    private SimpleDateFormat simpleDateTime = new SimpleDateFormat("yyyy/MM/dd");

    public static void main(String[] args) {
        System.out.println(Credentials.basic("root", "root"));
    }

    @Test
    public void testGetMethod() {
        try {
            //System.out.println(commonHttpUtil.get("http://visual-be/data").send().tryString());
            Future<com.jacobs.CommonHttpUtil.HttpResponse> responseFuture = commonHttpUtil.get("http://visual-be/data")
                    .async();
            com.jacobs.CommonHttpUtil.HttpResponse response = responseFuture.get(1000, TimeUnit.MILLISECONDS);
            response.setJson(true);
            System.out.println(response.tryJson());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void regTest() {
        System.out.println(simpleDateTime.format(Calendar.getInstance().getTime()));
    }
}
