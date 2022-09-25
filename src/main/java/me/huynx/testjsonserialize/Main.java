/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package me.huynx.testjsonserialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author huynx
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger("ROOT");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        NapasDocumentHeader doc = createDocument();
        String jsonContent = serializeToJson(doc);
        writeFile(jsonContent);
    }
    
    public static NapasDocumentHeader createDocument() {
        NapasDocumentHeader napasDocumentHeader = new NapasDocumentHeader();

        napasDocumentHeader.setMessageIdentifier("camt.052.001.07");
        napasDocumentHeader.setSenderReference("R531206730/941");
        napasDocumentHeader.setFormat("MX");
        
        String signatureEscaped = StringEscapeUtils.escapeJson("aggsg/9iigsg");
        napasDocumentHeader.setSignature(signatureEscaped);
        
        logger.info(signatureEscaped);

        return napasDocumentHeader;
    }
    
    public static String serializeToJson(NapasDocumentHeader napasDocument) {
        String rawJson = null;

        ObjectMapper mapperJson = new ObjectMapper();
        mapperJson.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try {
            rawJson = mapperJson.writeValueAsString(napasDocument);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            logger.error("Loi tao json:{}", ex.getMessage());
        }

        return rawJson;
    }
    
    public static void writeFile(String content) {
        String fileName = "test.json";
        String localPath = "./export/" + fileName;

        try {
            Files.deleteIfExists(Paths.get(localPath));

            Path path = Paths.get(localPath);
            byte[] strToBytes = content.getBytes();

            Files.write(path, strToBytes);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static String escapeJson(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int chx = (int) ch;
            // let's not put any nulls in our strings
            assert (chx != 0);

            if (ch == '\n') {
                output.append("\\n");
            } else if (ch == '\t') {
                output.append("\\t");
            } else if (ch == '\r') {
                output.append("\\r");
            } else if (ch == '\\') {
                output.append("\\\\");
            } else if (ch == '"') {
                output.append("\\\"");
            } else if (ch == '\b') {
                output.append("\\b");
            } else if (ch == '\f') {
                output.append("\\f");
            } else if (chx >= 0x10000) {
                assert false : "Java stores as u16, so it should never give us a character that's bigger than 2 bytes. It literally can't.";
            } else if (chx > 127) {
                output.append(String.format("\\u%04x", chx));
            } else {
                output.append(ch);
            }
        }
        return output.toString();
    }

}
