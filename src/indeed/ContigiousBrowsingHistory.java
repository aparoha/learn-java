package indeed;

import java.util.ArrayList;
import java.util.List;

/*
We have some clickstream data that we gathered on our client's website. Using cookies, we collected snippets of users' anonymized URL histories while they browsed the site. The histories are in chronological order, and no URL was visited more than once per person.

Write a function that takes two users' browsing histories as input and returns the longest contiguous sequence of URLs that appears in both.

Sample input:
user0 = ["/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"]
user1 = ["/start", "/pink", "/register", "/orange", "/red", "a"]
user2 = ["a", "/one", "/two"]
user3 = ["/pink", "/orange", "/yellow", "/plum", "/blue", "/tan", "/red", "/amber", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow", "/BritishRacingGreen"]
user4 = ["/pink", "/orange", "/amber", "/BritishRacingGreen", "/plum", "/blue", "/tan", "/red", "/lavender", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow"]
user5 = ["a"]
user6 = ["/pink","/orange","/six","/plum","/seven","/tan","/red", "/amber"]

Sample output:

findContiguousHistory(user0, user1) => ["/pink", "/register", "/orange"]
findContiguousHistory(user0, user2) => [] (empty)
findContiguousHistory(user0, user0) => ["/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"]
findContiguousHistory(user2, user1) => ["a"]
findContiguousHistory(user5, user2) => ["a"]
findContiguousHistory(user3, user4) => ["/plum", "/blue", "/tan", "/red"]
findContiguousHistory(user4, user3) => ["/plum", "/blue", "/tan", "/red"]
findContiguousHistory(user3, user6) => ["/tan", "/red", "/amber"]

n: length of the first user's browsing history
m: length of the second user's browsing history

 */
public class ContigiousBrowsingHistory {

    private static void findContiguousHistory(List<String> user1, List<String> user2) {
        int window = user1.size() + user2.size();
        List<String> ans = new ArrayList<>();
        int maxLength = 0;
        for (int i = 0; i < window - 1; i++) {
            int pointer1 = Math.max(0, user1.size() - 1 - i);
            int pointer2 = Math.max(0, i - user1.size() + 1);
            int tmpMax = 0;
            List<String> tmpAns = new ArrayList<>();
            while (pointer1 < user1.size() && pointer2 < user2.size()) {
                if (user1.get(pointer1).equals(user2.get(pointer2))) {
                    tmpMax += 1;
                    tmpAns.add(user1.get(pointer1));
                    ans = tmpAns.size() > ans.size() ? tmpAns : ans;
                    maxLength = Math.max(maxLength, tmpMax);
                } else {
                    tmpMax = 0;
                    tmpAns = new ArrayList<>();
                }
                pointer1++;
                pointer2++;
            }
        }
        System.out.println("The max sub-array length is:  " + maxLength);
        System.out.println("The max sub-array  is: " + ans + "\n");
    }

    public static void main(String[] args) {
        List<String> user0 = List.of("/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two");
        List<String> user1 = List.of("/start", "/pink", "/register", "/orange", "/red", "a");
        List<String> user2 = List.of("a", "/one", "/two");
        List<String> user3 = List.of("/pink", "/orange", "/yellow", "/plum", "/blue", "/tan", "/red", "/amber", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow", "/BritishRacingGreen");
        List<String> user4 = List.of("/pink", "/orange", "/amber", "/BritishRacingGreen", "/plum", "/blue", "/tan", "/red", "/lavender", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow");
        List<String> user5 = List.of("a");
        List<String> user6 = List.of("/pink","/orange","/six","/plum","/seven","/tan","/red", "/amber");
        findContiguousHistory(user0, user1);
    }
}
