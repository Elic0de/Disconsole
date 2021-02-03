package plugin.midorin.info.dc.util;

import java.io.*;
import java.util.ArrayList;

public class Logs
{
    public static ArrayList<String> getLogList()
    {
        File file = new File("/logs/latest.log");
        ArrayList<String> result = new ArrayList<>();
        try (FileReader f = new FileReader(file))
        {
            StringBuffer sb = new StringBuffer();
            while (f.ready())
            {
                char c = (char) f.read();
                if (c == '\n')
                {
                    result.add(sb.toString());
                    sb = new StringBuffer();
                }
                else sb.append(c);
            }
            if (sb.length() > 0) result.add(sb.toString());
        }
        catch (IOException e) { e.printStackTrace(); }
        return result;
    }
}
