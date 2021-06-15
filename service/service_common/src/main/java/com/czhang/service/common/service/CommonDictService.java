package com.czhang.service.common.service;

import com.czhang.model.model.MediCenterDict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CommonDictService {

    List<MediCenterDict> findChildData(Long id);

    void exportDictData(HttpServletResponse httpServletResponse);

    void importDictData(MultipartFile multipartFile);

    String getDictName(String dictCode, String value);

    List<MediCenterDict> findByDictCode(String dictCode);
}
