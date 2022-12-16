package com.projectsiakad.service;

import com.projectsiakad.model.Major;
import com.projectsiakad.repository.IMajorRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class MajorService implements IMajorService {

    private IMajorRepository majorRepository;

    public MajorService(IMajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    @Override
    public Page<Major> listMajor(Integer page, Integer pageSize, String sortDirection, String orderBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortDirection), orderBy);
        Pageable pageable = PageRequest.of((page - 1), pageSize, sort);
        return majorRepository.findAll(pageable);
    }

    @Override
    public Major createMajor(Major major) throws Exception {
        try {
            Major newMajor = majorRepository.save(major);
            return newMajor;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException();
        }
    }

    @Override
    public void deleteById(String majorCode) throws Exception {
        majorRepository.deleteById(majorCode);

    }

    @Override
    public Major updateById(String majorCode, Major major) throws Exception {
        Optional<Major> exsistingMajor = majorRepository.findById(majorCode);

        if (major.getMajorCode().equals(null) || (major.getMajorCode().equals(exsistingMajor.get().getMajorCode()))) {
            major.setMajorCode(exsistingMajor.get().getMajorCode());
        }
        else
        exsistingMajor.get().setMajorName(major.getMajorName());
        Major result = majorRepository.save(exsistingMajor.get());
        return result;
    }
}
