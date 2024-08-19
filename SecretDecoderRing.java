public class SecretDecoderRing {

    // Decodes the message based on shift operations
    public static String decodeMessage(String s, int[][] shifts) {
        char[] message = s.toCharArray();

        // Apply each shift operation to the message
        for (int[] shift : shifts) {
            applyShift(message, shift);
        }

        return new String(message);
    }

    // Applies a single shift operation to the message
    private static void applyShift(char[] message, int[] shift) {
        int start = shift[0];
        int end = shift[1];
        int direction = shift[2];

        // Rotate characters in the specified range
        for (int i = start; i <= end; i++) {
            message[i] = rotateChar(message[i], direction);
        }
    }

    // Rotates a character based on the direction
    private static char rotateChar(char c, int direction) {
        if (direction == 1) { // Clockwise
            return rotateClockwise(c);
        } else { // Counter-clockwise
            return rotateCounterClockwise(c);
        }
    }

    // Rotates a character clockwise (forward in the alphabet)
    private static char rotateClockwise(char c) {
        return c == 'z' ? 'a' : (char) (c + 1);
    }

    // Rotates a character counter-clockwise (backward in the alphabet)
    private static char rotateCounterClockwise(char c) {
        return c == 'a' ? 'z' : (char) (c - 1);
    }

    public static void main(String[] args) {
        String s = "hell";
        int[][] shifts = {{0, 1, 1}, {2, 3, 0}, {0, 2, 1}};
        System.out.println(decodeMessage(s, shifts)); // Output: "ifmmp"
    }
}
