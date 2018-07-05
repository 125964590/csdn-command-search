package com.huatu.ic.search.controller.api.v1;

import com.huatu.common.exception.BizException;
import com.huatu.ic.search.service.UserSearchService;
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
@RequestMapping("/v1/user")
public class UserSearchController {

    private final UserSearchService userSearchService;

    @Autowired
    public UserSearchController(UserSearchService userSearchService) {
        this.userSearchService = userSearchService;
    }

    @GetMapping("search")
    public Object user(@RequestParam String keyword, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size) throws BizException {
        return userSearchService.getUser(keyword, page-1, size);
    }
}
