package com.czhang.service.common.service.impl;

import com.czhang.model.model.MediCenterDict;
import com.czhang.service.common.repository.CommonDictRespository;
import com.czhang.service.common.service.CommonDictService;
import com.github.xiaolyuh.annotation.Cacheable;
import com.github.xiaolyuh.annotation.FirstCache;
import com.github.xiaolyuh.annotation.SecondaryCache;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CommonDictServiceImpl implements CommonDictService {

    private final CommonDictRespository commonDictRespository;

    public CommonDictServiceImpl(CommonDictRespository commonDictRespository) {
        this.commonDictRespository = commonDictRespository;
    }


    private boolean hasChildren(Long id) {
        return commonDictRespository.countByParentId(id) > 0;
    }
    @Override
    @Cacheable(value = "ChildData", key = "#id", depict = "Child Data Info By ID",
    firstCache = @FirstCache(expireTime = 5),
    secondaryCache = @SecondaryCache(expireTime = 15, preloadTime = 8, forceRefresh = true, timeUnit = TimeUnit.MINUTES))
    public List<MediCenterDict> findChildData(Long id) {
        List<MediCenterDict> mediCenterDicts = commonDictRespository.findAllByParentId(id);
        mediCenterDicts.forEach(dict -> dict.setHasChildren(hasChildren(dict.getId())));
        return mediCenterDicts;
    }

    @Override
    public void exportDictData(HttpServletResponse httpServletResponse) {

    }

    @Override
    public void importDictData(MultipartFile multipartFile) {

    }

    @Override
    public String getDictName(String dictCode, String value) {
        if(StringUtils.isNullOrEmpty(dictCode)) {
          MediCenterDict mediCenterDict = commonDictRespository.findDistinctByValue(value);
          return mediCenterDict.getName();
        } else {
          MediCenterDict mediCenterDict = findDistinctByDictCode(dictCode);
          MediCenterDict result = commonDictRespository.findDistinctByParentIdAndValue(mediCenterDict.getParentId(), value);
          return result.getName();
        }
    }

    @Override
    public List<MediCenterDict> findByDictCode(String dictCode) {
        MediCenterDict mediCenterDict = findDistinctByDictCode(dictCode);
        return findChildData(mediCenterDict.getId());
    }

    private MediCenterDict findDistinctByDictCode(String dictCode) {
       return commonDictRespository.findDistinctByDictCode(dictCode);
    }
}
