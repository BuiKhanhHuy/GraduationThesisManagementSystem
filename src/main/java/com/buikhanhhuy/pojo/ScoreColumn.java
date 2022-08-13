/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "score_column")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScoreColumn.findAll", query = "SELECT s FROM ScoreColumn s"),
    @NamedQuery(name = "ScoreColumn.findById", query = "SELECT s FROM ScoreColumn s WHERE s.id = :id"),
    @NamedQuery(name = "ScoreColumn.findByName", query = "SELECT s FROM ScoreColumn s WHERE s.name = :name"),
    @NamedQuery(name = "ScoreColumn.findByWeight", query = "SELECT s FROM ScoreColumn s WHERE s.weight = :weight")})
public class ScoreColumn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotEmpty(message = "{scoreColumn.add.name.notNullMessage}")
    @NotNull(message = "{scoreColumn.add.name.notNullMessage}")
    @Size(max = 100, message = "{scoreColumn.add.name.sizeMessage}")
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "weight")
    @NotNull(message = "{scoreColumn.add.weight.notNullMessage}")
    private Double weight;
    @JoinColumn(name = "score_component_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIncludeProperties({"id", "name"})
    private ScoreComponent scoreComponent;
    @OneToMany(mappedBy = "scoreColumn")
    @JsonIgnore
    private List<ScoreDetail> scoreDetails;

    public ScoreColumn() {
    }

    public ScoreColumn(Integer id) {
        this.id = id;
    }

    public ScoreColumn(Integer id, String name) {
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public ScoreComponent getScoreComponent() {
        return scoreComponent;
    }

    public void setScoreComponent(ScoreComponent scoreComponentId) {
        this.scoreComponent = scoreComponentId;
    }

    @XmlTransient
    public List<ScoreDetail> getScoreDetails() {
        return scoreDetails;
    }

    public void setScoreDetails(List<ScoreDetail> scoreDetailSet) {
        this.scoreDetails = scoreDetailSet;
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
        if (!(object instanceof ScoreColumn)) {
            return false;
        }
        ScoreColumn other = (ScoreColumn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.ScoreColumn[ id=" + id + " ]";
    }
    
}
