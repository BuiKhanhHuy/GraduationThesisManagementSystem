package utils;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.EvaluationMethod;
import com.buikhanhhuy.pojo.ScoreComponent;
import com.buikhanhhuy.pojo.ScoreColumn;

public class Utils {

    public static boolean checkTotalWeight(EvaluationMethod evaluationMethod) {
        double totalWeight = 0;

        for (ScoreComponent scoreComponent : evaluationMethod.getScoreComponents()) {
            for (ScoreColumn scoreColumn : scoreComponent.getScoreColumns()) {
                totalWeight += scoreColumn.getWeight();
            }
        }

        return totalWeight == 1;
    }

    public static int checkThesisResult(double score) {
        if (score >= SystemConstant.SCORE_PASS) {
            return 3;
        } else {
            return 2;
        }
    }

}
