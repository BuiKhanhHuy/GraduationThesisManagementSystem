/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

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
@Table(name = "council_detail")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "CouncilDetail.findAll", query = "SELECT c FROM CouncilDetail c"),
        @NamedQuery(name = "CouncilDetail.findById", query = "SELECT c FROM CouncilDetail c WHERE c.id = :id"),
        @NamedQuery(name = "CouncilDetail.findByPosition", query = "SELECT c FROM CouncilDetail c WHERE c.position = :position")})
public class CouncilDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull(message = "{councilDetail.add.position.notNullMessage}")
    @NotEmpty(message = "{councilDetail.add.position.notNullMessage}")
    @Size(max = 45, message = "{councilDetail.add.position.sizeMessage}")
    @Column(name = "position")
    private String position;
    @OneToMany(mappedBy = "councilDetail")
    @JsonIgnore
    private Set<Score> scores;
    @JoinColumn(name = "council_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIncludeProperties({"id", "name"})
    private Council council;
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id")
    @NotNull(message = "{councilDetail.add.lecturer.notNullMessage}")
    @ManyToOne(optional = false)
    @JsonIncludeProperties({"id", "fullName"})
    private Lecturer lecturer;

    public CouncilDetail() {
    }

    public CouncilDetail(Integer id) {
        this.id = id;
    }

    public CouncilDetail(Integer id, String position) {
        this.id = id;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @XmlTransient
    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scoreSet) {
        this.scores = scoreSet;
    }

    public Council getCouncil() {
        return council;
    }

    public void setCouncil(Council council) {
        this.council = council;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturerId) {
        this.lecturer = lecturerId;
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
        if (!(object instanceof CouncilDetail)) {
            return false;
        }
        CouncilDetail other = (CouncilDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.CouncilDetail[ id=" + id + " ]";
    }

}