package com.twu.service.Impl;

import com.twu.pojo.SearchEvent;
import com.twu.pojo.User;
import com.twu.service.UserService;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class UserServiceImpl extends AbstractUserImpl implements UserService {
    @Override
    public void voteForSearch(List<SearchEvent> searchRankings, String eventName, int ticketCounts, User user, Map<Integer, SearchEvent> payForEventMap) {
        SearchEvent event = null;
        int ownedTickets = user.getOwnedTickets();
        if (ticketCounts > ownedTickets || ticketCounts < 0) {
            System.out.println("投票失败，票数输入有误");
            return;
        }
        for (int i = 0; i < searchRankings.size(); i++) {
            SearchEvent searchEvent = searchRankings.get(i);
            if (searchEvent.getEventDescription().equals(eventName)) {
                event = searchEvent;
            }
        }
        if (event == null) {
            System.out.println("投票失败，没有这个热搜");
        } else {
            if (event.isSuperHot()) {
                event.setTicketCounts(event.getTicketCounts() + 2 * ticketCounts);
                user.setOwnedTickets(user.getOwnedTickets() - ticketCounts);
                System.out.println("投票成功");
            } else {
                event.setTicketCounts(event.getTicketCounts() + ticketCounts);
                user.setOwnedTickets(user.getOwnedTickets() - ticketCounts);
                System.out.println("投票成功");
            }
        }
        reorderRankings(searchRankings, payForEventMap);
    }

    @Override
    public void payForSearch(List<SearchEvent> searchRankings, String payForEventName,
                             int payForEventRank, int payForEventMoney, Map<Integer, SearchEvent> payForEventMap) {
        SearchEvent event = null;
        //先把排名转化成数组下标，好操作
        payForEventRank = payForEventRank - 1;
        if (payForEventRank >= searchRankings.size()) {
            System.out.println("排名输入有误");
            return;
        }
        for (int i = 0; i < searchRankings.size(); i++) {
            SearchEvent searchEvent = searchRankings.get(i);
            if (searchEvent.getEventDescription().equals(payForEventName)) {
                event = searchEvent;
            }
        }
        if (event == null) {
            System.out.println("购买失败，没有这个热搜");
            return;
        }
        SearchEvent originalEvent = payForEventMap.get(payForEventRank + 1);
        int originalpayForEventMoney = 0;
        if (originalEvent != null) {
            originalpayForEventMoney = originalEvent.getEventMoney();
        }
        if (payForEventMoney > originalpayForEventMoney) {
            SearchEvent finalEvent = event;
            AtomicReference<Integer> originalKey = new AtomicReference<>();
            payForEventMap.forEach((key, value) -> {
                if (value == finalEvent) {
                    originalKey.set(key);
                }
            });
            payForEventMap.remove(originalKey.get());
            event.setEventMoney(payForEventMoney);
            payForEventMap.put(payForEventRank + 1, event);
        } else {
            System.out.println("金额不足，需要大于" + originalpayForEventMoney + "元");
            return;
        }
        reorderRankings(searchRankings, payForEventMap);
        payForEventMap.clear();
        for (int i = 0; i < searchRankings.size(); i++) {
            SearchEvent tempEvent = searchRankings.get(i);
            if (tempEvent.getEventMoney() > 0) {
                payForEventMap.put(i + 1, tempEvent);
            }
        }
    }

    public void reorderRankings(List<SearchEvent> searchRankings, Map<Integer, SearchEvent> payForEventMap) {
        SearchEvent[] searchEventArrays = new SearchEvent[1000];
        Collections.sort(searchRankings);
        //先将购买的热搜放进来
        payForEventMap.keySet().stream().forEach((e) -> {
            searchEventArrays[e - 1] = payForEventMap.get(e);
        });
        int index = 0;
        for (SearchEvent searchRanking : searchRankings) {
            //再把普通热搜放进来
            if (searchRanking.getEventMoney() == 0) {
                while (searchEventArrays[index] != null) {
                    index++;
                }
                searchEventArrays[index] = searchRanking;
            }
        }
        List<SearchEvent> tempList = new ArrayList<>();
        Arrays.stream(searchEventArrays).forEach(e -> {
            if (e != null) {
                tempList.add(e);
            }
        });
        searchRankings.clear();
        searchRankings.addAll(tempList);
    }
}
