package com.ball.wx.mapper;

import com.ball.wx.bean.RankBean;

import java.util.List;

public interface RankMapper {

    public void insertData() throws Exception;

    public void deleteData() throws Exception;

    public List<RankBean> selectData() throws Exception;

}
