import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) {
        // Create a JSONObject
        System.out.print("Creating JSONObject ... ");
        JSONObject jsonObject = createJsonObject();
        System.out.println("Done.\n");

        // Print the jsonObject
        System.out.println("Printing the json object ... ");
        new JSONPrinter().print(jsonObject);
        System.out.println("Done.\n");

        // Save jsonObject to file
        String jsonFilename = "user.json";
        System.out.printf("Saving the json object to file %s ... ", jsonFilename);
        String jsonString = new JSONPrinter().serialize(jsonObject);
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(jsonFilename), StandardCharsets.UTF_8);
            writer.write(jsonString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done.\n");

        // Parse user.json file and print the parsed object
        System.out.printf("Parse %s ... %n", jsonFilename);
        JSONParser parser = new JSONParser();
        try {
            Object o = parser.parse(new FileReader(jsonFilename));
            if (o instanceof org.json.simple.JSONObject jsonObj) {
                new JSONPrinter().print2(jsonObj);
            }
            else if (o instanceof org.json.simple.JSONArray jsonArr) {
                new JSONPrinter().print2(jsonArr);
            }
        } catch (IOException e) {
            System.err.printf("Can't read file %s%n", jsonFilename);
            e.printStackTrace();
        } catch (ParseException e) {
            System.err.printf("Parse failed, Please check if the %s format is valid json.%n", jsonFilename);
            e.printStackTrace();
        }

        System.out.println("Done.\n");

    }

    private static JSONObject createJsonObject() {
        JSONObject programmer = new JSONObject();
        programmer.put("id", "0001");
        programmer.put("name", "John");
        programmer.put("surname", "Doe");
        programmer.put("age", 25);

        JSONObject pgLanguages = new JSONObject();
        JSONArray backend = new JSONArray();
        backend.put(createProgrammingLanguage("B001", "PHP"));
        backend.put(createProgrammingLanguage("B002", "Python"));

        JSONArray frontend = new JSONArray();
        frontend.put(createProgrammingLanguage("F001", "Javascript"));
        frontend.put(createProgrammingLanguage("F002", "Vue"));

        pgLanguages.put("backend", backend);
        pgLanguages.put("frontend", frontend);

        programmer.put("programming-languages", pgLanguages);

        return programmer;
    }

    private static JSONObject createProgrammingLanguage(String id, String name) {
        JSONObject lang = new JSONObject();
        lang.put("id", id);
        lang.put("name", name);

        return lang;
    }
}
