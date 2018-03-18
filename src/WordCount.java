import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class WordCount {
    public static void main(String[] args) {
        for (int i = 0;i<args.length;i++) {
            System.out.println(args[i]);
        }
        Processor p = new Processor();
        p.process("",null, null);

    }
}
