/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bkhuy
 */
@Entity
@Table(name = "lecturer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lecturer.findAll", query = "SELECT l FROM Lecturer l"),
    @NamedQuery(name = "Lecturer.findById", query = "SELECT l FROM Lecturer l WHERE l.id = :id"),
    @NamedQuery(name = "Lecturer.findByCode", query = "SELECT l FROM Lecturer l WHERE l.code = :code"),
    @NamedQuery(name = "Lecturer.findByFullName", query = "SELECT l FROM Lecturer l WHERE l.fullName = :fullName"),
    @NamedQuery(name = "Lecturer.findByEmail", query = "SELECT l FROM Lecturer l WHERE l.email = :email"),
    @NamedQuery(name = "Lecturer.findByPhone", query = "SELECT l FROM Lecturer l WHERE l.phone = :phone"),
    @NamedQuery(name = "Lecturer.findByBirthday", query = "SELECT l FROM Lecturer l WHERE l.birthday = :birthday"),
    @NamedQuery(name = "Lecturer.findByGender", query = "SELECT l FROM Lecturer l WHERE l.gender = :gender"),
    @NamedQuery(name = "Lecturer.findByAddress", query = "SELECT l FROM Lecturer l WHERE l.address = :address")})
public class Lecturer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotEmpty(message = "{lecturer.add.code.notNullMessage}")
    @NotNull(message = "{lecturer.add.code.notNullMessage}")
    @Size(max = 10, message = "{lecturer.add.code.sizeMessage}")
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotEmpty(message = "{lecturer.add.fullName.notNullMessage}")
    @NotNull(message = "{lecturer.add.fullName.notNullMessage}")
    @Size(max = 100, message = "{lecturer.add.fullName.sizeMessage}")
    @Column(name = "full_name")
    private String fullName;
    @Basic(optional = false)
    @NotEmpty(message = "{lecturer.add.email.notNullMessage}")
    @NotNull(message = "{lecturer.add.email.notNullMessage}")
    @Size(max = 100, message = "{lecturer.add.email.sizeMessage}")
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",  message="{lecturer.add.email.format}")
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotEmpty(message = "{lecturer.add.phone.notNullMessage}")
    @NotNull(message = "{lecturer.add.phone.notNullMessage}")
    @Size(max = 15, message = "{lecturer.add.phone.sizeMessage}")
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="{lecturer.add.phone.format}")
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull(message = "{lecturer.add.birthday.notNullMessage}")
    @Column(name = "birthday")
    private Date birthday;
    @Basic(optional = false)
    @NotNull(message = "{lecturer.add.gender.notNullMessage}")
    @Column(name = "gender")
    private int gender;
    @Basic(optional = false)
    @NotEmpty(message = "{lecturer.add.address.notNullMessage}")
    @NotNull(message = "{lecturer.add.address.notNullMessage}")
    @Size(max = 255, message = "{lecturer.add.address.sizeMessage}")
    @Column(name = "address")
    private String address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewLecturer")
    @JsonIgnore
    private Set<Thesis> reviewTheses;
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnoreProperties({"code", "name", "description", "founding"})
    private Department department;
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnoreProperties({"name", "description"})
    private Position position;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"news", "student", "lecturer", "manage", "notificationUsers"})
    @Valid
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecturer")
    @JsonIgnore
    private Set<CouncilDetail> councilDetails;
    @ManyToMany(mappedBy = "lecturers")
    @JsonIgnore
    private Set<Thesis> theses;

    public Lecturer() {
    }

    public Lecturer(Integer id) {
        this.id = id;
    }

    public Lecturer(Integer id, String code, String fullName, String email, String phone, Date birthday, int gender, String address) {
        this.id = id;
        this.code = code;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department departmentId) {
        this.department = departmentId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position positionId) {
        this.position = positionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

    @XmlTransient
    public Set<CouncilDetail> getCouncilDetails() {
        return councilDetails;
    }

    public void setCouncilDetails(Set<CouncilDetail> councilDetailSet) {
        this.councilDetails = councilDetailSet;
    }

    public Set<Thesis> getTheses() {
        return theses;
    }

    public void setTheses(Set<Thesis> theses) {
        this.theses = theses;
    }

    public Set<Thesis> getReviewTheses() {
        return reviewTheses;
    }

    public void setReviewTheses(Set<Thesis> reviewTheses) {
        this.reviewTheses = reviewTheses;
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
        if (!(object instanceof Lecturer)) {
            return false;
        }
        Lecturer other = (Lecturer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.Lecturer[ id=" + id + " ]";
    }

}
