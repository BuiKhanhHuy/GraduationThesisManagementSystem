/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author bkhuy
 */
@Entity
@Table(name = "thesis")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Thesis.findAll", query = "SELECT t FROM Thesis t"), @NamedQuery(name = "Thesis.findById", query = "SELECT t FROM Thesis t WHERE t.id = :id"), @NamedQuery(name = "Thesis.findByCode", query = "SELECT t FROM Thesis t WHERE t.code = :code"), @NamedQuery(name = "Thesis.findByStartDate", query = "SELECT t FROM Thesis t WHERE t.startDate = :startDate"), @NamedQuery(name = "Thesis.findByComplateDate", query = "SELECT t FROM Thesis t WHERE t.complateDate = :complateDate"), @NamedQuery(name = "Thesis.findByThesisStartDate", query = "SELECT t FROM Thesis t WHERE t.thesisStartDate = :thesisStartDate"), @NamedQuery(name = "Thesis.findByThesisEndDate", query = "SELECT t FROM Thesis t WHERE t.thesisEndDate = :thesisEndDate"), @NamedQuery(name = "Thesis.findByReportFile", query = "SELECT t FROM Thesis t WHERE t.reportFile = :reportFile"), @NamedQuery(name = "Thesis.findByStatus", query = "SELECT t FROM Thesis t WHERE t.status = :status"), @NamedQuery(name = "Thesis.findByTotalScore", query = "SELECT t FROM Thesis t WHERE t.totalScore = :totalScore"), @NamedQuery(name = "Thesis.findByResult", query = "SELECT t FROM Thesis t WHERE t.result = :result")})
public class Thesis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotEmpty(message = "{thesis.add.code.notNullMessage}")
    @NotNull(message = "{thesis.add.code.notNullMessage}")
    @Size(max = 20, message = "{thesis.add.code.sizeMessage}")
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull(message = "{thesis.add.startDate.notNullMessage}")
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @NotNull(message = "{thesis.add.endDate.notNullMessage}")
    @Column(name = "complate_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date complateDate;
    @Basic(optional = false)
    @NotNull(message = "{thesis.add.thesisStartDate.notNullMessage}")
    @Column(name = "thesis_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thesisStartDate;
    @Basic(optional = false)
    @NotNull(message = "{thesis.add.thesisEndDate.notNullMessage}")
    @Column(name = "thesis_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thesisEndDate;
    @Basic(optional = false)
    @Size(min = 1, max = 300, message = "{thesis.add.reportFile.notNullMessage}")
    @Column(name = "report_file")
    private String reportFile;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @Lob
    @Size(max = 2147483647, message = "{thesis.add.comment.sizeMessage}")
    @Column(name = "comment")
    private String comment;
    @Min(value = 0, message = "{thesis.add.totalScore.sizeMessage}")
    @Max(value = 10, message = "{thesis.add.totalScore.sizeMessage}")
    @Column(name = "total_score")
    private Double totalScore;
    @Column(name = "result")
    private Boolean result;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thesis")
    @JsonIgnore
    private Set<CounterArgument> counterArguments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thesis")
    @JsonIgnore
    private Set<Perform> performs;
    @OneToMany(mappedBy = "thesis")
    @JsonIgnore
    private Set<Score> scores;
    @JoinColumn(name = "council_id", referencedColumnName = "id")
    @JsonIgnore
    @ManyToOne
    private Council council;
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @ManyToOne
    private Department department;
    @JoinColumn(name = "school_year_id", referencedColumnName = "id")
    @ManyToOne
    private SchoolYear schoolYear;
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @ManyToOne
    private Topic topic;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thesis")
    @JsonIgnore
    private Set<Guide> guides;

    @Transient
    @NotEmpty(message = "{thesis.add.instructorsId.sizeMessage}")
    private Set<Integer> instructorsId;
    @Transient
    @NotEmpty(message = "{thesis.add.reviewLecturersId.sizeMessage}")
    private Set<Integer> reviewLecturersId;
    @Transient
    @NotEmpty(message = "{thesis.add.performStudentsId.sizeMessage}")
    private Set<Integer> performStudentsId;


    public Thesis() {
    }

    public Thesis(Integer id) {
        this.id = id;
    }

    public Thesis(Integer id, String code, Date startDate, Date complateDate, Date thesisStartDate, Date thesisEndDate, String reportFile, int status) {
        this.id = id;
        this.code = code;
        this.startDate = startDate;
        this.complateDate = complateDate;
        this.thesisStartDate = thesisStartDate;
        this.thesisEndDate = thesisEndDate;
        this.reportFile = reportFile;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getComplateDate() {
        return complateDate;
    }

    public void setComplateDate(Date complateDate) {
        this.complateDate = complateDate;
    }

    public Date getThesisStartDate() {
        return thesisStartDate;
    }

    public void setThesisStartDate(Date thesisStartDate) {
        this.thesisStartDate = thesisStartDate;
    }

    public Date getThesisEndDate() {
        return thesisEndDate;
    }

    public void setThesisEndDate(Date thesisEndDate) {
        this.thesisEndDate = thesisEndDate;
    }

    public String getReportFile() {
        return reportFile;
    }

    public void setReportFile(String reportFile) {
        this.reportFile = reportFile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    @XmlTransient
    public Set<CounterArgument> getCounterArguments() {
        return counterArguments;
    }

    public void setCounterArguments(Set<CounterArgument> counterArgumentSet) {
        this.counterArguments = counterArgumentSet;
    }

    @XmlTransient
    public Set<Perform> getPerforms() {
        return performs;
    }

    public void setPerforms(Set<Perform> performSet) {
        this.performs = performSet;
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

    public void setCouncil(Council councilId) {
        this.council = councilId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department departmentId) {
        this.department = departmentId;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYearId) {
        this.schoolYear = schoolYearId;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topicId) {
        this.topic = topicId;
    }

    @XmlTransient
    public Set<Guide> getGuides() {
        return guides;
    }

    public void setGuides(Set<Guide> guideSet) {
        this.guides = guideSet;
    }

    public Set<Integer> getInstructorsId() {
        return instructorsId;
    }

    public void setInstructorsId(Set<Integer> instructorsId) {
        this.instructorsId = instructorsId;
    }

    public Set<Integer> getReviewLecturersId() {
        return reviewLecturersId;
    }

    public void setReviewLecturersId(Set<Integer> reviewLecturersId) {
        this.reviewLecturersId = reviewLecturersId;
    }

    public Set<Integer> getPerformStudentsId() {
        return performStudentsId;
    }

    public void setPerformStudentsId(Set<Integer> performStudentsId) {
        this.performStudentsId = performStudentsId;
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
        if (!(object instanceof Thesis)) {
            return false;
        }
        Thesis other = (Thesis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.Thesis[ id=" + id + " ]";
    }

}
