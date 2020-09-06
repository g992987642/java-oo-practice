package com.twu.pojo;

import java.util.Objects;

public class User {
    String userName;
    int ownedTickets = 10;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getOwnedTickets() {
        return ownedTickets;
    }

    public void setOwnedTickets(int ownedTickets) {
        this.ownedTickets = ownedTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ownedTickets == user.ownedTickets &&
                Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, ownedTickets);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", ownedTickets=" + ownedTickets +
                '}';
    }
}
