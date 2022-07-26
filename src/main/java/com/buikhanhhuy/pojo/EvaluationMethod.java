/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bkhuy
 */
@Entity
@Table(name = "evaluation_method")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EvaluationMethod.findAll", query = "SELECT e FROM EvaluationMethod e"),
    @NamedQuery(name = "EvaluationMethod.findById", query = "SELECT e FROM EvaluationMethod e WHERE e.id = :id"),
    @NamedQuery(name = "EvaluationMethod.findByName", query = "SELECT e FROM EvaluationMethod e WHERE e.name = :name"),
    @NamedQuery(name = "EvaluationMethod.findByActive", query = "SELECT e FROM EvaluationMethod e WHERE e.active = :active")})
public class EvaluationMethod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @OneToMany(mappedBy = "evaluationMethod", fetch = FetchType.EAGER)
    private Set<ScoreComponent> scoreComponents;

    public EvaluationMethod() {
    }

    public EvaluationMethod(Integer id) {
        this.id = id;
    }

    public EvaluationMethod(Integer id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @XmlTransient
    public Set<ScoreComponent> getScoreComponents() {
        return scoreComponents;
    }

    public void setScoreComponents(Set<ScoreComponent> scoreComponentSet) {
        this.scoreComponents = scoreComponentSet;
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
        if (!(object instanceof EvaluationMethod)) {
            return false;
        }
        EvaluationMethod other = (EvaluationMethod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.EvaluationMethod[ id=" + id + " ]";
    }
    
}
