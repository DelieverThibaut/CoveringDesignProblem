package CoveringDesignProblem;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Webscraper {
    public static void main(String[] args) throws IOException {
        int maxV = 99;
        int maxK = 25;
        int maxT = 8;

        for(int t=2; t<=maxT; t++){
            for(int k=t+1; k<=maxK; k++){
                for(int v=k+1; v<=maxV; v++){
                    System.out.printf("Scraper: v=%1$d, k=%2$d, t=%3$d\n", v, k, t);
                    scraper(v, k, t);
                }
            }
        }
    }

    public static void scraper(int v, int k, int t) throws IOException {
        String url = String.format("http://ljcr.dmgordon.org/cover/show_cover.php?v=%1$d&k=%2$d&t=%3$d", v, k, t);

        try (final WebClient webClient = new WebClient()) {
            webClient.getOptions().setThrowExceptionOnScriptError(false);

            webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(10000);
            HtmlPage page = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();

            String titleText = ((HtmlHeading1) page.getFirstByXPath("//h1")).getTextContent();
            String subtitleText = ((HtmlHeading2) page.getFirstByXPath("//h2")).getTextContent();
            String lowerboundText = ((HtmlHeading3) page.getFirstByXPath("//h3")).getTextContent();
            String solutionText = ((HtmlPreformattedText) page.getFirstByXPath("//pre")).getTextContent();
            ArrayList<String> solutionSplitted = new ArrayList<>(Arrays.asList(solutionText.split("\n")));

            try{
                String fileName = String.format("C:\\Users\\thiba\\OneDrive - KU Leuven\\Bach_Informatica\\B3\\BACHELORPROEF\\CoveringDesignProblem\\src\\main\\KnownSolutions\\solution%1$d_%2$d_%3$d.txt", v, k, t);
                FileWriter f = new FileWriter(fileName, false);
                f.write("c "+ titleText + "\n");
                f.write("c "+ subtitleText + "\n");
                f.write("c "+ lowerboundText + "\n");
                f.write("c \n");

                for(String str : solutionSplitted){
                    ArrayList<String> strSplitted = new ArrayList<>(Arrays.asList(str.split(" ")));
                    strSplitted.removeAll(Arrays.asList("", null));

                    f.write("s ");
                    for(String strNum : strSplitted){
                        f.write(strNum);
                        if(strNum.equals(strSplitted.get(strSplitted.size()-1))){
                            if(!str.equals(solutionSplitted.get(solutionSplitted.size()-1))){
                                f.write("\n");
                            }
                        } else {
                            f.write(" ");
                        }
                    }

                }

                f.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
