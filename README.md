
# 🧾 HRMS Backend (Spring Boot)

This is the backend REST API for a full-featured **Human Resource Management System (HRMS)** built using **Spring Boot**. It handles all core functionalities required for managing employee data, roles, departments, attendance, leave management, and user authentication.

---

## 🔧 Technologies Used

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL **
- **Maven**
- **RESTful APIs**

---

## 📦 Features

- ✅ **Role-based Access Control** (Admin, HR, Employee)
- ✅ **Employee Management** (CRUD operations)
- ✅ **Department & Designation Management**
- ✅ **Leave Application & Approval System**
- ✅ **Attendance Tracking**
- ✅ **Email Notification Support (Optional)**

---

## 🗂️ Folder Structure

```
src/
├── controller/
├── service/
├── repository/
├── model/
├── config/
└── dto/
```

---

## ⚙️ API Endpoints (Examples)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/api/auth/register` | Register a new user |
| POST   | `/api/auth/login`    | Authenticate user and return JWT |
| GET    | `/api/employees`     | Get all employees |
| POST   | `/api/leaves/apply`  | Apply for leave |
| GET    | `/api/attendance/today` | Mark or fetch today's attendance |

---

## 🏁 Getting Started

```bash
# Clone the repo
git clone https://github.com/your-username/hrms-backend.git
cd hrms-backend

# Configure application.properties
# (Set DB URL, username, password, JWT secret, etc.)

# Run the project
./mvnw spring-boot:run
```

---


## 🤝 Contribution

Contributions are welcome! Please fork the repository and create a pull request for any enhancements.

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
