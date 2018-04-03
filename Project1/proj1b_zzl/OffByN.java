public class OffByN implements CharacterComparator {
    private int difference;
    public OffByN(int N) {
        difference = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return x - y == difference || y - x == difference;
    }
}
