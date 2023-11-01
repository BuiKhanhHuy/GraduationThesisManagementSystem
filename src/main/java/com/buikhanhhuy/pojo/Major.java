/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
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
import javax.persistence.Table;
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
@Table(name = "major")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Major.findAll", query = "SELECT m FROM Major m"),
    @NamedQuery(name = "Major.findById", query = "SELECT m FROM Major m WHERE m.id = :id"),
    @NamedQuery(name = "Major.findByCode", query = "SELECT m FROM Major m WHERE m.code = :code"),
    @NamedQuery(name = "Major.findByName", query = "SELECT m FROM Major m WHERE m.name = :name"),
    @NamedQuery(name = "Major.findByDescription", query = "SELECT m FROM Major m WHERE m.description = :description")})
public class Major implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotEmpty(message = "{major.add.code.notNullMessage}")
    @NotNull(message = "{major.add.code.notNullMessage}")
    @Size(max = 10, message = "{major.add.code.sizeMessage}")
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotEmpty(message = "{major.add.name.notNullMessage}")
    @NotNull(message = "{major.add.name.notNullMessage}")
    @Size(max = 100, message = "{major.add.name.sizeMessage}")
    @Column(name = "name")
    private String name;
    @Size(max = 255, message = "{major.add.description.maxMessage}")
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "major")
    @JsonIgnore
    private Set<Student> students;

    @OneToMany(mappedBy = "major")
    @JsonIgnore
    private Set<Thesis> theses;

    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"code", "description", "founding"})
    @ManyToOne
    private Department department;

    public Major() {
    }

    public Major(Integer id) {
        this.id = id;
    }

    public Major(Integer id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
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

    @XmlTransient
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> studentSet) {
        this.students = studentSet;
    }

    public Set<Thesis> getTheses() {
        return theses;
    }

    public void setTheses(Set<Thesis> theses) {
        this.theses = theses;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department departmentId) {
        this.department = departmentId;
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
        if (!(object instanceof Major)) {
            return false;
        }
        Major other = (Major) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.Major[ id=" + id + " ]";
    }
    
}
