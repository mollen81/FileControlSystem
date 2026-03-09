import fileControlSystem.Attributes;
import fileControlSystem.Document;
import fileControlSystem.FileControlSystem;
import org.junit.jupiter.api.Test;


import static fileControlSystem.Attributes.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;


public class FileControlSystemTest {
    private final String RESOURCES = "src" + File.separator +
            "test" + File.separator + "resources" + File.separator;
    private final String LETTER = RESOURCES + "patient.letter";
    private final String INVOICE = RESOURCES + "patient.invoice";
    private final String REPORT = RESOURCES + "patient.report";
    private final String IMAGE = RESOURCES + "xray.jpg";

    private final String JOE_BLOGGS = "Joe Bloggs";

    private FileControlSystem system = new FileControlSystem();

    @Test
    public void shouldImportLetterAttribute() throws Exception {
        system.importFile(LETTER);

        final Document document = onlyDocument();

        assertAttributeEquals(document, Attributes.PATH, LETTER);
        assertAttributeEquals(document, ADDRESS,
                "123 Fake Street\n" +
                        "Westminster\n" +
                        "London\n" +
                        "United Kingdom");
        assertAttributeEquals(document, BODY,
                "We are writing to you to confirm the re-scheduling of your appointment\n" +
                "with Dr. Avaj from 29th December 2016 to 5th January 2017.\n");
        assertTypeIs("LETTER", document);
    }

    private void assertAttributeEquals(
            final Document document,
            final String attributeName,
            final String expectedValue
    )
    {
        assertEquals(
                "Document has the wrong value for " + attributeName,
                expectedValue,
                document.getAttribute(attributeName));
    }

    private void assertTypeIs(String fileType, Document document) {
        assertAttributeEquals(document, TYPE, fileType);
    }

    private Document onlyDocument() {
        final List<Document> documents = system.getDocuments();
        assertThat(documents, hasSize(1));
        return documents.getFirst();
    }
}
