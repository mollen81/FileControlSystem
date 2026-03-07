import fileControlSystem.importers.Document;
import fileControlSystem.importers.ImageImporter;
import fileControlSystem.importers.Importer;
import fileControlSystem.importers.LetterImporter;
import fileControlSystem.importers.ReportImporter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileControlSystem
{
    private final Map<String, Importer> extensionToImporter = new HashMap<>();

    public FileControlSystem()
    {
        extensionToImporter.put("letter", new LetterImporter());
        extensionToImporter.put("jpg", new ImageImporter());
        extensionToImporter.put("report", new ReportImporter());
    }

    public void importFile(String path){}

    public List<Document> getDocuments(){}


}
