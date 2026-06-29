import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

// Unit tests for the Contact class.
class ContactTest {

    @Test
    void testValidContactCreation() {
        Contact contact = new Contact("12345", "John", "Smith", "5551234567", "123 Main Street");

        assertEquals("12345", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("5551234567", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }

    @Test
    void testContactIdCannotBeNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(null, "John", "Smith", "5551234567", "123 Main Street"));
    }

    @Test
    void testContactIdCannotBeLongerThanTenCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345678901", "John", "Smith", "5551234567", "123 Main Street"));
    }

    @Test
    void testContactIdCannotBeChanged() {
        Method[] methods = Contact.class.getMethods();

        for (Method method : methods) {
            assertFalse(method.getName().equals("setContactId"));
        }
    }

    @Test
    void testFirstNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345", null, "Smith", "5551234567", "123 Main Street"));
    }

    @Test
    void testFirstNameCannotBeLongerThanTenCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345", "JohnathanXX", "Smith", "5551234567", "123 Main Street"));
    }

    @Test
    void testLastNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345", "John", null, "5551234567", "123 Main Street"));
    }

    @Test
    void testLastNameCannotBeLongerThanTenCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345", "John", "SmithfieldX", "5551234567", "123 Main Street"));
    }

    @Test
    void testPhoneCannotBeNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345", "John", "Smith", null, "123 Main Street"));
    }

    @Test
    void testPhoneMustBeExactlyTenDigits() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345", "John", "Smith", "555123456", "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345", "John", "Smith", "55512345678", "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345", "John", "Smith", "555123ABCD", "123 Main Street"));
    }

    @Test
    void testAddressCannotBeNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345", "John", "Smith", "5551234567", null));
    }

    @Test
    void testAddressCannotBeLongerThanThirtyCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345", "John", "Smith", "5551234567",
                        "1234567890123456789012345678901"));
    }
}
