package com.ztool.box.oauth.wx.api;

import feign.Headers;
import feign.RequestLine;

/**
 * @Author zhaoj
 * @Date 2022/4/15 18:02
 */
public interface WxOauthClient {

    @Headers({"Content-Type: application/json"})
    @RequestLine("GET /sns/oauth2/access_token")
    void accessToken();
}
