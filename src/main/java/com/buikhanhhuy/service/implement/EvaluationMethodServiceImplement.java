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
}
