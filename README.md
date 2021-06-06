# Line Editor
This repository is for test file editor.

### Supported actions
- ins n - insert a line at n
- del n - delete line at n
- save - saves to disk
- quit - quits the editor and returns to the command line

## Maven Build

To build and run this project you must have the following items installed:
- [Java 1.8+](https://www.oracle.com/java/technologies/javase-downloads.html) Make sure `JAVA_HOME` is properly set to the JDK installation directory. 
- [Maven 3.6.3+](https://maven.apache.org/download.cgi)
- [Git](http://git-scm.com/)

Download the latest code:
```
git clone https://github.com/Berlizov/test-line-editor.git
```
Change into the test-line-editor directory:
```
cd test-line-editor
```
Build it with Maven:
```
mvn install
```

## Usage
The program has one required argument - the path to open the file. An example of opening a `./test` file:
```
java -jar target/lineeditor-1.0-SNAPSHOT.jar ./test
```
