/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bkhuy
 */
@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id"),
    @NamedQuery(name = "Student.findByCode", query = "SELECT s FROM Student s WHERE s.code = :code"),
    @NamedQuery(name = "Student.findByFullName", query = "SELECT s FROM Student s WHERE s.fullName = :fullName"),
    @NamedQuery(name = "Student.findByEmail", query = "SELECT s FROM Student s WHERE s.email = :email"),
    @NamedQuery(name = "Student.findByPhone", query = "SELECT s FROM Student s WHERE s.phone = :phone"),
    @NamedQuery(name = "Student.findByBirthday", query = "SELECT s FROM Student s WHERE s.birthday = :birthday"),
    @NamedQuery(name = "Student.findByGender", query = "SELECT s FROM Student s WHERE s.gender = :gender"),
    @NamedQuery(name = "Student.findByAddress", query = "SELECT s FROM Student s WHERE s.address = :address"),
    @NamedQuery(name = "Student.findByGpa", query = "SELECT s FROM Student s WHERE s.gpa = :gpa")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "full_name")
    private String fullName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gender")
    private int gender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gpa")
    private double gpa;
    @JoinColumn(name = "major_id", referencedColumnName = "id")
    @ManyToOne
    private Major major;
    @JoinColumn(name = "school_year_id", referencedColumnName = "id")
    @ManyToOne
    private SchoolYear schoolYear;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Set<Perform> performs;

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    public Student(Integer id, String code, String fullName, String email, String phone, Date birthday, int gender, String address, double gpa) {
        this.id = id;
        this.code = code;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.gpa = gpa;
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

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major majorId) {
        this.major = majorId;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYearId) {
        this.schoolYear = schoolYearId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

    @XmlTransient
    public Set<Perform> getPerforms() {
        return performs;
    }

    public void setPerforms(Set<Perform> performSet) {
        this.performs = performSet;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.Student[ id=" + id + " ]";
    }
    
}
