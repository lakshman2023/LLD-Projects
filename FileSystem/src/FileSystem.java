import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileSystem {
    private final ReentrantReadWriteLock lock;
    private final Node root;
    public FileSystem() {
        this.lock = new ReentrantReadWriteLock();
        this.root = new Directory("/");
    }

    public Node resolvePath(String path) throws FileSystemException {
        String[] parts = path.split("/");
        Node current = root;
        for (String part : parts) {
            if (part.isEmpty()) { continue; }
            if(! (current instanceof Directory)){
                throw new FileSystemException("Path is not a directory");
            }
            current = ((Directory) current).getChild(part);
            if(current == null){
                throw new FileSystemException("Path does not exist : "+ path);
            }
        }
        return current;
    }

    public void createDirectory(String path) throws FileSystemException {
        lock.writeLock().lock();
        Node current = root;
        try{
            String[] parts = path.split("/");
            for (String part : parts) {
                if (part.isEmpty()) { continue; }
                current = getNextNode(current, part);
            }
        } catch (FileSystemException e){
            System.out.println(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Node getNextNode(Node current, String pathPart) throws FileSystemException {
        if(! (current instanceof Directory)){
            throw new FileSystemException("Path is not a directory");
        }
        Node node = ((Directory) current).getChild(pathPart);
        if(node == null){
            node = new Directory(pathPart);
            ((Directory)current).addChild(node);
            current = node;
        } else if(node instanceof Directory){
            current = node;
        } else {
            throw new FileSystemException("Path is not a directory");
        }
        return current;
    }

    public void createFile(String path) throws FileSystemException {
        lock.writeLock().lock();
        Node current = root;
        try{
            String[] parts = path.split("/");
            for (int i = 0; i < parts.length - 1; i++) {
                String part = parts[i];
                if (part.isEmpty()) { continue; }
                current = getNextNode(current, part);
            }
            String fileName = parts[parts.length - 1];
            if(((Directory)current).getChild(fileName) == null) {
                File file = new File(fileName);
                ((Directory)current).addChild(file);
            } else {
                throw new FileSystemException("File already exists");
            }
        } catch (FileSystemException e){
            System.out.println(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void writeToFile(String path, String content) throws FileSystemException {
        lock.writeLock().lock();
        try{
            Node current = resolvePath(path);
            if(! (current instanceof File)){
                throw new FileSystemException("Path is not a file");
            } else {
                ((File)current).writeContent(content);
            }
        } catch(FileSystemException e){
            System.out.println(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public String readFromFile(String path) throws FileSystemException {
        lock.readLock().lock();
        try{
            Node current = resolvePath(path);
            if(! (current instanceof File)){
                throw new FileSystemException("Path is not a file");
            } else {
                return ((File)current).readContent();
            }
        } catch(FileSystemException e){
            System.out.println(e);
        } finally {
            lock.readLock().unlock();
        }
        return null;
    }

    public List<String> listDirectory(String path) throws FileSystemException {
        lock.readLock().lock();
        try{
            Node current = resolvePath(path);
            if(! (current instanceof Directory)){
                throw new FileSystemException("Path is not a directory");
            }
            List<String> children = new ArrayList<String>();
            for(Node child : ((Directory)current).getChildren()){
                children.add(child.getName());
            }
            return children;
        } catch (FileSystemException e){
            System.out.println(e);
        } finally {
            lock.readLock().unlock();
        }
        return null;
    }
}
