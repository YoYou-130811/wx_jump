package com.ball.wx.service.Impl;

import com.ball.wx.CommonValue;
import com.ball.wx.bean.RankBean;
import com.ball.wx.mapper.RankMapper;
import com.ball.wx.service.RankService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankServiceImpl implements RankService {

    private static final RankMapper rankMapper = CommonValue.sqlSession.getMapper(RankMapper.class);

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
        return rankMapper.selectData();
    }

}
