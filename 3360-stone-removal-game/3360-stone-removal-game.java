class Solution {
    public boolean canAliceWin(int n) {
        int toRemove = 10;
        boolean aliceWins = false;

        while (n >= toRemove) {
            n -= toRemove;
            toRemove--;
            aliceWins = !aliceWins;
        }

        return aliceWins;
    }
}