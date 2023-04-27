package com.example.management.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * @since 2023-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Deleted implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "customerId")
    private Integer customerId;

    @TableField("customerName")
    private String customerName;

    @TableField("customerEmail")
    private String customerEmail;

    @TableField("customerGender")
    private String customerGender;

    @TableField("customerAddress")
    private String customerAddress;

    @TableField("customerPhone")
    private String customerPhone;

    @TableField("customerJob")
    private String customerJob;

    @TableField("customerJointime")
    private String customerJointime;

    @TableField("chargeStaff")
    private String chargeStaff;

    @TableField("customerQQ")
    private String customerQQ;

    @TableField("deleteTime")
    private String deleteTime;

    private String nickname;

    @TableField("staffId")
    private Integer staffId;

    @TableField("staffName")
    private String staffName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerJob() {
        return customerJob;
    }

    public void setCustomerJob(String customerJob) {
        this.customerJob = customerJob;
    }

    public String getCustomerJointime() {
        return customerJointime;
    }

    public void setCustomerJointime(String customerJointime) {
        this.customerJointime = customerJointime;
    }

    public String getChargeStaff() {
        return chargeStaff;
    }

    public void setChargeStaff(String chargeStaff) {
        this.chargeStaff = chargeStaff;
    }

    public String getCustomerQQ() {
        return customerQQ;
    }

    public void setCustomerQQ(String customerQQ) {
        this.customerQQ = customerQQ;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
