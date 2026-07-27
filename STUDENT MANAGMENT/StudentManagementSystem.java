import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem extends JFrame {

// =====================================================
// STUDENT MODEL
// =====================================================

static class Student {

    int id;
    String rollNo;
    String name;
    String fatherName;
    String gender;
    String phone;
    String email;
    String address;
    String course;
    String semester;

    Student(
            int id,
            String rollNo,
            String name,
            String fatherName,
            String gender,
            String phone,
            String email,
            String address,
            String course,
            String semester
    ) {
        this.id = id;
        this.rollNo = rollNo;
        this.name = name;
        this.fatherName = fatherName;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.course = course;
        this.semester = semester;
    }
}

// =====================================================
// COURSE MODEL
// =====================================================

static class Course {

    int id;
    String code;
    String name;
    String semester;
    String teacher;

    Course(
            int id,
            String code,
            String name,
            String semester,
            String teacher
    ) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.semester = semester;
        this.teacher = teacher;
    }
}

// =====================================================
// ATTENDANCE MODEL
// =====================================================

static class Attendance {

    int id;
    String rollNo;
    String subject;
    int totalClasses;
    int attendedClasses;

    Attendance(
            int id,
            String rollNo,
            String subject,
            int totalClasses,
            int attendedClasses
    ) {
        this.id = id;
        this.rollNo = rollNo;
        this.subject = subject;
        this.totalClasses = totalClasses;
        this.attendedClasses = attendedClasses;
    }

    double getPercentage() {

        if (totalClasses == 0) {
            return 0;
        }

        return ((double) attendedClasses / totalClasses) * 100;
    }
}

// =====================================================
// MARK MODEL
// =====================================================

static class Mark {

    int id;
    String rollNo;
    String subject;
    int obtainedMarks;
    int totalMarks;

    Mark(
            int id,
            String rollNo,
            String subject,
            int obtainedMarks,
            int totalMarks
    ) {
        this.id = id;
        this.rollNo = rollNo;
        this.subject = subject;
        this.obtainedMarks = obtainedMarks;
        this.totalMarks = totalMarks;
    }

    String getGrade() {

        if (totalMarks == 0) {
            return "N/A";
        }

        double percentage =
                ((double) obtainedMarks / totalMarks) * 100;

        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}

// =====================================================
// FEE MODEL
// =====================================================

static class Fee {

    int id;
    String rollNo;
    double totalFee;
    double paidFee;

    Fee(
            int id,
            String rollNo,
            double totalFee,
            double paidFee
    ) {
        this.id = id;
        this.rollNo = rollNo;
        this.totalFee = totalFee;
        this.paidFee = paidFee;
    }

    double getRemainingFee() {
        return totalFee - paidFee;
    }
}

// =====================================================
// GLOBAL DATA
// =====================================================

static List<Student> studentList =
        new ArrayList<>();

static List<Course> courseList =
        new ArrayList<>();

static List<Attendance> attendanceList =
        new ArrayList<>();

static List<Mark> markList =
        new ArrayList<>();

static List<Fee> feeList =
        new ArrayList<>();

static int studentId = 1;
static int courseId = 1;
static int attendanceId = 1;
static int markId = 1;
static int feeId = 1;

// =====================================================
// MAIN METHOD
// =====================================================

public static void main(String[] args) {

    SwingUtilities.invokeLater(
            () -> new LoginFrame()
    );
}

// =====================================================
// LOGIN FRAME
// =====================================================

static class LoginFrame extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;

