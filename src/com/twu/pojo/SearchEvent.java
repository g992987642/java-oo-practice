package com.twu.pojo;

import java.util.Date;
import java.util.Objects;

public class SearchEvent implements Comparable<SearchEvent> {
    int ticketCounts;
    int eventMoney;
    boolean isSuperHot = false;
    String eventDescription;
    Date createTime = new Date();

    public SearchEvent(String eventDescription, boolean isSuperHot) {
        this.isSuperHot = isSuperHot;
        this.eventDescription = eventDescription;
    }

    public SearchEvent(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public int getTicketCounts() {
        return ticketCounts;
    }

    public void setTicketCounts(int ticketCounts) {
        this.ticketCounts = ticketCounts;
    }

    public int getEventMoney() {
        return eventMoney;
    }

    public void setEventMoney(int eventMoney) {
        this.eventMoney = eventMoney;
    }

    public boolean isSuperHot() {
        return isSuperHot;
    }

    public void setSuperHot(boolean superHot) {
        isSuperHot = superHot;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchEvent that = (SearchEvent) o;
        return ticketCounts == that.ticketCounts &&
                eventMoney == that.eventMoney &&
                isSuperHot == that.isSuperHot &&
                Objects.equals(eventDescription, that.eventDescription) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketCounts, eventMoney, isSuperHot, eventDescription, createTime);
    }

    @Override
    public String toString() {
        return "SearchEvent{" +
                "ticketCounts=" + ticketCounts +
                ", eventMoney=" + eventMoney +
                ", isSuperHot=" + isSuperHot +
                ", eventDescription='" + eventDescription + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public int compareTo(SearchEvent o) {
        int result = 0;
        result = -(this.eventMoney - o.eventMoney);
        if (result == 0) {
            result = -(this.ticketCounts - o.ticketCounts);
        }
        if (result == 0) {
            result = this.createTime.compareTo(o.createTime);
        }
        return result;
    }
}
