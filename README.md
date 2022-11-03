# FilecontentSearching
FilecontentSearching - Searching specific patterns from file based on regex and display its starting and end position

Language used -> Java 1.8+

Tools used -> Eclipse

Assumptions:

1. Input file will be text file.
2. Contents of the text file will be similar like sample input given in requirement.
3. Regex was added as of now based on the sample input given in requirement.

How to configure in Eclipse?

1. Go to https://github.com/ and search with name "FilecontentSearching"
2. Check out the code which is built using maven project.
3. Open eclipse and import the folder as Maven Project
4. Update the java build path and java compiler version to >= Java 1.8

How to execute?

FileContentSearching.java is the entry point of this standalone application.

Inputs required -> Options (1,2,3) = denotes the type of reader and FileName with its full path.

Sample input and output:

2022-11-03 22:38:57 INFO  FileContentSearching:21 - Option 1 - Run with ReadFileUsingStreams
2022-11-03 22:38:57 INFO  FileContentSearching:22 - Option 2 - Run with ReadFileUsingScanner
2022-11-03 22:38:57 INFO  FileContentSearching:23 - Option 3 - Run with ReadFileUsingBufferReader
2022-11-03 22:38:57 INFO  FileContentSearching:26 - Enter option :
1
2022-11-03 22:39:00 INFO  FileContentSearching:30 - Enter file name with full path :
/Users/vseeema690/Documents/Varun/TEMP/test1-49MB.txt
2022-11-03 22:39:02 INFO  FileContentSearching:40 - ==================== ReadFileUsingStreams ======================
2022-11-03 22:39:05 INFO  FileContentSearching:52 - ReadFileUsingStreams :: timeElapsedLineCount 3335 millis
2022-11-03 22:39:05 INFO  FileContentSearching:54 - ==================================================================

Inference on execution time with varying file size:

Refer to file inference.txt in project directory