    LoginFrame() {

        setTitle(
                "Student Management System - Login"
        );

        setSize(
                500,
                400
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(null);

        getContentPane().setBackground(
                new Color(
                        240,
                        245,
                        250
                )
        );

        JLabel title =
                new JLabel(
                        "STUDENT MANAGEMENT SYSTEM"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        title.setBounds(
                60,
                40,
                400,
                40
        );

        add(title);

        JLabel usernameLabel =
                new JLabel(
                        "Username:"
                );

        usernameLabel.setBounds(
                80,
                120,
                100,
                30
        );

        usernameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        add(usernameLabel);

        usernameField =
                new JTextField();

        usernameField.setBounds(
                190,
                120,
                220,
                30
        );

        add(usernameField);

        JLabel passwordLabel =
                new JLabel(
                        "Password:"
                );

        passwordLabel.setBounds(
                80,
                170,
                100,
                30
        );

        passwordLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        add(passwordLabel);

        passwordField =
                new JPasswordField();

        passwordField.setBounds(
                190,
                170,
                220,
                30
        );

        add(passwordField);

        JButton loginButton =
                new JButton(
                        "LOGIN"
                );

        loginButton.setBounds(
                170,
                240,
                150,
                40
        );

        loginButton.setBackground(
                new Color(
                        30,
                        100,
                        180
                )
        );

        loginButton.setForeground(
                Color.WHITE
        );

        loginButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        add(loginButton);

        loginButton.addActionListener(
                e -> login()
        );

        setVisible(true);
    }

    void login() {

        String username =
                usernameField.getText();

        String password =
                new String(
                        passwordField.getPassword()
                );

        if (
                username.equals("admin")
                        &&
                password.equals("admin123")
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login Successful"
            );

            dispose();

            new Dashboard();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Username or Password"
            );
        }
    }
}

// =====================================================
// DASHBOARD
// =====================================================

static class Dashboard extends JFrame {

    Dashboard() {

        setTitle(
                "Student Management System - Dashboard"
        );

        setSize(
                1000,
                650
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );

        JPanel header =
                new JPanel();

        header.setBackground(
                new Color(
                        30,
                        80,
                        150
                )
        );

        header.setBorder(
                new EmptyBorder(
                        20,
                        10,
                        20,
                        10
                )
        );

        JLabel title =
                new JLabel(
                        "STUDENT MANAGEMENT SYSTEM"
                );

        title.setForeground(
                Color.WHITE
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        header.add(title);

        add(
                header,
                BorderLayout.NORTH
        );

        JPanel menu =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                20,
                                20
                        )
                );

        menu.setBorder(
                new EmptyBorder(
                        50,
                        100,
                        50,
                        100
                )
        );

        JButton studentButton =
                new JButton(
                        "STUDENT MANAGEMENT"
                );

        JButton courseButton =
                new JButton(
                        "COURSE MANAGEMENT"
                );

        JButton attendanceButton =
                new JButton(
                        "ATTENDANCE"
                );

        JButton marksButton =
                new JButton(
                        "MARKS / RESULT"
                );

        JButton feeButton =
                new JButton(
                        "FEE MANAGEMENT"
                );

        JButton logoutButton =
                new JButton(
                        "LOGOUT"
                );

        menu.add(
                studentButton
        );

        menu.add(
                courseButton
        );

        menu.add(
                attendanceButton
        );

        menu.add(
                marksButton
        );

        menu.add(
                feeButton
        );

        menu.add(
                logoutButton
        );

        add(
                menu,
                BorderLayout.CENTER
        );

        studentButton.addActionListener(
                e -> new StudentManagement()
        );

        courseButton.addActionListener(
                e -> new CourseManagement()
        );

        attendanceButton.addActionListener(
                e -> new AttendanceManagement()
        );

        marksButton.addActionListener(
                e -> new MarksManagement()
        );

        feeButton.addActionListener(
                e -> new FeeManagement()
        );

        logoutButton.addActionListener(
                e -> {

                    dispose();

                    new LoginFrame();
                }
        );

        setVisible(true);
    }
}

// =====================================================
// STUDENT MANAGEMENT
// =====================================================

static class StudentManagement extends JFrame {

    JTextField rollField;
    JTextField nameField;
    JTextField fatherField;
    JTextField phoneField;
    JTextField emailField;
    JTextField addressField;
    JTextField courseField;
    JTextField semesterField;
    JTextField searchField;

    JComboBox<String> genderBox;

    JTable table;

    DefaultTableModel model;

