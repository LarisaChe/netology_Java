package lache;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Employee> RESULT_LIST = new ArrayList<Employee>();
    public static void main(String[] args) throws IOException {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";

        parseCSV(columnMapping, fileName);

        String json = listToJson();

        try (FileWriter file = new FileWriter("data.json")) {
            file.write(json);
            file.flush();
        } catch (IOException ex) {
            //System.out.println(ex.getMessage());//
            ex.printStackTrace();
        }
    }
    private static String listToJson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        StringBuilder json = new StringBuilder();
        json = json.append(gson.toJson(RESULT_LIST, new TypeToken<List<Employee>>(){}.getType()));
        return json.toString();
    }
    private static void parseCSV(String[] columnMapping, String fileName) {
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(strategy)
                    .build();
            RESULT_LIST = csv.parse();
        } catch (IOException ex) {
            //System.out.println(ex.getMessage());//
            ex.printStackTrace();
        }
    }
}