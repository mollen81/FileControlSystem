package fileControlSystem;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static fileControlSystem.Attributes.*;

public class ReceiptImporter implements Importer
{
    static final String PATIENT_PREFIX = "Patient: ";

    @Override
    public Document importFile(File file) throws IOException {
        TextFile textFile = new TextFile(file);
        textFile.addLineSuffix(PATIENT_PREFIX, NAME);

        int lineNumber = textFile.addLines(2, String::isEmpty, ADDRESS);
        lineNumber = textFile.addLines(lineNumber + 1, String::isEmpty, MEDICINAL_PRODUCT);
        textFile.addLines(lineNumber + 1, String::isEmpty, DOSAGE_AND_SCHEDULE);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, RECEIPT);

        return new Document(attributes);
    }
}
