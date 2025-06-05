package se.lexicon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.data_access.StudentDao;
import se.lexicon.models.Student;
import se.lexicon.util.ScannerInputService;
import se.lexicon.util.UserInputService;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentManagementConsoleImpl implements StudentManagement{
    private final ScannerInputService scannerInputService;
    private StudentDao studentDao;

    private UserInputService userInputService;


    //ConstructorInjector
    @Autowired
    public StudentManagementConsoleImpl(StudentDao studentDao, UserInputService userInputService, ScannerInputService scannerInputService) {
        this.studentDao = studentDao;
        this.userInputService = userInputService;
        this.scannerInputService = scannerInputService;
    }

    @Override
    public Student create() {
        System.out.println("Enter student name ");
        String name = scannerInputService.getString();
        Student student = new Student(name);
        return save(student);

    }

    @Override
    public Student save(Student student) {
        if (student == null) {throw new IllegalArgumentException("Not allowed to save a null student");}
        return studentDao.save(student);
    }

    @Override
    public Student find(int id) {
        if (id <= 0) {throw new IllegalArgumentException("Not allowed to find student with id " + id);}
        return studentDao.find(id);
    }

    @Override
    public Student remove(int id) {
        if (id <= 0) {throw new IllegalArgumentException("Not allowed to remove student with id " + id);}
        Student student = studentDao.find(id);
        if (student == null) {throw new IllegalArgumentException("Student with id: " + id + " not found");}
        studentDao.delete(id);
        return student;
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(studentDao.findAll());
    }

    @Override
    public Student edit(Student student) {
        if (student == null || student.getId() <= 0) {throw new IllegalArgumentException("Not allowed to add null student");}
        Student studentFound = studentDao.find(student.getId());
        if (studentFound == null) {throw new IllegalArgumentException("Student with id: " + student.getId() + " not found");}
        studentFound.setId(student.getId());
        studentFound.setName(student.getName());
        return studentDao.save(studentFound);
    }
}
