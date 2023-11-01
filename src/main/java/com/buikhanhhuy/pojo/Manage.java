package com.buikhanhhuy.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author bkhuy
 */
@Entity
@Table(name = "manage")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Manage.findAll", query = "SELECT m FROM Manage m"),
        @NamedQuery(name = "Manage.findById", query = "SELECT m FROM Manage m WHERE m.id = :id"),
        @NamedQuery(name = "Manage.findByFullName", query = "SELECT m FROM Manage m WHERE m.fullName = :fullName"),
        @NamedQuery(name = "Manage.findByEmail", query = "SELECT m FROM Manage m WHERE m.email = :email"),
        @NamedQuery(name = "Manage.findByPhone", query = "SELECT m FROM Manage m WHERE m.phone = :phone")})
public class Manage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotEmpty(message = "{manage.add.fullName.notNullMessage}")
    @NotNull(message = "{manage.add.fullName.notNullMessage}")
    @Size(max = 100, message = "{manage.add.fullName.sizeMessage}")
    @Column(name = "full_name")
    private String fullName;
    @Basic(optional = false)
    @NotEmpty(message = "{manage.add.email.notNullMessage}")
    @NotNull(message = "{manage.add.email.notNullMessage}")
    @Size(max = 100, message = "{manage.add.email.sizeMessage}")
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "{manage.add.email.format}")
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotEmpty(message = "{manage.add.phone.notNullMessage}")
    @NotNull(message = "{manage.add.phone.notNullMessage}")
    @Size(max = 15, message = "{manage.add.phone.sizeMessage}")
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "{manage.add.phone.format}")
    @Column(name = "phone")
    private String phone;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"news", "student", "lecturer", "manage", "notificationUsers", "userRole"})
    @Valid
    private User user;

    public Manage() {
    }

    public Manage(Integer id) {
        this.id = id;
    }

    public Manage(Integer id, String fullName, String email, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
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
        if (!(object instanceof Manage)) {
            return false;
        }
        Manage other = (Manage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.Manage[ id=" + id + " ]";
    }

}
