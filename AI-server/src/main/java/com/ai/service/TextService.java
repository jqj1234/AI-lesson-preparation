package com.ai.service;

import com.ai.result.Result;

import java.util.HashMap;

/**
 * @author ：褚婧雯
 * @date ：2025/4/7 15:30
 * @description ：教案生成
 */
public interface TextService {

    Result textGen(String prompt);

    /**
     * 保存教案
     * @param info
     * @return
     */
    Result lessonSave(HashMap<String, String> info);

    /**
     * 删除教案
     * @param info
     * @return
     */
    Result lessonDelete(HashMap<String, String> info);
}
