package companies.grab.indeed;

import java.util.*;

public class FuzzyStringsSearch {

/*
Given a raw string with a job title, find the clean tittle that matches best:

Scoring criteria: Most number of matching words

raw_title = "Financial Advisor from New York"
clean_titles = ["Waste Manager", "Quality Engineer", "Financial", "Advisor", "New York", "Financial Advisor", "Lead Financial Advisor",
                "Tech Bro", "Financial Bro"]
correct return = "Financial Advisor" (since this one has the most words from the clean title)

My current output = "Lead Financial Advisor" (This is the return I currently get on my code, attached in the image)
 */

    //https://github.com/interviewdiscussion/files/tree/master/Indeed%20Onsite%E8%AE%B0%E5%BD%95
    //https://yaopiupiupiu.gitbooks.io/i2017/content/shortest-word-distance.html
    public static String fuzzySearch(String rawTitle, List<String> cleanTitles) {
        String[] lowerRawTitleWords = rawTitle.toLowerCase().split(" ");
        Map<String, Integer> rawTitleWordsMap = createWordFreqMap(lowerRawTitleWords);
        List<String> globalMatch = new ArrayList<>();
        for (String cleanTitle : cleanTitles) {
            Map<String, Integer> cleanTitleWordsMap = createWordFreqMap(cleanTitle.toLowerCase().split(" "));
            List<String> currentMatch = getCurrentMatch(rawTitleWordsMap, cleanTitleWordsMap);
            globalMatch = currentMatch.size() > globalMatch.size() ? currentMatch : globalMatch;
        }
        return String.join(" ", globalMatch);
    }

    private static List<String> getCurrentMatch(Map<String, Integer> rawTitleWordsMap, Map<String, Integer> cleanTitleWordsMap) {
        List<String> currentMatch = new ArrayList<>();
        // Condition 1 - check if raw title has all words of clean title
        if (rawTitleWordsMap.keySet().containsAll(cleanTitleWordsMap.keySet())) {
            for (String clearWordKey : cleanTitleWordsMap.keySet()) {
                // Condition 2 - check if frequency of word is same in raw title and clean title
                if (rawTitleWordsMap.get(clearWordKey).equals(cleanTitleWordsMap.get(clearWordKey))) {
                    currentMatch.add(clearWordKey.substring(0, 1).toUpperCase() + clearWordKey.substring(1));
                }
            }
        }
        return currentMatch.size() == cleanTitleWordsMap.keySet().size() ? currentMatch : Collections.emptyList();
    }

    private static HashMap<String, Integer> createWordFreqMap(String[] words) {
        // Using LinkedHashMap to maintain order of insertion of words
        HashMap<String, Integer> wordsFreqMap = new LinkedHashMap<>();
        for (String word : words) {
            wordsFreqMap.put(word, wordsFreqMap.getOrDefault(word, 0) + 1);
        }
        return wordsFreqMap;
    }

    public static String getHighestTitle(String rawTitle, String[] cleanTitles){
        String result = "";
        int highScore = Integer.MIN_VALUE;
        for (String cleanTitle : cleanTitles){
            int curScore = getScore(rawTitle, cleanTitle);
            if (curScore > highScore){
                highScore = curScore;
                result = cleanTitle;
            }
        }
        return result;
    }

    public static int getScore(String raw, String ct){
        int score = 0, tempScore = 0;
        int rIdx = 0, cIdx = 0;
        String[] rA = raw.split(" ");
        String[] cA = ct.split(" ");
        while (rIdx < rA.length){
            String rCur = rA[rIdx];
            String cCur = cA[cIdx];
            if (!rCur.equals(cCur)){
                cIdx = 0;
                tempScore = 0;
            }
            else {
                tempScore++;
                cIdx++;
            }
            rIdx++;
            score = Math.max(score, tempScore);
            if (cIdx == cA.length)
                break;
        }

        return score;
    }

    public static String getHightestTitleWithDup(String rawTitle, String[] cleanTitles){
        String res = "";
        int highScore = 0;
        String[] rA = rawTitle.split(" ");
        for (String ct: cleanTitles) {
            String[] cA = ct.split(" ");
            int temp = getScoreWithDup(rA, cA);
            System.out.println("temp is "+ temp);
            if (temp > highScore){
                highScore = temp;
                res = ct;
            }
        }
        return res;
    }

    public static int getScoreWithDup(String[] rA, String[] cA){
        int col = rA.length;
        int row = cA.length;
        int res = 0;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++){
            String cCur = cA[i];
            for (int j = 0; j < col; j++){
                String rCur = rA[j];
                if (rCur.equals(cCur)){
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    }
                    else {
                        dp[i][j] = Math.max(1, dp[i-1][j-1] + 1);
                    }
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //System.out.println(getHighestTitle("Financial Advisor from New York", new String[]{ "Waste Manager", "Quality Engineer", "Financial", "Advisor", "Financial Advisor", "Lead Financial Advisor",
         //       "Tech Bro", "Financial Bro"}));
        //System.out.println(getHighestTitle("I need a software engineer", new String[]{"Software Engineer", "Electrical Engineer", "Plumber"}));
        //System.out.println(getHighestTitle("senior software engineer", new String[]{"software engineer", "mechanical engineer", "senior software engineer"}));
        System.out.println(getHighestTitle("a a b b", List.of("a a b").stream().toArray(String[]::new)));
    }
}
