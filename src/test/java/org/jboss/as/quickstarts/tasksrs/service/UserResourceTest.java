package org.jboss.as.quickstarts.tasksrs.service;

import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.LinkedList;
import java.util.List;

import org.jboss.as.quickstarts.tasksrs.category.UnitTest;
import org.jboss.as.quickstarts.tasksrs.model.Task;
import org.jboss.as.quickstarts.tasksrs.model.User;
import org.jboss.as.quickstarts.tasksrs.model.UserDao;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
@Category(UnitTest.class)
public class UserResourceTest {
    @InjectMocks
    private UserResource userResource;

    @Mock
    private UserDao userDao;

    @Before
    public void setupMocks() {
        List<User> users = new LinkedList<>();
        users.add(newUser("user1", randomTasks(1)));
        users.add(newUser("user2", randomTasks(5)));
        users.add(newUser("user3", randomTasks(3)));

        Mockito.when(userDao.getAll()).thenReturn(users);
    }

    @Test
    // TODO: comment out to make the test run
    @Ignore
    public void getUsersSortedByTask() {
        List<User> users = userResource.getUsers();

        verify(userDao).getAll();

        assertEquals("user2", users.get(0).getUsername());
        assertEquals("user3", users.get(1).getUsername());
        assertEquals("user1", users.get(2).getUsername());

    }


    // helper methods

    private User newUser(String username, Task...tasks) {
        User user = new User();
        user.setId(currentTimeMillis());
        user.setUsername(username);
        user.setTasks(asList(tasks));
        return user;
    }

    private Task[] randomTasks(int count) {
        List<Task> tasks = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            Task task = new Task();
            task.setId(i * 1L);
            task.setTitle("Task " + i);
            tasks.add(task);
        }

        return tasks.toArray(new Task[tasks.size()]);
    }
}
