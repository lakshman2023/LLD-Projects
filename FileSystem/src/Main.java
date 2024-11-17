import java.nio.file.FileSystemException;

public class Main {
    public static void main(String[] args) throws FileSystemException {

        FileSystem fs = new FileSystem();

        fs.createFile("/a/b/file1.txt");
        fs.writeToFile("/a/b/file1.txt", "Hello, World!");
        System.out.println("File content: " + fs.readFromFile("/a/b/file1.txt"));

        fs.createDirectory("/a/b");
        System.out.println("Directory contents: " + fs.listDirectory("/a/b"));
        //System.out.println("Hello world!");
    }
}