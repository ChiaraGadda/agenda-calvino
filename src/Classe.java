import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class Classe {
    String denominazione;
    String codiceAula;

    JsonAPI JsonAPI;

    public Classe(String denominazione, String codiceAula, JsonAPI JsonAPI) {
        this.denominazione = denominazione;
        this.codiceAula = codiceAula;
        this.JsonAPI = JsonAPI;
    }

    public String getDenominazione () {
        return denominazione;
    }

    public String insClasse() {
        JSONArray classJsonArray = (JSONArray) JsonAPI.getData();

        Boolean isValidInsert = true;
        for (int i = 0 ; i < classJsonArray.size(); i++) {
            JSONObject objClass = (JSONObject) classJsonArray.get(i);
            if ((String) objClass.get("denominazione") == denominazione) {
                isValidInsert = false;
                break;
            }
        }

        if (isValidInsert) {
            JSONObject classe = new JSONObject();
            classe.put("denominazione", denominazione);
            classe.put("codiceAula", codiceAula);
    
            // Aggiunge il nuovo oggetto all'array
            classJsonArray.add(classe);
    
            JsonAPI.updateJsonFile(classJsonArray);
        }

        return isValidInsert ? "Inserimento completato!" : "ERRORE: Denominazione classe già esistente!";
    }

    public void delete() {
        
    }
}