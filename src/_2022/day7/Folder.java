package _2022.day7;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Folder {
    private String dirName;

    private Folder parentFolder;
    private Map<String, Long> child;
    private Map<String, Folder> childDir;

    public Folder() {
        this.dirName = "/";
        this.parentFolder = null;
        this.child = new HashMap<>();
        this.childDir = new HashMap<>();
    }

    public Folder(String dirName) {
        this.dirName = dirName;
        this.child = new HashMap<>();
        this.childDir = new HashMap<>();
    }

    public Folder(String dirName, Folder parentFolder) {
        this.dirName = dirName;
        this.parentFolder = parentFolder;
        this.child = new HashMap<>();
        this.childDir = new HashMap<>();
    }



    public String getDirName() {
        return dirName;
    }

    public Map<String, Long> getChild() {
        return child;
    }

    public void putChild(String fileName, Long fileLength) {
        if (this.child == null)
            child = new HashMap<>();

        child.put(fileName, fileLength);
    }

    public Map<String, Folder> getChildDir() {
        return childDir;
    }

    public void addChildDir(Folder childFolder) {
        if (this.childDir == null)
            childDir = new HashMap<>();

        childDir.put(childFolder.getDirName(), childFolder);
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }


    public Long getChildFilesSize() {
        AtomicReference<Long> filesSize = new AtomicReference<>(0L);
        if (child != null) {
            child.forEach((k, v) -> {
                filesSize.set(filesSize.get() + v);
            });
        }

        return filesSize.get();
    }


    public Long getTotalFoldersSize(Long maxFileSize) {
        AtomicReference<Long> foldersSize = new AtomicReference<>(0L);
        if (childDir != null) {
            if(childDir.size() == 0) {
                // return filesSize
                return getChildFilesSize();
            }

            childDir.forEach((k, v) -> {
                foldersSize.set(foldersSize.get() + v.getTotalFoldersSize(maxFileSize));
            });
        }

        return foldersSize.get();
    }

    @Override
    public String toString() {
        AtomicReference<String> output = new AtomicReference<>("");
        String[] folders = dirName.split("/");
        String tabs = "";

        for (String f: folders) {
            tabs += " ";
        }

        String finalTabs = tabs;
        output.updateAndGet(i -> i.concat(finalTabs + "- " + dirName + " (dir)" + "\n"));

        getChild().forEach((k, v) -> output.updateAndGet(i -> i.concat(finalTabs + "  - " + k + " (file, size="+v+")" + "\n")));

        getChildDir().forEach((k, v) -> output.updateAndGet(i -> i.concat(v.toString())));


        return output.toString();
    }

}