package com.example.management.pojo.vo;

import com.example.management.pojo.po.Manu;
import lombok.Data;

@Data
public class SimpleManu {
    private Integer manuId;
    private String manuName;

    public SimpleManu(Integer manuId, String manuName) {
        this.manuId = manuId;
        this.manuName = manuName;
    }

    public SimpleManu(Manu manu){
        this.manuId = manu.getManuId();
        this.manuName = manu.getManuName();
    }
}
