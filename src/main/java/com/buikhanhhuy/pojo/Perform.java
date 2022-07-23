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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bkhuy
 */
@Entity
@Table(name = "perform")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perform.findAll", query = "SELECT p FROM Perform p"),
    @NamedQuery(name = "Perform.findById", query = "SELECT p FROM Perform p WHERE p.id = :id")})
public class Perform implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "thesis_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Thesis thesis;

    public Perform() {
    }

    public Perform(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student studentId) {
        this.student = studentId;
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
        if (!(object instanceof Perform)) {
            return false;
        }
        Perform other = (Perform) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.Perform[ id=" + id + " ]";
    }
    
}
