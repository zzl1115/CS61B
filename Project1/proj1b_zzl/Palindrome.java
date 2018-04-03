public class Palindrome {
    public static Deque<Character> wordToDeque(String word){
        Deque<Character> wordList = new ArrayDequeSolution<>();
        for(int i = 0; i < word.length(); i++) {
            wordList.addLast(word.charAt(i));
        }
        return wordList;
    }
    public static boolean isPalindrome(String word){
        Deque<Character> wordList = wordToDeque(word);
        int length = wordList.size() / 2;
        for(int i = 0; i < length; i++) {
            if(wordList.get(i) != wordList.get(wordList.size() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordList = wordToDeque(word);
        int length = wordList.size() / 2;
        for(int i = 0; i < length; i++) {
            if(!cc.equalChars(wordList.get(i), wordList.get(wordList.size() - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 26; i++) {
            In in = new In("words.txt");
            CharacterComparator cc = new OffByN(i);
            int count = 0;
            while (!in.isEmpty()) {
                String s = in.readString();
                if (Palindrome.isPalindrome(s, cc) && s.length() != 1) {
                count++;
                }
            }
            System.out.println("Palindrome by " + i + " has: " + count);
        }
    }
}
