OpenShift Tasks: JAX-RS, JPA quickstart
==============================
[![CircleCI](https://circleci.com/gh/OpenShiftDemos/openshift-tasks.svg?&style=shield&circle-token=1462d2d99334a912b32f758616eb39f6390a200b)](https://circleci.com/gh/OpenShiftDemos/openshift-tasks)

What is it?
-----------
a
The `tasks-rs` quickstart demonstrates how to implement a JAX-RS service that uses JPA 2.0 persistence deployed to Red Hat JBoss Enterprise Application Platform.

The application manages User and Task JPA entities. A user represents an authenticated principal and is associated with zero or more Tasks. Service methods validate that there is an authenticated principal and the first time a principal is seen, a JPA User entity is created to correspond to the principal. JAX-RS annotated methods are provided for associating Tasks with this User and for listing and removing Tasks.

_Note_: This quickstart uses the H2 database included with Red Hat JBoss Enterprise Application Platform 6. It is a lightweight, relational example datasource that is used for examples only. It is not robust or scalable, is not supported, and should NOT be used in a production environment!_

_Note_: This quickstart uses a `*-ds.xml` datasource configuration file for convenience and ease of database configuration. These files are deprecated in JBoss EAP 6.4 and should not be used in a production environment. Instead, you should configure the datasource using the Management CLI or Management Console. Datasource configuration is documented in the [Administration and Configuration Guide](https://access.redhat.com/documentation/en-US/JBoss_Enterprise_Application_Platform/) for Red Hat JBoss Enterprise Application Platform._


REST Endpoints on OpenShift
-------------------

* Create task

  ```
  curl -i -u 'redhat:redhat1!' -H "Content-Length: 0" -X POST http://tasks-dev.10.1.2.10.xip.io/ws/tasks/task1
  ```

* Get a task by id

  ```
  curl -u 'redhat:redhat1!' -H "Accept: application/json" -X GET http://tasks-dev.10.1.2.10.xip.io/ws/tasks/1
  ```

* Get all user tasks

  ```

  curl -u 'redhat:redhat1!' -H "Accept: application/json" -X GET http://tasks-dev.10.1.2.10.xip.io/ws/tasks
  ```

* Delete a task by id

  ```
  curl -i -u 'redhat:redhat1!' -X DELETE http://tasks-dev.10.1.2.10.xip.io/ws/tasks/1
  ```

* Generate CPU load. Last parameter is duration of load in seconds

  ```
  curl -X GET http://tasks-dev.10.1.2.10.xip.io/demo/load/5 # 5 seconds
  ```
