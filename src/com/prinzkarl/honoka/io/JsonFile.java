package com.prinzkarl.honoka.io;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Created by charlesztt on 2/28/16.
 */
public class JsonFile {
    private String jsonFileContent;

    public JsonFile (String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        this.jsonFileContent = new String(encoded, StandardCharsets.UTF_8);
    }

    public String getJsonFileContent()
    {
        return this.jsonFileContent;
    }

    //Test Purpose
    public static void main(String[] args) throws IOException, ParseException {
        JsonFile self = new JsonFile("./data/credentials.json");
        System.out.println(self.jsonFileContent);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(self.jsonFileContent);
        JSONObject jsonObject = (JSONObject)obj;
        JSONObject mailObject = (JSONObject)jsonObject.get("mail_module");
        System.out.println(mailObject.get("account"));
        System.out.println(mailObject.get("password"));

    }
}
