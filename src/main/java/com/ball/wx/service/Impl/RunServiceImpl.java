package com.ball.wx.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.ball.wx.CommonValue;
import com.ball.wx.bean.LoginBean;
import com.ball.wx.mapper.UserMapper;
import com.ball.wx.service.RunService;
import com.ball.wx.util.AESUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RunServiceImpl implements RunService {

    private static final UserMapper userMapper = CommonValue.sqlSession.getMapper(UserMapper.class);

    public Object getRunData(Object data) {
        Map<String, String> map = new HashMap<>();
        String[] _data = data.toString().split(",");
        System.err.println(data.toString());
        map.put(_data[0].split("\\{")[1].split("=")[0], _data[0].split("=")[1]);
        map.put(_data[1].trim().split("=")[0], _data[1].split("=")[1] + "==");
        map.put(_data[2].trim().split("=")[0], _data[2].split("=")[1] + "==");
        Integer userId = Integer.parseInt(map.get(_data[0].split("\\{")[1].split("=")[0]));
        JSONObject jsonObject = new JSONObject();
        try {
            LoginBean user = userMapper.selectUserByUserId(userId);
            String session_key = user.getSession_key();
            Integer step = AESUtil.getTodayRunData(map.get(_data[1].trim().split("=")[0]), session_key, map.get(_data[2].trim().split("=")[0]));
            if (step != -1) {
                jsonObject.put("errcode", CommonValue.ERROR_CODE_OK);
                jsonObject.put("step", step);
                return jsonObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("errcode", CommonValue.ERROR_CODE_FAILED);
        return jsonObject;
    }

}
