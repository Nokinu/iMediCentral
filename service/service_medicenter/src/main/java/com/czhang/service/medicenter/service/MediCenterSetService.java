package com.czhang.service.medicenter.service;

import com.czhang.model.model.MediCenterSet;
import com.czhang.model.vo.medicenter.MediCenterSetQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MediCenterSetService {

    boolean updateStatusById(Integer status, Long id);

    boolean batchDeleteCenterSet(List<Long> ids);

    MediCenterSet updateCenterSet(MediCenterSet mediCenterSet);

    List<MediCenterSet> findAllCenterSets();

    MediCenterSet findCenterSetsById(Long id);

    boolean deleteCenterSet(Long id);

    MediCenterSet addCenterSets(MediCenterSet mediCenterSet);

    Page<MediCenterSet> findCenterSetsByPage(Pageable pageable, MediCenterSetQueryVo mediCenterSetQueryVo);
}
