package firstmock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.fistmock.EmailClient;
import com.example.fistmock.User;
import com.example.fistmock.UserRepository;
import com.example.fistmock.UserService;

@ExtendWith(MockitoExtension.class)  // Enables Mockito annotations
class UserServiceTest {
    
    @Mock
    private UserRepository repository;  // Mock the dependency
    
    @Mock
    private EmailClient emailClient;  // Mock the dependency
    
    @InjectMocks
    private UserService userService;  // Inject mocks automatically

    @Test
    void getUser_existingUser_returnsUser() {
        // Arrange: Configure the mock
        User expectedUser = new User("John", "john@test.com");
        expectedUser.setId(1L);
        
        when(repository.findById(1L)).thenReturn(Optional.of(expectedUser));
        
        // Act: Call the method under test
        User actualUser = userService.getUser(1L);
        
        // Assert: Verify the result
        assertEquals(expectedUser, actualUser);
        assertEquals("John", actualUser.getName());
    }

    @Test
    void getUser_nonExistentUser_throwsException() {
        // Arrange: Mock returns empty Optional
        when(repository.findById(999L)).thenReturn(Optional.empty());
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userService.getUser(999L);
        });
    }

/*
Test 3: Invalid input rejection

Test with null name
Test with invalid email
Assert IllegalArgumentException is thrown
Task 5: Write Your Own Tests (10 minutes)
Without guidance, write tests for:

getActiveUsers()
getUserCount()
    */
    @Test
    void createUser_NonExistingUser_returnsId(){
        User user = new User("John", "john@test.com");
        when(repository.existsByEmail(user.getEmail())).thenReturn(false);
        when(repository.save(user)).thenReturn(new User(1L, user.getName(), user.getEmail(), false));

        User savedUser = userService.createUser(user);
        assertNotNull(savedUser.getId());
    }

    @Test
    void createUser_DuplicateEmail_throwsException(){
        User user = new User("John", "john@test.com");
        when(repository.existsByEmail(user.getEmail())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(user);
        });
    }

    @Test
    void createUser_InvalidEmail_throwsException(){
        User user = new User("John", "johntest.com");
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(user);
        });
    }

    @Test
    void createUser_nullName_throwsException(){
        User user = new User(null, "john@test.com");
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(user);
        });
    }

    @Test
    void getActiveUsers_UsersExsist_returnsUsers(){
        List<User> users = new ArrayList<>(Arrays.asList(
            new User("John", "john@test.com"),
            new User("John2", "john2@test.com")
        ));

        when(repository.findAllActive()).thenReturn(users);
        assertTrue(userService.getActiveUsers().size() > 0);
    }

    @Test
    void getUserCount_UsersExsist_returnsCount(){
        when(repository.count()).thenReturn(5L);
        assertTrue(userService.getUserCount() > 0);
    }
}