    StudentManagement() {

        setTitle(
                "Student Management"
        );

        setSize(
                1250,
                750
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                6,
                                4,
                                10,
                                10
                        )
                );

        formPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Student Information"
                )
        );

        rollField =
                new JTextField();

        nameField =
                new JTextField();

        fatherField =
                new JTextField();

        phoneField =
                new JTextField();

        emailField =
                new JTextField();

        addressField =
                new JTextField();

        courseField =
                new JTextField();

        semesterField =
                new JTextField();

        genderBox =
                new JComboBox<>(
                        new String[]{
                                "Male",
                                "Female",
                                "Other"
                        }
                );

        formPanel.add(
                new JLabel(
                        "Roll Number"
                )
        );

        formPanel.add(
                rollField
        );

        formPanel.add(
                new JLabel(
                        "Student Name"
                )
        );

        formPanel.add(
                nameField
        );

        formPanel.add(
                new JLabel(
                        "Father Name"
                )
        );

        formPanel.add(
                fatherField
        );

        formPanel.add(
                new JLabel(
                        "Gender"
                )
        );

        formPanel.add(
                genderBox
        );

        formPanel.add(
                new JLabel(
                        "Phone"
                )
        );

        formPanel.add(
                phoneField
        );

        formPanel.add(
                new JLabel(
                        "Email"
                )
        );

        formPanel.add(
                emailField
        );

        formPanel.add(
                new JLabel(
                        "Address"
                )
        );

        formPanel.add(
                addressField
        );

        formPanel.add(
                new JLabel(
                        "Course"
                )
        );

        formPanel.add(
                courseField
        );

        formPanel.add(
                new JLabel(
                        "Semester"
                )
        );

        formPanel.add(
                semesterField
        );

        JButton addButton =
                new JButton(
                        "ADD STUDENT"
                );

        JButton updateButton =
                new JButton(
                        "UPDATE"
                );

        JButton deleteButton =
                new JButton(
                        "DELETE"
                );

        JButton clearButton =
                new JButton(
                        "CLEAR"
                );

        formPanel.add(
                addButton
        );

        formPanel.add(
                updateButton
        );

        formPanel.add(
                deleteButton
        );

        formPanel.add(
                clearButton
        );

        add(
                formPanel,
                BorderLayout.NORTH
        );

        JPanel searchPanel =
                new JPanel();

        searchField =
                new JTextField(
                        25
                );

        JButton searchButton =
                new JButton(
                        "SEARCH"
                );

        JButton showAllButton =
                new JButton(
                        "SHOW ALL"
                );

        searchPanel.add(
                new JLabel(
                        "Search:"
                )
        );

        searchPanel.add(
                searchField
        );

        searchPanel.add(
                searchButton
        );

        searchPanel.add(
                showAllButton
        );

        add(
                searchPanel,
                BorderLayout.SOUTH
        );

        model =
                new DefaultTableModel();

        model.setColumnIdentifiers(
                new String[]{
                        "ID",
                        "Roll No",
                        "Name",
                        "Father Name",
                        "Gender",
                        "Phone",
                        "Email",
                        "Address",
                        "Course",
                        "Semester"
                }
        );

        table =
                new JTable(
                        model
                );

        add(
                new JScrollPane(
                        table
                ),
                BorderLayout.CENTER
        );

        loadStudents();

        addButton.addActionListener(
                e -> addStudent()
        );

        updateButton.addActionListener(
                e -> updateStudent()
        );

        deleteButton.addActionListener(
                e -> deleteStudent()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        searchButton.addActionListener(
                e -> searchStudent()
        );

        showAllButton.addActionListener(
                e -> loadStudents()
        );

        table.getSelectionModel()
                .addListSelectionListener(
                        e -> selectStudent()
                );

        setVisible(true);
    }

    void addStudent() {

        if (
                rollField.getText().trim().isEmpty()
                        ||
                nameField.getText().trim().isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Roll Number and Name are required"
            );

            return;
        }

        Student student =
                new Student(
                        studentId++,
                        rollField.getText(),
                        nameField.getText(),
                        fatherField.getText(),
                        genderBox
                                .getSelectedItem()
                                .toString(),
                        phoneField.getText(),
                        emailField.getText(),
                        addressField.getText(),
                        courseField.getText(),
                        semesterField.getText()
                );

        studentList.add(
                student
        );

        JOptionPane.showMessageDialog(
                this,
                "Student Added Successfully"
        );

        loadStudents();

        clearFields();
    }

    void updateStudent() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a student first"
            );

            return;
        }

        int id =
                Integer.parseInt(
                        model.getValueAt(
                                row,
                                0
                        ).toString()
                );

        for (Student student : studentList) {

            if (
                    student.id == id
            ) {

                student.rollNo =
                        rollField.getText();

                student.name =
                        nameField.getText();

                student.fatherName =
                        fatherField.getText();

                student.gender =
                        genderBox
                                .getSelectedItem()
                                .toString();

                student.phone =
                        phoneField.getText();

                student.email =
                        emailField.getText();

                student.address =
                        addressField.getText();

                student.course =
                        courseField.getText();

                student.semester =
                        semesterField.getText();

                break;
            }
        }

        JOptionPane.showMessageDialog(
                this,
                "Student Updated Successfully"
        );

        loadStudents();

        clearFields();
    }

    void deleteStudent() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a student first"
            );

            return;
        }

        int id =
                Integer.parseInt(
                        model.getValueAt(
                                row,
                                0
                        ).toString()
                );

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                choice
                        ==
                JOptionPane.YES_OPTION
        ) {

            studentList.removeIf(
                    student ->
                            student.id == id
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Student Deleted Successfully"
            );

            loadStudents();

            clearFields();
        }
    }

    void searchStudent() {

        String search =
                searchField
                        .getText()
                        .toLowerCase();

        model.setRowCount(
                0
        );

        for (
                Student student :
                studentList
        ) {

            if (
                    student.rollNo
                            .toLowerCase()
                            .contains(search)
                            ||
                    student.name
                            .toLowerCase()
                            .contains(search)
            ) {

                addStudentToTable(
                        student
                );
            }
        }
    }

    void loadStudents() {

        model.setRowCount(
                0
        );

        for (
                Student student :
                studentList
        ) {

            addStudentToTable(
                    student
            );
        }
    }

    void addStudentToTable(
            Student student
    ) {

        model.addRow(
                new Object[]{
                        student.id,
                        student.rollNo,
                        student.name,
                        student.fatherName,
                        student.gender,
                        student.phone,
                        student.email,
                        student.address,
                        student.course,
                        student.semester
                }
        );
    }

    void selectStudent() {

        int row =
                table.getSelectedRow();

        if (
                row == -1
        ) {
            return;
        }

        rollField.setText(
                model.getValueAt(
                        row,
                        1
                ).toString()
        );

        nameField.setText(
                model.getValueAt(
                        row,
                        2
                ).toString()
        );

        fatherField.setText(
                model.getValueAt(
                        row,
                        3
                ).toString()
        );

        genderBox.setSelectedItem(
                model.getValueAt(
                        row,
                        4
                ).toString()
        );

        phoneField.setText(
                model.getValueAt(
                        row,
                        5
                ).toString()
        );

        emailField.setText(
                model.getValueAt(
                        row,
                        6
                ).toString()
        );

        addressField.setText(
                model.getValueAt(
                        row,
                        7
                ).toString()
        );

        courseField.setText(
                model.getValueAt(
                        row,
                        8
                ).toString()
        );

        semesterField.setText(
                model.getValueAt(
                        row,
                        9
                ).toString()
        );
    }

    void clearFields() {

        rollField.setText(
                ""
        );

        nameField.setText(
                ""
        );

        fatherField.setText(
                ""
        );

        phoneField.setText(
                ""
        );

        emailField.setText(
                ""
        );

        addressField.setText(
                ""
        );

        courseField.setText(
                ""
        );

        semesterField.setText(
                ""
        );

        genderBox.setSelectedIndex(
                0
        );
    }
}

