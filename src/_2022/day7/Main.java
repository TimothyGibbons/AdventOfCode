package _2022.day7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File("src/_2022/day7/input.txt")));
        String line;

        Folder root = new Folder();
        Folder currentFolder = null;

        while((line = br.readLine()) != null) {

            if (line.startsWith("$")) {

                if (line.equals("$ ls"))
                    continue;

                if (line.startsWith("$ cd ")) {
                    if (line.startsWith("$ cd /")) {
                        currentFolder = root;
                        continue;
                    }

                    if (line.equals("$ cd ..")) {
                        if (currentFolder.getParentFolder() == null) {
                            currentFolder = root;
                            continue;
                        }

                        currentFolder = currentFolder.getParentFolder();
                        continue;
                    }

                    String dirName = line.substring(5);
                    String pwd = currentFolder.getDirName();

                    currentFolder = currentFolder.getChildDir().get((pwd.equals("/") ? pwd : pwd + "/") + dirName);
                    continue;
                }
            }

            if (line.startsWith("dir")) {
                String pwd = currentFolder.getDirName();
                Folder folder = new Folder((pwd.equals("/") ? pwd : pwd + "/") + line.substring(4), currentFolder);
                currentFolder.addChildDir(folder);
                continue;
            }

            String[] file = line.split(" ");

            currentFolder.putChild(file[1], Long.parseLong(file[0]));
        }

//        System.out.println(root);
        getDirectorySize(root, 100000L);
        System.out.println(dirSum);

    }

    private static Long dirSum = 0L;

    private static Long getDirectorySize(Folder folder, Long maxDirLimit) {

        AtomicReference<Long> dirsSize = new AtomicReference<>(0L);

        folder.getChildDir().forEach((k, v) -> {
            Long directorySize = getDirectorySize(v, maxDirLimit);

            if (directorySize < maxDirLimit)
            dirSum += directorySize;

            dirsSize.set(dirsSize.get() + directorySize);
        });

        AtomicReference<Long> filesSize = new AtomicReference<>(0L);

        if (folder.getChild() != null) {
            folder.getChild().forEach((k, v) -> {
                filesSize.set(filesSize.get() + v);
            });
        }
        return dirsSize.get() + filesSize.get();
    }

}