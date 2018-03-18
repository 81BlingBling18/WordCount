import com.sun.javafx.binding.StringFormatter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Processor {
    private int charCount = 0;
    private int wordCount = 0;
    private int lineCount = 0;

    private int codeLineCount = 0;
    private int emptyLineCount = 0;
    private int noteLineCount = 0;

    private boolean c = true;
    private boolean w = true;
    private boolean l = true;
    private boolean a = true;

    private HashMap<String, String> stopList = new HashMap<>();
    private boolean stopListUsed = false;

    private void init(ArrayList<Boolean> functions, String stopListPath) {
        charCount = 0;
        wordCount = 0;
        lineCount = 0;

        codeLineCount = 0;
        emptyLineCount = 0;
        noteLineCount = 0;

        if (functions != null) {
            c = functions.get(0);
            w = functions.get(1);
            l = functions.get(2);
            a = functions.get(3);
        }

        if (stopListPath != null) {
            stopListUsed = true;
            stopList.clear();

            BufferedReader bufferedReader = null;


                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(stopListPath)));
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] words = line.split(" ");
                        for (String w : words) {
                            if (!"".equals(w)) {
                                stopList.put(w, w);
                            }
                        }
                    }
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        bufferedReader.close();
                        bufferedReader = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                        bufferedReader = null;
                    }
                }


        } else {
            stopListUsed = false;
        }

    }

    void process(String p, ArrayList<Boolean> functions, String stopListPath) {
        //变量初始化
        stopListPath = System.getProperty("user.dir") + "\\src\\stopList.txt";
        init(functions, stopListPath);

        String path = System.getProperty("user.dir") + "\\src\\test.txt";

        //读取stopList列表

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);

                if (c) {
                    String tmpLine = line.replaceAll("        ", " ");
                    charCount += tmpLine.length() + 1;
                }

                if (w) {
                    processWord(line);
                }

                if (l) {
                    lineCount++;
                }

                if (a) {
                    processCode(line);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                bufferedReader = null;
            } catch (Exception e) {
                e.printStackTrace();
                bufferedReader = null;
            }
        }


        String[] tmp = path.split("\\\\");
        String fileName = tmp[tmp.length - 1] + ",";
        ArrayList<String> result = new ArrayList<>();
        if (c) {
            result.add(fileName + "字符数: " + charCount);
        }
        if (w) {
            result.add(fileName + "单词数: " + wordCount);
        }
        if (l) {
            result.add(fileName + "行数: " + lineCount);
        }
        if (a) {
            result.add(fileName + "代码行/空行/注释行: " + String.format("%d/%d/%d", codeLineCount, emptyLineCount, noteLineCount));
        }
        for (String s : result) {
            System.out.println(s);
        }
    }

    private void processWord(String rawLine) {
        String[] splits = {",","\t", "\\s{1,}|,"};
        for (String s : splits) {
            rawLine = rawLine.replaceAll(s, " ");
        }
        String[] pieces = rawLine.split(" ");
        for (String s : pieces) {
            if ((!"".equals(s)) &&(!stopListUsed||stopList.get(s) == null)) {
                wordCount++;
                System.out.println(s);
                System.out.println("".equals(s));
            }
        }
    }

    private void processCode(String line){

    }
}
