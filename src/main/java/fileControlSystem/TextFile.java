package fileControlSystem;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static fileControlSystem.Attributes.PATH;

class TextFile
{
    private final Map<String, String> attributes;
    private final List<String> lines;

    public TextFile(File file)
    {
        this.attributes = new HashMap<>();
        attributes.put(PATH, file.getPath());
        this.lines = file.toString().lines().toList();
    }

    void addLineSuffix(final String prefix, final String attributeName)
    {
        for(final String line : lines)
        {
            if(line.startsWith(prefix))
            {
                attributes.put(attributeName, line.substring(prefix.length()));
                break;
            }
        }
    }

    void addLines(final int start, Predicate<String> isEnd, final String attributeName) {
        final StringBuilder sb = new StringBuilder();
        int lineNumber;

        for(lineNumber = start; lineNumber < lines.size(); lineNumber++) {
            final String line = lines.get(lineNumber);
            if(isEnd.test(line)) {
                break;
            }

            sb.append(line);
            sb.append("\n");
        }

        attributes.put(attributeName, sb.toString().trim());
    }

    Map<String, String> getAttributes() {
        return this.attributes;
    }
}
