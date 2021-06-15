package com.czhang.service.medicenter.repository;

import com.czhang.model.model.MediCenterSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MediCenterSetRepository extends JpaRepository<MediCenterSet, Long> {

    @Modifying
    @Transactional
    @Query("update MediCenterSet m set m.status = ?1 where m.id = ?2")
    void updateStatusById(Integer status, Long id);

}
