package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.pojo.Student;
import com.buikhanhhuy.pojo.Thesis;
import com.buikhanhhuy.repository.CounterArgumentRepository;
import com.buikhanhhuy.repository.ThesisRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class ThesisRepositoryImplement implements ThesisRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;
    @Autowired
    private CounterArgumentRepository counterArgumentRepository;

    @Override
    public List<Thesis> getTheses() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Thesis> query = builder.createQuery(Thesis.class);
        Root<Thesis> root = query.from(Thesis.class);

        query.select(root);

        return session.createQuery(query).getResultList();
    }

    @Override
    public boolean addThesis(Thesis thesis) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            Set<Student> students = new HashSet<>();
            Set<Lecturer> lecturers = new HashSet<>();
            for (int performStudentId : thesis.getPerformStudentsId()) {
                students.add(session.get(Student.class, performStudentId));
            }
            for (int instructorId : thesis.getInstructorsId()) {
                lecturers.add(session.get(Lecturer.class, instructorId));
            }

            thesis.setStudents(students);
            thesis.setLecturers(lecturers);
            session.save(thesis);

            this.counterArgumentRepository.addCounterArgument(thesis, thesis.getReviewLecturer());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public Thesis getThesisById(int thesisId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Thesis> query = builder.createQuery(Thesis.class);
            Root<Thesis> root = query.from(Thesis.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), thesisId));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
