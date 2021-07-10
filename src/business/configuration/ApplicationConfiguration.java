package business.configuration;

import util.InfraException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationConfiguration {

    private static final String CONFIG_PATH = "config/app.config";

    private static Properties properties = new Properties();
    private static String pdfPersistence = null;

    /**
     * Deve ser chamado quando a aplicação iniciar para
     * carregar as configurações.
     * @throws IOException
     */
    public static void load() throws InfraException {
        try{
            FileInputStream stream = new FileInputStream(CONFIG_PATH);
            properties.load(stream);
            pdfPersistence = properties.getProperty("pdf.persistence");
            stream.close();
        }
        catch (IOException e){
            throw new InfraException();
        }
    }

    public static String getPdfPersistence(){
        return pdfPersistence;
    }
}
