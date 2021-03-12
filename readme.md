# Reviewing input and output from Computer Science

Input and Output are not isolated to Java but are a concept used in all programming languages. In a general sense, **input** is simply
anything which the system recieves. While output is anything the system sends out. Files are a special case as they are one of the only forms of persistent storage such that we can access data without having to store everything in literals. Other options include databases (which are really just files) and Environmental variables (which are really literals stored in configuration files...so still files).

In this chapter we focus on the ```Scanner object``` which is used to recieve input from the keyboard and files which are used for 
both input and output. However, if one progresses to a GUI system such as JavaFX they will learn about 
[Events and Listener objects](https://docs.oracle.com/javafx/2/events/handlers.htm) which handle everything from dropdown box change 
to mouse clicks to many others.


## A tale of two packages

There are two packages for I/O: 
- the older java.io package (Java <=4: does not support symbolic links)
    - used with **System.in** (Scanner) for user input
    - used with **System.out** (which provides a PrintStream) for all the ```System.out.print```s
- the newer java.nio ("new io") package (Java >4)
    - Improved exception handling with java.nio.file.
      - Further improved in Java 8 (with other improvements too)
    - Java.nio.Files & BufferedReader/Writer which help with socket and other input/output handling as well
    
## Printing to the Screen
### java.io #1: Printing to Screen
System.out is part of java.io (the old package) and includes several versions to print:
1. ```System.out.println()``` : Print out and advance to a new line after
2. ```System.out.print()``` : Print out and leave cursor at the end (no newline)
3. ```System.out.printf()``` : Formatted print out

We will only cover printf() here as we've handled the others

##### Formatted Print with printf and String.format
> System.out.printf("format-string", [arg1, arg2, ... argN]);

###### Format String: 
This is composed of string literals and format specifiers. The string literals are simply the strings you want to 
output while the format specifiers follow the below syntax (square brackets are optional):

```%[flags][width][.precision] conversion-character``` 

###### Flags:
- \-: left-justify (default is right)
- \+ : output positiveor negative sign (+/-) for numeric values
- 0 : forces numerical values to be zero-padded
- , : add grouping separator (i.e. 1,000 not just 1000)

###### Width:
Specifies the minimum number of characters for the output and the field width of the argument. This includes special characters 
(commas, decimal points, or +/-signs).

###### .Precision:
Restricts the number of digits of precisionbased on the given argument. Basically, rounds the number to the specified precision.
Conversion-Characters:
- d: decimal or integer (short, int, long)
- f: floating-point number (float, double)
- c: character
- C: FORCE UPPERCASE CHARACTER
- S: FORCE UPPERCASE STRING
- n: platform specific newline (so with printf use %n instead of \n)

##### printf Usage Examples:
```
String title = "Words of Radiance";
String author = "Brandon Sanderson";
System.out.printf("The book %s author is %s", title, author);// output: The book Words of Radiance author is Brandon Sanderson
```

```
int itemSku = 1234;
System.out.printf("The eight digit sku is: %08d", itemSku); // output: The eight digit sku is: 00001234

double balanceDue = 2345.389;
System.out.printf("The total due is %,.2f%n", balanceDue); // output: The total due is 2,345.39
```

###### As of Java 5: Java provides the String.Format() class
This allows you to create a static string for processing or assignment. So using the last two examples you can also just assign them 
directly to a String variable instead of printing it.

```
String printItemSku = String.format("The eight digit sku is: %08d", 1234); // variable = The eight digit sku is: 00001234

String printBalanceDue = String.format("The total due is %,.2f%n", 2345.389); // 2,345.39 with newline
```

## Simple Java Input: Inputting from the keyboard

##### Pre-5 you only had Scanner (and we still use it for single line input)
So **Scanner** with **System.in** is still the main method of recieving a single line of input. Using the standard:

```
Scanner scan = new Scanner(System.in); // instant of Scanner object

System.out.print("How old are you?"); // print out prompt
int age = scan.nextInt(); // Input Stream recieves the next int
scan.nextLine(); // remove extra characters including the newline from Stream

System.out.print("Enter your name: "); // Prompt
String name = scan.nextLine();
```

Alternatively, one can use a loopto handle infinite keyboard input (or at least until memory overflow). It's also one of 
the few usages I know of for do/while loops.:

```
Scanner scan = new Scanner(System.in); // instant of Scanner object
List<String> players = new ArrayList<>(); // create List to hold all players
do {
     System.out.print("Please enter the player's name: ");
     players.add(scan.nextLine());
} while(players.size() > 0 && !players.get(players.size() -1).equals(""));
// runs once then if they add any value it contiues, else exit; if the last value is "" then exit

// remove the blank last value and print out list
players.remove(players.size()-1);
System.out.println(players);
```
Note that the code above should have some try/catch and error handling but used for example.

## FILE INPUT
### "Buffered"?
Both Buffer reader and writer "buffer" the characters (bytes) being written/read to ensure efficiency. They are used to read/write files
and sockets line-by-line over all at once. This buffering means the break up the I/O operations into chunks of bytes and store them in an
internal buffer which the "reader" or "writer" can use directly instead of the underlying stream or file.

There are other methods (like Stream) that allow for immediately loading data from a file into a data structure.

### BufferedReader
**Buffering Another Reader or no?**

So in pre-Java 7, one needed to load BufferedReader over a FileReader object. This is still how it works but a new interface was added in 
Java 7 which makes it a little easier to implement (but allows it to be backward complient). We will use that method with Files but the old
technique is still valid.

##### Syntax
```
Path someFile = ;

// Note using BufferedReader inside a try/catch block closes it on exit (so don't need to close reader explicitly)
try (BufferedReader reader = Files.newBufferedReader(Paths.get("/path/to/file/file.txt")) {
  // String to hold each line of the file (and reset each time we loop)
  String line;
  
  while ((line = reader.readLine()) != null) { 
  // assign current line to variable
  // If nothing is assigned it sets it to "null" and we exit
    System.out.println(line);
  }
} catch (NoSuchFileException nofile) {
  nofile.printStackTrace();
} catch (IOException ex) {
  ex.printStackTrace();
}
```

### BufferedReader vs Scanner
So to compair the older Scanner technique (from Java.io) with the Buffered reader and Paths (from Java.nio):

1. BufferedReader is synchronized (thread-safe). Scanner is not
2. Scanner can parse primitive types and strings using regular expressions directly
    - BufferedReader needs to use parsers (like ```Integer.parseInt()```)
3. It's possible to change the size of the buffer with BufferedReader. Scanner is fixed.
    - BufferedReader's default buffer size is larger by default
4. Scanner only uses **InputMismatchException** while **BufferedReader** forces us to handle **IOException**
5. Typically BufferedReader is faster as it doesn't try to parse the type (its assume strings - see #2)

## FILE OUTPUT

### BufferedWriter
Writer has the same advantages (less disadvantages because we are writing strings either way) then BufferedReader due to its
Buffering. So read that section for more on that.

##### Syntax
```
// Again, if you use a try block you don't have to close it after
try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("file.txt"))) {
  // Just simple countdown to write to file
  for (int i = 10; i > 0; i--) {
    writer.write(String.format("Countdown %s%n", i));
  }
// Again we need to catch if their is no file or ioexceptions
} catch (NoSuchFileException nofile) {
  nofile.printStackTrace();
} catch (IOException ex) {
  ex.printStackTrace();
}
```
