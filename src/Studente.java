import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Studente {
    String nome;
    String cognome;
    String codFisc;
    String classe;

    JsonAPI JsonAPI;

    public Studente(String nome, String cognome, String codFisc, String classe, JsonAPI JsonAPI){
        this.nome=nome;
        this.cognome=cognome;
        this.codFisc=codFisc;
        this.classe=classe;
        this.JsonAPI=JsonAPI;
    }

    public String insStudente() {
        JSONArray studentJsonArray = (JSONArray) JsonAPI.getData();

        JSONObject student = new JSONObject();
        student.put("nome", nome);
        student.put("cognome", cognome);
        student.put("codFisc", codFisc);
        student.put("classe", classe);

        // Aggiunge il nuovo oggetto all'array
        studentJsonArray.add(student);

        JsonAPI.updateJsonFile(studentJsonArray);

        return "Inserimento completato!";
    }
    public String getClasse(){
        return classe;
    }
}