package com.twu.service.Impl;

import com.twu.pojo.Admin;
import com.twu.pojo.SearchEvent;
import com.twu.pojo.User;
import com.twu.service.AdminService;
import com.twu.service.SystemService;
import com.twu.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class SystemServiceImpl implements SystemService {

    AdminService adminService = new AdminServiceImpl();
    UserService userService = new UserServiceImpl();

    @Override
    public void login(List<SearchEvent> searchRankings, List<User> userList, List<Admin> adminList, Map<Integer, SearchEvent> payForEventMap) {
        System.out.println("欢迎来到热搜排行榜，你是？");
        System.out.println("1.用户");
        System.out.println("2.管理员");
        System.out.println("3.退出");
        Scanner sc = new Scanner(System.in);
        String role = sc.nextLine();
        switch (role) {
            case "1":
                System.out.println("请输入您的昵称:");
                String name = sc.nextLine();
                //variable used in lambda expression should be final or effectively final
                AtomicReference<User> user = new AtomicReference<>();
                userList.stream().forEach((e) -> {
                    if (e.getUserName().equals(name)) {
                        user.set(e);
                    }
                });
                if (user.get() == null) {
                    user.set(new User(name));
                }
                userList.add(user.get());
                handleSearchRankingsForUser(searchRankings, user.get(), userList, adminList, payForEventMap);
                break;
            case "2":
                System.out.println("请输入您的昵称:");
                String adminName = sc.nextLine();
                System.out.println("请输入你的密码");
                String adminPWD = sc.nextLine();
                AtomicReference<Admin> admin = new AtomicReference<>();
                adminList.stream().forEach((e) -> {
                    if (e.getUsername().equals(adminName) && e.getPassword().equals(adminPWD)) {
                        admin.set(e);
                    }
                });
                if (admin.get() == null) {
                    System.out.println("账号或密码错误，请重新登录");
                    login(searchRankings, userList, adminList, payForEventMap);
                    break;
                } else {
                    handleSearchRankingsForAdmin(searchRankings, admin.get(), userList, adminList, payForEventMap);
                }
                break;
            case "3":
                break;
        }
    }

    @Override
    public void handleSearchRankingsForUser(List<SearchEvent> searchRankings, User user, List<User> userList, List<Admin> adminList, Map<Integer, SearchEvent> payForEventMap) {
        System.out.println("你好," + user.getUserName() + ",你可以:");
        System.out.println("1.查看热搜排行榜");
        System.out.println("2.给热搜事件投票");
        System.out.println("3.购买热搜");
        System.out.println("4.添加热搜");
        System.out.println("5.退出");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        switch (option) {
            case "1":
                userService.showSearchRankings(searchRankings);
                handleSearchRankingsForUser(searchRankings, user, userList, adminList, payForEventMap);
                break;
            case "2":
                int ownedTickets = user.getOwnedTickets();
                System.out.println("请输入你要投票的热搜名称");
                String voteForeventName = sc.nextLine();
                System.out.println("请输入你要投票的热搜票数:(你目前还有" + ownedTickets + "票)");
                int ticketCounts = Integer.parseInt(sc.nextLine());
                userService.voteForSearch(searchRankings, voteForeventName, ticketCounts, user, payForEventMap);
                handleSearchRankingsForUser(searchRankings, user, userList, adminList, payForEventMap);
                break;
            case "3":
                System.out.println("请输入你要购买的热搜名称:");
                String payForEventName = sc.nextLine();
                System.out.println("请输入你要购买的热搜排名");
                int payForEventRank = Integer.parseInt(sc.nextLine());
                System.out.println("请输入你要购买的热搜金额");
                int payForEventMoney = Integer.parseInt(sc.nextLine());
                userService.payForSearch(searchRankings, payForEventName, payForEventRank, payForEventMoney, payForEventMap);
                handleSearchRankingsForUser(searchRankings, user, userList, adminList, payForEventMap);
                break;
            case "4":
                System.out.println("请输入你要添加的热搜事件的名字");
                String readyToAddEventName = sc.nextLine();
                userService.addHotSerch(searchRankings, readyToAddEventName);
                handleSearchRankingsForUser(searchRankings, user, userList, adminList, payForEventMap);
                break;
            case "5":
                login(searchRankings, userList, adminList, payForEventMap);
                break;
        }
    }

    @Override
    public void handleSearchRankingsForAdmin(List<SearchEvent> searchRankings, Admin admin, List<User> userList, List<Admin> adminList, Map<Integer, SearchEvent> payForEventMap) {
        System.out.println("你好," + admin.getUsername() + ",你可以:");
        System.out.println("1.查看热搜排行榜");
        System.out.println("2.添加热搜");
        System.out.println("3.添加超级热搜");
        System.out.println("4.退出");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        switch (option) {
            case "1":
                adminService.showSearchRankings(searchRankings);
                handleSearchRankingsForAdmin(searchRankings, admin, userList, adminList, payForEventMap);
                break;
            case "2":
                System.out.println("请输入你要添加的热搜事件的名字");
                String readyToAddEventName = sc.nextLine();
                adminService.addHotSerch(searchRankings, readyToAddEventName);
                handleSearchRankingsForAdmin(searchRankings, admin, userList, adminList, payForEventMap);
                break;
            case "3":
                System.out.println("请输入你要添加的超级热搜事件的名字");
                String readyToAddSuperEventName = sc.nextLine();
                adminService.addSuperHotSerch(searchRankings, readyToAddSuperEventName);
                handleSearchRankingsForAdmin(searchRankings, admin, userList, adminList, payForEventMap);
                break;
            case "4":
                login(searchRankings, userList, adminList, payForEventMap);
                break;
        }
    }
}
