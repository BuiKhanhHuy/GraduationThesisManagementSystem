/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bkhuy
 */
@Entity
@Table(name = "counter_argument")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CounterArgument.findAll", query = "SELECT c FROM CounterArgument c"),
    @NamedQuery(name = "CounterArgument.findById", query = "SELECT c FROM CounterArgument c WHERE c.id = :id"),
    @NamedQuery(name = "CounterArgument.findByScores", query = "SELECT c FROM CounterArgument c WHERE c.scores = :scores"),
    @NamedQuery(name = "CounterArgument.findByAllow", query = "SELECT c FROM CounterArgument c WHERE c.allow = :allow")})
public class CounterArgument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "comment")
    private String comment;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "scores")
    private Double scores;
    @Column(name = "allow")
    private Boolean allow;
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lecturer lecturer;
    @JoinColumn(name = "thesis_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Thesis thesis;

    public CounterArgument() {
    }

    public CounterArgument(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getScores() {
        return scores;
    }

    public void setScores(Double scores) {
        this.scores = scores;
    }

    public Boolean getAllow() {
        return allow;
    }

    public void setAllow(Boolean allow) {
        this.allow = allow;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturerId) {
        this.lecturer = lecturerId;
    }

    public Thesis getThesis() {
        return thesis;
    }

    public void setThesis(Thesis thesisId) {
        this.thesis = thesisId;
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
        if (!(object instanceof CounterArgument)) {
            return false;
        }
        CounterArgument other = (CounterArgument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.CounterArgument[ id=" + id + " ]";
    }
    
}
