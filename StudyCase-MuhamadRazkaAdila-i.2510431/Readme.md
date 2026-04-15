# Study Case: Employee Management System

**Tema**  
Sistem manajemen karyawan pada sebuah perusahaan yang mengelola berbagai jenis pegawai: Manager, Developer, dan Intern.

**Tujuan pembelajaran**  
Menggabungkan semua konsep OOP yang sudah dipelajari:
- Class & Object
- Encapsulation (data private + getter/setter + validasi)
- Inheritance (superclass & subclass)
- Polymorphism (method overriding + runtime polymorphism via reference superclass)

---

## Struktur Folder

```
employee/
├── model/                         # Abstract superclass (Abstraction)
│   └── Employee.java
├── role/                          # Inheritance + Polymorphism
│   ├── Manager.java
│   ├── Developer.java
│   └── Intern.java
├── manager/                       # Encapsulation + CRUD logic
│   └── EmployeeManager.java
└── MainEmployee.java              # Demo penggunaan semua konsep
```

---

## Spesifikasi Detail

### 1. Abstract Class `Employee` (superclass)

- **Atribut** (protected):
  - `String id`
  - `String name`
  - `String email`
  - `boolean isActive` (default = true)

- **Constructor**:
  - Menerima `id`, `name`, dan `email`
  - Memanggil setter untuk `name` dan `email` agar validasi berjalan saat instansiasi

- **Method**:
  - `void activate()` → ubah `isActive` menjadi `true`
  - `void deactivate()` → ubah `isActive` menjadi `false`
  - `abstract String getRoleName()` → subclass wajib mengimplementasikan, mengembalikan nama role
  - `abstract double calculateBonus(double baseSalary)` → subclass wajib mengimplementasikan, mengembalikan nominal bonus
  - `void displayInfo()` → cetak id, name, email, role, dan status aktif (bisa di-override)

- **Setter dengan validasi**:
  - `setName(String name)` → jika name kosong/null → throw `IllegalArgumentException`
  - `setEmail(String email)` → jika email tidak mengandung `@` → throw `IllegalArgumentException`

---

### 2. Subclass (Inheritance + Polymorphism)

| Class       | Atribut tambahan              | Bonus               | Override `displayInfo()` tambahan     |
|-------------|-------------------------------|---------------------|---------------------------------------|
| `Manager`   | `String department`           | 20% dari base salary | Tampilkan department                 |
| `Developer` | `String programmingLanguage`  | 15% dari base salary | Tampilkan programming language       |
| `Intern`    | `String university`, `int durationMonths` | 5% dari base salary | Tampilkan university & durasi bulan |

> **Catatan `Intern`:** `durationMonths` divalidasi di setter — jika nilai di luar range 1–12, akan throw `IllegalArgumentException`.

---

### 3. Class `EmployeeManager` (Encapsulation + CRUD)

Mengelola koleksi employee menggunakan array statis berkapasitas maksimal 50 data.

- **Atribut private**:
  - `Employee[] employeeList` — array penyimpan semua employee
  - `int count` — jumlah employee yang terdaftar saat ini
  - `int MAX_CAPACITY = 50` — kapasitas maksimal

- **Method penting**:

  | Method | Fungsi |
  |--------|--------|
  | `addEmployee(Employee emp)` | Menambahkan employee baru; cek duplikasi ID sebelum menambah |
  | `findById(String id)` | Mencari dan mengembalikan employee berdasarkan ID (case-insensitive) |
  | `removeEmployee(String id)` | Menghapus employee berdasarkan ID; menggeser elemen array ke kiri |
  | `displayAll()` | Menampilkan semua employee beserta detailnya |
  | `displayByRole(String roleName)` | Menampilkan employee yang difilter berdasarkan nama role |
  | `displayBonus(String id, double baseSalary)` | Menampilkan hasil kalkulasi bonus untuk employee tertentu |

---

### 4. Di `MainEmployee.java` (Demo)

**Data sample yang sudah di-preload:**

```java
new Manager("EMP-001", "Budi Santoso", "budi@company.com", "Engineering")
new Developer("EMP-002", "Rina Wijaya", "rina@company.com", "Java")
new Developer("EMP-003", "Andi Nugroho", "andi@company.com", "Python")
new Intern("EMP-004", "Siti Rahma", "siti@company.com", "Universitas Brawijaya", 6)
```

**Menu yang tersedia:**

```
=== MAIN MENU ===
1. Display all employees
2. Add new employee
3. Search employee by ID
4. Remove employee
5. Filter by role
6. Calculate bonus
0. Exit
```

**Alur Add Employee (Menu 2):**
1. User memilih role terlebih dahulu (Manager / Developer / Intern)
2. Input field umum: ID, Name, Email
3. Input field tambahan sesuai role yang dipilih
4. Validasi dijalankan otomatis via setter di constructor

**Alur Remove Employee (Menu 4):**
1. Semua employee ditampilkan terlebih dahulu
2. User memasukkan ID yang ingin dihapus
3. Konfirmasi `(y/n)` sebelum eksekusi penghapusan

**Alur Calculate Bonus (Menu 6):**
1. User memasukkan ID employee
2. User memasukkan base salary
3. Sistem menghitung bonus berdasarkan persentase role masing-masing

---

## Konsep OOP yang Diimplementasikan

| Konsep | Implementasi dalam Program |
|--------|---------------------------|
| **Encapsulation** | Atribut `id`, `name`, `email` bersifat `protected` dengan setter bervalidasi; `EmployeeManager` menyembunyikan array internal sepenuhnya |
| **Inheritance** | `Manager`, `Developer`, dan `Intern` semuanya `extends Employee` dan mewarisi semua atribut serta method dasar |
| **Polymorphism** | `getRoleName()` dan `calculateBonus()` di-override berbeda di tiap subclass; `displayAll()` memanggil `displayInfo()` via reference tipe `Employee[]` |
| **Abstraction** | `Employee` adalah `abstract class` — tidak bisa diinstansiasi langsung; memaksa subclass mengimplementasikan `getRoleName()` dan `calculateBonus()` |

---

## Cara Kompilasi & Menjalankan

```bash
# Kompilasi semua file Java
javac -d out $(find src -name "*.java")

# Jalankan program
java -cp out StudyCase.employee.MainEmployee
```