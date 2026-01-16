package importers;

import domain_objects.Document;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static attributes.Attributes.*;

public class LetterImporter implements Importer
{

    @Override
    public Document importFile(File file) throws IOException
    {
        final Map<String, String> attributes = new HashMap<>();
        attributes.put(PATH, file.getPath());

        return new Document(attributes);
    }


}
