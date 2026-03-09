package fileControlSystem;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static fileControlSystem.Attributes.*;

public class InvoiceImporter implements Importer
{
    private final static String NAME_PREFIX = "Dear ";
    private final static String AMOUNT_PREFIX = "Amount: ";

    @Override
    public Document importFile(File file) throws IOException
    {
        TextFile textFile = new TextFile(file);
        textFile.addLineSuffix(NAME_PREFIX, NAME);
        textFile.addLineSuffix(AMOUNT_PREFIX, AMOUNT);

        textFile.addLines(2, (line) -> line.startsWith("regards"), BODY);
        Map<String, String> attributes = new java.util.HashMap<>(textFile.getAttributes());
        attributes.put(TYPE, INVOICE);

        return new Document(attributes);
    }
}
