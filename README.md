## Notebook app
This is a note taking app called "My Documents" which an user can create, read, edit, and delete past notes (documents). To that end, the frameworks supporting the functions of the app are the Spring boot, Spring data Jpa to manage databases, PostgreSQL as DBMS, Thymeleaf as a template engine, and the Spring Security to protect the app. Each user has documents which can be seen in the home page.

### Entity-Relationship model
There are three main entities in this application: user, privilege, and document. Those entities are in the folder entities. The rules for their relationships are:
[1] A user has 0 or more privileges;
[2] Each privilege belongs to one user;
[3] A user has 0 or many documents;

By rules 1 and 2, the user and privilege have one-to-many relationship, as well and the user and documets.

## To-do list.
[1] Register page.
[2] Create and delete documents.
[3] Test security.
[4] How the users give access to others in their pages.





