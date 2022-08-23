package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.EvaluationMethod;
import com.buikhanhhuy.repository.EvaluationMethodRepository;
import com.buikhanhhuy.service.EvaluationMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EvaluationMethodServiceImplement implements EvaluationMethodService {
    @Autowired
    private EvaluationMethodRepository evaluationMethodRepository;

    @Override
    public List<EvaluationMethod> getEvaluationMethods() {
        return this.evaluationMethodRepository.getEvaluationMethods();
    }

    @Override
    public long countEvaluationMethod(Map<String, String> params) {
        return this.evaluationMethodRepository.countEvaluationMethod(params);
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
    public EvaluationMethod getEvaluationMethodActive() {
        return this.evaluationMethodRepository.getEvaluationMethodActive();
    }

    @Override
    public boolean updateEvaluationMethod(int evaluationMethodId, EvaluationMethod evaluationMethod) {
        return this.evaluationMethodRepository.updateEvaluationMethod(evaluationMethodId, evaluationMethod);
    }

    @Override
    public boolean deleteEvaluationMethod(int evaluationMethodId) {
        return this.evaluationMethodRepository.deleteEvaluationMethod(evaluationMethodId);
    }

    @Override
    public boolean activeAEvaluationMethod(int evaluationMethodId) {
        return this.evaluationMethodRepository.activeAEvaluationMethod(evaluationMethodId);
    }


}
