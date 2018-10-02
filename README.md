OpenShift Tasks: JAX-RS, JPA quickstart 
==============================
[![Build Status](https://travis-ci.org/OpenShiftDemos/openshift-tasks.svg?branch=eap-7)](https://travis-ci.org/OpenShiftDemos/openshift-tasks)

What is it?
-----------
a
The `tasks-rs` quickstart demonstrates how to implement a JAX-RS service that uses JPA 2.0 persistence deployed to Red Hat JBoss Enterprise Application Platform.

The `tasks-rs` quickstart demonstrates how to implement a JAX-RS service that uses JPA persistence deployed to Red Hat JBoss Enterprise Application Platform.

* The client uses HTTP to interact with the service. It builds on the *tasks* quickstart, which provides simple task management with secure login.

* The service interface is implemented using JAX-RS. The SecurityContext JAX-RS annotation is used to inject the security details into each REST method.


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
