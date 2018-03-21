package UnitTest;

import java.io.File;
import java.util.ArrayList;

public class CommandDecoder {

    public CommandDecoder() {
    }

    public Commands decode(String[] args) {
        String filePath = null;
        Commands cmds= new Commands();
        for (int i = 0; i < args.length; i++) {
            String cmd = args[i];
            switch (cmd) {
                case "-c":
                    cmds.c = true;
                    break;
                case "-w":
                    cmds.w = true;
                    break;
                case "-l":
                    cmds.l = true;
                    break;
                case "-s":
                    cmds.s = true;
                    break;
                case "-e":
                    cmds.stopListPath = System.getProperty("user.dir") + "\\" + args[++i];
                    break;
                case "-o":
                    cmds.outputPath = args[++i];
                    break;
                case "-a":
                    cmds.a = true;
                    break;
                default:
                    filePath = args[i];
            }
        }

        //递归遍历目录树
        if (cmds.s) {
            if (filePath.contains("\\")) {
                int index = filePath.lastIndexOf("\\");
                String extension = filePath.substring(index + 2, filePath.length());
                filePath = filePath.substring(0, index);
                getAllFilePath(filePath, cmds.filePath, extension);
            } else {
                getAllFilePath(System.getProperty("user.dir"), cmds.filePath, filePath.substring(1, filePath.length()));
            }
        } else {
            cmds.filePath.add(filePath.contains("\\") ? filePath : System.getProperty("user.dir") + "\\" + filePath);
        }
        return cmds;
    }

    private void getAllFilePath(String filePath, ArrayList<String> paths,String extension) {
        File file = new File(filePath);

            for (String s : file.list()) {
                s = filePath + "\\" + s;
                File f = new File(s);
                if (f.isDirectory()) {
                    getAllFilePath(s, paths, extension);
                } else {
                    if (s.endsWith(extension))
                        paths.add(s);
                }
            }
    }
}
