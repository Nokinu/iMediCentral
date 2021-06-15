package com.czhang.service.common.repository;

import com.czhang.model.model.MediCenterDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonDictRespository extends JpaRepository<MediCenterDict, Long> {
    List<MediCenterDict> findAllByParentId(Long id);
    Long countByParentId(Long id);
    MediCenterDict findDistinctByDictCode(String dictCode);
    MediCenterDict findDistinctByValue(String value);
    MediCenterDict findDistinctByParentIdAndValue(Long id, String value);
}
