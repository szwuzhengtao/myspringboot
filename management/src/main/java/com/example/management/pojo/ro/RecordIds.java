package com.example.management.pojo.ro;

import lombok.Data;

import java.util.List;

@Data
public class RecordIds {
    private List<Integer> recordIds;
    private Integer staffId;
    private Integer customerId;
    private String title;
    private String note;

    public RecordIds() {
    }

    public RecordIds(List<Integer> recordIds, Integer staffId, Integer customerId, String title, String note) {
        this.recordIds = recordIds;
        this.staffId = staffId;
        this.customerId = customerId;
        this.title = title;
        this.note = note;
    }

    public List<Integer> getRecordIds() {
        return recordIds;
    }

    public void setRecordIds(List<Integer> recordIds) {
        this.recordIds = recordIds;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
