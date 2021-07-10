package infra;

import util.InfraException;

public interface IReportPersistence {

    void saveFile(String data, String file) throws InfraException;
}
