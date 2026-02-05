# 🏢 Employee Management System (EMS)

Let’s be honest: tracking employee data in messy spreadsheets is a headache. I built this **Employee Management System** to move away from manual record-keeping and into a structured, automated environment. It’s a full-stack desktop application that handles the entire lifecycle of an employee—from their first day of onboarding to record updates and eventual removal.



##  Key Features
* **Full CRUD Operations:** Seamlessly Add, View, Update, and Remove employee records.
* **Smart Search:** Instantly pull up employee details using a dynamic search bar and Choice components.
* **Auto-ID Generation:** Uses custom logic to assign unique IDs to every new hire, ensuring zero data overlap.
* **Modern Full-Screen UI:** Every window is optimized to launch in maximized mode with centered layouts, making it look great on any monitor resolution.
* **Printing Support:** Built-in functionality to generate physical printouts of the employee table directly from the app.
* **Secure Backend:** Powered by MySQL with PreparedStatement logic to keep data transactions smooth and safe.

##  The Tech Stack
* **Language:** Java (Swing & AWT for the GUI)
* **Database:** MySQL Database
* **API/Libraries:** * **JDBC:** For database connectivity.
    * **rs2xml (DbUtils):** To convert SQL ResultSets into readable JTables.
    * **JCalendar:** For a user-friendly date-of-birth picker.



##  Project Roadmap
- [x] **Splash Screen:** A professional entry point with a blinking "Click to Continue" call-to-action.
- [x] **Secure Login:** A gatekeeper screen to ensure only authorized users access the data.
- [x] **Dynamic Home:** A centralized navigation hub.
- [x] **Data Persistence:** Full integration with MySQL for reliable storage.

##  Getting Started

### Prerequisites
1. **Java JDK** (8)
2. **MySQL Server**
3. **Libraries needed:** * `mysql-connector-java.jar`
    * `rs2xml.jar`
    * `jcalendar.jar`

### Installation
1. **Clone the Repo:**
   ```bash
   git clone [https://github.com/Muhammad-Abdullah-SE/Employee-Mangement-System.git)
