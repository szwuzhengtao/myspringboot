package com.example.management.pojo;

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
 * @since 2023-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleManu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("roleId")
    private Integer roleId;

    @TableField("roleName")
    private String roleName;

    @TableField("manuId")
    private Integer manuId;

    @TableField("manuName")
    private String tableName;

    public RoleManu(Integer roleId, Integer manuId) {
        this.roleId = roleId;
        this.manuId = manuId;
    }

    public RoleManu(Integer roleId, String roleName, Integer manuId, String tableName) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.manuId = manuId;
        this.tableName = tableName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getManuId() {
        return manuId;
    }

    public void setManuId(Integer manuId) {
        this.manuId = manuId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
