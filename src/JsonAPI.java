import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonAPI {
    String nomeFile;
    // String primaryKey; //chiave primaria della tabella (denominazione classe, codFiscale alunni)

    JSONArray data;
    Boolean cached;

    public JsonAPI(String nome/* , String primaryKey*/) {
        this.cached = false;
        this.nomeFile = nome;
        // this.primaryKey = primaryKey;
    }

    private String percorso() {
        Path filePath = Paths.get("src").resolveSibling("database").resolve(nomeFile + ".json");
        String absolutePath = filePath.toAbsolutePath().toString();
        return absolutePath;
    }

    public JSONArray getData() {
        if (cached) {
            return data;
        }
        return readJsonFile();
    }

    private JSONArray readJsonFile() {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(percorso())) {
            // Legge il file JSON
            Object obj = parser.parse(reader);

            // Converte l'oggetto in un JSONObject
            JSONArray jsonArray = (JSONArray) obj;

            cached = true;
            data = jsonArray;

            return jsonArray;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateJsonFile(JSONArray jsonArray) {
        try (FileWriter fileWriter = new FileWriter(percorso())) {
            fileWriter.write(jsonArray.toJSONString());
            cached = false;
            System.out.println("Oggetto inserito con successo nel file JSON.");
        } catch (IOException e) {
            System.out.println("Errore durante la scrittura nel file JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void clear() {//zap sul file
        try (FileWriter fileWriter = new FileWriter(percorso())) {
            fileWriter.write(new JSONArray().toJSONString());
            cached = false;
            System.out.println("Pulizia FILE: " + nomeFile + " completata!");
        } catch (IOException e) {
            System.out.println("Errore durante la scrittura nel file JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}