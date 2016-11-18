package com.example.administrator.client.utils;

/**
 * Created by LG on 2016/10/27.
 * Tips:
 */

public interface Apis {
    String URL_CLIENT = "https://passport.4c.cn/api/v1/login?";
    String URL_TEXT_PHONE = "https://passport.4c.cn/api/v1/exists?";
    String URL_RECEIVE_VERIFICATION = "https://passport.4c.cn/api/v1/sms?";
    String URL_TEXT_VERIFICATION = "https://passport.4c.cn/api/v1/check_verify_code?";
    String URL_SET_PASSWORD = "https://passport.4c.cn/api/v1//signup?";
}
