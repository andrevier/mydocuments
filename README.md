## User authentication with database.
This example applies user authentication for pre-registered users in the database.

Configure two entities in the database: users and privileges. Those entities are in the folder entities and they have a one-to-many relationship such that:
- A user has 0 or more privileges;
- The same privilege can belong to multiple users.

The Controller.java in the folder controller is a restcontroller and expose a single endpoint called home.

<!-- explain what the configuration means -->
Configure the database connections in resources/application.yaml.
Configure the services in the containers in ./compose.yaml

Check the database in the commandline: 
docker compose up
if the last message is :
LOG:  database system is ready to accept connections

The configurations are correct.

#



