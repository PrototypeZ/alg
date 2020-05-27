package io.michaelrocks.paranoid;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static void main(String[] args) throws Throwable {

        Map<String, String> dic = new HashMap<>();

        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(Parser.class.getResourceAsStream("/main/resources/public.xml"));
        Element root = doc.getDocumentElement();
        NodeList items = root.getChildNodes();
        for (int i = 0; i < items.getLength(); i++) {
            Node item = items.item(i);
            NamedNodeMap attrs = item.getAttributes();
            if (attrs != null) {
                String type = item.getAttributes().getNamedItem("type").getNodeValue();
                String name = item.getAttributes().getNamedItem("name").getNodeValue();
                long id = Long.decode(item.getAttributes().getNamedItem("id").getNodeValue());
                dic.put(String.valueOf(id), "R." + type + "." + name);
                System.out.println("id:" + id + ", value:" + "R." + type + "." + name);
            }
        }

        String srcFolderPath = "/home/jason/localProj/pandemic/app/src/main/java";
        transformJavaFile(dic, new File(srcFolderPath));
    }

    private static void transformJavaFile(Map<String, String> dic, File folder) throws FileNotFoundException {
        File[] files = folder.listFiles();
        for (int i = 0; files != null && i < files.length; i++) {
            File f = files[i];
            if (f.isDirectory()) {
                transformJavaFile(dic, f);
            } else {
                System.out.println("reading :" + f);
                // read
                java.util.Scanner s = new java.util.Scanner(new FileInputStream(f)).useDelimiter("\\A");
                String text =  s.hasNext() ? s.next() : "";

                StringBuffer sb = new StringBuffer(); //use StringBuffer before Java 9
                Pattern pattern = Pattern.compile("(2[0-9]{9})");
                Matcher m = pattern.matcher(text);

                while (m.find()) {
                    System.out.println(m);
                    String matchedString = m.group(1);
                    System.out.println("matchedString:" + matchedString);
                    String value = dic.get(matchedString);
                    if (value != null) {
                        m.appendReplacement(sb, value);
                    }

                    // Avoids throwing a NullPointerException in the case that you
                    // Don't have a replacement defined in the map for the match
//                    String repString = replacements.get(m.group(1));
//                    if (repString != null)
//                        m.appendReplacement(sb, repString);
                }
                m.appendTail(sb);

//                System.out.println(sb.toString());

//                 write back
                try (PrintWriter p = new PrintWriter(new FileOutputStream(f))) {
                    p.println(sb.toString());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
