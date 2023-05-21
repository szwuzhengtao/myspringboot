package com.example.management.pojo.ro;

import lombok.Data;

import java.util.Map;

@Data
public class RoleManus {
    private Integer roleId;
    private Integer[] manuId;
    private Integer[] state;

}
