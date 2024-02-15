import java.util.Scanner;
//comandi da fare: 
// delstudente [<codiceFiscale>]
// modclasse [<NomeClasse  >] [ <codiceAula> ]
// modstudente [<codiceFiscale>] [<Cognome>,<Nome>,<classeFreq>]
// liststudente all [<NomeClasse >]
public class App {
    public static void main(String[] args) throws Exception {
        helper();

        String shellLine = null;
        Agenda Agenda = new Agenda();

        while (true) {
            System.out.println("Inserisci un comando...");
            Scanner input = new Scanner(System.in);
            shellLine = input.nextLine();
            String[] tokens = shellLine.split("\\s+"); //significa uno o più spazi-per approf. ved regex: espressioni regolari

            String shellCommand = tokens[0];
            String[] shellArgs = (tokens.length > 1) ? tokens[1].split(",") : null;

            switch (shellCommand) {
                case "insclasse":
                    if (shellArgs != null && shellArgs.length == 2) {
                        String message = Agenda.insclasse(shellArgs[0], shellArgs[1]);
                        System.out.println(message);
                    } else {
                        System.out.println("Formato del comando non corretto. Utilizzo corretto: insclasse <NomeClasse>,<codiceAula>");
                    }
                    break;

                case "listclasse":
                    String classesList = Agenda.listclasse();
                    System.out.println(classesList);
                    break;
                
                case "delclasse":
                    if (shellArgs != null && shellArgs.length == 1) {
                        String message = Agenda.delclasse(shellArgs[0]);
                        System.out.println(message);
                    } else {
                        System.out.println("Formato del comando non corretto. Utilizzo corretto: delclasse <NomeClasse>");
                    }
                    break;

                case "liststudenti":
                    String studentsList = Agenda.liststudenti();
                    System.out.println(studentsList);
                    break;

                case "insstudente":
                    if (shellArgs != null && shellArgs.length == 4) {
                        String message = Agenda.insstudente(shellArgs[0], shellArgs[1], shellArgs[2], shellArgs[3]);
                        System.out.println(message);
                    } else {
                        System.out.println("Formato del comando non corretto. Utilizzo corretto: insstudente <Nome> ecc");
                    }
                    break;
 
                case "zap":
                    String message = Agenda.zap();
                    System.out.println(message);
                    break;

                case "esci":
                case "exit":
                    System.out.println("Chiusura shell...");
                    System.exit(0);
                    break;

                default:
                    // Utilizzo lo stato 1 per un'uscita anomala
                    System.out.println("comando non valido");
                    //System.exit(1);
                    break;
            }

        }

    }

    private static void helper() {
        System.out.println("Questa è una shell per gestire una piccola agenda del calvino.");
        System.out.println("Puoi utilizzare i seguenti comandi:");
        System.out.println("- insclasse [<NomeClasse>,<codiceAula>]");
        System.out.println("- delclasse [<NomeClasse>]");
        System.out.println("- listclasse");
        System.out.println("- insstudente [<Cognome>,<Nome>,<codiceFiscale>,<classeFreq>]");
        System.out.println("- liststudenti");
        System.out.println("- zap");
        System.out.println("esempio: insclasse 2bii,A3");
    }
}
