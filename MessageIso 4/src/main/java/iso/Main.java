package iso;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        ISO_Client iso = new ISO_Client();  //creation objet de type iso_client ( config aussi)

        System.out.println("----|Veuillez choisir la transaction à simuler |---- \n ");
        System.out.println("**** 1 : Achat TPE **** \n ");
        System.out.println("**** 2 : Achat Ecommerce **** \n ");
        System.out.println("**** 3 : Network Management **** \n ");
        System.out.println("------------------------------------------------------- \n \n");
        BufferedReader reader = new BufferedReader(      //prend input user
                new InputStreamReader(System.in));
        String transactionType = reader.readLine();
        switch (transactionType){
            case "1": iso.achatTPE(iso.packager, iso.isoMsg);
                break;
            case "2": iso.achatEcommerce(iso.packager, iso.isoMsg);
                break;
            case "3":iso.NetworkManagement(iso.packager, iso.isoMsg);
                break;
            default: System.exit(-1);           //Exit in case there's no value in input
        }
        iso.printSendMsg(iso.isoMsg, iso.channel);
    }
}