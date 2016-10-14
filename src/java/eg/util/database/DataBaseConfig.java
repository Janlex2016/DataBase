package eg.util.database;

import eg.models.ConfigData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DataBaseConfig {

    private static final String path = "src/resources/ConnectionConfig.xml";
    private ConfigData configData;

    public DataBaseConfig() {
        parse();
    }

    private void parse() {

        try {

            File fXmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("config");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Element eElement = (Element) nList.item(temp);

                configData = new ConfigData(
                        eElement.getElementsByTagName("URL").item(0).getTextContent(),
                        eElement.getElementsByTagName("login").item(0).getTextContent(),
                        eElement.getElementsByTagName("password").item(0).getTextContent()
                );

//                System.out.println("URL : " + eElement.getElementsByTagName("URL").item(0).getTextContent());
//                System.out.println("login : " + eElement.getElementsByTagName("login").item(0).getTextContent());
//                System.out.println("password : " + eElement.getElementsByTagName("password").item(0).getTextContent());
            }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConfigData getConfigData() {
        return configData;
    }

}
