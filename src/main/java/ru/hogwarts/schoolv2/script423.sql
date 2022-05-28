SELECT student.name, student.age, faculty.name
FROM student
         LEFT JOIN faculty faculty ON student.faculty_id = faculty.id;

SELECT student.name, student.age
FROM student
         INNER JOIN avatar avatar ON student.student_id = avatar.id;
