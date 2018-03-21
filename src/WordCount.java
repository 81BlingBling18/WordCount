import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.ArrayList;

public class WordCount {
    public static void main(String[] args) {
        for (int i = 0;i<args.length;i++) {
            System.out.println(args[i]);
        }
        CommandDecoder decoder = new CommandDecoder();
        Processor processor = new Processor();
        Commands cmds = decoder.decode(args);
        System.out.println(cmds.toString());
        ArrayList<Boolean> functions = new ArrayList<>();
        functions.add(cmds.c);
        functions.add(cmds.w);
        functions.add(cmds.l);
        functions.add(cmds.a);
        ArrayList<String> result = new ArrayList<>();
        for (String path : cmds.filePath) {
            result.addAll(processor.process(path, functions, cmds.stopListPath));
        }

        //输出结果
        String outputPath = null;
        if (cmds.outputPath != null) {
            if (cmds.outputPath.contains("\\")) {
                outputPath = cmds.outputPath;
            } else {
                outputPath = System.getProperty("user.dir") + "\\" + cmds.outputPath;
            }
        } else {
            outputPath = System.getProperty("user.dir") + "\\result.txt";
        }

        File file = new File(outputPath);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for (String s : result) {
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                writer = null;
            }

        }

    }
}
