# 软件开发及测试

***



## 项目地址

> github项目地址：https://github.com/81BlingBling18/WordCount

## 本次项目中用到的PSP表格

| **PSP2.1**                               | **PSP** **阶段**          | **预估耗时**  *（分钟）* | **实际耗时** *（分钟）* |
| ---------------------------------------- | ----------------------- | ---------------- | --------------- |
| Planning                                 | 计划                      | 20               | 30              |
| ·  Estimate                              | ·  估计这个任务需要多少时间         | 15               | 14              |
| Development                              | 开发                      | 280              | 300             |
| ·  Analysis                              | ·  需求分析 (包括学习新技术)       | 20               | 12              |
| ·  Design Spec                           | ·  生成设计文档               | 15               | 18              |
| ·  Design Review                         | ·  设计复审 (和同事审核设计文档)     | 18               | 17              |
| ·  Coding Standard                       | ·  代码规范 (为目前的开发制定合适的规范) | 30               | 24              |
| ·  Design                                | ·  具体设计                 | 25               | 22              |
| ·  Coding                                | ·  具体编码                 | 150              | 300             |
| ·  Code Review                           | ·  代码复审                 | 20               | 20              |
| ·  Test                                  | ·  测试（自我测试，修改代码，提交修改）   | 45               | 50              |
| Reporting                                | 报告                      | 30               | 45              |
| ·  Test Report                           | ·  测试报告                 | 20               | 15              |
| ·  Size Measurement                      | ·  计算工作量                | 23               | 20              |
| ·  Postmortem & Process Improvement Plan | ·  事后总结, 并提出过程改进计划      | 15               | 10              |
|                                          | 合计                      | 726              | 897             |

## 思路解析

这周软件测试课程的任务是写一个WordCount，要求可以看下面第一篇参考资料。

看过一遍要求之后就可以知道，这是一个从命令行获得命令，将命令分解之后执行命令，汇总输出结果，然后输出结果的程序，这是程序的框架。程序的功能是统计字符数、单词数等等，可以通过分解命令来实现。初步思路是程序应当包括以下三个部分：

* 命令处理：分析输入的命令，例如是否要递归处理文件，是否要使用stopList等等，并生成内部控制数据结构实例。
* 命令执行控制：根据*命令处理*得到的结构体，分析如何执行命令，并且循环调用*命令执行*模块，汇总执行结果，进行输出等操作。
* 命令执行：执行简单命令，产生运行结果并返回。

首先是*命令处理*部分，老师规定的实现语言是Java。main函数签名是`main(String[] args)`这里的args就可以接收命令行中传入的参数。然后分析命令特点`[-c -w -l -s -a] [codeFileName] [-o] [outputFile] [-e] [stopListFile]  `。也就是说`-o`后面一定接输出文件，`-e`后面一定接stopListFile，剩下的就是codeFileName了。这样就可以直接`switch-case`处理。

剩下的就是分析是否指定`-s`参数，是的话，递归扫描文件夹，找出所有符合条件的文件路径，否则直接分析指定的文件。分析完成之后生成数据结构实例。

*命令执行控制部分*比较简单，根据数据结构实例拆解命令，然后循环分析每个文件，汇总每次的执行结果，并且输出到`-o`指定的文件，或者默认的`result.txt`就行了

*命令执行*这个接收命令，输出执行结果。每一个命令如`-s`设置一个flag，为`true`时表示需要进行相应的分析，编写相应的处理方法并调用即可。需要注意的是，在命令执行之前，若指定了stopList则需要进行相应的初始化。

## 程序设计实现

综上所述，至少应当包括四个类

* WordCount：命令执行控制
* CommandDecoder：命令处理
* Processor：命令执行
* Commands：相当于命令数据结构体


下面给出整体的流程图：

![未命名文件](C:\Users\wqm\Desktop\未命名文件.png)

下面给出每个类中的方法签名、返回值以及功能注释：

```java
public class WordCount {

    public static void main(String[] args) //接收输入参数，调用相关方法执行命令，汇总执行结果，输出到指定文件或默认文件中
    
}
```

~~~java

public class CommandDecoder {

    public CommandDecoder()//构造方法

    public Commands decode(String[] args) //输入参数，进行分析之后返回Commands实例，该实例用于内部表示输入的参数

