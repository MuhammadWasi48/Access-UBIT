public class URLValidator {

    public static boolean isValidURL(String url) {
        // Check valid starting pattern
        String prefix = "";
        if (url.startsWith("http://")) {
            prefix = "http://";
        } else if (url.startsWith("https://")) {
            prefix = "https://";
        } else if (url.startsWith("www.")) {
            prefix = "www.";
        } else {
            return false; // invalid start
        }

        // Remove the starting part
        String rest = url.substring(prefix.length());

        // Split by '.'
        String[] parts = rest.split("\\.");

        // Each part must have > 2 chars and only letters/digits
        for (String part : parts) {
            if (part.length() <= 2) {
                return false;
            }
            if (!part.matches("[A-Za-z0-9]+")) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Test cases
        String[] testURLs = {
            "http://example.com",
            "https://www.google123.com",
            "www.mysite123.net",
            "http://ab.c",         // invalid (too short)
            "ftp://wrongstart.com",// invalid (wrong prefix)
            "www.si@te.com"        // invalid (special char)
        };

        for (String url : testURLs) {
            System.out.println(url + " → " + isValidURL(url));
        }
    }
}