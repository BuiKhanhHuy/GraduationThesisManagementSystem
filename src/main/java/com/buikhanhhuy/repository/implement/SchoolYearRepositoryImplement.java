package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.SchoolYear;
import com.buikhanhhuy.repository.SchoolYearRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class SchoolYearRepositoryImplement implements SchoolYearRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<SchoolYear> getSchoolYears() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SchoolYear> query = builder.createQuery(SchoolYear.class);
        Root<SchoolYear> root = query.from(SchoolYear.class);
        query.select(root);

        return session.createQuery(query).getResultList();
    }

    @Override
    public boolean addSchoolYear(SchoolYear schoolYear) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            session.save(schoolYear);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public SchoolYear getSchoolYearById(int schoolYearId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<SchoolYear> query = builder.createQuery(SchoolYear.class);
            Root<SchoolYear> root = query.from(SchoolYear.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), schoolYearId));

            return session.createQuery(query).getSingleResult();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateSchoolYear(int schoolYearId, SchoolYear schoolYear) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            SchoolYear objSchoolYear = session.get(SchoolYear.class, schoolYearId);
            objSchoolYear.setName(schoolYear.getName());
            objSchoolYear.setStartDate(schoolYear.getStartDate());
            objSchoolYear.setEndDate(schoolYear.getEndDate());

            session.update(objSchoolYear);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteSchoolYear(int schoolYearId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            SchoolYear schoolYear = session.get(SchoolYear.class, schoolYearId);
            session.delete(schoolYear);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
