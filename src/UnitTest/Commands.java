import java.util.ArrayList;

public class Commands {
    boolean c = false;
    boolean w = false;
    boolean l = false;
    boolean a = false;
    boolean s = false;

    String stopListPath = null;
    String outputPath = null;

    ArrayList<String> filePath = new ArrayList<String>();

    @Override
    public String toString() {
        return " c " + c + " w " + w + " l " + a + " s " + s + " stop list path: " + stopListPath + " outputpath " + outputPath + " sourcecodepath "  + filePath.toString();
    }

    public Commands() {
    }

    public Commands(boolean c, boolean w, boolean l, boolean a, boolean s, String stopListPath, String outputPath, ArrayList<String> filePath) {
        this.c = c;
        this.w = w;
        this.l = l;
        this.a = a;
        this.s = s;
        this.stopListPath = stopListPath;
        this.outputPath = outputPath;
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Commands)) {
            return false;
        }
        Commands commands = (Commands) obj;
        if (!(c == commands.c && w == commands.w && l == commands.l && a == commands.a && s == commands.s)) {
            return false;
        }
        for (String s : filePath) {
//            System.out.println(s);
        }

            System.out.println("hello");
        if (filePath.size() != commands.filePath.size()) {
            return false;
        }

        for (int i = 0;i<filePath.size();i++) {
            if (!filePath.get(i).endsWith(commands.filePath.get(i))) {
                return false;
            }
        }

        if (stopListPath == null && commands.stopListPath != null || stopListPath != null && commands.stopListPath == null) {
            return false;
        }
        if (stopListPath != null && !stopListPath.endsWith(commands.stopListPath)) {
            return false;
        }

        if (outputPath == null && commands.outputPath != null || outputPath != null && commands.outputPath == null) {
            return false;
        }
        if (outputPath != null && !outputPath.endsWith(commands.outputPath)) {
            return false;
        } else {
            return true;
        }

    }

}
