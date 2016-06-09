tasks-rs: JAX-RS, JPA quickstart
==============================
Author: Mike Musgrove  
Level: Intermediate  
Technologies: JPA, JAX-RS  
Summary: The `tasks-rs` quickstart demonstrates how to implement a JAX-RS service that uses JPA 2.0 persistence.  
Prerequisites: tasks  
Target Product: JBoss EAP  
Source: <https://github.com/jboss-developer/jboss-eap-quickstarts/>  

What is it?
-----------

The `tasks-rs` quickstart demonstrates how to implement a JAX-RS service that uses JPA 2.0 persistence deployed to Red Hat JBoss Enterprise Application Platform.

The application manages User and Task JPA entities. A user represents an authenticated principal and is associated with zero or more Tasks. Service methods validate that there is an authenticated principal and the first time a principal is seen, a JPA User entity is created to correspond to the principal. JAX-RS annotated methods are provided for associating Tasks with this User and for listing and removing Tasks.

_Note_: This quickstart uses the H2 database included with Red Hat JBoss Enterprise Application Platform 6. It is a lightweight, relational example datasource that is used for examples only. It is not robust or scalable, is not supported, and should NOT be used in a production environment!_

_Note_: This quickstart uses a `*-ds.xml` datasource configuration file for convenience and ease of database configuration. These files are deprecated in JBoss EAP 6.4 and should not be used in a production environment. Instead, you should configure the datasource using the Management CLI or Management Console. Datasource configuration is documented in the [Administration and Configuration Guide](https://access.redhat.com/documentation/en-US/JBoss_Enterprise_Application_Platform/) for Red Hat JBoss Enterprise Application Platform._


REST Endpoints on OpenShift
-------------------

* Create task

  ```
  curl -i -u 'redhat:redhat1!' -H "Content-Length: 0" -X POST http://tasks-dev.10.1.2.10.xip.io/tasks/task1
  ```

* Get a task by id

  ```
  curl -u 'redhat:redhat1!' -H "Accept: application/xml" -X GET http://tasks-dev.10.1.2.10.xip.io/tasks/1
  ```

* Get all user tasks

  ```

  curl -u 'redhat:redhat1!' -H "Accept: application/xml" -X GET http://tasks-dev.10.1.2.10.xip.io/tasks
  ```

* Delete a task by id

  ```
  curl -i -u 'redhat:redhat1!' -i -X DELETE http://tasks-dev.10.1.2.10.xip.io/tasks/1
  ```
