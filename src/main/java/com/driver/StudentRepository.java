package com.driver;


import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.*;

@Repository
public class StudentRepository {

    private HashMap<String,Student> studentMap;
    private HashMap<String,Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student){
        studentMap.put(student.getName(), student);
    }

    public void saveTeacher(Teacher teacher){
        teacherMap.put(teacher.getName(), teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            studentMap.put(student, studentMap.get(student));
            teacherMap.put(teacher, teacherMap.get(teacher));
            List<String> currentStudent = new ArrayList<String>();
            if(teacherStudentMapping.containsKey(teacher)) currentStudent = teacherStudentMapping.get(teacher);
            currentStudent.add(student);
            teacherStudentMapping.put(teacher, currentStudent);
        }
    }

    public Student findStudent(String student){
        return studentMap.get(student);
    }

    public Teacher findTeacher(String teacher){
        return teacherMap.get(teacher);
    }

    public List<String> findStudentsFromTeacher(String teacher){
        List<String> studentsList = new ArrayList<String>();
            if(teacherStudentMapping.containsKey(teacher)) studentsList = teacherStudentMapping.get(teacher);
        return studentsList;
    }

    public List<String> findAllStudents(){
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacher(String teacher){
        List<String> students = new ArrayList<String>();
        if(teacherStudentMapping.containsKey(teacher)){
            students = teacherStudentMapping.get(teacher);
            for(String student: students){
                if(studentMap.containsKey(student)){
                    studentMap.remove(student);
                }
            }

            teacherStudentMapping.remove(teacher);
        }

        if(teacherMap.containsKey(teacher)){
            teacherMap.remove(teacher);
        }
    }

    public void deleteAllTeachers(){
        HashSet<String> studentsSet = new HashSet<String>();

        //directorMap = new HashMap<>();

        for(String teacher: teacherStudentMapping.keySet()){
            for(String student: teacherStudentMapping.get(teacher)){
                studentsSet.add(student);
            }
        }

        for(String movie: studentsSet){
            if(studentMap.containsKey(movie)){
                studentMap.remove(movie);
            }
        }
    }

}
