import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } //Uncomment this class once you've created your Palindrome class.

    @Test
    public void testPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("Aca"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));

        OffByOne offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertFalse(palindrome.isPalindrome("alaka", offByOne));

        OffByN offByN = new OffByN(1);
        assertTrue(palindrome.isPalindrome("flake", offByN));
        assertTrue(palindrome.isPalindrome("a", offByN));
        assertTrue(palindrome.isPalindrome("", offByN));
        assertFalse(palindrome.isPalindrome("alaka", offByN));
    }
}