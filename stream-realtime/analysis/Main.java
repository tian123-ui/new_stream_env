package com.analysis;

// 导入用户登录分析器相关类（内部类LoginRecord和分析器类UserLoginAnalyzer）
import com.analysis.UserLoginAnalyzer.LoginRecord;
import com.analysis.UserLoginAnalyzer;

// 导入日期处理类LocalDate
import java.time.LocalDate;
// 导入集合框架相关类（列表、数组工具类等）
import java.util.*;

/**
 * 主程序类，包含程序入口main方法
 * 用于演示用户登录数据分析和两数之和算法的功能
 */
public class Main {
    /**
     * 程序主入口方法
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        // =============== 第一部分：新老用户分析 ===============
        // 创建模拟的用户登录日志列表
        List<LoginRecord> loginLogs = Arrays.asList(
                new LoginRecord("U1", "D1", LocalDate.of(2024, 1, 1)),  // U1用户在2024-01-01使用D1设备登录
                new LoginRecord("U2", "D2", LocalDate.of(2024, 1, 1)),  // U2用户在2024-01-01使用D2设备登录
                new LoginRecord("U1", "D1", LocalDate.of(2024, 1, 2)),  // U1用户在2024-01-02再次登录
                new LoginRecord("U3", "D3", LocalDate.of(2024, 1, 2)),  // U3用户在2024-01-02首次登录
                new LoginRecord("U2", "D2", LocalDate.of(2024, 1, 3)),  // U2用户在2024-01-03再次登录
                new LoginRecord("U3", "D3", LocalDate.of(2024, 1, 4)),  // U3用户在2024-01-04再次登录
                new LoginRecord("U4", "D4", LocalDate.of(2024, 1, 1)),  // U4用户在2024-01-01首次登录
                new LoginRecord("U4", "D4", LocalDate.of(2024, 1, 5))   // U4用户在2024-01-05再次登录
        );

        // 调用用户登录分析器的统计方法，传入日志列表进行分析
        UserLoginAnalyzer.analyzeLoginStats(loginLogs);

        // =============== 第二部分：两数之和 ===============
        // 创建两数之和算法实现类的实例
        TwoSum twoSum = new TwoSum();
        // 测试用例：目标数组
        int[] nums = {2, 7, 11, 15};
        // 测试用例：目标和
        int target = 9;
        try {
            // 调用两数之和方法，获取结果下标
            int[] result = twoSum.twoSum(nums, target);
            // 打印两数之和的计算结果
            System.out.println("\n=== 两数之和 ===");
            // 格式化输出数组和目标值（将int数组转换为字符串）
            System.out.printf("数组: [%s], 目标: %d%n",
                    String.join(", ", Arrays.stream(nums).mapToObj(String::valueOf).toArray(String[]::new)),
                    target);
            // 格式化输出结果下标及对应元素的和
            System.out.printf("结果下标: [%d, %d] → %d + %d = %d%n",
                    result[0], result[1],       // 下标
                    nums[result[0]], nums[result[1]],  // 下标对应的元素
                    target);                    // 目标和
        } catch (Exception e) {
            // 捕获并打印异常信息（如无符合条件的元素对）
            System.err.println("Error: " + e.getMessage());
        }
    }
}