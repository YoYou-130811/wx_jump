package com.ball.wx.bean;

import lombok.Data;

@Data
public class LoginBean {

    private String openid;

    private String session_key;

    private String unionid;

    private Long errcode;

    private String errmsg;

    private Integer userId;

    private String nickName;

    private String headImg;

    private Integer gender;

    private Integer topScore;

    private String rawData;

    private String signature;

}
