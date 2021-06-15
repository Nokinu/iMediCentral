package com.czhang.service.medicenter.service.impl;

import com.czhang.model.mapper.ServiceModelMapper;
import com.czhang.model.model.MediCenterSet;
import com.czhang.model.vo.medicenter.MediCenterSetQueryVo;
import com.czhang.service.medicenter.repository.MediCenterSetRepository;
import com.czhang.service.medicenter.service.MediCenterSetService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class MediCenterSetServiceImpl implements MediCenterSetService {

    private final MediCenterSetRepository mediCenterSetRepository;

    private final ServiceModelMapper mapper;

    public MediCenterSetServiceImpl(MediCenterSetRepository mediCenterSetRepository, ServiceModelMapper mapper) {
        this.mediCenterSetRepository = mediCenterSetRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean updateStatusById(Integer status, Long id) {
        if(mediCenterSetRepository.existsById(id)) {
            mediCenterSetRepository.updateStatusById(status, id);
            return true;
        }
        return false;
    }

    @Override
    public boolean batchDeleteCenterSet(List<Long> ids) {
        return ids.stream().allMatch(this::deleteCenterSet);

    }

    @Override
    public MediCenterSet updateCenterSet(MediCenterSet mediCenterSet) {
        MediCenterSet data = mediCenterSetRepository.getOne(mediCenterSet.getId());
        mapper.updateMediCenterSets(mediCenterSet, data);
        return mediCenterSetRepository.saveAndFlush(data);
    }

    @Override
    public List<MediCenterSet> findAllCenterSets() {
        return mediCenterSetRepository.findAll();
    }

    @Override
    public MediCenterSet findCenterSetsById(Long id) {
        Optional<MediCenterSet> result= mediCenterSetRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteCenterSet(Long id) {
        if (mediCenterSetRepository.existsById(id)) {
            MediCenterSet mediCenterSet = mediCenterSetRepository.getOne(id);
            if (mediCenterSet.getIsDeleted() == 0) {
                mediCenterSet.setIsDeleted(1);
            }
            mediCenterSetRepository.saveAndFlush(mediCenterSet);
            return true;
        }
        return false;
    }

    @Override
    public MediCenterSet addCenterSets(MediCenterSet mediCenterSet) {
        return mediCenterSetRepository.saveAndFlush(mediCenterSet);
    }

    @Override
    public Page<MediCenterSet> findCenterSetsByPage(Pageable pageable, MediCenterSetQueryVo mediCenterSetQueryVo) {
        if(!StringUtils.hasText(mediCenterSetQueryVo.getMediCenterCode())
        && !StringUtils.hasText(mediCenterSetQueryVo.getMediCenterName())) {
            return mediCenterSetRepository.findAll(pageable);
        }
        MediCenterSet mediCenterSet = new MediCenterSet();
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll();
        if(StringUtils.hasText(mediCenterSetQueryVo.getMediCenterCode())) {
            mediCenterSet.setCenterCode(mediCenterSetQueryVo.getMediCenterCode());
            exampleMatcher = exampleMatcher.withMatcher("centerCode", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());
        }
        if(StringUtils.hasText(mediCenterSetQueryVo.getMediCenterName())) {
            mediCenterSet.setCenterName(mediCenterSetQueryVo.getMediCenterName());
            exampleMatcher = exampleMatcher.withMatcher("centerName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        }
        Example<MediCenterSet> example = Example.of(mediCenterSet, exampleMatcher);
        return mediCenterSetRepository.findAll(example, pageable);
    }
}
