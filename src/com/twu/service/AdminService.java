package com.twu.service;

import com.twu.pojo.SearchEvent;

import java.util.List;


public interface AdminService extends BasicService {
    /**
     * 添加一个超级热搜或者设置一个已有的热搜成为超级热搜
     * @param searchRankings 热搜排行榜
     * @param eventName      要添加的超级热搜的名字
     */
    public void addSuperHotSerch(List<SearchEvent> searchRankings, String eventName);
}
