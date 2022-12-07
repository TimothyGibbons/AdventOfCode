package _2022.day7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MainPart2 {

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

        System.out.println(root);
        Long rootSize = getDirectorySize(root);
        System.out.println("70000000 - " + rootSize + " = " + (70000000L - rootSize));
        System.out.println("30000000 - "+(70000000L - rootSize) + " = " + (30000000L-(70000000L - rootSize)));

        System.out.println("lowest-----------------------------------");
        dirSum.stream().sorted().forEach(System.out::println);


    }

    private static List<Long> dirSum = new ArrayList<>();

    private static Long getDirectorySize(Folder folder) {

        AtomicReference<Long> dirsSize = new AtomicReference<>(0L);
        folder.getChildDir().forEach((k, v) -> {
            Long directorySize = getDirectorySize(v);
            if (directorySize >= 532950 && directorySize < 549688){
                dirSum.add(directorySize);

                System.out.println(v.getDirName() + " : " + directorySize);
            }

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

//29467050
//33269966
//5000000

//30000000
//33269966
//1423358
//Answer = 1623571