package com.projectsiakad.service;

import com.projectsiakad.model.Student;
import com.projectsiakad.repository.IStudentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    private IStudentRepository studentRepository;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<Student> listStudent(Integer page, Integer pageSize, String sortDirection, String orderBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortDirection), orderBy);
        Pageable pageable = PageRequest.of((page - 1), pageSize, sort);
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student createStudent(Student student) throws Exception {
        try {
            Student newStudent = studentRepository.save(student);
            return newStudent;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException();
        }
    }

    @Override
    public void deleteById(Integer npm) throws Exception {
        studentRepository.deleteById(npm);
    }

    @Override
    public Student updateById(Integer npm, Student student) throws Exception {
        Optional<Student> exsistingStudent = studentRepository.findById(npm);

        if (student.getNpm().equals(null) || (student.getNpm().equals(exsistingStudent.get().getNpm()))) {
            student.setNpm(exsistingStudent.get().getNpm());
        }
        else
            exsistingStudent.get().setFullName(student.getFullName());
        Student result = studentRepository.save(exsistingStudent.get());
        return result;
    }
}
