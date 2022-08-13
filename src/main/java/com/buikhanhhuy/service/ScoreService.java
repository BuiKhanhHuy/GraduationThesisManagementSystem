package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Score;

public interface ScoreService {
    public Score getScoreWithThesisIdAndCouncilDetailId(int thesisId, int councilDetailId);
    public boolean addScore(Score score);
    public boolean updateScore(int scoreId, Score score);
}
