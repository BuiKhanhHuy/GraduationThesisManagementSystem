/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bkhuy
 */
@Entity
@Table(name = "council")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Council.findAll", query = "SELECT c FROM Council c"),
    @NamedQuery(name = "Council.findById", query = "SELECT c FROM Council c WHERE c.id = :id"),
    @NamedQuery(name = "Council.findByName", query = "SELECT c FROM Council c WHERE c.name = :name"),
    @NamedQuery(name = "Council.findByDescription", query = "SELECT c FROM Council c WHERE c.description = :description")})
public class Council implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "council")
    private Set<CouncilDetail> councilDetails;
    @JoinColumn(name = "school_year_id", referencedColumnName = "id")
    @ManyToOne
    private SchoolYear schoolYear;
    @OneToMany(mappedBy = "council")
    private Set<Thesis> theses;

    public Council() {
    }

    public Council(Integer id) {
        this.id = id;
    }

    public Council(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Set<CouncilDetail> getCouncilDetails() {
        return councilDetails;
    }

    public void setCouncilDetails(Set<CouncilDetail> councilDetailSet) {
        this.councilDetails = councilDetailSet;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYearId) {
        this.schoolYear = schoolYearId;
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
        if (!(object instanceof Council)) {
            return false;
        }
        Council other = (Council) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.Council[ id=" + id + " ]";
    }
    
}
