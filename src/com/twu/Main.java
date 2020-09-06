package com.twu;

import com.twu.pojo.Admin;
import com.twu.pojo.SearchEvent;
import com.twu.pojo.User;
import com.twu.service.Impl.SystemServiceImpl;
import com.twu.service.SystemService;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<SearchEvent> searchRankings = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        List<Admin> adminList = new ArrayList<>();
        Map<Integer, SearchEvent> payForEventMap = new HashMap<>();
        adminList.add(new Admin("admin", "admin123"));
        SystemService systemService = new SystemServiceImpl();
        systemService.login(searchRankings, userList, adminList, payForEventMap);
    }
}
