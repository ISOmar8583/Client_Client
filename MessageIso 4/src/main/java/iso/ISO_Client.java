package iso;
import java.io.FileInputStream;
import org.jpos.iso.*;
import org.jpos.iso.channel.NACChannel;
import org.jpos.iso.packager.GenericPackager;

public class ISO_Client {
    GenericPackager packager; //Attributs de la classe ISO_Client
    ISOMsg isoMsg;
    NACChannel channel;
    public  ISO_Client() throws Exception { //Constructeur ...Initialiser les paramètres et créer configuration channel/packager
        try {
            this.packager = new GenericPackager(new FileInputStream("/Users/mohammedmohsinabbas/Downloads/test-packager.xml")); //initialiser attribut class
            this.channel = new NACChannel("localhost", 9009, packager, null);
            channel.connect();
            this.isoMsg = new ISOMsg();
        } catch (ISOException e) {
            throw new Exception(e);
        }
    }
    public String printSendMsg(ISOMsg isoMsg, NACChannel channel) throws Exception{ // envoie message et récupère la réponse du serveur + print
        try {
            printISOMessage(isoMsg);
            channel.send(isoMsg);
            ISOMsg response=channel.receive();
            printISOMessage(response);
            byte[] result = isoMsg.pack();
            System.out.println(ISOUtil.hexdump(result));
            System.out.println(result);
            channel.disconnect();
            return new String(result);
        } catch (ISOException e) {
            throw new Exception(e);
        }
    }

    public void achatTPE(GenericPackager packager,ISOMsg MsgAchatTPE) throws Exception{  // construction message achat TPE
        try{
            MsgAchatTPE.setPackager(packager);
            MsgAchatTPE.setMTI("0100");
            MsgAchatTPE.set(3, "000000");
            MsgAchatTPE.set(4, "000000001000");
            MsgAchatTPE.set(7, "0606155007");
            MsgAchatTPE.set(11, "006156");
            MsgAchatTPE.set(12, "220606");
            MsgAchatTPE.set(13, "0606");
            MsgAchatTPE.set(14, "3012");
            MsgAchatTPE.set(18, "5999");
            MsgAchatTPE.set(22, "000");
            MsgAchatTPE.set(23, "000");
            MsgAchatTPE.set(25, "00");
            MsgAchatTPE.set(26, "53");
            MsgAchatTPE.set(28, "000000000");
            MsgAchatTPE.set(32, "27610000001");
            MsgAchatTPE.set(33, "27610000003");
            MsgAchatTPE.set(35, "9876500000306082=30121011123123000");
            MsgAchatTPE.set(37, "50074123");
            MsgAchatTPE.set(41, "20390059");
            MsgAchatTPE.set(42, "111120000012");
            MsgAchatTPE.set(43, "contact@neaPay.com\\Almere-Amsterdam\\Neth");
            MsgAchatTPE.set(49, "566");
            MsgAchatTPE.set(51, "566");
            MsgAchatTPE.set(52, "FEE8CA6A604F09F0");
        } catch (ISOException e) {
            throw new Exception(e);
        }
    }


    public void achatEcommerce(GenericPackager packager,ISOMsg MsgAchatEcommerce) throws Exception{  // construction message achat Ecommerce
        try{
            MsgAchatEcommerce.setPackager(packager);
            MsgAchatEcommerce.setMTI("0100");
            MsgAchatEcommerce.set(3, "000000");
            MsgAchatEcommerce.set(4, "000000001000");
            MsgAchatEcommerce.set(7, "0606155007");
            MsgAchatEcommerce.set(11, "006156");
            MsgAchatEcommerce.set(12, "220606");
            MsgAchatEcommerce.set(13, "0606");
            MsgAchatEcommerce.set(14, "3012");
            MsgAchatEcommerce.set(18, "5999");
            MsgAchatEcommerce.set(22, "000");
            MsgAchatEcommerce.set(25, "00");
            MsgAchatEcommerce.set(26, "53");
            MsgAchatEcommerce.set(28, "000000000");
            MsgAchatEcommerce.set(32, "27610000001");
            MsgAchatEcommerce.set(33, "27610000003");
            MsgAchatEcommerce.set(35, "9876500000306082=30121011123123000");
            MsgAchatEcommerce.set(37, "50074123");
            MsgAchatEcommerce.set(41, "20390059");
            MsgAchatEcommerce.set(42, "111120000012");
            MsgAchatEcommerce.set(43, "contact@neaPay.com\\Almere-Amsterdam\\Neth");
            MsgAchatEcommerce.set(49, "566");
            MsgAchatEcommerce.set(51, "566");
            MsgAchatEcommerce.set(52, "FEE8CA6A604F09F0");
        } catch (ISOException e) {
            throw new Exception(e);
        }
    }

    public void NetworkManagement(GenericPackager packager,ISOMsg MsgNetworkManagement) throws Exception{  // construction message Network Management
        try{
            MsgNetworkManagement.setMTI("0800");
            MsgNetworkManagement.set(3, "000010");
            MsgNetworkManagement.set(11, "1500");
            MsgNetworkManagement.set(41, "12060412");
            MsgNetworkManagement.set(70, "001");

        } catch (ISOException e) {
            throw new Exception(e);
        }
    }



    private void printISOMessage(ISOMsg isoMsg) {
        try {
            System.out.printf("MTI = %s%n", isoMsg.getMTI());
            for (int i = 1; i <= isoMsg.getMaxField(); i++) {
                if (isoMsg.hasField(i)) {
                    System.out.printf("Field (%s) = %s%n", i, isoMsg.getString(i));
                }
            }
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }
}
