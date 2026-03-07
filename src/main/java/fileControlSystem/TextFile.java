package fileControlSystem;

import java.io.File;
import java.util.List;
import java.util.Map;

import static fileControlSystem.attributes.Attributes.NAME_PREFIX;

class TextFile
{
    private final Map<String, String> attributes;
    private final List<String> lines;

    public TextFile(File file)
    {
        this.lines = file.toString().lines().toList();
        addLineSuffix(NAME_PREFIX, "Уважаемый");
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
}
