package com.twu.service;

import com.twu.pojo.Admin;
import com.twu.pojo.SearchEvent;
import com.twu.pojo.User;

import java.util.List;
import java.util.Map;

public interface SystemService {
    /**
     * 登录操作
     * @param searchRankings 热搜排行榜
     * @param userList 普通用户列表
     * @param adminList 管理员列表
     * @param payForEventMap 购买的热搜的 映射表<排名，热搜事件>
     */
    public void login(List<SearchEvent> searchRankings, List<User> userList,
                      List<Admin> adminList, Map<Integer, SearchEvent> payForEventMap);

    /**
     * 普通用户的操作平台
     * @param searchRankings 热搜排行榜
     * @param user 登录的普通用户
     * @param userList 普通用户列表
     * @param adminList 管理员列表
     * @param payForEventMap 已被购买的热搜的 映射表<排名，热搜事件>
     */
    public void handleSearchRankingsForUser(List<SearchEvent> searchRankings, User user, List<User> userList,
                                            List<Admin> adminList, Map<Integer, SearchEvent> payForEventMap);

    /**
     * 管理员的操作平台
     * @param searchRankings 热搜排行榜
     * @param admin 登录的管理员用户
     * @param userList 普通用户列表
     * @param adminList 管理员列表
     * @param payForEventMap 已被购买的热搜的 映射表<排名，热搜事件>
     */
    public void handleSearchRankingsForAdmin(List<SearchEvent> searchRankings, Admin admin, List<User> userList,
                                             List<Admin> adminList, Map<Integer, SearchEvent> payForEventMap);
}
