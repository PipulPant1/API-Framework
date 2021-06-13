/*
package frameworkUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minidev.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;

public class AmazonOauth2 {

    */
/**
     * To obtain an access token, make an HTTPS request to Amazon
     * and include your client_id and client_secret values.
     *//*

    public String getAuthToken(String clientId, String clientSecret) throws Exception
    {
        // Encode the body of your request, including your clientID and clientSecret values.
        String body = "grant_type="    + URLEncoder.encode("client_credentials", "UTF-8") + "&" +
                "scope="         + URLEncoder.encode("messaging:push", "UTF-8")     + "&" +
                "client_id="     + URLEncoder.encode(clientId, "UTF-8")             + "&" +
                "client_secret=" + URLEncoder.encode(clientSecret, "UTF-8");

        // Create a new URL object with the base URL for the access token request.
        URL authUrl = new URL("https://api.amazon.com/auth/O2/token");

        // Generate the HTTPS connection. You cannot make a connection over HTTP.
        HttpsURLConnection con = (HttpsURLConnection) authUrl.openConnection();
        con.setDoOutput( true );
        con.setRequestMethod( "POST" );

        // Set the Content-Type header.
        con.setRequestProperty( "Content-Type" , "application/x-www-form-urlencoded" );
        con.setRequestProperty( "Charset" , "UTF-8" );
        // Send the encoded parameters on the connection.
        OutputStream os = con.getOutputStream();
        os.write(body.getBytes( "UTF-8" ));
        os.flush();
        con.connect();

        // Convert the response into a String object.
        String responseContent = parseResponse(con.getInputStream());

        // Create a new JSONObject to hold the access token and extract
        // the token from the response.
        System.out.println("Respoonse Object is"+responseContent);
        return responseContent;
    }

    private String parseResponse(InputStream in) throws Exception
    {
        InputStreamReader inputStream = new InputStreamReader(in, "UTF-8" );
        BufferedReader buff = new BufferedReader(inputStream);

        StringBuilder sb = new StringBuilder();
        String line = buff.readLine();
        while (line != null )
        {
            sb.append(line);
            line = buff.readLine();
        }

        return sb.toString();
    }

}
*/
