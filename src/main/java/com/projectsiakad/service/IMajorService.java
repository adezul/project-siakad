package com.projectsiakad.service;

import com.projectsiakad.model.Major;
import org.springframework.data.domain.Page;

public interface IMajorService {

    Page<Major> listMajor(Integer page, Integer pageSize, String sortDirection, String orderBy) throws Exception;

    Major createMajor(Major major) throws Exception;

    void deleteById(String majorCode) throws Exception;

    Major updateById(String majorCode, Major major) throws Exception;
}
