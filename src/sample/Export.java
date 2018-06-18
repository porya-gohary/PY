package sample;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.stream.Collectors;

public class Export {


    public void Export(Controller c) {

        try {

            FileOutputStream fout = new FileOutputStream(c.StrorageFolder+"Export.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fout);

            oos.writeUTF("\n>> Depth Search: \n");

           // oos.writeChars(c.listView1.getItems().toString());
            List<String> numbers = c.listView1.getItems();
            String listString = String.join("\n", numbers);

            oos.writeBytes(listString);


            oos.writeUTF("\n___________________***********************________________________\n");
            oos.writeUTF("\n>> Files Link: \n");
            List<String> numbers2 = c.listView2.getItems();
            String listString2 = String.join("\n", numbers2);
            oos.writeUTF(listString2);

            oos.writeUTF("\n___________________***********************________________________\n");
            oos.writeUTF("\n>> First Page Links: \n");
            List<String> numbers3 = c.listView3.getItems();
            String listString3 = String.join("\n", numbers3);
            oos.writeUTF(listString3);

            oos.close();
            //System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
