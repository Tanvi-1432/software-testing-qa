import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Unit tests for the ContactService class.
class ContactServiceTest {
    private ContactService service;
    private Contact contact;

    @BeforeEach
    void setUp() {
        service = new ContactService();
        contact = new Contact("12345", "John", "Smith", "5551234567", "123 Main Street");
    }

    @Test
    void testAddContact() {
        service.addContact(contact);

        assertEquals(contact, service.getContact("12345"));
    }

    @Test
    void testAddContactRejectsNullContact() {
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    @Test
    void testAddContactRejectsDuplicateContactId() {
        service.addContact(contact);
        Contact duplicate = new Contact("12345", "Jane", "Jones", "5559876543", "456 Oak Street");

        assertThrows(IllegalArgumentException.class, () -> service.addContact(duplicate));
    }

    @Test
    void testDeleteContact() {
        service.addContact(contact);

        service.deleteContact("12345");

        assertNull(service.getContact("12345"));
    }

    @Test
    void testDeleteContactThatDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("99999"));
    }

    @Test
    void testUpdateFirstName() {
        service.addContact(contact);

        service.updateFirstName("12345", "Jane");

        assertEquals("Jane", service.getContact("12345").getFirstName());
    }

    @Test
    void testUpdateLastName() {
        service.addContact(contact);

        service.updateLastName("12345", "Jones");

        assertEquals("Jones", service.getContact("12345").getLastName());
    }

    @Test
    void testUpdatePhone() {
        service.addContact(contact);

        service.updatePhone("12345", "5559876543");

        assertEquals("5559876543", service.getContact("12345").getPhone());
    }

    @Test
    void testUpdateAddress() {
        service.addContact(contact);

        service.updateAddress("12345", "456 Oak Street");

        assertEquals("456 Oak Street", service.getContact("12345").getAddress());
    }

    @Test
    void testInvalidUpdateValues() {
        service.addContact(contact);

        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("12345", null));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("12345", "LongLastName"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("12345", "12345"));
        assertThrows(IllegalArgumentException.class,
                () -> service.updateAddress("12345", "1234567890123456789012345678901"));
    }

    @Test
    void testUpdateContactThatDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("99999", "Jane"));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("99999", "Jones"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("99999", "5559876543"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("99999", "456 Oak Street"));
    }
}
