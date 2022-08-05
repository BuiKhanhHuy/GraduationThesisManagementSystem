package utils;

import com.buikhanhhuy.pojo.EvaluationMethod;
import com.buikhanhhuy.pojo.ScoreComponent;
import com.buikhanhhuy.pojo.ScoreColumn;

import java.util.Map;

public class Utils {
    public static boolean checkTotalWeight(EvaluationMethod evaluationMethod) {
        double totalWeight = 0;

        for (ScoreComponent scoreComponent : evaluationMethod.getScoreComponents()) {
            for (ScoreColumn scoreColumn : scoreComponent.getScoreColumns()) {
                totalWeight += scoreColumn.getWeight();
            }
        }

        return !(totalWeight > 1) && !(totalWeight < 0);
    }
}
