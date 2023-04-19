package com.example.management.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zerowo
 * @since 2023-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "recordblock",autoResultMap = true)
public class Recordblock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "blockId")
    private Integer blockId;

    @TableField("recordId")
    private Integer recordId;

    @TableField("personId")
    private String personId;

    public Recordblock() {
    }

    public Recordblock(Integer blockId, Integer recordId, String personId) {
        this.blockId = blockId;
        this.recordId = recordId;
        this.personId = personId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