// =====================================================
// COURSE MANAGEMENT
// =====================================================

static class CourseManagement extends JFrame {

    JTextField codeField;
    JTextField nameField;
    JTextField semesterField;
    JTextField teacherField;

    JTable table;

    DefaultTableModel model;

    CourseManagement() {

        setTitle(
                "Course Management"
        );

        setSize(
                900,
                600
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                3,
                                4,
                                10,
                                10
                        )
                );

        panel.setBorder(
                BorderFactory.createTitledBorder(
                        "Course Details"
                )
        );

        codeField =
                new JTextField();

        nameField =
                new JTextField();

        semesterField =
                new JTextField();

        teacherField =
                new JTextField();

        panel.add(
                new JLabel(
                        "Course Code"
                )
        );

        panel.add(
                codeField
        );

        panel.add(
                new JLabel(
                        "Course Name"
                )
        );

        panel.add(
                nameField
        );

        panel.add(
                new JLabel(
                        "Semester"
                )
        );

        panel.add(
                semesterField
        );

        panel.add(
                new JLabel(
                        "Teacher"
                )
        );

        panel.add(
                teacherField
        );

        JButton addButton =
                new JButton(
                        "ADD COURSE"
                );

        JButton deleteButton =
                new JButton(
                        "DELETE"
                );

