package com.twu.service.Impl;

import com.twu.pojo.SearchEvent;
import com.twu.service.AdminService;

import java.util.List;
import java.util.Optional;

public class AdminServiceImpl extends AbstractUserImpl implements AdminService {
    @Override
    public void addSuperHotSerch(List<SearchEvent> searchRankings, String eventName) {
        Optional<SearchEvent> first = searchRankings.stream().filter((e) -> {
            if (e.getEventDescription().equals(eventName)) {
                return true;
            } else {
                return false;
            }
        }).findFirst();
        if (first.isPresent()) {
            if (first.get().isSuperHot()) {
                System.out.println("设置失败，事件已经是超级热搜了");
            } else {
                first.get().setSuperHot(true);
                System.out.println("成功将事件设置为超级热搜");
            }
        } else {
            searchRankings.add(new SearchEvent(eventName, true));
            System.out.println("添加超级热搜事件成功");
        }

    }

}
