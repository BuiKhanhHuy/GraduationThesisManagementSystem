<h1 align="center">GRADUATION THESIS MANAGEMENT SYSTEM</h1>
<p>Thesis Management System is an online platform specifically designed to assist in the management and tracking of thesis progress in a learning environment. This system provides separate roles and functions for admin, lecture (lecturer) and student (student), helping them perform the tasks of managing and monitoring the thesis efficiently and conveniently.</p>

## Who is it for

*   Admin
*   Lecturer
*   Student

## Functionality

### Admin

*   Admin is the highest authority in the system and has access to all functions and data.
*   User management function: Admin can add, delete and modify account information of lecturers and students.
*   Course management: Admin can create, edit and delete theses. They also have the power to assign instructors to each thesis and assign students to study groups.
*   Statistics and reports: Admin can view reports on thesis progress, number of students in each course and other system activities.

### Lecturer

*   The lecturer is the one who guides and supervises the progress of the students' thesis.
*   Course management: Lecturers can view the list of theses they are guiding and update the progress of each thesis.
*   Communication with students: Instructors can exchange information, send documents, and give feedback to students through the system through mailboxes or internal messages.
*   Evaluation and approval: Once the thesis is completed, the lecturer can evaluate and approve the student's results.

### Student

*   Students can access information about the thesis they are working on and track progress.
*   Submission and communication: Students can submit assignments, documents and progress reports through the system. They can also submit questions or request assistance from their instructors.
*   View feedback: Students can see feedback from faculty and admins after submitting their work and can make edits based on this feedback.

## General features of the system

*   Login and account management: Users will log into the system through their personal accounts and manage their account information.
*   User-friendly interface: The user interface is designed to be easy to use and friendly, making it easy for users to interact with the system.
*   Notifications and reminders: The system will provide notifications and reminders to help users track the progress of theses and other important tasks.
*   Schedules and deadlines: Students and faculty can view schedules and important deadlines related to their thesis.

## Setup

### 👉 Clone repo

```plaintext
git clone https://github.com/BuiKhanhHuy/GraduationThesisManagementSystem.git
```

```plaintext
cd GraduationThesisManagementSystem
```

### 👉 Run project

📌 You can use one of two ways: use Docker to setup or setup manually

#### 1️⃣ Docker

→ Update environment variable values in `docker-compose.yaml`

→ Run in terminal: 

```plaintext
docker compose -p graduation-thesis-management-system-project up -d
```

→ Go to: [http://localhost:8081/GraduationThesisManagementSystem/](http://localhost:8081/GraduationThesisManagementSystem/)

#### 2️⃣ Manual (Windows)