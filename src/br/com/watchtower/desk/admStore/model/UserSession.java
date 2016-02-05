/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.model;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wallace
 */
@Entity
@Table(name = "user_session")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSession.findAll", query = "SELECT u FROM UserSession u"),
    @NamedQuery(name = "UserSession.findByDateSessionStart", query = "SELECT u FROM UserSession u WHERE u.dateSessionStart = :dateSessionStart"),
    @NamedQuery(name = "UserSession.findByDateSessionEnd", query = "SELECT u FROM UserSession u WHERE u.dateSessionEnd = :dateSessionEnd"),
    @NamedQuery(name = "UserSession.findByUserId", query = "SELECT u FROM UserSession u WHERE u.userId = :userId")})
public class UserSession implements BaseModel {
    private static final long serialVersionUID = 1L;
    @Column(name = "DATE_SESSION_START")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSessionStart;
    @Column(name = "DATE_SESSION_END")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSessionEnd;
    @Id
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Integer userId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public UserSession() {
    }

    public UserSession(Integer userId) {
        this.userId = userId;
    }

    public Date getDateSessionStart() {
        return dateSessionStart;
    }

    public void setDateSessionStart(Date dateSessionStart) {
        this.dateSessionStart = dateSessionStart;
    }

    public Date getDateSessionEnd() {
        return dateSessionEnd;
    }

    public void setDateSessionEnd(Date dateSessionEnd) {
        this.dateSessionEnd = dateSessionEnd;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        if (!(object instanceof UserSession)) {
            return false;
        }
        UserSession other = (UserSession) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.watchtower.desk.admStore.model.UserSession[ userId=" + userId + " ]";
    }
    
}
