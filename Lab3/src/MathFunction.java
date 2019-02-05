public class MathFunction {
    String NameToShow;
    String NameToParser;
    int CursorPosition;

    public MathFunction(String nameToShow, String nameToParser, int cursorPosition) {
        NameToShow = nameToShow;
        NameToParser = nameToParser;
        CursorPosition = cursorPosition;
    }

    @Override
    public String toString() {
        return NameToShow;
    }

    public String getNameToParser() {
        return NameToParser;
    }

    public int getCursorPosition() {
        return CursorPosition;
    }
}
