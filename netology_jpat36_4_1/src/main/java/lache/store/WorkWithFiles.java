package lache.store;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class WorkWithFiles {
    private static String usersFileName = "users.csv";
    private static String orderNumFileName = "ordernum.csv";

    public static int getNextOrderNum() throws IOException {
        File orderNumFile = new File(orderNumFileName);
        int result = 0;
        if (orderNumFile.exists()) {
            try {
                FileReader fr = new FileReader(orderNumFile);
                result = fr.read();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        result++;
        saveOrderNumToFile(result);
        return result;
    }

    public static void checkUsersFromFile(String userName) throws IOException {
        File usersFile = new File(usersFileName);
        boolean userFound = false;
        if (usersFile.exists()) {
            try {
                FileReader fr = new FileReader(usersFile);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();

                while (line != null) {
                    //System.out.println(line);
                    if (userFound = line.trim().equals(userName)) {
                        break;
                    }
                    line = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!userFound) {
            saveUsersToFile(userName);
        }
    }

    public static void saveUsersToFile(String userName) throws IOException {
        saveToFile(usersFileName, userName, true);
    }

    public static void saveOrderNumToFile(int orderNum) throws IOException {
        saveToFile(orderNumFileName, String.valueOf(orderNum), false);
    }

    public static void saveToFile(String fn, String str, boolean append) throws IOException {
        try (FileWriter writer = new FileWriter(fn, append)) {
            writer.write(str);
            writer.append('\n');
            writer.flush();
        }
    }

    public static <T> List<T> parseCSV(String[] columnMapping, String fileName, Class<? extends T> obj) { //, List<T> list
        List<T> resultList = new ArrayList<T>();
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<T>();
            strategy.setType(obj); //Product.class);
            strategy.setColumnMapping(columnMapping);
            //CsvToBean<Product> csv = new CsvToBeanBuilder<Product>(reader)
            CsvToBean<T> csv = new CsvToBeanBuilder<T>(reader)
                    .withMappingStrategy(strategy)
                    .build();
            resultList = csv.parse();
        } catch (IOException ex) {
            //System.out.println(ex.getMessage());//
            ex.printStackTrace();
        }
        return resultList;
    }
}
