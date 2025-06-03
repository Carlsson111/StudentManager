package se.lexicon.data_access;

import org.springframework.stereotype.Component;
import se.lexicon.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class StudentDaoListImpl implements StudentDao{
    private final List<Student> students = new ArrayList<>();

    @Override
    public Student save(Student student) {
        students.add(student);
        return student;
    }

    @Override
    public Student find(int id) {
        return students.stream()
                .filter(student -> student.getId()==id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Student with ID " + id + " not found"));
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    @Override
    public void delete(int id) {
        students.removeIf(student -> student.getId()==id);

    }
}
