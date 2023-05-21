package com.example.management.pojo.vo;

import com.example.management.pojo.po.Manu;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AllManus {
    private Map<String, List> lists;
    private List<SimpleManu> manus;
}
