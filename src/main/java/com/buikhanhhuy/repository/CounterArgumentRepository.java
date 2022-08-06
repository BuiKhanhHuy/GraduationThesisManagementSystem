package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.pojo.Thesis;

public interface CounterArgumentRepository {
    public boolean addCounterArgument(Thesis thesis, Lecturer lecturer);
}
