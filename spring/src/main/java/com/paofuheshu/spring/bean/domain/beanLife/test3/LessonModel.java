package com.paofuheshu.spring.bean.domain.beanLife.test3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/24 14:54
 * @des
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonModel {

    private String name;

    private int lessonCount;

    private String description;
}
