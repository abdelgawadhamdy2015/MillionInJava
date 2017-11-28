package com.example.abdelgawad.MillionInJava;

/**
 * Created by abdelgawad on 8/17/2017.
 */

public class Constants {
    static int[] images = {R.drawable.p, R.drawable.m, R.drawable.k, R.drawable.r, R.drawable.i};
    static String[][] q_a_java = {{"Which of the following correctly illustrate how an InputStreamReader can be created:\n"
            ,"What is the permanent effect on the file system of writing data to a new FileWriter(\"report\"), given the\n" +
            "file report already exists?","What is the effect of adding the sixth element to a vector created in the following manner:\n" +
            "new Vector(5, 10);","What is the range of data type short in Java?" ,
            "What is the range of data type byte in Java? " ,
            "An expression involving byte, int, and literal numbers is promoted to which of these? " ,
            "Which of these literals can be contained in a data type float variable? " ,
            "Which data type value is returned by all transcendental math functions?",
            "It is the Java keyword that creates inheritance."},
            {"new InputStreamReader(new FileInputStream(\"data\"));","The data is appended to the file","An IndexOutOfBounds exception is raised.","-128 to 127 " , "-128 to 127  " , "int " , "1.7e-308 " , "int ","Enlarge"},
            {"new InputStreamReader(new FileReader(\"data\"));","The file is replaced with a new file","The vector grows in size to a capacity of 10 elements","-32768 to 32767" , " -32768 to 32767 " , "long " , "3.4e-038  " , "float","Extends"},
            {"new InputStreamReader(new BufferedReader(\"data\"));","An exception is raised as the file already exists","The vector grows in size to a capacity of 15 elements","-2147483648 to 2147483647 " , "-2147483648 to 2147483647 " , "byte  " , "1.7e+308  " , "double ","Inherits"},
            {"new InputStreamReader(\"data\");","The data is written to random locations within the file","Nothing, the vector will have grown when the fifth element was added","None of the mentioned  " , "None of the mentioned " , "float " , "3.4e-050 " , " long "," dkfjdsfhsdf"},
            {"new InputStreamReader(new FileInputStream(\"data\"));","The file is replaced with a new file","The vector grows in size to a capacity of 15 elements","-32768 to 32767  " , "-128 to 127 " , "int  " , "3.4e-038     " , " double ","Inherits"}};
    static String[][] questions = {{"Using the declaration below, what will be the final element of the array? int [ ] grades = new int[35];" ,
            "It is the Java keyword that creates inheritance." ,
            "It is the rules of a programming language." ,
            "It is the code/s inside a pair of curly braces." ,
            "If A=10, then after B=++A, the value of B is _______." ,
            "It is the characteristics of Java which ensures that a program can go only where it is designed to go and eliminates the possibility of altering system data unintentionally." ,
            "It is a form of Java program that runs locally on the command line." ,
            "It defines the common variables and methods of a set of objects." ,
            "It is the process of removing errors found in the program." ,
            "Which symbol is used to denote a multi-line comment?" ,
            "Which of the following is not a unary operator?" ,
            "Which of the following is not a Java keyword?" ,
            "Which of the following is not a primitive data type?" ,
            "Which of the following is an invalid first character of an identifier?" ,
            "It is the length of the data type float." ,
            "It is the length of the data type short." ,
            "Which of the following is an invalid variable declaration in Java?" ,
            "It terminates every line of code in Java" ,
            "It is the command used to compile Java program in the command prompt." ,
            "The symbol ! in java means?" ,
            " ____ the first web browser developed in Java?" ,
            "_____ is the ability of an Java application to perform multiple tasks at the same time?" ,
            "_______ consist a data and methods?"},
            {"Grades[0]" , "Enlarge" , "Format" , "Block" , "10" , "Java is simple." , "Applets" , "Objects" , "Editing" , "//" ,
                    "Negation" , "Default" , "Byte" , "_" , "8 bits" , "8 bits" , "Int NumberOfStudents = 250;" , "}" , "Ja" ,
                    "Logical NOT" , "Internet Explorer" , "Multiprogramming" , "Variable"},
            {"Grades[34]" , "Extends" , "Logic" , "Brick" , "11" , "Java is secure." , "Application" , "Class" , "Debugging" , "/* */" ,
                    "Decrement" , "For" , "String" , "$" , "12 bits" , "16 bits" , "Double Salary =0.0;" , ")" , "Jav" ,
                    "Logical OR" , "Netscape Navigator" , "Multithreading" , "Class"},
            {"Grades[35]" , "Inherits" , "Object" , "Function" , "12" , "Java is free." , "Midlets" , "Function" , "Compiling" , "{ }" ,
                    "Bitwise complement" , "Volatile" , "Char" , "A" , "16 bits" , "32 bits" , "String NAME =" , ";" , "Java" ,
                    "Logical XOR" , "Opera" , "Multiprocessing" , "Vectors"},
            {"Impossible to tell" , "Inheritance" , "Syntax" , "Method" , "13" , "Java is portable." , "Servlets" , "Method" , "Running" , "< >" ,
                    "Assignment" , "Of" , "Long" , "8" , "32 bits" , "64 bits" , "Char CivilStatus =" , "." , "Javac" ,
                    "AND" , "HotJava" , "Multitasking" , "None of these"},
            {"Grades[34]" , "Extends" , "Syntax" , "Block" , "11" , "Java is secure." , "Application" , "Class" , "Debugging" , "/* */" ,
                    "Assignment" , "Of" , "String" , "8" , "32 bits" , "16 bits" , "Char CivilStatus =" , ";" , "Javac" ,
                    "Logical NOT" , "Internet Explorer" , "Multitasking" , "Class"}};
    static String[][] midiumQuestion = {{" Exception and Error are immediate subclasses of a class called" , " Object" , "Throwable" , "AWT " , "Panel" , "Object"},
            {"The order of the three top level elements of the java source file are " , " Import, Package, Class " , " Class, Import, Package " , "Package, Import, Class " , "Random order" , "Package, Import, Class"},
            {"Java uses ___ to represent characters" , " ASCII code " , " Unicode" , "Byte code " , "None of the above" , "Unicode"},
            {"Which one is not supported by OOP?" , " Abstraction " , "Polymorphism" , "Encapsulation " , "Global variables" , "Global variables"},
            {"Java programs are" , " Platform-dependent" , "Interpreter-dependent " , "Platform-independent" , "Interpreter-independent" , "Platform-independent"},
            {"The new operator" , " returns a pointer to a variable " , "creates a variable called new" , "obtains memory for a new variable " , "tells how much memory is available" , "obtains memory for a new variable "},
            {"Which of the following statement is correct?" , " For positive numbers, result of operators >> and >>> are same " , "Java provides two operators to do left shift <<< and << " , ">> is the zero fill right shift operator " , ">>> is the signed right shift operator" , " For positive numbers, result of operators >> and >>> are same "},
            {"Java language has support for which of the following types of comment?" , " block, line and javadoc " , "javadoc,literal and string" , "javadoc,char and string " , "single, multiple and quote" , "block, line and javadoc "},
            {" ______ is a mechanism for naming and visibility control of a class and its content." , " Object " , "Packages" , "Interfaces " , "None of the Mentioned" , "Packages"},
            {"The java compiler" , " creates executable " , "translates java source code to byte code" , "creates classes " , "produces java Interpreter" , "translates java source code to byte code"},
            {"Two threads cannot simultaneously enter into the methods of the same object if the methods are" , " static " , "synchronized " , "private " , "package" , "synchronized"},
            {"While using threads which of the following is incorrect?" , " You invoke the Run method " , "You implement Runnable interface " , "You extend from Thread class " , "You call the start method" , "You invoke the Run method "},
            {"What is the name of the method used to schedule a thread for execution?" , " init() " , "start() " , " run() " , "resume()" , "run() "}};
    static final String[] probabilities = {
            " I am not sure " ,
            " I am sure " ,
            "I think that"
    };
    static final int[] scores = {100, 200, 300, 400, 500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 125000, 250000, 500000, 1000000};
    static final String DATABASE_NAME = "MILLION_IN_PROGRAMMING";
    static final int DATABASE_VERSION = 23;
    static final String ID = "id";
    static final String QUESTION = "question";
    static final String ANSWER_A = "answer_a";
    static final String ANSWER_B = "answer_b";
    static final String ANSWER_C = "answer_c";
    static final String ANSWER_D = "answer_d";
    static final String ANSWER_True = "answer_true";
    static final String ADVANCED_TABLE = "advanced_table";
    static final String MEDIUM_TABLE = "medium_table ";
    static final String EASY_TABLE = "easy_table";
}