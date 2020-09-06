package com.twu.service.Impl;

import com.twu.pojo.SearchEvent;
import com.twu.service.BasicService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractUserImpl implements BasicService {
    @Override
    public void showSearchRankings(List<SearchEvent> searchRankings) {
        for (int i = 0; i < searchRankings.size(); i++) {
            SearchEvent searchEvent = searchRankings.get(i);
            System.out.println(i + 1 + " " + searchEvent.getEventDescription() + " " + searchEvent.getTicketCounts());
        }
    }

    @Override
    public void addHotSerch(List<SearchEvent> searchRankings, String eventName) {
        Optional<SearchEvent> first = searchRankings.stream().filter((e) -> {
            if (e.getEventDescription().equals(eventName)) {
                return true;
            } else {
                return false;
            }
        }).findFirst();
        if (first.isPresent()) {
            System.out.println("已经存在相同名字的热搜事件");
        } else {
            searchRankings.add(new SearchEvent(eventName));
            System.out.println("添加热搜事件成功");
        }

    }
}
