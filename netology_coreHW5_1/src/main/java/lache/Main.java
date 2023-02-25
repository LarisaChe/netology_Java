package lache;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Employee> employeesFromCSV = new ArrayList<>();
    private static List<Employee> employeesFromXML = new ArrayList<>();

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileNameCSV = "data.csv";
        String fileNameXML = "data.xml";
        String fileNameJson = "new_data.json";

        // Задача 1: CSV - JSON парсер
        parseCSV(columnMapping, fileNameCSV);
        String json1 = listToJson(employeesFromCSV);
        saveJson(json1, "data.json");

        // Задача 2: XML - JSON парсер
        parseXML(fileNameXML);
        //System.out.println(employeesFromXML.toString());
        String json2 = listToJson(employeesFromXML);
        saveJson(json2, "data2.json");

        // Задача 3: JSON парсер
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileNameJson));
            JSONArray jsonArray = (JSONArray) obj;
            List<Employee> list = jsonToList(jsonArray);
            //System.out.println(list.toString());
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    private static List<Employee> jsonToList(JSONArray jsonArray) {
        List<Employee> list = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        for (Object json3 : jsonArray) {
            Employee employee = gson.fromJson(json3.toString(), Employee.class);
            list.add(employee);
            System.out.println(employee.toString());
        }
        return list;
    }
    private static void parseXML(String fileNameXML) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(fileNameXML));

        Node root = doc.getDocumentElement();
        //System.out.println("Корневой элемент: " + root.getNodeName());
        read(root);
    }
    private static void read(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node1 = nodeList.item(i);
            if (Node.ELEMENT_NODE == node1.getNodeType()) {
                NodeList nodeList2 = node1.getChildNodes();

                long id = 0;
                String firstName = "";
                String lastName = "";
                String country = "";
                int age = 0;

                for (int j = 0; j < nodeList2.getLength(); j++) {
                    Node node2 = nodeList2.item(j);
                    if (Node.ELEMENT_NODE == node2.getNodeType()) {
                        switch (node2.getNodeName()) {
                            case "id":
                                id = Long.valueOf(node2.getTextContent());
                                break;
                            case "firstName":
                                firstName = node2.getTextContent();
                                break;
                            case "lastName":
                                lastName = node2.getTextContent();
                                break;
                            case "country":
                                country = node2.getTextContent();
                                break;
                            case "age":
                                age = Integer.valueOf(node2.getTextContent());
                                break;
                        }
                    }
                }
                employeesFromXML.add(new Employee(id, firstName, lastName, country, age));
            }
        }
    }
    private static void saveJson(String json, String fileName) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(json);
            file.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private static String listToJson(List<Employee> employeeList) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        StringBuilder json = new StringBuilder();
        json.append(gson.toJson(employeeList, new TypeToken<List<Employee>>() {}.getType()));
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
            employeesFromCSV = csv.parse();
        } catch (IOException ex) {
            //System.out.println(ex.getMessage());//
            ex.printStackTrace();
        }
    }
}