package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.EvaluationMethod;
import com.buikhanhhuy.repository.EvaluationMethodRepository;
import com.buikhanhhuy.service.EvaluationMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationMethodServiceImplement implements EvaluationMethodService {
    @Autowired
    private EvaluationMethodRepository evaluationMethodRepository;

    @Override
    public List<EvaluationMethod> getEvaluationMethods() {
        return this.evaluationMethodRepository.getEvaluationMethods();
    }

    @Override
    public boolean addEvaluationMethod(EvaluationMethod evaluationMethod) {
        return this.evaluationMethodRepository.addEvaluationMethod(evaluationMethod);
    }

    @Override
    public EvaluationMethod getEvaluationMethodById(int evaluationMethodId) {
        return this.evaluationMethodRepository.getEvaluationMethodById(evaluationMethodId);
    }

    @Override
    public boolean deleteEvaluationMethod(int evaluationMethodId) {
        return this.evaluationMethodRepository.deleteEvaluationMethod(evaluationMethodId);
    }


}
