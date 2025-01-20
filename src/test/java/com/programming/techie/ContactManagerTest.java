package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import javax.crypto.Mac;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {
    ContactManager contactManager;

    @BeforeAll
    public void setupAll(){
        System.out.println("Should Print Before All Tests");
    }

    @BeforeEach
    public void setup(){
        contactManager = new ContactManager();
    }
    @Test
    public void shouldCreateContact() {
        contactManager.addContact("Eno", "Ibas", "0806218547");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact -> contact.getFirstName().equals("Eno") &&
                        contact.getLastName().equals("Ibas") &&
                        contact.getPhoneNumber().equals("0806218547"))
                .findAny()
                .isPresent());
    }

   @Test
    @DisplayName("Should not create when first name Null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null,"Ibas", "0806218547");
        });
   }

    @Test
    @DisplayName("Should not create when last name is Null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Eno",null, "0806218547");
        });
    }

    @Test
    @DisplayName("Should not create when phone is Null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Eno","Ibas", null);
        });
    }

    @AfterEach
    public void tearDown(){
        System.out.println("Should execute after each Test");
    }

    @AfterAll
    public void tearDownAll(){
        System.out.println("Should be executed at the end of the Test");
    }

    @Test
    @DisplayName("Should Create Contact Only on MAC OS")
    @EnabledOnOs(value = OS.MAC, disabledReason = "Enabled only on Mac")
    public void shouldCreateContactOnlyOnMac() {
        contactManager.addContact("Eno", "Ibas", "0806218547");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact -> contact.getFirstName().equals("Eno") &&
                        contact.getLastName().equals("Ibas") &&
                        contact.getPhoneNumber().equals("0806218547"))
                .findAny()
                .isPresent());
    }

    @Test
    @DisplayName("Should Create Contact Only on Windows OS")
    @DisabledOnOs(value = OS.WINDOWS, disabledReason = "Enabled only on Mac")
    public void shouldNotCreateContactOnWindows() {
        contactManager.addContact("Eno", "Ibas", "0806218547");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact -> contact.getFirstName().equals("Eno") &&
                        contact.getLastName().equals("Ibas") &&
                        contact.getPhoneNumber().equals("0806218547"))
                .findAny()
                .isPresent());
    }

    @DisplayName("Repeat Contact Creation Test 5 Times")
    @RepeatedTest(value = 5, name = "Repeating Contact Creation Test {currentRepetition} of {totalRepetitions}")
    public void shouldTestContactCreationRepeatedly() {
        contactManager.addContact("Eno", "Ibas", "0806218547");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @DisplayName("Repeat Contact Creation Test 5 Times")
   // @ParameterizedTest
   // @ValueSource(string = {"0806218547"})
    public void shouldTestContactCreationUsingValueSource() {
        contactManager.addContact("Eno", "Ibas", "0806218547");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }
}