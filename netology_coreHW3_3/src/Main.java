import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    final static String PATH = "D://Games//savegames";
    final static String ZIP_FILE = "save_dat.zip";
    final static String PATTERN = "save\\d{1,}.dat";

    public static void main(String[] args) {
        // Произвести распаковку архива в папке savegames.
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(checkSlash(PATH) + ZIP_FILE))) {
            ZipEntry entry;
            String fileName;
            while ((entry = zin.getNextEntry()) != null) {
                fileName = entry.getName();
                FileOutputStream fout = new FileOutputStream(checkSlash(PATH) + fileName);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // Произвести считывание и десериализацию одного из разархивированных файлов save.dat.
        String fileName = "";
        File folder = new File(PATH);
        for (File item : folder.listFiles()) {
            if ((!item.isDirectory()) && (item.getName().matches(PATTERN))) {
                fileName = item.getName();
                System.out.println(fileName);
                break;
            }
        }

        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(checkSlash(PATH) + fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // Вывести в консоль состояние сохранненой игры.
        try {
            System.out.println(gameProgress.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String checkSlash(String str) {
        return str.substring(str.length() - 2).equals("//") ? str : str + "//";
    }
}
