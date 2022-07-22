package datastructures.graphs.design;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

    private Queue<String> queue;
    private Set<String> discoveredWebsites;

    public WebCrawler() {
        this.queue = new LinkedList<>();
        this.discoveredWebsites = new HashSet<>();
    }

    // root is the starting url
    public void discoverWeb(String root) {
        this.queue.add(root);
        this.discoveredWebsites.add(root);
        while (!queue.isEmpty()) {
            String currentURL = this.queue.poll();
            String rawHtml = readUrl(currentURL);
            String regexp = "https://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(rawHtml);
            while (matcher.find()) {
                String w = matcher.group();
                if (!discoveredWebsites.contains(w)) {
                    discoveredWebsites.add(w);
                    System.out.println("Website found: " + w);
                    queue.add(w);
                }
            }
        }
    }

    private String readUrl(String currentURL) {
        StringBuilder rawHtml = new StringBuilder();
        try {
            URL url = new URL(currentURL);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                rawHtml.append(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Problem while reading from url " + currentURL);
        }
        return rawHtml.toString();
    }

    public static void main(String[] args) {
        WebCrawler wc = new WebCrawler();
        wc.discoverWeb("https://www.bbc.com");
    }
}
