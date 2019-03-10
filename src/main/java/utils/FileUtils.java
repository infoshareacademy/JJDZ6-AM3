package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FileUtils {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static <T> void saveListToJsonFile(T list, String path) {
        try (Writer writer = new FileWriter(path)) {
            GSON.toJson(list, writer);
            System.out.println("Saved to json file:  " + path);
        } catch (IOException e) {
            System.out.println("Exception during saving json file: " + e.getMessage());
        }
    }

    public static <T> ArrayList<T> readListFromJsonFile(String path) {
        try (Reader reader = new FileReader(path)) {
            System.out.println("Reading from json file: " + path);
            Type collectionType = new TypeToken<ArrayList<T>>() {}.getType();
            ArrayList<T> collection = GSON.fromJson(reader, collectionType);
            System.out.println("List successfully uploaded. Number of elements: " + collection.size());
            return collection;
        } catch (IOException e) {
            System.out.println("File not found or broken: " + e.getMessage());
            return new <T> ArrayList<T>();
        }
    }
}
