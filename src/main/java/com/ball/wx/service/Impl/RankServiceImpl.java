package com.ball.wx.service.Impl;

import cn.hutool.core.util.ObjectUtil;
import com.ball.wx.CommonValue;
import com.ball.wx.bean.RankBean;
import com.ball.wx.mapper.RankMapper;
import com.ball.wx.service.RankService;
import com.ball.wx.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankServiceImpl implements RankService {

    private static final RankMapper rankMapper = CommonValue.sqlSession.getMapper(RankMapper.class);

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void insertData() throws Exception {
        rankMapper.insertData();
    }

    @Override
    public void deleteData() throws Exception {
        rankMapper.deleteData();
    }

    @Override
    public List<RankBean> selectData() throws Exception {
        List<?> redisList = redisUtil.lGet("rankList", 0L, 10L);
        if (ObjectUtil.isNull(redisList)) {
            redisUtil.lSet("rankList", rankMapper.selectData());
        }
        List<RankBean> rankBeanList = new ArrayList<RankBean>();
        for (Object object : redisList) {
            RankBean rankBean = (RankBean) object;
            rankBeanList.add(rankBean);
        }
        return rankBeanList;
    }

}
