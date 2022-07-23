/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bkhuy
 */
@Entity
@Table(name = "guide")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Guide.findAll", query = "SELECT g FROM Guide g"),
    @NamedQuery(name = "Guide.findById", query = "SELECT g FROM Guide g WHERE g.id = :id"),
    @NamedQuery(name = "Guide.findByThesisId", query = "SELECT g FROM Guide g WHERE g.thesis = :thesis")})
public class Guide implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lecturer lecturer;
    @JoinColumn(name = "thesis_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Thesis thesis;

    public Guide() {
    }

    public Guide(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
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
        if (!(object instanceof Guide)) {
            return false;
        }
        Guide other = (Guide) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.Guide[ id=" + id + " ]";
    }
    
}
