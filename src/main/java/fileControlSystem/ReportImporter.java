package fileControlSystem;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static fileControlSystem.Attributes.*;

public class ReportImporter implements Importer
{
    static final String NAME_PREFIX = "Patient: ";

    @Override
    public Document importFile(File file) throws IOException
    {
        TextFile textFile = new TextFile(file);
        textFile.addLineSuffix(NAME_PREFIX, NAME);

        textFile.addLines(2, String::isEmpty, BODY);

        final Map<String, String> attributes = new java.util.HashMap<>(textFile.getAttributes());
        attributes.put(TYPE, REPORT);

        return new Document(attributes);
    }
}
