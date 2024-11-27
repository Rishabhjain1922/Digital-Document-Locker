Digital Document Locker
A secure web application for uploading, storing, and retrieving sensitive documents. Users can upload files with a password and retrieve them using a unique key and password combination, ensuring data security and restricted access.

Features
Secure Document Upload: Upload files with a password for secure storage.
Restricted Access: Retrieve files only with a unique key and password.
RESTful APIs: Built using Spring Boot for seamless frontend-backend communication.
H2 Database Integration: Dynamic in-memory database for testing and development.
File Storage: Files are stored locally in a dedicated upload directory.
Technologies Used
Backend: Java, Spring Boot, Spring Data JPA
Frontend: HTML, CSS, JavaScript
Database: H2 Database (in-memory database)
Tools: Postman for API testing, IntelliJ IDEA for development
How to Run the Project
Clone the Repository:

bash
Copy code
git clone https://github.com/<your-repository>/digital-document-locker.git
cd digital-document-locker
Configure Application:

Update application.properties for database configurations and file storage path:
properties
Copy code
spring.datasource.url=jdbc:h2:mem:digital_locker
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
file.upload-dir=uploads
Run the Application:

bash
Copy code
./mvnw spring-boot:run
Access the application at http://localhost:8080.

Access H2 Console (Optional):

URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:digital_locker
Username: sa
Password: password
API Endpoints
Upload Document
Endpoint: POST /documents/upload
Request:
file (Multipart File): The document to upload.
password (String): Password to secure the document.
Response:
key (String): A unique key for retrieving the document.
Retrieve Document
Endpoint: GET /documents/{key}
Request:
key (Path Variable): Unique key of the document.
password (Query Param): Password for authentication.
Response:
The requested file as a downloadable resource.
Folder Structure
src/main/java: Contains the main application, services, controllers, and model classes.
src/main/resources: Contains configuration files such as application.properties.
uploads: Directory where uploaded files are stored.
Future Enhancements
Encryption: Add file encryption for enhanced security.
Cloud Storage: Integrate with cloud services like AWS S3 for scalable storage.
User Authentication: Implement user accounts and session-based authentication.


Author
Rishabh Jain
Email- Rishabhjain1922@gmail.com
Feel free to reach out for collaboration or feedback!
