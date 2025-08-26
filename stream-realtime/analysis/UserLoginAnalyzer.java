package com.analysis;

import java.time.LocalDate;
import java.util.*;

public class UserLoginAnalyzer {

    public static class LoginRecord {
        String userId;
        String deviceId;
        LocalDate loginDate;

        public LoginRecord(String userId, String deviceId, LocalDate loginDate) {
            this.userId = userId;
            this.deviceId = deviceId;
            this.loginDate = loginDate;
        }

        @Override
        public String toString() {
            return String.format("User: %s, Device: %s, Date: %s", userId, deviceId, loginDate);
        }
    }

    public static void analyzeLoginStats(List<LoginRecord> logs) {
        if (logs == null || logs.isEmpty()) return;

        // 1. 找出每个用户的首次登录日期
        Map<String, LocalDate> firstLoginMap = new HashMap<>();
        for (LoginRecord log : logs) {
            firstLoginMap.merge(log.userId, log.loginDate,
                    (existing, current) -> existing.isBefore(current) ? existing : current
            );
        }

        // 2. 按日期统计新/老用户
        TreeMap<LocalDate, Integer> newUsers = new TreeMap<>();
        TreeMap<LocalDate, Integer> oldUsers = new TreeMap<>();

        for (LoginRecord log : logs) {
            LocalDate firstLogin = firstLoginMap.get(log.userId);
            if (log.loginDate.equals(firstLogin)) {
                newUsers.merge(log.loginDate, 1, Integer::sum);
            } else {
                oldUsers.merge(log.loginDate, 1, Integer::sum);
            }
        }

        // 3. 合并输出
        Set<LocalDate> allDates = new TreeSet<>();
        allDates.addAll(newUsers.keySet());
        allDates.addAll(oldUsers.keySet());

        System.out.println("\n=== 每日新老用户统计 ===");
        System.out.printf("%-12s %-12s %-12s%n", "Login Date", "New Users", "Old Users");
        System.out.println("----------------------------------------");
        for (LocalDate date : allDates) {
            int newCnt = newUsers.getOrDefault(date, 0);
            int oldCnt = oldUsers.getOrDefault(date, 0);
            System.out.printf("%-12s %-12d %-12d%n", date, newCnt, oldCnt);
        }
    }
}