    private void getAllFilePath(String filePath, ArrayList<String> paths,String extension) //输入文件夹地址、文件地址容器、指定后缀名，递归扫描指定文件夹中的所有文件
}
~~~

```java
public class Processor {

    private void init(ArrayList<Boolean> functions, String stopListPath) //输入相关命令和stopList地址（若指定），初始化相关参数

    ArrayList<String> process(String path, ArrayList<Boolean> functions, String stopListPath)//根据文件地址、需要分析的子功能、stopList对文件进行分析，返回分析结果

    private void processWord(String rawLine) //输入一行代码，分析该行代码中的单词数，若指定stopList则与stopList对比

    private void processCode(String rawLine)//分析输入的一行代码是空行、代码行还是注释行

}
```

```java
public class Commands {
    boolean c = false;//是否分析字符数，true则进行分析
    boolean w = false;//是否分析单词数
    boolean l = false;//是否分析行数
    boolean a = false;//是否对代码行、空行以及注释行进行统计
    boolean s = false;//是否递归分析

    String stopListPath = null;//stopList路径，null表示不使用
    String outputPath = null;//输出文件路径，null表示默认文件
    ArrayList<String> filePath = new ArrayList<String>();//所有需要分析的文件路径
}
```

上面的流程图和方法注释可以很清楚的了解程序的整体结构和设计实现过程，不再赘述。

## 代码说明

上述说明已经给出了程序的实现思路，在*代码说明*这一节，选取几个比较复杂的函数和处理方法进行说明。这里我们给出`decode(String[] args)`、`process(String path, ArrayList<Boolean> functions, String stopListPath)`、`processCode(String rawLine)`和stopList的处理的部分代码和分析。

首先是`decode(String[] args)`

~~~java
    public Commands decode(String[] args) {
      //根据之前分析的命令特点，-o后面必跟输出文件路径，-e后面必跟stopList路径，剩下的就是要分析的源代码的路径
        String filePath = null;//命令中指定的原始路径
        Commands cmds= new Commands();//用来表示原始命令的内部实例，可以看作输入命令的结构体
      //for循环处理每个arg，用switch-case确定每个arg对应的操作。
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
                    cmds.stopListPath = System.getProperty("user.dir") + "\\" + args[++i];//获得stopList的绝对路径
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

        //若指定-s参数之后，需要递归遍历目录树
        if (cmds.s) {
            if (filePath.contains("\\")) {
                //处理C:\adb\*.c类似路径
            } else {
                //处理*.txt类似路径
            }
        } else {
           //未指定时，获得指定文件的绝对路径
        }
        return cmds;
~~~

其次是`process(String path, ArrayList<Boolean> functions, String stopListPath)`

~~~java

    ArrayList<String> process(String path, ArrayList<Boolean> functions, String stopListPath) {
        //由WordCount将命令进行分解，循环调用本方法进行处理。
        //整体思路：从源代码文件中循环读取每一行，根据相应的flag进行处理，完成之后根据各个flag汇总处理结果
        //变量初始化
        init(functions, stopListPath);
		
        BufferedReader bufferedReader = null;
		//循环读取每一行代码并根据flag进行处理
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {

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
                    System.out.println(noteLineCount + "   flag  " + noteFlag);

                }
            }
            lineCount--;//换行符纠正
            //关闭流并进行try-catch-finally处理
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
        String fileName = tmp[tmp.length - 1] + ",";//根据输入的文件路径获取文件名
        //根据flag汇总对当前文件的分析结果
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
            result.add(fileName + "代码行/空行/注释行: " + String.format("%d/%d/%d", codeLineCount, emptyLineCouneLineCount));
        }

        return result;
    }
~~~

接下来是`processCode(String rawLine)`

