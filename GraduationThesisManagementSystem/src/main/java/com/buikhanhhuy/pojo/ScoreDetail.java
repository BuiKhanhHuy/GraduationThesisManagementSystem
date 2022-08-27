/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bkhuy
 */
@Entity
@Table(name = "score_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScoreDetail.findAll", query = "SELECT s FROM ScoreDetail s"),
    @NamedQuery(name = "ScoreDetail.findById", query = "SELECT s FROM ScoreDetail s WHERE s.id = :id")})
public class ScoreDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull(message = "{score.add.scoreNum.notNullMessage}")
    @DecimalMin(value = "0", message = "{score.add.scoreNum.sizeMessage}")
    @DecimalMax(value = "10", message = "{score.add.scoreNum.sizeMessage}")
    @Column(name = "score")
    private Double scoreNum;
    @JoinColumn(name = "score_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Score score;
    @JoinColumn(name = "score_column_id", referencedColumnName = "id")
    @ManyToOne
    private ScoreColumn scoreColumn;

    public ScoreDetail() {
    }

    public ScoreDetail(Integer id) {
        this.id = id;
    }

    public ScoreDetail(Integer id, double scoreNum) {
        this.id = id;
        this.scoreNum = scoreNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(Double score) {
        this.scoreNum = score;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score scoreId) {
        this.score = scoreId;
    }

    public ScoreColumn getScoreColumn() {
        return scoreColumn;
    }

    public void setScoreColumn(ScoreColumn scoreColumnId) {
        this.scoreColumn = scoreColumnId;
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
        if (!(object instanceof ScoreDetail)) {
            return false;
        }
        ScoreDetail other = (ScoreDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.ScoreDetail[ id=" + id + " ]";
    }
    
}
