/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "department")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
    @NamedQuery(name = "Department.findById", query = "SELECT d FROM Department d WHERE d.id = :id"),
    @NamedQuery(name = "Department.findByCode", query = "SELECT d FROM Department d WHERE d.code = :code"),
    @NamedQuery(name = "Department.findByName", query = "SELECT d FROM Department d WHERE d.name = :name"),
    @NamedQuery(name = "Department.findByDescription", query = "SELECT d FROM Department d WHERE d.description = :description"),
    @NamedQuery(name = "Department.findByFounding", query = "SELECT d FROM Department d WHERE d.founding = :founding")})
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotEmpty(message = "{department.add.code.notNullMessage}")
    @NotNull(message ="{department.add.code.notNullMessage}")
    @Size(max = 10, message = "{department.add.code.sizeMessage}")
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotEmpty(message = "{department.add.name.notNullMessage}")
    @NotNull(message = "{department.add.name.notNullMessage}")
    @Size(max = 100, message = "{department.add.name.sizeMessage}")
    @Column(name = "name")
    private String name;
    @Size(max = 255, message = "{department.add.description.maxMessage}")
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull(message = "{department.add.founding.notNullMessage}")
    @Column(name = "founding")
    private Date founding;
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<Lecturer> lecturers;
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<Major> majors;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<Topic> topics;

    public Department() {
    }

    public Department(Integer id) {
        this.id = id;
    }

    public Department(Integer id, String code, String name, Date founding) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.founding = founding;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFounding() {
        return founding;
    }

    public void setFounding(Date founding) {
        this.founding = founding;
    }

    @XmlTransient
    public Set<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<Lecturer> lecturerSet) {
        this.lecturers = lecturerSet;
    }

    @XmlTransient
    public Set<Major> getMajors() {
        return majors;
    }

    public void setMajors(Set<Major> majorSet) {
        this.majors = majorSet;
    }


    @XmlTransient
    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topicSet) {
        this.topics = topicSet;
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
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.Department[ id=" + id + " ]";
    }
    
}
