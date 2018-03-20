import java.util.ArrayList;

public class UnitTest {
    public static void main(String[] args) {
//        String testCasePath = args[0];
        System.out.println(System.getProperty("user.dir"));
        UnitTest unitTest = new UnitTest();
        unitTest.decodeTest();
        unitTest.processCodeTest();
    }
    //测试decode方法
    void decodeTest() {
        String[] test1 = {"-c", "-l", "-w", "test.txt", "-e", "stopList.txt","-o","output.txt"};
        String[] test2 = {"-c", "-s", "*.txt"};
        String[] test3 = {"-c", "-s", ".\\pkg\\*.txt"};


        //test1
        CommandDecoder decoder = new CommandDecoder();
        Commands commands = decoder.decode(test1);
        ArrayList<String> tmpList = new ArrayList<>();
        tmpList.add("test.txt");
        tmpList.add("stopList.txt");
        Commands result = new Commands(true, true, true, false, false, "stopList.txt"
                , "output.txt",tmpList);
        if (commands.equals(result)) {
            System.out.println("decode UnitTest test1 success");
        }{
            System.out.println("decode UnitTest test1 failed");
        }

        //test2
        commands = decoder.decode(test2);
        tmpList.add("test2.txt");
        result = new Commands(true, false, false, false, true, null, null, tmpList);
        if (commands.equals(result)) {
            System.out.println("decode UnitTest test1 success");
        }{
            System.out.println("decode UnitTest test1 failed");
        }

        //test3
        tmpList.remove(0);
        tmpList.remove(0);
        if (commands.equals(result)) {
            System.out.println("decode UnitTest test1 success");
        }{
            System.out.println("decode UnitTest test1 failed");
        }

    }

    //测试processCode方法
    void processCodeTest() {
        Processor processor = new Processor();
        processor.processCode("//aa");
        if (processor.noteLineCount == 1 && processor.codeLineCount == 0 && processor.emptyLineCount == 0) {
            System.out.println("process code UnitTest //aa success");
        } else {
            System.out.println("process code UnitTest //aa failed");
        }

        processor = new Processor();
        processor.processCode("/*aa");
        if (processor.noteLineCount == 1 && processor.codeLineCount == 0 && processor.emptyLineCount == 0) {
            System.out.println("process code UnitTest /*aa success");
        } else {
            System.out.println("process code UnitTest /*aa failed");
        }

        processor = new Processor();
        processor.processCode("{/*aa");
        if (processor.noteLineCount == 1 && processor.codeLineCount == 0 && processor.emptyLineCount == 0) {
            System.out.println("process code UnitTest {/*aa success");
        } else {
            System.out.println("process code UnitTest {/*aa failed");
        }

        processor = new Processor();
        processor.processCode("}/*aa");
        if (processor.noteLineCount == 1 && processor.codeLineCount == 0 && processor.emptyLineCount == 0) {
            System.out.println("process code UnitTest }/*aa success");
        } else {
            System.out.println("process code UnitTest }/*aa failed");
        }

        processor = new Processor();
        processor.processCode("{//aa");
        if (processor.noteLineCount == 1 && processor.codeLineCount == 0 && processor.emptyLineCount == 0) {
            System.out.println("process code UnitTest {//aa success");
        } else {
            System.out.println("process code UnitTest {//aa failed");
        }

        processor = new Processor();
        processor.processCode("}//aa");
        if (processor.noteLineCount == 1 && processor.codeLineCount == 0 && processor.emptyLineCount == 0) {
            System.out.println("process code UnitTest }//aa success");
        } else {
            System.out.println("process code UnitTest }//aa failed");
        }

        processor = new Processor();
        processor.processCode("*/");
        if (processor.noteLineCount == 1 && processor.codeLineCount == 0 && processor.emptyLineCount == 0) {
            System.out.println("process code UnitTest */ success");
        } else {
            System.out.println("process code UnitTest */ failed");
        }
    }
}
