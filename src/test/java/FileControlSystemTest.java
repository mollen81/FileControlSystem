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
    private final String RECEIPT = RESOURCES + "patient.receipt";
    private final String IMAGE = RESOURCES + "xray.jpg";

    private final String JOE_BLOGGS = "Joe Bloggs";

    private FileControlSystem system = new FileControlSystem();

    @Test
    public void shouldImportFile() throws Exception {
        system.importFile(LETTER);

        final Document document = onlyDocument();

        assertAttributeEquals(document, PATH, LETTER);
    }

    @Test
    public void shouldImportLetterAttribute() throws Exception {
        system.importFile(LETTER);

        final Document document = onlyDocument();

        assertAttributeEquals(document, NAME, JOE_BLOGGS);
        assertAttributeEquals(document, ADDRESS,
                "123 Fake Street\n" +
                        "Westminster\n" +
                        "London\n" +
                        "United Kingdom");
        assertAttributeEquals(document, BODY,
                "We are writing to you to confirm the re-scheduling of your appointment\n" +
                "with Dr. Avaj from 29th December 2016 to 5th January 2017.");
        assertTypeIs(Attributes.LETTER, document);
    }

    @Test
    public void shouldImportInvoiceAttribute() throws Exception {
        system.importFile(INVOICE);

        final Document document = onlyDocument();

        assertAttributeEquals(document, Attributes.PATH, INVOICE);
        assertAttributeEquals(document, NAME, JOE_BLOGGS);
        assertAttributeEquals(document, BODY,
                "Here is your invoice for the dental treatment that you received.");
        assertAttributeEquals(document, AMOUNT, "$100");
        assertTypeIs(Attributes.INVOICE, document);
    }

    @Test
    public void shouldImportReportAttribute() throws Exception {
        system.importFile(REPORT);

        final Document document = onlyDocument();

        assertAttributeEquals(document, Attributes.PATH, REPORT);
        assertAttributeEquals(document, NAME, JOE_BLOGGS);
        assertAttributeEquals(document, BODY,
                "On 5th January 2017 I examined Joe's teeth.\n" +
                        "We discussed his switch from drinking Coke to Diet Coke.\n" +
                        "No new problems were noted with his teeth.");
        assertTypeIs(Attributes.REPORT, document);
    }

    @Test
    public void shouldImportReceiptAttribute() throws Exception {
        system.importFile(RECEIPT);

        final Document document = onlyDocument();

        assertAttributeEquals(document, Attributes.PATH, RECEIPT);
        assertAttributeEquals(document, NAME, JOE_BLOGGS);
        assertAttributeEquals(document, Attributes.ADDRESS,
                "123 Fake Street\n" +
                        "Westminster\n" +
                        "London\n" +
                        "United Kingdom");
        assertAttributeEquals(document, Attributes.MEDICINAL_PRODUCT, "antibiotic");
        assertAttributeEquals(document, Attributes.DOSAGE_AND_SCHEDULE,
                "Dosage and schedule: take as prescribed: follow the exact dosage and frequency instructed by your doctor. Do not take more or less than the specified amount.\n" +
        "Maintain consistency: To ensure a steady level of the medication in your bloodstream, take the antibiotic at regular intervals throughout the day.\n" +
        "Timing: If instructed to take the medicine multiple times daily, spread the doses out evenly (e.g., every 8 or 12 hours).\n" +
        "Administration Conditions: With or without food: Some antibiotics should be taken on an empty stomach (typically 1 hour before or 2 hours after a meal) for optimal absorption,\n" +
        "while others are best taken with food to reduce potential stomach upset.\n" +
        "Hydration: Take the medication with a full glass of water.\n" +
        "Avoid taking it with dairy products, certain juices, or alcohol, as these can interfere with the drug's effectivenessHydration: Take the medication with a full glass of water.\n" +
        "Avoid taking it with dairy products, certain juices, or alcohol, as these can interfere with the drug's effectiveness.");
        assertTypeIs(Attributes.RECEIPT, document);
    }

    @Test
    public void shouldImportImageAttribute() throws Exception {
        system.importFile(IMAGE);

        final Document document = onlyDocument();

        assertAttributeEquals(document, Attributes.PATH, IMAGE);
        assertAttributeEquals(document, HEIGHT, document.getAttribute("height"));
        assertAttributeEquals(document, WIDTH, document.getAttribute("width"));

        assertTypeIs(Attributes.IMAGE, document);
    }


    private void assertAttributeEquals(
            final Document document,
            final String attributeName,
            final String expectedValue
    )
    {
        assertEquals(
                expectedValue,
                document.getAttribute(attributeName),
                "Document has the wrong value for " + attributeName);
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
