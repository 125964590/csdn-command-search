package com.huatu.ic.search.enums;

import com.huatu.ic.common.util.EnumUtils;
import com.huatu.ic.common.util.entity.IEnum;

/**
 * 
 * 热词类型
 * 
 * @author songxiao
 *
 */
public enum HotWordTypeEnum implements IEnum<Integer> {

	INDEX(1, "首页"),
	TIKU(2, "题库");

	private Integer value;

	private String title;

	HotWordTypeEnum(Integer value, String title) {
		this.value = value;
		this.title = title;
	}

	public static HotWordTypeEnum create(Integer value) {
		return (HotWordTypeEnum) EnumUtils.getEnum(values(), value);
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public String getTitle() {
		return title;
	}
}
