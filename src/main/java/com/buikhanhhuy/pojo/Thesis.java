/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buikhanhhuy.pojo;

import com.fasterxml.jackson.annotation.*;

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
@NamedQueries({@NamedQuery(name = "Thesis.findAll", query = "SELECT t FROM Thesis t"), @NamedQuery(name = "Thesis.findById", query = "SELECT t FROM Thesis t WHERE t.id = :id"), @NamedQuery(name = "Thesis.findByCode", query = "SELECT t FROM Thesis t WHERE t.code = :code"), @NamedQuery(name = "Thesis.findByStartDate", query = "SELECT t FROM Thesis t WHERE t.startDate = :startDate"), @NamedQuery(name = "Thesis.findByComplateDate", query = "SELECT t FROM Thesis t WHERE t.complateDate = :complateDate"), @NamedQuery(name = "Thesis.findByThesisStartDate", query = "SELECT t FROM Thesis t WHERE t.thesisStartDate = :thesisStartDate"), @NamedQuery(name = "Thesis.findByThesisEndDate", query = "SELECT t FROM Thesis t WHERE t.thesisEndDate = :thesisEndDate"), @NamedQuery(name = "Thesis.findByReportFile", query = "SELECT t FROM Thesis t WHERE t.reportFile = :reportFile"), @NamedQuery(name = "Thesis.findByTotalScore", query = "SELECT t FROM Thesis t WHERE t.totalScore = :totalScore"), @NamedQuery(name = "Thesis.findByResult", query = "SELECT t FROM Thesis t WHERE t.result = :result")})
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
    @Column(name = "report_file")
    private String reportFile;

    @Lob
    @Size(max = 2147483647, message = "{thesis.add.comment.sizeMessage}")
    @Column(name = "comment")
    private String comment;
    @Min(value = 0, message = "{thesis.add.totalScore.sizeMessage}")
    @Max(value = 10, message = "{thesis.add.totalScore.sizeMessage}")
    @Column(name = "total_score")
    private Double totalScore;
    @Column(name = "result")
    private Integer result;

    @OneToMany(mappedBy = "thesis")
    @JsonIgnore
    private Set<Score> scores;
    @JoinColumn(name = "council_id", referencedColumnName = "id")
    @JsonIgnore
    @ManyToOne
    private Council council;
    @JoinColumn(name = "major_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"code", "description", "department"})
    @ManyToOne
    private Major major;
    @JoinColumn(name = "school_year_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"startDate", "endDate"})
    @ManyToOne
    private SchoolYear schoolYear;
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"description", "department"})
    @ManyToOne
    private Topic topic;
    @ManyToMany(fetch = FetchType.EAGER)
    @NotEmpty(message = "{thesis.add.lecturers.notNullMessage}")
    @JoinTable(name = "guide", joinColumns = {@JoinColumn(name = "thesis_id")}, inverseJoinColumns = {@JoinColumn(name = "lecturer_id")})
    @JsonIncludeProperties({"id", "fullName"})
    private Set<Lecturer> lecturers;
    @ManyToMany(fetch = FetchType.EAGER)
    @NotEmpty(message = "{thesis.add.students.notNullMessage}")
    @JoinTable(name = "perform", joinColumns = {@JoinColumn(name = "thesis_id")}, inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private Set<Student> students;

    @ManyToOne
    @JoinColumn(name = "review_lecturer_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id"})
    private Lecturer reviewLecturer;

    @Transient
    private Boolean scored;

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
    }

    {
        this.totalScore = 0.0;
        this.result = 1;
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

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public Council getCouncil() {
        return council;
    }

    public void setCouncil(Council council) {
        this.council = council;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Set<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Lecturer getReviewLecturer() {
        return reviewLecturer;
    }

    public void setReviewLecturer(Lecturer reviewLecturer) {
        this.reviewLecturer = reviewLecturer;
    }

    public Boolean getScored() {
        return scored;
    }

    public void setScored(Boolean scored) {
        this.scored = scored;
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
