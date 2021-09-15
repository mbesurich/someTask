import java.util.*;

public class SomeTask {

    public static void main(String[] args) {
        SomeTask test = new SomeTask();
        System.out.println(test.lengthOfLongestSubstring("abba"));
    }

    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        char tempCh;
        int maxLength = 0, curLength = 0, lastCollisionIndex = -1, newCollisionIndex = 0;
        for (int i = 0; i < chars.length; i++){
            tempCh = chars[i];
            if (!map.containsKey(tempCh)){
                map.put(tempCh, i);
                curLength++;
            } else {
                newCollisionIndex = map.get(tempCh);
                maxLength = Math.max(maxLength, curLength);
                for (int j = lastCollisionIndex + 1; j < newCollisionIndex; j++) {
                    map.remove(chars[j]);
                }
                map.put(tempCh, i);
                if (lastCollisionIndex == 0){
                    if (newCollisionIndex == 0){
                        curLength = curLength - (newCollisionIndex - lastCollisionIndex);
                    } else curLength = i - newCollisionIndex;
                } else curLength = curLength - (newCollisionIndex - lastCollisionIndex) + 1;
                lastCollisionIndex = newCollisionIndex;
            }
        }
        maxLength = Math.max(maxLength, curLength);
        return maxLength;
    }
}
