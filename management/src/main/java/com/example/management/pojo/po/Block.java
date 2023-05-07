package com.example.management.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zerowo
 * @since 2023-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Block implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "blockId", type = IdType.AUTO)
    private Integer blockId;

    @TableField("staffId")
    private Integer staffId;

    @TableField("customerId")
    private Integer customerId;

    @TableField("title")
    private String title;

    private String note;

    public Block(Integer staffId, String note) {
        this.staffId = staffId;
        this.note = note;
    }

    public Block(Integer staffId, Integer customerId, String title, String note) {
        this.staffId = staffId;
        this.customerId = customerId;
        this.title = title;
        this.note = note;
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
