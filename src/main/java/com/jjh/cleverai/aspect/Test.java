package com.jjh.cleverai.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Test {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 手机号
     */
    private String userPhone;

    /**
     * appName
     */
    private String appName;


    public static void main(String[] args) {
        // 构造测试数据
        List<Test> testData = new ArrayList<>();
        testData.add(new Test(1L, 1, "123456", "app1"));
        testData.add(new Test(2L, 1, "123456", "app2"));
        testData.add(new Test(9L, 1, "123456", "app2"));
//        testData.add(new Test(3L, 2, "123456","app1"));
        testData.add(new Test(4L, 1, "789012", "app2"));
        testData.add(new Test(5L, 2, "789012", "app1"));
        String appName = "app2";
        List<List<Test>> collect = testData.stream()
                .collect(Collectors.groupingBy(Test::getUserPhone))
                .values().stream()
                .filter(tests -> {
                            System.out.println("123");
//                            tests.removeIf(o -> !appName.equals(o.getAppName()))
                            return tests.stream()
                                    .map(Test::getProductId)
                                    .distinct().count() == 1;
                        }

                ).collect(Collectors.toList());
        List<Long> userIdList = collect.stream()
                .filter(tests -> {
                    System.out.println("12343213");
                    return true;
                })

                .flatMap(values -> values.stream().map(Test::getUserId))
                .collect(Collectors.toList());

        // 输出结果
        System.out.println(userIdList);
    }
}
