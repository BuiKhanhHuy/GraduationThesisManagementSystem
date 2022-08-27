/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
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
    @JsonIncludeProperties({"id"})
    private CouncilDetail councilDetail;
    @JoinColumn(name = "thesis_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIncludeProperties({"id"})
    private Thesis thesis;
    @OneToMany(mappedBy = "score", fetch = FetchType.EAGER)
    @Valid
    private List<ScoreDetail> scoreDetails;

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
