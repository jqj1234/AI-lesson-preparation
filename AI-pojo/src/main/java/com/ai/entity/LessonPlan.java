package com.ai.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：褚婧雯
 * @date ：2025/4/7 21:05
 * @description ：用户教案表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonPlan {
    private Long id;
    private Long userid;

    private String title;

    private String content;

    private Integer deleted;

    private Date createTime;

    private Date updateTime;
}
