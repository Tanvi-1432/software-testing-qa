import java.util.HashMap;
import java.util.Map;

// Stores and manages contacts in memory.
public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    // Adds a contact only when its ID is unique.
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact is required.");
        }

        String contactId = contact.getContactId();
        if (contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID must be unique.");
        }

        contacts.put(contactId, contact);
    }

    // Deletes an existing contact by ID.
    public void deleteContact(String contactId) {
        Contact contact = contacts.remove(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID does not exist.");
        }
    }

    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }

    public void updateFirstName(String contactId, String firstName) {
        findContact(contactId).setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        findContact(contactId).setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        findContact(contactId).setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        findContact(contactId).setAddress(address);
    }

    private Contact findContact(String contactId) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID does not exist.");
        }
        return contact;
    }
}
