## User authentication with database.
This example applies user authentication for pre-registered users in the database. Each user has documents which can be seen in the moment they are authenticated.

## To-do list.
[1] Custom login page.
[2] Register page.
[3] Create,update, and delete documents.

### Comments.
There are three main entities in this application: user, privilege, and document. Those entities are in the folder entities. The rules for their relationships are:
[1] A user has 0 or more privileges;
[2] Each privilege belongs to one user;
[3] A user has 0 or many documents;

By rules 1 and 2, the user and privilege have one-to-many relationship, as well and the user and documets.

The Controller.java in the folder controller expose a single endpoint called home.

<!-- explain what the configuration means 
Configure the database connections in resources/application.yaml.
Configure the services in the containers in ./compose.yaml
-->




