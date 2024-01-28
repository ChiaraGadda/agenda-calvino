import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Agenda {
    Map<String,JsonAPI> InstancesJsonAPI = new HashMap<>();

    public Agenda() {
        InstancesJsonAPI.put("classes", new JsonAPI("classes", "denominazione"));
        InstancesJsonAPI.put("students", new JsonAPI("students", "codiceFisc"));
    }

    public String insclasse(String denominazione, String codiceAula) {
        JsonAPI jsonAPI = (JsonAPI) InstancesJsonAPI.get("classes");
        Classe ClasseToInsert = new Classe(denominazione, codiceAula, jsonAPI);
        return ClasseToInsert.insClasse();
    }

    public String listclasse() {
        JsonAPI jsonAPI = (JsonAPI) InstancesJsonAPI.get("classes");
        return jsonAPI.getData().toString();
    }

    public String delclasse(String denominazione) {
        JsonAPI studenteJsonAPI = (JsonAPI) InstancesJsonAPI.get("students");
        JSONArray studenti = (JSONArray) studenteJsonAPI.getData();

        // Classe ClasseToDelete;
        Boolean isValidCancellation = true;
        Boolean isPresentInClasses = false;
        for (int i = 0; i < studenti.size(); i++) {
            JSONObject objStudent = (JSONObject) studenti.get(i);
            String currentDenominazione = (String) objStudent.get("classeFreq");
            if (currentDenominazione.equals(denominazione)) {
                isValidCancellation = false;
                break;
            }
        }

        if (isValidCancellation) {
            JsonAPI classesJsonAPI = (JsonAPI) InstancesJsonAPI.get("classes");
            JSONArray classi = (JSONArray) classesJsonAPI.getData();
            JSONArray newClassi = new JSONArray();
            for (int i = 0; i < classi.size(); i++) {
                JSONObject objClass = (JSONObject) classi.get(i);
                String currentDenominazione = (String) objClass.get("denominazione");
                if (currentDenominazione.equals(denominazione)) {
                    isPresentInClasses = true;
                } else {
                    newClassi.add(objClass);
                }
            }
            if (isPresentInClasses) {
                classesJsonAPI.updateJsonFile(newClassi);
                return "Cancellazione riuscita!";
            } else {
                return "Classe non presente!";
            }
        }

        return "Cancellazione fallita... Studente presente che afferisce alla classe!";
    }

    public String zap() {
        for (Map.Entry<String, JsonAPI> entry : InstancesJsonAPI.entrySet()) {
            JsonAPI instanceJsonAPI = entry.getValue();
            instanceJsonAPI.clear();
        }

        return "Eliminazione DB completato";
    }
}
