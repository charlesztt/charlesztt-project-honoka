package com.prinzkarl.honoka.mail;

import com.prinzkarl.honoka.io.JsonFile;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by charlesztt on 2/28/16.
 */
public class ObtainCredentials {
    /**
     * Put a json file in data fold with
     * {
     *   "mail_module":{
     *     "account": "example@sample.com",
     *     "password": "your_password"
     *   }
     * }
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static String[] obtainCredentials() throws ParseException, IOException {
        String[] result = new String[2];
        JsonFile credentialFile = new JsonFile("./data/credentials.json");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(credentialFile.getJsonFileContent());
        JSONObject jsonObject = (JSONObject)obj;
        JSONObject mailObject = (JSONObject)jsonObject.get("mail_module");
        result[0] = mailObject.get("account").toString();
        result[1] = mailObject.get("password").toString();
        return result;
    }
}
