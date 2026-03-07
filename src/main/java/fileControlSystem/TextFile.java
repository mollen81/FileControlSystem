package fileControlSystem;

import java.io.File;
import java.util.List;
import java.util.Map;

class TextFile
{
    private final Map<String, String> attributes = Map.of();
    private final List<String> lines;

    public TextFile(File file)
    {
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

    Map<String, String> getAttributes() {
        return this.attributes;
    }
}
