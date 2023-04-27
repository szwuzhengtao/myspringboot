package com.example.management.pojo.vo;

import lombok.Data;

@Data
public class AnalyzeAll {
    private Integer customerNum;
    private Integer staffNum;
    private Integer followupNum;
    private Integer chatNum;
    private Integer[] followups;
    private Integer otherNum;

    public Integer getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(Integer customerNum) {
        this.customerNum = customerNum;
    }

    public Integer getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(Integer staffNum) {
        this.staffNum = staffNum;
    }

    public Integer getFollowupNum() {
        return followupNum;
    }

    public void setFollowupNum(Integer followupNum) {
        this.followupNum = followupNum;
    }

    public Integer getChatNum() {
        return chatNum;
    }

    public void setChatNum(Integer chatNum) {
        this.chatNum = chatNum;
    }

    public Integer[] getFollowups() {
        return followups;
    }

    public void setFollowups(Integer[] followups) {
        this.followups = followups;
    }

    public Integer getOtherNum() {
        return otherNum;
    }

    public void setOtherNum(Integer otherNum) {
        this.otherNum = otherNum;
    }
}
