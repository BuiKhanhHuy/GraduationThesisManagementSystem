package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.CouncilDetail;

import java.util.List;
import java.util.Map;

public interface CouncilDetailRepository {
    public List<CouncilDetail> getCouncilsDetail(Map<String, String> params);
    public List<Object[]> getCouncilsDetail(int lecturerId, Map<String, String> params);
    public long countCouncilDetail(int lecturerId, Map<String, String> params);
    public CouncilDetail getCouncilDetailById(int councilDetailId);
    public Boolean checkAllowAddAndEdit(int councilDetailId);
}
