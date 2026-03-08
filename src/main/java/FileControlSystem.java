import fileControlSystem.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileControlSystem
{
    private final List<Document> documents = new ArrayList<>();
    private final List<Document> viewableDocuments = Collections.unmodifiableList(documents);
    private final Map<String, Importer> extensionToImporter = new HashMap<>();

    public FileControlSystem()
    {
        extensionToImporter.put("letter", new LetterImporter());
        extensionToImporter.put("jpg", new ImageImporter());
        extensionToImporter.put("report", new ReportImporter());
    }
    {
        extensionToImporter.put("invoice", new InvoiceImporter());
    }

    public void importFile(String path) throws IOException
    {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException(path);
        }

        final int separatorIndex = path.lastIndexOf('.');
        if(separatorIndex != -1) {
            if(separatorIndex == path.length()) {
                throw new UnknownFileTypeException("No extension find for file: " + path);
            }
            final String extension = path.substring(separatorIndex + 1);
            final Importer importer = extensionToImporter.get(extension);
            if(importer == null) {
                throw new UnknownFileTypeException("No extension find for file: " + path);
            }

            final Document document = importer.importFile(file);
            documents.add(document);
        }
        else {
            throw new UnknownFileTypeException("No extension find for file: " + path);
        }
    }



}
