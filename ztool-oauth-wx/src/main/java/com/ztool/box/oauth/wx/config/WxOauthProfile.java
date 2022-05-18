package com.ztool.box.oauth.wx.config;

/**
 * @Author zhaoj
 * @Date 2022/4/15 18:03
 */
public interface WxOauthProfile {

    String gateway = "https://api.weixin.qq.com";

    String appid();

    String secret();
}
