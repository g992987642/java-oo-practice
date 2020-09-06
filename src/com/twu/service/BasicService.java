package com.twu.service;

import com.twu.pojo.SearchEvent;

import java.util.List;

public interface BasicService {
    /**
     * 展示热搜排行榜
     * @param searchRankings 热搜排行榜
     */
    public void showSearchRankings(List<SearchEvent> searchRankings);

    /**
     * 添加一个新的热搜
     * @param searchRankings 热搜排行榜
     * @param eventName 新添加的热搜的名字
     */
    public void addHotSerch(List<SearchEvent> searchRankings, String eventName);
}
