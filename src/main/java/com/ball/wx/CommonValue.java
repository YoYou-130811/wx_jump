package com.ball.wx;

import com.ball.wx.util.DBUtil;
import com.ball.wx.util.RedisUtil;
import org.apache.ibatis.session.SqlSession;

public class CommonValue {

    public static final String APPID = "wx127dd2aed53197c6";

    public static final String SECRET = "274c637cc8c6c3eec292006a77a076d6";

    public static final String GET_USER_DATA_URL_HEAD = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET + "&js_code=";

    public static final String GET_USER_DATA_URL_END = "&grant_type=authorization_code";

    public static final String FIND_OPENID_BY_SESSION_KEY = "findOpenIdBySession_key";

    public static final Long ERROR_CODE_OK = 10000L;

    public static final Long ERROR_CODE_FAILED = 10001L;

    public static final SqlSession sqlSession = DBUtil.getSession();

}