~~~java
 private void processCode(String rawLine){
   //由于测试用例不一定遵循语法规范，这里的代码仅适用于课程测试用例
   //此方法用来区分代码行、注释行、空行
   /*简单处理思路：
   	*空行：若去掉空格和制表符之后长度小于等于1则为空行；
    *代码行：若在/* * /中，则为注释行，否则去掉空格和制表符之后是以下情况，则为注释行，否则为代码行
    	1.//开头
    	2./*开头，并且本行不包含* /或者本行以* /结尾或者以* /}结尾
    	3.{/*开头，且与2类似
    	4.}/*且与2类似
    	5.{//开头
    	6.}//开头
    	7.本行为* /
    	8.本行为* /}
    */
        String tmp = rawLine.replaceAll("[ \t]", "");
        int index = 0;
        while ((rawLine.charAt(index) + "").equals(" ")) {
            index++;
        }
        tmp = rawLine.substring(index, rawLine.length());
        System.out.println(tmp);
   //长度小于等于1则为空行
        if (tmp.length() <= 1) {
            emptyLineCount++;
            return;
        }
//设置flag，用于判断是否在多行注释中
        if (tmp.contains("/*")) {
            noteFlag = true;
        }
        if (tmp.contains("*/")&&noteFlag) {
            noteFlag = false;
        }

        if (noteFlag ) {//判断是否在多行注释之间
            noteLineCount++;
            return;
        } else if (tmp.startsWith("//")//判断是否在上述八种代码行情况之中
                || (tmp.startsWith("/*")
                && (!tmp.contains("*/") || (tmp.endsWith("*/") || tmp.endsWith("*/}"))))
                || (tmp.startsWith("{/*")
                && (!tmp.contains("*/") || (tmp.endsWith("*/") || tmp.endsWith("*/}"))))
                ||(tmp.startsWith("}/*")
                && (!tmp.contains("*/") || (tmp.endsWith("*/") || tmp.endsWith("*/}"))))
                || tmp.startsWith("{//")
                || tmp.startsWith("}//")
                ||tmp.equals("*/")
                ||tmp.equals("*/}")) {
            noteLineCount++;
            return;
        }

        codeLineCount++;
    }
~~~

最后是stopList的处理：

将stopList进行分割时候用`HashMap<String，String>` 进行存储,可以实现快速查找。

## 测试设计过程

**在线学习2.3节对判定的测试中指出**“语句覆盖就是要保证设计的测试用例应至少覆盖函数中所有的可执行语句”，为此，我针对CommandDecoder类中的decode方法，使用语句覆盖指标设计测试用例，共产生3个测试用例。

下面给出CommandDecoder的程序图：

![chengxutu](C:\Users\wqm\Desktop\chengxutu.png)

通过上面的程序图可以看到，环复杂度为11（分析方法参见[1]），我们可以设计十一个测试用例来进行测试,但是实际上由于循环的存在，最多需要三个测试用例即可覆盖全部路径。通过上面我们对decode方法的分析，可以发现高风险部分在于，当需要递归处理文件时，能够将所有该目录及子目录下所有匹配到的文件都找到，因此应特别关注需要递归处理时结果的正确性。

设计的3个测试用例及覆盖的路径如下表所示：

| 编号   | 用例                                       | 路径覆盖                                     |
| ---- | ---------------------------------------- | ---------------------------------------- |
| 1    | -c -l -w test.txt -e stopList.txt -o output.txt | e1 e2 e10 e3 e11 e4 e12 e6 e14 e7 e15 e18 e20 e23 |
| 2    | -c -s *.c                                | e1 e2 e10 e4 e12 e18 e19 e22 e25         |
| 3    | -c -s C:\Users\testfile\\*.c             | e1 e2 e10 e4 e12 e18 e19 e21 e24         |

覆盖率为100%

同样的，我们对Processor类的processCode方法进行分析，也可以得到如下程序图

![C2](C:\Users\wqm\Desktop\C2.png)

环复杂度为12，由此设计了12个测试用例，由于篇幅原因下面只给出七个

| 编号   | 用例    | 覆盖路径                   |
| ---- | ----- | ---------------------- |
| 1    | //aa  | e1 e3 e5 e7 e10 e18 e9 |
| 2    | /*aa  | e1 e3 e5 e7 e11 e19 e9 |
| 3    | {/*aa | e1 e3 e5 e7 e12 e20 e9 |
| 4    | }/*aa | e1 e3 e5 e7 e13 e21 e9 |
| 5    | {//aa | e1 e3 e5 e7 e14 e22 e9 |
| 6    | }//aa | e1 e3 e5 e7 e15 e23 e9 |
| 7    | */    | e1 e3 e5 e7 e16 e24 e9 |

路径覆盖58.3%



**参考资料：**

无