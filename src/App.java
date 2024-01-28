public class App {
    public static void main(String[] args) throws Exception {
        JsonQuery json=new JsonQuery("classes");
        System.out.println(json.getJsonFile());
    }
}
