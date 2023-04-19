package com.example.management.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecordIds {
    private List<Integer> recordIds;
    private Integer staffId;
    private String note;

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
}
