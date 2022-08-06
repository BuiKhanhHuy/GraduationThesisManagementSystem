package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.CounterArgument;
import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.pojo.Thesis;
import com.buikhanhhuy.repository.CounterArgumentRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CounterArgumentRepositoryImplement implements CounterArgumentRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public boolean addCounterArgument(Thesis thesis, Lecturer lecturer) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            CounterArgument counterArgument = new CounterArgument();
            counterArgument.setThesis(thesis);
            counterArgument.setLecturer(lecturer);

            session.save(counterArgument);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
