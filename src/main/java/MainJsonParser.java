import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainJsonParser {
    public static void main(String[] args) {
        String json = readString("new_data.json");
        List<Employee> list = jsonToList(json);
        for (Employee empl : list) {
            System.out.println(empl.toString());
        }
    }

    public static String readString(String fileName) {
        String jsonText = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String s;
            StringBuilder stringBuild = new StringBuilder();
            while ((s = br.readLine()) != null) {
                stringBuild.append(s);
            }
            jsonText = stringBuild.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonText;
    }

    public static List<Employee> jsonToList(String text) {
        List<Employee> list = new ArrayList<>();
        try {
            Object obj = new JSONParser().parse(text);
            JSONArray jsonArr = (JSONArray) obj;
            String s;
            for (Object ob : jsonArr) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                JSONObject jsonOb2 = (JSONObject) ob;
                s = jsonOb2.toString();
                Employee empl = gson.fromJson(s, Employee.class);
                list.add(empl);
            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
