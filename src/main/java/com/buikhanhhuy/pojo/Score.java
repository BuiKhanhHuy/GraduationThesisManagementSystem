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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bkhuy
 */
@Entity
@Table(name = "score")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Score.findAll", query = "SELECT s FROM Score s"),
    @NamedQuery(name = "Score.findById", query = "SELECT s FROM Score s WHERE s.id = :id")})
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "council_detail_id", referencedColumnName = "id")
    @ManyToOne
    private CouncilDetail councilDetail;
    @JoinColumn(name = "thesis_id", referencedColumnName = "id")
    @ManyToOne
    private Thesis thesis;
    @OneToMany(mappedBy = "score")
    private Set<ScoreDetail> scoreDetails;

    public Score() {
    }

    public Score(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CouncilDetail getCouncilDetail() {
        return councilDetail;
    }

    public void setCouncilDetail(CouncilDetail councilDetailId) {
        this.councilDetail = councilDetailId;
    }

    public Thesis getThesis() {
        return thesis;
    }

    public void setThesis(Thesis thesisId) {
        this.thesis = thesisId;
    }

    @XmlTransient
    public Set<ScoreDetail> getScoreDetails() {
        return scoreDetails;
    }

    public void setScoreDetails(Set<ScoreDetail> scoreDetailSet) {
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
        if (!(object instanceof Score)) {
            return false;
        }
        Score other = (Score) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.Score[ id=" + id + " ]";
    }
    
}
