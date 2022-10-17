import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class CurrencyConverter {
    private double inputMoney;
    private double outputMoney;

    String endpoint;


    //http request and properties
    String configFilePath = "src/api.properties";
    FileInputStream propertiesFile= new FileInputStream(configFilePath);
    Properties prop = new Properties();


    public CurrencyConverter(double inputMoney, int choosedMethod) throws IOException {
        this.inputMoney = inputMoney;
        this.outputMoney = outputMoney;
        prop.load(propertiesFile);
        this.chooseEndpoint(choosedMethod);


    }

    public String formRequest(){
        return prop.getProperty("api_protocol") + "://" + prop.getProperty("api_host") + ":" + prop.getProperty("api_port") + prop.getProperty("api_basePath") + this.endpoint;

    }
    public void chooseEndpoint(int choose)
    {

        switch(choose){

            case 1:
                this.endpoint = "currency_data/list";
                break;

            case 2:
                this.endpoint = "currency_data/convert";
                break;

            default:
                 System.out.println("There is no implementation for choosen method.");
                 this.endpoint = "";
                 break;

        }
    }

    public void createRequest() throws IOException {
        URL url = new URL(this.formRequest());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("apikey", prop.getProperty("api_key"));
        System.out.println(con);
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }








}
