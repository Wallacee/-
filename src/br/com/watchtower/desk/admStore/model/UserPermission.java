/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wallace
 */
@Entity
@Table(name = "user_permission")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserPermission.findAll", query = "SELECT u FROM UserPermission u"),
    @NamedQuery(name = "UserPermission.findByUserId", query = "SELECT u FROM UserPermission u WHERE u.userId = :userId"),
    @NamedQuery(name = "UserPermission.findByCodePermission", query = "SELECT u FROM UserPermission u WHERE u.codePermission = :codePermission")})
public class UserPermission implements BaseModel {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "CODE_PERMISSION")
    private int codePermission;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public UserPermission() {
    }

    public UserPermission(Integer userId) {
        this.userId = userId;
    }

    public UserPermission(Integer userId, int codePermission) {
        this.userId = userId;
        this.codePermission = codePermission;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getCodePermission() {
        return codePermission;
    }

    public void setCodePermission(int codePermission) {
        this.codePermission = codePermission;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserPermission)) {
            return false;
        }
        UserPermission other = (UserPermission) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.watchtower.desk.admStore.model.UserPermission[ userId=" + userId + " ]";
    }
    
}
