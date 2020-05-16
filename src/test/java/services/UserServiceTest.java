package services;

import data_access.UserDA;
import model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDA userDA;

    @Mock
    private User user;

    private Fixture fixture;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        fixture = new Fixture();
    }

    @Test
    public void testIsExistingUser_UserExists() throws SQLException {
        fixture.givenUserExists();
        fixture.whenIsExistingUserIsCalled();
        fixture.thenUserIsFound(true);
    }

    @Test
    public void testIsExistingUser_UserDoesNotExist() throws SQLException {
        fixture.givenUserDoesNotExist();
        fixture.whenIsExistingUserIsCalled();
        fixture.thenUserIsFound(false);
    }

    private class Fixture {

        private static final String TEST_USERNAME = "username";
        private static final String TEST_PASSWORD = "password";
        private boolean actualResult;

        private void givenUserExists() throws SQLException {
            when(userDA.getUserByUsernameAndPassword(TEST_USERNAME, TEST_PASSWORD)).thenReturn(user);
        }

        private void givenUserDoesNotExist() throws SQLException {
            when(userDA.getUserByUsernameAndPassword(TEST_USERNAME, TEST_PASSWORD)).thenReturn(null);
        }

        private void whenIsExistingUserIsCalled() throws SQLException {
            actualResult = userService.login(TEST_USERNAME, TEST_PASSWORD);
        }

        private void thenUserIsFound(boolean expectedResult) {
            assertEquals(expectedResult, actualResult);
        }
    }
}