package com.ai.controller;

import com.ai.result.Result;
import com.ai.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author ：褚婧雯
 * @date ：2025/4/7 14:58
 * @description ：教案生成
 */
@RestController
@RequestMapping("/text")
public class TextController {

    @Autowired
    private TextService textService;


    /**
     * 教案生成
     * @param prompt
     * @return
     */
    @PostMapping
    public Result textGen(@RequestBody String prompt) {
        return textService.textGen(prompt);
    }


    /**
     * 保存教案
     * @param info
     * @return
     */
    @PostMapping("/lesson")
    public Result lessonSave(@RequestBody HashMap<String,String> info) {
        return textService.lessonSave(info);
    }

    /**
     * 删除教案
     * @param info
     * @return
     */
    @DeleteMapping("/lesson")
    public Result lessonDelete(@RequestBody HashMap<String,String> info) {
        return textService.lessonDelete(info);
    }
}
