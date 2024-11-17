public class File extends Node{
    private StringBuilder content;

    public File(String name){
        super(name);
        this.content = new StringBuilder();
    }

    public String readContent() {
        return this.content.toString();
    }

    public void writeContent(String content) {
        this.content.append(content);
    }

    @Override
    public boolean isFile(){
        return true;
    }


}
