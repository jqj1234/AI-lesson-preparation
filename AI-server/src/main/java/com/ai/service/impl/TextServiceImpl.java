package com.ai.service.impl;

import com.ai.mapper.LessonPlanMapper;
import com.ai.properties.TextProperties;
import com.ai.result.Result;
import com.ai.service.TextService;
import com.ai.utils.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.dashscope.app.*;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.ai.entity.LessonPlan;


import java.util.HashMap;

/**
 * @author ：褚婧雯
 * @date ：2025/4/7 15:31
 * @description ：教案生成
 */
@Service
public class TextServiceImpl implements TextService {

    @Autowired
    private TextProperties textProperties;

    @Autowired
    private LessonPlanMapper lessonPlanMapper;

    @Autowired
    private IdWorker idWorker;


    @Override
    public Result textGen(String prompt) {
        String s = "";
        try {
            s = Call(prompt);
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
//            System.err.println("message："+e.getMessage());
//            System.out.println("请参考文档：https://help.aliyun.com/zh/model-studio/developer-reference/error-code");
        }
        return Result.success(s);
    }

    /**
     * 保存教案
     * @param info
     * @return
     */
    @Override
    public Result lessonSave(HashMap<String, String> info) {
        LessonPlan lessonPLan = new LessonPlan();
        lessonPLan.setId(idWorker.nextId());
        lessonPLan.setUserid(Long.valueOf(info.get("id")));
        lessonPLan.setContent(info.get("text"));
        lessonPLan.setTitle(info.get("title"));
        lessonPlanMapper.insert(lessonPLan);
        return Result.success("保存成功");
    }

    /**
     * 删除教案
     * @param info
     * @return
     */
    @Override
    public Result lessonDelete(HashMap<String, String> info) {
        String id = info.get("id");
        LessonPlan lessonPlan = lessonPlanMapper.selectById(id);
        lessonPlan.setDeleted(0);
        lessonPlanMapper.updateById(lessonPlan);
        return Result.success("删除成功");
    }


    /**
     * 调用阿里大模型
     * @param prompt
     * @return
     * @throws ApiException
     * @throws NoApiKeyException
     * @throws InputRequiredException
     */
    public String Call(String prompt)
            throws ApiException, NoApiKeyException, InputRequiredException {
        ApplicationParam param = ApplicationParam.builder()
                // 若没有配置环境变量，可用百炼API Key将下行替换为：.apiKey("sk-xxx")。但不建议在生产环境中直接将API Key硬编码到代码中，以减少API Key泄露风险。
                .apiKey(textProperties.getApiKey())
                .appId(textProperties.getAppId())
                .prompt(prompt)
                .build();

        Application application = new Application();
        ApplicationResult result = application.call(param);

//        System.out.printf("text: %s\n",
//                result.getOutput().getText());

        return result.getOutput().getText();
    }
}
