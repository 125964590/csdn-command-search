package com.huatu.ic.search.controller.api.v1;

import com.huatu.common.exception.BizException;
import com.huatu.ic.search.service.QuestionSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jbzm
 * @date 201812:40 AM
 **/
@RestController
@RequestMapping("v1/question")
@Slf4j
public class QuestionSearchController {
    private final QuestionSearchService questionSearchService;

    @Autowired
    public QuestionSearchController(QuestionSearchService questionSearchService) {
        this.questionSearchService = questionSearchService;
    }

    @GetMapping("search")
    public Object questionSearch(@RequestParam(name = "keyword") String keyword, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size) throws BizException {
        return questionSearchService.getQuestion(keyword,page,size-1);
    }
}