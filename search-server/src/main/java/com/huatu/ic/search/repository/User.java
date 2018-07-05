package com.huatu.ic.search.repository;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

/**
 * 用户
 * 
 * @author zhouwei
 *
 */
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@Document(indexName = "cool-user-index", type = "cool-user-table")
@Mapping
public class User {
	@Id
	private Long id;
	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 性别
	 *
	 MAN(1, "男"),
	 WOMAN(2, "女");
	 */
	private int sex;
	/**
	 * 是否关注 0不是 1我方已添加 2双方互加
	 */
	private Integer collectionFlag;
	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * 签名
	 */
	private String signature;

	/**
	 * 手机号
	 */
	private String phoneNum;


}
