package com.Train;

/**
 * Created by josdas on 29.06.2017.
 */
public class StringDistance {
    public static double levenshtein(String a, String b) {
        final int n = a.length();
        final int m = b.length();
        double[][] dp = new double[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
                dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }
        return 2 * dp[n][m] / (n + m);
    }

    public static double levenshteinSwap(String a, String b) {
        final int n = a.length();
        final int m = b.length();
        double[][] dp = new double[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
                if (i > 1 && j > 1
                        && a.charAt(i - 2) == b.charAt(j - 1)
                        && a.charAt(i - 1) == b.charAt(j - 2)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 2][j - 2] + 0.9);
                }
                dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }
        return dp[n][m];
    }
}
