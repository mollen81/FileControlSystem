package fileControlSystem;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static fileControlSystem.Attributes.*;

public class LetterImporter implements Importer
{
    static final String NAME_PREFIX = "Dear ";

    @Override
    public Document importFile(File file) throws IOException
    {
        TextFile textFile = new TextFile(file);
        textFile.addLineSuffix(NAME_PREFIX, NAME);

        int lineNumber = textFile.addLines(2, String::isEmpty, ADDRESS);
        textFile.addLines(lineNumber + 1, (line) -> line.startsWith("regards,"), BODY);

        final Map<String, String> attributes = new java.util.HashMap<>(textFile.getAttributes());
        attributes.put(TYPE, LETTER);

        return new Document(attributes);
    }
}
