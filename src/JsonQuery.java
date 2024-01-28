import java.io.FileReader;
import java.nio.file.Paths;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
//import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;

public class JsonQuery {
    String nomeFile;

    public JsonQuery(String nome){
        this.nomeFile=nome;
    }

    private String percorso(){
        String absPath=Paths.get("").toAbsolutePath().toString();
        return absPath+"../database/"+nomeFile+".json";
    }
    public Object getJsonFile(){
        JSONParser jsonParser = new JSONParser(); 
        FileReader file = new FileReader(percorso());
        return jsonParser.parse(file);
    }
}