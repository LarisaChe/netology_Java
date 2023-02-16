import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    final static String TEMPLATE = "save%d.dat";
    final static String PATH = "D://Games//savegames//";
    final static String ZIP_FILE = "save_dat.zip";

    public static void main(String[] args) {
        // Создать три экземпляра класса GameProgress
        GameProgress gp1 = new GameProgress(77, 9, 1, 234.89);
        GameProgress gp2 = new GameProgress(91, 5, 3, 389.5);
        GameProgress gp3 = new GameProgress(55, 15, 9, 4389);

        // Сохранить сериализованные объекты GameProgress в папку savegames из предыдущей задачи
        saveGame(PATH, gp1);
        saveGame(PATH, gp2);
        saveGame(PATH, gp3);

        // Созданные файлы сохранений из папки savegames запаковать в архив zip
        String zfn = checkSlash(PATH) + ZIP_FILE;
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zfn))) {
            for (String fileName : getFiles(PATH, false)) {
                addFileToZip(zout, checkSlash(PATH) + fileName, fileName);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // Удалить файлы сохранений, лежащие вне архива
        try {
            for (String fileName : getFiles(PATH, true)) {
                File file = new File(fileName);
                if (!file.delete())
                    System.out.println("Не удален файл: " + fileName);
            }
        }  catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void addFileToZip(ZipOutputStream zout, String fileName, String fileNameInZip) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            ZipEntry entry = new ZipEntry(fileNameInZip);
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void saveGame(String dir, GameProgress gameProgress) {
        String fn = nextFileName(dir);
        try (FileOutputStream fos = new FileOutputStream(fn);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String nextFileName(String dir) {
        int i = 1;
        while (true) {
            String fileName = String.format(checkSlash(dir) + TEMPLATE, i);
            File file = new File(fileName);
            if (file.exists())
                i++;
            else
                return fileName;
        }
    }

    public static List<String> getFiles(String dir, boolean withPath) {
        List<String> resultList = new ArrayList<>();
        File folder = new File(dir);
        for (File item : folder.listFiles()) {
            if ((!item.isDirectory()) && checkSubstrBegin(item.getName(), TEMPLATE.substring(0, 4))
                    && checkSubstrEnd(item.getName(), TEMPLATE.substring(TEMPLATE.length() - 3))) {
                if (withPath)
                    resultList.add(checkSlash(dir) + item.getName());
                else
                    resultList.add(item.getName());
            }
        }
        //System.out.println(resultList);
        return resultList;
    }

    public static String checkSlash(String str) {
        return str.substring(str.length() - 2).equals("//") ? str : str + "//";
    }

    public static boolean checkSubstrBegin(String str, String subStr) {
        return str.substring(0, subStr.length()).equals(subStr);
    }

    public static boolean checkSubstrEnd(String str, String subStr) {
        return str.substring(str.length() - subStr.length()).equals(subStr);
    }
}
