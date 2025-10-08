import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiFacade {
    
    public String getAttributeValueFromJson(String urlString, String attributeName) 
            throws IllegalArgumentException, IOException {
        
        String json = sendHttpGetRequest(urlString);
        return extractAttributeFromJson(json, attributeName);
    }
    
    private String sendHttpGetRequest(String urlString) throws IOException {
        URL url;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new IOException("Invalid URL: " + urlString, e);
        }
        
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            
            int responseCode = con.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP request failed with response code: " + responseCode);
            }
            
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                return content.toString();
            }
        } catch (IOException e) {
            throw new IOException("Failed to send HTTP request: " + e.getMessage(), e);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }
    
    private String extractAttributeFromJson(String json, String attributeName) 
            throws IllegalArgumentException {
        
        String searchKey = "\"" + attributeName + "\"";
        int keyIndex = json.indexOf(searchKey);
        
        if (keyIndex == -1) {
            throw new IllegalArgumentException(
                "Attribute '" + attributeName + "' not found in JSON response");
        }
        
        int colonIndex = json.indexOf(":", keyIndex);
        if (colonIndex == -1) {
            throw new IllegalArgumentException("Invalid JSON format");
        }
        
        int valueStart = colonIndex + 1;
        while (valueStart < json.length() && Character.isWhitespace(json.charAt(valueStart))) {
            valueStart++;
        }
        
        if (valueStart >= json.length()) {
            throw new IllegalArgumentException("Invalid JSON format");
        }
        
        char firstChar = json.charAt(valueStart);
        
        if (firstChar == '"') {
            int valueEnd = json.indexOf('"', valueStart + 1);
            if (valueEnd == -1) {
                throw new IllegalArgumentException("Invalid JSON format");
            }
            return json.substring(valueStart + 1, valueEnd);
        } else if (firstChar == '{' || firstChar == '[') {
            return extractComplexValue(json, valueStart, firstChar);
        } else {
            int valueEnd = findValueEnd(json, valueStart);
            return json.substring(valueStart, valueEnd).trim();
        }
    }
    
    private String extractComplexValue(String json, int start, char openChar) {
        char closeChar = (openChar == '{') ? '}' : ']';
        int depth = 1;
        int i = start + 1;
        
        while (i < json.length() && depth > 0) {
            char c = json.charAt(i);
            if (c == openChar) depth++;
            else if (c == closeChar) depth--;
            i++;
        }
        
        return json.substring(start, i);
    }
    
    private int findValueEnd(String json, int start) {
        int i = start;
        while (i < json.length()) {
            char c = json.charAt(i);
            if (c == ',' || c == '}' || c == ']') {
                break;
            }
            i++;
        }
        return i;
    }
}