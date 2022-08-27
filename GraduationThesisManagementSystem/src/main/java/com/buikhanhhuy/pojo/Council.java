package com.buikhanhhuy.pojo;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author bkhuy
 */
@Entity
@Table(name = "council")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Council.findAll", query = "SELECT c FROM Council c"), @NamedQuery(name = "Council.findById", query = "SELECT c FROM Council c WHERE c.id = :id"), @NamedQuery(name = "Council.findByName", query = "SELECT c FROM Council c WHERE c.name = :name"), @NamedQuery(name = "Council.findByDescription", query = "SELECT c FROM Council c WHERE c.description = :description")})
public class Council implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull(message = "{council.add.name.notNullMessage}")
    @NotEmpty(message = "{council.add.name.notNullMessage}")
    @Size(max = 200, message = "{council.add.name.sizeMessage}")
    @Column(name = "name")
    private String name;
    @Size(max = 255, message = "{council.add.description.sizeMessage}")
    @Column(name = "description")
    private String description;

    @Column(name = "is_block")
    private Boolean block;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "council")
    @NotEmpty(message = "{council.add.councilDetails.notNullMessage}")
    @JsonIncludeProperties({"position", "lecturer", "council"})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Valid
    private List<CouncilDetail> councilDetails;
    @JoinColumn(name = "school_year_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIncludeProperties({"id", "name"})
    private SchoolYear schoolYear;
    @OneToMany(mappedBy = "council", fetch = FetchType.EAGER)
    @NotEmpty(message = "{council.add.theses.notNullMessage}")
    @JsonIncludeProperties({"id", "code", "topic", "students"})
    private Set<Thesis> theses;


    {
        this.block = true;
    }

    public Council() {
    }

    public Council(Integer id) {
        this.id = id;
    }

    public Council(Integer id, String name) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }

    @XmlTransient
    public List<CouncilDetail> getCouncilDetails() {
        return councilDetails;
    }

    public void setCouncilDetails(List<CouncilDetail> councilDetailSet) {
        this.councilDetails = councilDetailSet;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYearId) {
        this.schoolYear = schoolYearId;
    }

    public Set<Thesis> getTheses() {
        return theses;
    }

    public void setTheses(Set<Thesis> theses) {
        this.theses = theses;
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
        if (!(object instanceof Council)) {
            return false;
        }
        Council other = (Council) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buikhanhhuy.pojo.Council[ id=" + id + " ]";
    }

}
