package complement;

public abstract class ColorfulMessage {

    public enum Color {
        red("\u001B[31m"),
        cyan("\u001B[36m"),
        green("\u001B[32m"),
        white("\u001B[37m"),
        yellow("\u001B[33m"),
        reset("\u001B[0m");

        private final String code;

        private Color(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public static void printLog(String text) {
            printLog(text, Color.reset);
        }

        public static void printLog(String text, Color color) {
            if (color == null) {
                Color.printLog(text);
            } else {
                System.out.println("\u229E" + " " + color.getCode() + text + Color.reset.getCode());
            }
        }
    }

}
