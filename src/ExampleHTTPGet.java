import java.io.BufferedInputStream;
import java.net.URL;

public class ExampleHTTPGet {
    private String siteContent = "";
 
    public String HTTPGet( String strUrl )
    {
        String content;
        try
         {
            byte[] buffer = new byte[4096];
            URL url = new URL( strUrl );
 
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            int bytesRead = 0;
 
            while ((bytesRead = bis.read(buffer)) != -1)
            {
                content = new String(buffer, 0, bytesRead);
                this.siteContent = this.siteContent + content;
            }
            bis.close();
 
            return this.siteContent;
 
        } catch (Exception ex)
        {
            System.out.println("OMG, Houston, we have a exception  " + ex.getMessage() + " xD");
            return "";
        }
    }
}