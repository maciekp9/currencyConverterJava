import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");



        try {
            CurrencyConverter moneyCurrencyConverter = new CurrencyConverter(150.00, 1);
            System.out.println(moneyCurrencyConverter.formRequest());
            moneyCurrencyConverter.createRequest();
        } catch (IOException error){
            error.printStackTrace();
        }


    }
}