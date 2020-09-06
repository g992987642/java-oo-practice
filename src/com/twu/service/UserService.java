package com.twu.service;

import com.twu.pojo.SearchEvent;
import com.twu.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService extends BasicService {
    /**
     * 给热搜事件投票
     * @param searchRankings 热搜排行榜
     * @param eventName 投票的事件名字
     * @param ticketcounts 投票数量
     * @param user 登录的普通用户
     * @param payForEventMap 已被购买的热搜的 映射表<排名，热搜事件>
     */
    public void voteForSearch(List<SearchEvent> searchRankings, String eventName, int ticketcounts,
                              User user, Map<Integer, SearchEvent> payForEventMap);

    /**
     * 购买热搜事件排名
     * @param searchRankings 热搜排行榜
     * @param payForEventName 购买的热搜的名字
     * @param payForEventRank 购买的热搜的排名
     * @param payForEventMoney 购买的热搜的钱
     * @param payForEventMap 已被购买的热搜的 映射表<排名，热搜事件>
     */
    public void payForSearch(List<SearchEvent> searchRankings, String payForEventName,
                             int payForEventRank, int payForEventMoney, Map<Integer, SearchEvent> payForEventMap);

}