        panel.add(
                addButton
        );

        panel.add(
                deleteButton
        );

        add(
                panel,
                BorderLayout.NORTH
        );

        model =
                new DefaultTableModel();

        model.setColumnIdentifiers(
                new String[]{
                        "ID",
                        "Code",
                        "Course Name",
                        "Semester",
                        "Teacher"
                }
        );

        table =
                new JTable(
                        model
                );

        add(
                new JScrollPane(
                        table
                ),
                BorderLayout.CENTER
        );

        addButton.addActionListener(
                e -> addCourse()
        );

        deleteButton.addActionListener(
                e -> deleteCourse()
        );

        loadCourses();

        setVisible(true);
    }

    void addCourse() {

        if (
                codeField
                        .getText()
                        .trim()
                        .isEmpty()
                        ||
                nameField
                        .getText()
                        .trim()
                        .isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Course code and name are required"
            );

            return;
        }

        Course course =
                new Course(
                        courseId++,
                        codeField.getText(),
                        nameField.getText(),
                        semesterField.getText(),
                        teacherField.getText()
                );

        courseList.add(
                course
        );

        JOptionPane.showMessageDialog(
                this,
                "Course Added Successfully"
        );

        loadCourses();
    }

    void deleteCourse() {

        int row =
                table.getSelectedRow();

        if (
                row == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a course first"
            );

            return;
        }

        int id =
                Integer.parseInt(
                        model.getValueAt(
                                row,
                                0
                        ).toString()
                );

        courseList.removeIf(
                course ->
                        course.id == id
        );

        loadCourses();
    }

    void loadCourses() {

        model.setRowCount(
                0
        );

        for (
                Course course :
                courseList
        ) {

            model.addRow(
                    new Object[]{
                            course.id,
                            course.code,
                            course.name,
                            course.semester,
                            course.teacher
                    }
            );
        }
    }
}

// =====================================================
// ATTENDANCE MANAGEMENT
// =====================================================

static class AttendanceManagement extends JFrame {

    JTextField rollField;
    JTextField subjectField;
    JTextField totalField;
    JTextField attendedField;

    JTable table;

    DefaultTableModel model;

    AttendanceManagement() {

        setTitle(
                "Attendance Management"
        );

        setSize(
                900,
                600
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                2,
                                5,
                                10,
                                10
                        )
                );

        panel.setBorder(
                BorderFactory.createTitledBorder(
                        "Attendance Details"
                )
        );

        rollField =
                new JTextField();

        subjectField =
                new JTextField();

        totalField =
                new JTextField();

        attendedField =
                new JTextField();

        panel.add(
                new JLabel(
                        "Roll No"
                )
        );

        panel.add(
                rollField
        );

        panel.add(
                new JLabel(
                        "Subject"
                )
        );

