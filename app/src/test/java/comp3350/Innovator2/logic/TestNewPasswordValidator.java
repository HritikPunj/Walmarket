package comp3350.Innovator2.logic;

import org.junit.Test;

import static org.junit.Assert.*;

import comp3350.Innovator2.logic.exceptions.InvalidPasswordLengthException;
import comp3350.Innovator2.logic.exceptions.InvalidPasswordSpaceException;
import comp3350.Innovator2.logic.exceptions.InvalidUpperCaseException;
import comp3350.Innovator2.logic.exceptions.InvalidLowerCaseException;
import comp3350.Innovator2.logic.exceptions.InvalidSpecialCharacterException;
import comp3350.Innovator2.logic.exceptions.NewPasswordException;

public class TestNewPasswordValidator {
    @Test
    public void testValidPasswords() throws InvalidPasswordSpaceException, InvalidPasswordLengthException,
            InvalidUpperCaseException, InvalidLowerCaseException, InvalidSpecialCharacterException {
        System.out.println("\nStarting testValidPasswords");

        NewPasswordValidator newPasswordValidator = new NewPasswordValidator();

        String[] validPasswords = {
                "Aa!12345", "Bb@23456", "Cc#34567", "Dd$45678",
                "Ee%56789", "Ff^67890", "Gg&78901", "Hh*89012",
                "Ii(90123", "Jj)01234"
        };

        for (String password : validPasswords) {
            assertTrue(newPasswordValidator.validateNewPassword(password));
        }

        System.out.println("Finished testValidPasswords");
    }

    @Test
    public void testInvalidSpacePasswords() {
        System.out.println("\nStarting testInvalidSpacePasswords");

        NewPasswordValidator newPasswordValidator = new NewPasswordValidator();

        String[] invalidPasswords = {
                // all the following are invalid as they contain 1 or more white spaces
                "Aa gv!1", "Bb7h@  2", "Cc# ", " Ddvvy$", " Ee%##1j ", "        ",
                " ", " G j g & ", "Hhk 97h*", "Ii (", "              Jj)"
        };

        for (String invalidPassword : invalidPasswords) {
            try {
                newPasswordValidator.validateNewPassword(invalidPassword);
            }
            catch (InvalidPasswordSpaceException e) {
                // expected exception; continue
            }
            catch (Exception e) {
                fail("Unexpected exception of password: " + invalidPassword);
            }
        }
        System.out.println("Finished testInvalidSpacePasswords");
    }

    @Test
    public void testInvalidLengthPasswords() {
        System.out.println("\nStarting testInvalidLengthPasswords");

        NewPasswordValidator newPasswordValidator = new NewPasswordValidator();

        String[] invalidPasswords = {
                // the following are invalid in length; also covers the empty string edge case
                "Aagv!1", "Bb7h@2", "Cc#", "Ddvvy$", "Ee%##1j",
                "", "Gjg&", "Hhk97h*", "Ii(", "Jj)", "A221rrv"
        };

        for (String invalidPassword : invalidPasswords) {
            try {
                newPasswordValidator.validateNewPassword(invalidPassword);
            }
            catch (InvalidPasswordLengthException e) {
                // expected exception; continue
            }
            catch (Exception e) {
                fail("Unexpected exception of password: " + invalidPassword);
            }
        }
        System.out.println("Finished testInvalidLengthPasswords");
    }

    @Test
    public void testInvalidUpperCasePasswords() {
        System.out.println("\nStarting testInvalidUpperCasePasswords");

        NewPasswordValidator newPasswordValidator = new NewPasswordValidator();

        String[] invalidPasswords = {
                // all these are long enough but are missing an upper case character
                "aa!12345", "bb@23456", "cc#34567", "dd$45678", "ee%56789",
                "ff^67890", "gg&78901", "hh*89012", "ii(90123", "jj)01234"
        };

        for (String invalidPassword : invalidPasswords) {
            try {
                newPasswordValidator.validateNewPassword(invalidPassword);
            }
            catch (InvalidUpperCaseException e) {
                // expected exception; continue
            }
            catch (Exception e) {
                fail("Unexpected exception of password: " + invalidPassword);
            }
        }
        System.out.println("Finished testInvalidUpperCasePasswords");
    }

    @Test
    public void testInvalidLowerCasePasswords() {
        System.out.println("\nStarting testInvalidLowerCasePasswords");

        NewPasswordValidator newPasswordValidator = new NewPasswordValidator();

        String[] invalidPasswords = {
                // these are all long enough but are missing a lower case character
                "AA!12345", "BB@23456", "CC#34567", "DD$45678", "EE%56789",
                "FF^67890", "GG&78901", "HH*89012", "II(90123", "JJ)01234"
        };

        for (String invalidPassword : invalidPasswords) {
            try {
                newPasswordValidator.validateNewPassword(invalidPassword);
            }
            catch (InvalidLowerCaseException e) {
                // expected exception; continue
            }
            catch (Exception e) {
                fail("Unexpected exception of password: " + invalidPassword);
            }
        }
        System.out.println("Finished testInvalidLowerCasePasswords");
    }

    @Test
    public void testInvalidSpecialCharPasswords() {
        System.out.println("\nStarting testInvalidSpecialCharPasswords");

        NewPasswordValidator newPasswordValidator = new NewPasswordValidator();

        String[] invalidPasswords = {
                // these are all long enough and have proper cases but are missing a special character
                "AaB123456", "BbC234567", "CcD345678", "DdE456789", "EeF567890",
                "FfG678901", "GgH789012", "HhI890123", "IiJ901234", "JjK012345"
        };

        for (String invalidPassword : invalidPasswords) {
            try {
                newPasswordValidator.validateNewPassword(invalidPassword);
            }
            catch (InvalidSpecialCharacterException e) {
                // expected exception; continue
            }
            catch (Exception e) {
                fail("Unexpected exception of password: " + invalidPassword);
            }
        }
        System.out.println("Finished testInvalidSpecialCharPasswords");
    }
}