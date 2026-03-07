package fileControlSystem;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static fileControlSystem.Attributes.*;

public class LetterImporter implements Importer
{
    static final String NAME_PREFIX = "Уважаемый ";

    @Override
    public Document importFile(File file) throws IOException
    {
        TextFile textFile = new TextFile(file);
        textFile.addLineSuffix(NAME_PREFIX, NAME);

        final Map<String, String> attributes = new  java.util.HashMap<>(textFile.getAttributes());
        attributes.put(TYPE, LETTER);

        return new Document(attributes);
    }
}
