package com.project.OrderService.Request;

import java.util.List;

public class OrderReq {
    private Long uId;
    private Long bId;
    private List<Long> items;

    public OrderReq() {
    }

    public OrderReq(Long uId, Long bId, List<Long> items) {
        this.uId = uId;
        this.bId = bId;
        this.items = items;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    public List<Long> getItems() {
        return items;
    }

    public void setItems(List<Long> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderReq{" +
                "uId=" + uId +
                ", bId=" + bId +
                ", items=" + items +
                '}';
    }
}
