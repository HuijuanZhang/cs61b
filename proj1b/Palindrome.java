import javax.security.auth.callback.CallbackHandler;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque d = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        Deque d = wordToDeque(word);
        if(d.size() == 0 || d.size() == 1) {
            return true;
        } else {
            Character first = (Character) d.removeFirst();
            Character last = (Character) d.removeLast();
            return isPalindrome(word.substring(1, word.length()-1)) && (first == last);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque d = wordToDeque(word);
        if(d.size() == 0 || d.size() == 1) {
            return true;
        } else {
            Character first = (Character) d.removeFirst();
            Character last = (Character) d.removeLast();
            return isPalindrome(word.substring(1, word.length()-1), cc) && cc.equalChars(first, last);
        }
    }
}