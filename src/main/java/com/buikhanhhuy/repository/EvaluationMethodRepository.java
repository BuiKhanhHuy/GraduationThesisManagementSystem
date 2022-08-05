package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.EvaluationMethod;

import java.util.List;
import java.util.Map;

public interface EvaluationMethodRepository {
    public List<EvaluationMethod> getEvaluationMethods ();
    public long countEvaluationMethod(Map<String, String> params);
    public EvaluationMethod getEvaluationMethodById(int evaluationMethodId);
    public boolean addEvaluationMethod(EvaluationMethod evaluationMethod);
    public boolean updateEvaluationMethod(int evaluationMethodId, EvaluationMethod evaluationMethod);
    public boolean deleteEvaluationMethod(int evaluationMethodId);
}
