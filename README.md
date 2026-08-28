# EJAAssignment_EDUV4870328_ChurchApplication

![Image description](https://github.com/Nianca-Chetty103/EJAAssignment_EDUV4870328_ChurchApplication/blob/main/Screenshot%202026-08-28%20084723.png))
## Features
### 🔐 User Authentication
User registration and login
Username and password validation
Session management for authenticated users
Role-based user access

### 👤 User Management
Store and manage user information
Support for different user roles
Prevent duplicate usernames during registration

### 📋 Church Information Management
Display church-related information
Manage church activities and services
Provide users with relevant church information

### 📅 Event Management
View upcoming church events
Store event details
Allow users to access important event information

### 💬 Communication
Provide a platform for users to interact with the system
Display relevant announcements and information

### 🖥️ Dynamic Web Pages
JSP pages for displaying dynamic content
Servlets for processing user requests
HTML and CSS for the user interface

### 🔒 Session Management
Creates an HTTP session after successful authentication
Keeps track of logged-in users
Prevents unauthenticated users from accessing restricted pages

### ⚠️ Input Validation
Checks that required fields are completed
Validates login and registration information
Handles invalid user input with appropriate responses

### What I Did to Build the Project

I developed the application as a Java web application using the MVC-style separation between the user interface, application logic, and data.

1. Designed the User Interface

I created the application's frontend using:

HTML
CSS
JSP

I designed pages such as:

Login
Registration
Home/Dashboard
Church information
Events

The JSP pages are responsible for presenting information to the user and submitting forms to the appropriate Servlets.

2. Created Java Servlets

I developed Java Servlets to handle requests and application logic.

For example:

LoginServlet – processes login requests and authenticates users
RegistrationServlet – processes new user registrations
Additional Servlets – handle other system functionality

The Servlets receive form data, validate it, process the request, and return an appropriate response.

3. Implemented Authentication

I implemented a login system that:

Accepts a username and password.
Checks that the fields are not empty.
Validates the user's credentials.
Creates an HTTP session after successful authentication.
Stores the authenticated user in the session.
Redirects the user to the appropriate page.
4. Implemented User Registration

The registration functionality allows new users to create accounts.

The system checks:

Required fields
Username availability
Password information
User role
Duplicate users

Once the information is valid, the user is added to the application's user data.

5. Created Java Model Classes

I created Java classes to represent information used by the application.

For example:

User.java

The model classes store information about entities used within the system and allow the application to work with structured Java objects.

6. Implemented Data Management

I created a data-management layer to store and retrieve application information.

For example:

UserStore.java

This separates the storage logic from the Servlets, making the application easier to maintain and update.

7. Added Session Management

HTTP sessions were implemented to keep track of authenticated users.

After a successful login, the application creates a session and stores the user's information:

HttpSession session = request.getSession();
session.setAttribute("user", user);

This allows the application to identify the logged-in user across different pages.

8. Configured GlassFish Server

I configured GlassFish Server as the application server used to deploy and run the Java web application.

The project was tested locally through GlassFish to ensure that the JSP pages and Servlets were correctly deployed and accessible.

9. Developed the Project in NetBeans

I used Apache NetBeans as the main development environment.

The project structure contains separate areas for:
