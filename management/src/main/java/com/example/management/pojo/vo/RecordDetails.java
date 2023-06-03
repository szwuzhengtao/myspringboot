package com.example.management.pojo.vo;

import lombok.Data;

@Data
public class RecordDetails {
    private Integer recordId;
    private String personId;
    private String nickname;
    private String avatarURL;
    private String time;
    private String chatId;
    private String chatName;
    private String content;
    private Integer isCustomer;

}
