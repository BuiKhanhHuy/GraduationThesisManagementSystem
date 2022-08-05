package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.EvaluationMethod;

import java.util.List;

public interface EvaluationMethodRepository {
    public List<EvaluationMethod> getEvaluationMethods ();
    public EvaluationMethod getEvaluationMethodById(int evaluationMethodId);
    public boolean addEvaluationMethod(EvaluationMethod evaluationMethod);
    public boolean deleteEvaluationMethod(int evaluationMethodId);
}
