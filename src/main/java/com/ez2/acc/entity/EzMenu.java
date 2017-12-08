/*
 * This file is part of the EZ2 Accounting Project.
 * Author :
 * - Ivan Aribanilia
 * - Lukman Arizal
 * - Aristion Rizki
 * - Gabriel Sebastian
 *
 * Copyright (c) 2017. EZ2 Company.
 * Inc. All Rights Reserved. Terms of Use, Privacy and Trademark Guidelines
 */

package com.ez2.acc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ez_menu", schema = "ez")
public class EzMenu extends AuditTrail implements Serializable {

    @Id
    @Column(name = "menu_id", length = 100, nullable = false)
    private String menuId;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "menu_class")
    private String menuClass;
    @Column(name = "no_urut", length = 5)
    private Long noUrut;
    @Column(name = "param", length = 200)
    private String param;
    @Column(name = "parent_id", length = 100, nullable = false)
    private String parentId;

    @Column(name = "have_child", length = 1, nullable = false)
    private String haveChild;

    public EzMenu() {
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuClass() {
        return menuClass;
    }

    public void setMenuClass(String menuClass) {
        this.menuClass = menuClass;
    }

    public Long getNoUrut() {
        return noUrut;
    }

    public void setNoUrut(Long noUrut) {
        this.noUrut = noUrut;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getHaveChild() {
        return haveChild;
    }

    public void setHaveChild(String haveChild) {
        this.haveChild = haveChild;
    }

}
