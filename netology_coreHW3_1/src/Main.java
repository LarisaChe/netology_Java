import java.io.File;
import java.io.IOException;

public class Main {
    public static StringBuilder resultText = new StringBuilder();

    public static void main(String[] args){

        final String dir0 = "D://Games";
        createDir(dir0);

        //В папке Games создайте несколько директорий: src, res, savegames, temp.
        final String dir1 = dir0 + "//src";
        createDir(dir1);
        final String dir2 = dir0 + "//res";
        createDir(dir2);
        final String dir3 = dir0 + "//savegames";
        createDir(dir3);
        final String dir4 = dir0 + "//temp";
        createDir(dir4);

        //В каталоге src создайте две директории: main, test.
        createDir(dir1 + "//main");
        createDir(dir1 + "//test");

        //В подкаталоге main создайте два файла: Main.java, Utils.java.
        createFile("Main.java", dir1 + "//main//");
        createFile("Utils.java", dir1 + "//main//");

        //В каталог res создайте три директории: drawables, vectors, icons.
        createDir(dir3 + "//drawables");
        createDir(dir3 + "//vectors");
        createDir(dir3 + "//icons");

        //В директории temp создайте файл temp.txt.
        createFile("temp.txt",dir4 + "//");

        System.out.println(resultText);
    }

    public static void createDir(String dirName) {
        File dir = new File(dirName);
        if (!dir.exists() || !dir.isDirectory()) {
            if (dir.mkdir())
                resultText = resultText.append("Каталог " + dirName + " создан.\n");
        }
        else
            resultText = resultText.append("Каталог " + dirName + " уже существует.\n");
    }

    public static void createFile(String fileName, String dirName) {
        File newFile = new File(dirName + fileName);
        try {
            if (newFile.createNewFile()) {
                resultText.append("Файл " + fileName + " создан в каталоге " + dirName + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
