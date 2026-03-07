package fileControlSystem.importers;

import fileControlSystem.attributes.Attributes;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReportImporter implements Importer
{
    @Override
    public Document importFile(File file) throws IOException
    {
        final Map<String, String> attributes = new HashMap<>();
        attributes.put(Attributes.PATH, file.getPath());

        return new Document(attributes);
    }
}