        panel.add(
                subjectField
        );

        panel.add(
                new JLabel(
                        "Total Classes"
                )
        );

        panel.add(
                totalField
        );

        panel.add(
                new JLabel(
                        "Attended Classes"
                )
        );

        panel.add(
                attendedField
        );

        JButton addButton =
                new JButton(
                        "ADD ATTENDANCE"
                );

        panel.add(
                addButton
        );

        add(
                panel,
                BorderLayout.NORTH
        );

        model =
                new DefaultTableModel();

        model.setColumnIdentifiers(
                new String[]{
                        "ID",
                        "Roll No",
                        "Subject",
                        "Total Classes",
                        "Attended",
                        "Percentage"
                }
        );

        table =
                new JTable(
                        model
                );

        add(
                new JScrollPane(
                        table
                ),
                BorderLayout.CENTER
        );

        addButton.addActionListener(
                e -> addAttendance()
        );

        loadAttendance();

        setVisible(true);
    }

    void addAttendance() {

        try {

            int totalClasses =
                    Integer.parseInt(
                            totalField.getText()
                    );

            int attendedClasses =
                    Integer.parseInt(
                            attendedField.getText()
                    );

            if (
                    totalClasses <= 0
                            ||
                    attendedClasses < 0
                            ||
                    attendedClasses > totalClasses
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter valid attendance values"
                );

                return;
            }

            Attendance attendance =
                    new Attendance(
                            attendanceId++,
                            rollField.getText(),
                            subjectField.getText(),
                            totalClasses,
                            attendedClasses
                    );

            attendanceList.add(
                    attendance
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Attendance Added Successfully"
            );

            loadAttendance();

        } catch (
                NumberFormatException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter valid numbers"
            );
        }
    }

    void loadAttendance() {

        model.setRowCount(
                0
        );

        for (
                Attendance attendance :
                attendanceList
        ) {

            model.addRow(
                    new Object[]{
                            attendance.id,
                            attendance.rollNo,
                            attendance.subject,
                            attendance.totalClasses,
                            attendance.attendedClasses,
                            String.format(
                                    "%.2f%%",
                                    attendance
                                            .getPercentage()
                            )
                    }
            );
        }
    }
}

// =====================================================
// MARKS MANAGEMENT
// =====================================================

static class MarksManagement extends JFrame {

    JTextField rollField;
    JTextField subjectField;
    JTextField marksField;
    JTextField totalMarksField;

    JTable table;

    DefaultTableModel model;

    MarksManagement() {

        setTitle(
                "Marks and Result Management"
        );

        setSize(
                900,
                600
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                2,
                                5,
                                10,
                                10
                        )
                );

        panel.setBorder(
                BorderFactory.createTitledBorder(
                        "Marks Details"
                )
        );

        rollField =
                new JTextField();

        subjectField =
                new JTextField();

        marksField =
                new JTextField();

        totalMarksField =
                new JTextField();

        panel.add(
                new JLabel(
                        "Roll No"
                )
        );

        panel.add(
                rollField
        );

        panel.add(
                new JLabel(
                        "Subject"
                )
        );

        panel.add(
                subjectField
        );

        panel.add(
                new JLabel(
                        "Obtained Marks"
                )
        );

        panel.add(
                marksField
        );

        panel.add(
                new JLabel(
                        "Total Marks"
                )
        );

        panel.add(
                totalMarksField
        );

        JButton addButton =
                new JButton(
                        "ADD MARKS"
                );

        panel.add(
                addButton
        );

        add(
                panel,
                BorderLayout.NORTH
        );

        model =
                new DefaultTableModel();

        model.setColumnIdentifiers(
                new String[]{
                        "ID",
                        "Roll No",
                        "Subject",
                        "Marks",
                        "Total Marks",
                        "Grade"
                }
        );

        table =
                new JTable(
                        model
                );

        add(
                new JScrollPane(
                        table
                ),
                BorderLayout.CENTER
        );

        addButton.addActionListener(
                e -> addMarks()
        );

        loadMarks();

