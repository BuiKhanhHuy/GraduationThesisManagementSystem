/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "school_year")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SchoolYear.findAll", query = "SELECT s FROM SchoolYear s"),
    @NamedQuery(name = "SchoolYear.findById", query = "SELECT s FROM SchoolYear s WHERE s.id = :id"),
    @NamedQuery(name = "SchoolYear.findByName", query = "SELECT s FROM SchoolYear s WHERE s.name = :name"),
    @NamedQuery(name = "SchoolYear.findByStartDate", query = "SELECT s FROM SchoolYear s WHERE s.startDate = :startDate"),
    @NamedQuery(name = "SchoolYear.findByEndDate", query = "SELECT s FROM SchoolYear s WHERE s.endDate = :endDate")})
public class SchoolYear implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotEmpty(message = "{schoolYear.add.name.notNullMessage}")
    @NotNull(message = "{schoolYear.add.name.notNullMessage}")
    @Size(max = 50, message = "{schoolYear.add.name.sizeMessage}")
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull(message = "{schoolYear.add.startDate.notNullMessage}")
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull(message = "{schoolYear.add.endDate.notNullMessage}")
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @OneToMany(mappedBy = "schoolYear")
    @JsonIgnore
    private Set<Student> students;
    @OneToMany(mappedBy = "schoolYear")
    @JsonIgnore
    private Set<Council> councils;
    @OneToMany(mappedBy = "schoolYear")
    @JsonIgnore
    private Set<Thesis> theses;

    public SchoolYear() {
    }

    public SchoolYear(Integer id) {
        this.id = id;
    }

    public SchoolYear(Integer id, String name, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @XmlTransient
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> studentSet) {
        this.students = studentSet;
    }

    @XmlTransient
    public Set<Council> getCouncils() {
        return councils;
    }

    public void setCouncils(Set<Council> councilSet) {
        this.councils = councilSet;
    }

    @XmlTransient
    public Set<Thesis> getTheses() {
        return theses;
    }

    public void setTheses(Set<Thesis> thesisSet) {
        this.theses = thesisSet;
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
        if (!(object instanceof SchoolYear)) {
            return false;
        }
        SchoolYear other = (SchoolYear) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.SchoolYear[ id=" + id + " ]";
    }
    
}
