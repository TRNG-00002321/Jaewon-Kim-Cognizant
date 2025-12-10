package assertion;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.assertion.StringUtils;
import com.example.assertion.User;

public class StringUtilTest {

    @Test
    public void testReverse(){
        assertEquals("olleh", StringUtils.reverse("hello"));
        assertEquals("a", StringUtils.reverse("a"));
        assertEquals("", StringUtils.reverse(""));
    }

    @Test
    public void testIsEmpty(){
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty("FASLE"));
    }
    
    @Test
    public void testFindFirst(){
        String[] strings = {"one", "two"};
        assertNotNull(StringUtils.findFirst(strings, "one"));
        assertNull(StringUtils.findFirst(strings, "three"));
    }

    @Test
    public void testSplit(){
        String data = "abc";
        String[] expected = {"a", "b", "c"};
        assertArrayEquals(expected, StringUtils.split(data, ""));
    }

    @Test
    public void testUserObject(){
        User user = new User(1L, "example@test.org", "Jimbo");

        assertAll(
            ()-> assertEquals("Jimbo", user.getName()),
            ()->assertEquals("example@test.org", user.getEmail()),
            ()->assertEquals(1L, user.getId())
        );

    }
}
