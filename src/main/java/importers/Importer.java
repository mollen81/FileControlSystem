package importers;

import java.io.File;
import java.io.IOException;
import domain_objects.Document;

public interface Importer
{
    Document importFile(File file) throws IOException;
}
