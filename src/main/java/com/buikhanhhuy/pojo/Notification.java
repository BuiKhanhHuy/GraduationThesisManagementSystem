/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bkhuy
 */
@Entity
@Table(name = "notification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
    @NamedQuery(name = "Notification.findById", query = "SELECT n FROM Notification n WHERE n.id = :id"),
    @NamedQuery(name = "Notification.findByTitle", query = "SELECT n FROM Notification n WHERE n.title = :title"),
    @NamedQuery(name = "Notification.findByContent", query = "SELECT n FROM Notification n WHERE n.content = :content")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull(message = "{notification.add.title.notNullMessage}")
    @NotEmpty(message = "{notification.add.title.notNullMessage}")
    @Size(max = 100, message = "{notification.add.title.sizeMessage}")
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull(message = "{notification.add.content.notNullMessage}")
    @NotEmpty(message = "{notification.add.content.notNullMessage}")
    @Size(max = 255, message = "{notification.add.content.sizeMessage}")
    @Column(name = "content")
    private String content;
    @Column(name = "created_date")
    private Timestamp createdDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notification")
    @JsonIgnore
    private Set<NotificationUser> notificationUsers;
    @Transient
    private Integer [] usersId;

    {
        this.createdDate = new Timestamp(System.currentTimeMillis());
    }

    public Notification() {
    }

    public Notification(Integer id) {
        this.id = id;
    }

    public Notification(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @XmlTransient
    public Set<NotificationUser> getNotificationUsers() {
        return notificationUsers;
    }

    public void setNotificationUsers(Set<NotificationUser> notificationUserSet) {
        this.notificationUsers = notificationUserSet;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Integer[] getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer[] usersId) {
        this.usersId = usersId;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.Notification[ id=" + id + " ]";
    }
    
}
