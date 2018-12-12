package com.ball.wx.service.Impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSONObject;
import com.ball.wx.CommonValue;
import com.ball.wx.bean.LoginBean;
import com.ball.wx.mapper.UserMapper;
import com.ball.wx.service.LoginService;
import com.ball.wx.util.HttpUtil;
import com.ball.wx.util.ImgUtil;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

@Service
public class LoginServiceImpl implements LoginService {

    private static final UserMapper userMapper = CommonValue.sqlSession.getMapper(UserMapper.class);

    @Override
    public LoginBean getOpenId$key(String code) throws Exception {
        String v = code.split(":")[1];
        String result = v.substring(1, v.length() - 2);
        String url = CommonValue.GET_USER_DATA_URL_HEAD + result + CommonValue.GET_USER_DATA_URL_END;
        String data = HttpUtil.doPost(url, "");
        JSONObject jsonObject = JSONObject.parseObject(data);
        LoginBean loginBean = userMapper.selectUserByOpenId(jsonObject.get("openid").toString());
        if (ObjectUtil.isNotNull(loginBean)) {
            LoginBean _loginBean = getLoginBean(loginBean, jsonObject.get("openid").toString(), jsonObject.get("session_key").toString(), jsonObject.get("unionid").toString(), Long.parseLong(jsonObject.get("errcode").toString()), jsonObject.get("errmsg").toString());
            userMapper.updateUser(_loginBean);
            CommonValue.sqlSession.commit();
            return _loginBean;
        } else {
            LoginBean _loginBean = getLoginBean(null, jsonObject.get("openid").toString(), jsonObject.get("session_key").toString(), jsonObject.get("unionid").toString(), Long.parseLong(jsonObject.get("errcode").toString()), jsonObject.get("errmsg").toString());
            userMapper.insertUser(_loginBean);
            CommonValue.sqlSession.commit();
            return _loginBean;
        }
    }

    @Override
    public LoginBean setUserInfo(LoginBean loginBean) throws Exception {
        LoginBean _loginBean = userMapper.selectUserByUserId(loginBean.getUserId());
        String result = DigestUtil.sha1Hex(loginBean.getRawData() + _loginBean.getSession_key());
        if (result.equals(loginBean.getSignature())) {
            _loginBean.setNickName(loginBean.getNickName());
            _loginBean.setHeadImg(ImgUtil.downLoadImage(loginBean.getHeadImg(), "D:\\cocos_pros\\jump\\assets\\Texture\\", loginBean.getUserId() + ".jpg"));
            _loginBean.setGender(loginBean.getGender());
            userMapper.updateUser(_loginBean);
            CommonValue.sqlSession.commit();
            _loginBean.setErrcode(CommonValue.ERROR_CODE_OK);
        } else {
            _loginBean.setErrcode(CommonValue.ERROR_CODE_FAILED);
            _loginBean.setErrmsg("数据不完整！");
        }
        return _loginBean;
    }

    @Override
    public LoginBean getUserData(Integer userId) throws Exception {
        return userMapper.selectUserByUserId(userId);
    }

    @Override
    public void deleteAllUsers() throws Exception {
        userMapper.deleteAllUsers();
    }

    private LoginBean getLoginBean(LoginBean loginBean, String openid, String session_key, String unionid, Long errcode, String errmsg) {
        if (ObjectUtil.isNotNull(loginBean)) {
            if (!openid.equals("")) {
                loginBean.setOpenid(openid);
            }
            if (!session_key.equals("")) {
                loginBean.setSession_key(session_key);
            }
            if (!unionid.equals("")) {
                loginBean.setUnionid(unionid);
            }
            if (!errcode.equals("")) {
                loginBean.setErrcode(errcode);
            }
            if (!errmsg.equals("")) {
                loginBean.setErrmsg(errmsg);
            }
            return loginBean;
        } else {
            LoginBean _loginBean = new LoginBean();
            if (!openid.equals("")) {
                loginBean.setOpenid(openid);
            }
            if (!session_key.equals("")) {
                loginBean.setSession_key(session_key);
            }
            if (!unionid.equals("")) {
                loginBean.setUnionid(unionid);
            }
            if (!errcode.equals("")) {
                loginBean.setErrcode(errcode);
            }
            if (!errmsg.equals("")) {
                loginBean.setErrmsg(errmsg);
            }
            return _loginBean;
        }
    }

}
