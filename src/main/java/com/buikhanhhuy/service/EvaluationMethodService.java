package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.EvaluationMethod;

import java.util.List;

public interface EvaluationMethodService {
    public List<EvaluationMethod> getEvaluationMethods();
    public boolean addEvaluationMethod(EvaluationMethod evaluationMethod);
    public EvaluationMethod getEvaluationMethodById(int evaluationMethodId);
    public boolean deleteEvaluationMethod(int evaluationMethodId);
}