        setVisible(true);
    }

    void addMarks() {

        try {

            int obtainedMarks =
                    Integer.parseInt(
                            marksField.getText()
                    );

            int totalMarks =
                    Integer.parseInt(
                            totalMarksField.getText()
                    );

            if (
                    totalMarks <= 0
                            ||
                    obtainedMarks < 0
                            ||
                    obtainedMarks > totalMarks
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter valid marks"
                );

                return;
            }

            Mark mark =
                    new Mark(
                            markId++,
                            rollField.getText(),
                            subjectField.getText(),
                            obtainedMarks,
                            totalMarks
                    );

            markList.add(
                    mark
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Marks Added Successfully"
            );

            loadMarks();

        } catch (
                NumberFormatException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter valid numbers"
            );
        }
    }

    void loadMarks() {

        model.setRowCount(
                0
        );

        for (
                Mark mark :
                markList
        ) {

            model.addRow(
                    new Object[]{
                            mark.id,
                            mark.rollNo,
                            mark.subject,
                            mark.obtainedMarks,
                            mark.totalMarks,
                            mark.getGrade()
                    }
            );
        }
    }
}

// =====================================================
// FEE MANAGEMENT
// =====================================================

static class FeeManagement extends JFrame {

    JTextField rollField;
    JTextField totalFeeField;
    JTextField paidFeeField;

    JTable table;

    DefaultTableModel model;

    FeeManagement() {

        setTitle(
                "Fee Management"
        );

        setSize(
                900,
                600
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                10,
                                10
                        )
                );

        panel.setBorder(
                BorderFactory.createTitledBorder(
                        "Fee Details"
                )
        );

        rollField =
                new JTextField();

        totalFeeField =
                new JTextField();

        paidFeeField =
                new JTextField();

        panel.add(
                new JLabel(
                        "Roll No"
                )
        );

        panel.add(
                rollField
        );

        panel.add(
                new JLabel(
                        "Total Fee"
                )
        );

        panel.add(
                totalFeeField
        );

        panel.add(
                new JLabel(
                        "Paid Fee"
                )
        );

        panel.add(
                paidFeeField
        );

        JButton addButton =
                new JButton(
                        "ADD PAYMENT"
                );

        panel.add(
                addButton
        );

        add(
                panel,
                BorderLayout.NORTH
        );

        model =
                new DefaultTableModel();

        model.setColumnIdentifiers(
                new String[]{
                        "ID",
                        "Roll No",
                        "Total Fee",
                        "Paid Fee",
                        "Remaining Fee"
                }
        );

        table =
                new JTable(
                        model
                );

        add(
                new JScrollPane(
                        table
                ),
                BorderLayout.CENTER
        );

        addButton.addActionListener(
                e -> addFee()
        );

        loadFees();

        setVisible(true);
    }

    void addFee() {

        try {

            double totalFee =
                    Double.parseDouble(
                            totalFeeField.getText()
                    );

            double paidFee =
                    Double.parseDouble(
                            paidFeeField.getText()
                    );

            if (
                    totalFee < 0
                            ||
                    paidFee < 0
                            ||
                    paidFee > totalFee
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter valid fee amounts"
                );

                return;
            }

            Fee fee =
                    new Fee(
                            feeId++,
                            rollField.getText(),
                            totalFee,
                            paidFee
                    );

            feeList.add(
                    fee
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Fee Payment Added Successfully"
            );

            loadFees();

        } catch (
                NumberFormatException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter valid fee amount"
            );
        }
    }

    void loadFees() {

        model.setRowCount(
                0
        );

        for (
                Fee fee :
                feeList
        ) {

            model.addRow(
                    new Object[]{
                            fee.id,
                            fee.rollNo,
                            String.format(
                                    "$%.2f",
                                    fee.totalFee
                            ),
                            String.format(
                                    "$%.2f",
                                    fee.paidFee
                            ),
                            String.format(
                                    "$%.2f",
                                    fee.getRemainingFee()
                            )
                    }
            );
        }
    }
}

}
