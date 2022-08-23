package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.Council;
import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.pojo.Major;
import com.buikhanhhuy.pojo.SchoolYear;
import com.buikhanhhuy.pojo.Score;
import com.buikhanhhuy.pojo.Student;
import com.buikhanhhuy.pojo.Topic;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-24T00:31:36")
@StaticMetamodel(Thesis.class)
public class Thesis_ { 

    public static volatile SingularAttribute<Thesis, Date> thesisEndDate;
    public static volatile SingularAttribute<Thesis, String> code;
    public static volatile SetAttribute<Thesis, Score> scores;
    public static volatile SingularAttribute<Thesis, String> reportFile;
    public static volatile SetAttribute<Thesis, Lecturer> lecturers;
    public static volatile SetAttribute<Thesis, Student> students;
    public static volatile SingularAttribute<Thesis, Lecturer> reviewLecturer;
    public static volatile SingularAttribute<Thesis, Date> complateDate;
    public static volatile SingularAttribute<Thesis, Double> totalScore;
    public static volatile SingularAttribute<Thesis, Integer> result;
    public static volatile SingularAttribute<Thesis, Date> thesisStartDate;
    public static volatile SingularAttribute<Thesis, Major> major;
    public static volatile SingularAttribute<Thesis, Council> council;
    public static volatile SingularAttribute<Thesis, SchoolYear> schoolYear;
    public static volatile SingularAttribute<Thesis, Topic> topic;
    public static volatile SingularAttribute<Thesis, String> comment;
    public static volatile SingularAttribute<Thesis, Integer> id;
    public static volatile SingularAttribute<Thesis, Date> startDate;

}