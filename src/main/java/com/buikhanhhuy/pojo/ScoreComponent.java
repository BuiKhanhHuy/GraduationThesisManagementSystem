/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bkhuy
 */
@Entity
@Table(name = "score_component")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScoreComponent.findAll", query = "SELECT s FROM ScoreComponent s"),
    @NamedQuery(name = "ScoreComponent.findById", query = "SELECT s FROM ScoreComponent s WHERE s.id = :id"),
    @NamedQuery(name = "ScoreComponent.findByName", query = "SELECT s FROM ScoreComponent s WHERE s.name = :name")})
public class ScoreComponent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "scoreComponent")
    private Set<ScoreColumn> scoreColumns;
    @JoinColumn(name = "evaluation_method_id", referencedColumnName = "id")
    @ManyToOne
    private EvaluationMethod evaluationMethod;

    public ScoreComponent() {
    }

    public ScoreComponent(Integer id) {
        this.id = id;
    }

    public ScoreComponent(Integer id, String name) {
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

    @XmlTransient
    public Set<ScoreColumn> getScoreColumns() {
        return scoreColumns;
    }

    public void setScoreColumns(Set<ScoreColumn> scoreColumnSet) {
        this.scoreColumns = scoreColumnSet;
    }

    public EvaluationMethod getEvaluationMethod() {
        return evaluationMethod;
    }

    public void setEvaluationMethod(EvaluationMethod evaluationMothodId) {
        this.evaluationMethod = evaluationMothodId;
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
        if (!(object instanceof ScoreComponent)) {
            return false;
        }
        ScoreComponent other = (ScoreComponent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.ScoreComponent[ id=" + id + " ]";
    }
    
}
