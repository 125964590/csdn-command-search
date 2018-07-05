package com.huatu.ic.search.controller.api.v1;

import com.google.common.collect.Lists;
import com.huatu.ic.search.enums.HotWordTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhouwei
 * @Description: TODO
 * @create 2018-06-22 下午5:13
 **/
@RestController
@RequestMapping("/v1/hotWord")
@Slf4j
public class SearchController {

	/**
	 * 根据类型获取热词
	 *
	 * @param hotWordType
	 *            热词类型
	 * @return 热词列表
	 */
	@GetMapping("/type/{type}")
	public Object hotWordList(@PathVariable HotWordTypeEnum type) {
		List<String> hotWords = Lists.newArrayList();

		if (HotWordTypeEnum.INDEX.equals(type)) {
			hotWords.addAll(Arrays.asList("热词首页", "结构化", "无领导"));
		} else if (HotWordTypeEnum.TIKU.equals(type)) {
			hotWords.addAll(Arrays.asList("热词题库", "方法论", "认知能力"));
		}

		return hotWords;
	}
}
