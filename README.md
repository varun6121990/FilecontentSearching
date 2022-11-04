# FilecontentSearching
## FilecontentSearching - Searching specific patterns from file based on regex and display its starting and end position

* Language used -> Java 1.8+ *

* Tools used -> Eclipse *

## Assumptions:

1. Input file will be text file.
2. Contents of the text file will be similar like sample input given in requirement.
3. Regex was added as of now based on the sample input given in requirement.

## How to configure in Eclipse?

1. Go to https://github.com/ and search with name "FilecontentSearching"
2. Check out the code which is built using maven project.
3. Open eclipse and import the folder as Maven Project
4. Update the java build path and java compiler version to >= Java 1.8

## How to execute?

FileContentSearching.java is the entry point of this standalone application.

Inputs required -> Options (1,2,3) = denotes the type of reader and FileName with its full path.

## Sample input and output:

**Note: To see the output, first enable the log statement in any one of the reader class. By default output log statement will be commented**

```
2022-11-04 10:30:14 INFO  FileContentSearching:29 - Option 1 - Run with ReadFileUsingStreams
2022-11-04 10:30:14 INFO  FileContentSearching:30 - Option 2 - Run with ReadFileUsingScanner
2022-11-04 10:30:14 INFO  FileContentSearching:31 - Option 3 - Run with ReadFileUsingBufferReader
2022-11-04 10:30:14 INFO  FileContentSearching:34 - Enter option :
1
2022-11-04 10:30:16 INFO  FileContentSearching:38 - Enter file name with full path :
/Users/vseeema690/Documents/Varun/TEMP/testing.txt
2022-11-04 10:30:18 INFO  FileContentSearching:48 - ==================== ReadFileUsingStreams ======================
2022-11-04 10:30:18 INFO  ReadFileUsingStreams:67 - Response via ReadFileUsingStreams : [{"searchText":"24","positionDetailsBean":{"lineNumber":1,"startingPosition":91,"endingPosition":93}},{"searchText":"28.84.060","positionDetailsBean":{"lineNumber":2,"startingPosition":80,"endingPosition":89}},{"searchText":"5","positionDetailsBean":{"lineNumber":3,"startingPosition":192,"endingPosition":193}},{"searchText":"10","positionDetailsBean":{"lineNumber":3,"startingPosition":252,"endingPosition":254}}]
2022-11-04 10:30:18 INFO  FileContentSearching:60 - ReadFileUsingStreams :: timeElapsedLineCount 200 millis
2022-11-04 10:30:18 INFO  FileContentSearching:62 - ==================================================================
```

Inference on execution time with varying file size:

Refer to file inference.txt in project directory
