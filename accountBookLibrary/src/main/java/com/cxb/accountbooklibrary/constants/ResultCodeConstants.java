/**
 * Copyright (C) 2015 才兴邦科技有限公司
 * @Title: ResultCodeConstants.java
 * @Package com.cxb.uc.constants
 * @author dj
 * @date 2015年11月23日 上午11:29:03
 * @version V1.0
 */
package com.cxb.accountbooklibrary.constants;

/**
 * @ClassName: ResultCodeConstants
 * @Description: ResultCode常量定义
 * @author Devin.Ding
 * @date 2015年11月23日 上午11:29:03
 * @version 1.0
 */
public class ResultCodeConstants {

    /** 成功 */
    public static final String RESULT_CODE_0 = "成功";

    /** 无效的TOKEN */
    public static final String RESULT_CODE_1 = "无效的TOKEN";

    /** 无效的参数 */
    public static final String RESULT_CODE_2 = "无效的参数";

    /** 处理失败 */
    public static final String RESULT_CODE_3 = "处理失败";

    /** 密码格式不正确，必需是大于6位并且包含大写字母、小写字母和数字 */
    public static final String RESULT_CODE_1001 = "密码格式不正确，必需是大于6位并且包含大写字母、小写字母和数字";

    /** 用户已存在 */
    public static final String RESULT_CODE_1002 = "用户已存在";

    /** 手机号已存在 */
    public static final String RESULT_CODE_1003 = "手机号已存在";

    /** 该部门存在下级部门，不能删除 */
    public static final String RESULT_CODE_1004 = "该部门存在下级部门，不能删除";

    /** 相同名称的部门已存在 */
    public static final String RESULT_CODE_1005 = "相同名称的部门已存在";

    /** 该项目存在子项目，不能删除 */
    public static final String RESULT_CODE_1006 = "该项目存在子项目，不能删除";

    /** 多个用户已存在 */
    public static final String RESULT_CODE_1007 = "多个用户已存在";

    /** 不能删除正在使用的账号 */
    public static final String RESULT_CODE_1008 = "不能删除正在使用的账号";

    /** 当前账套不能删除 */
    public static final String RESULT_CODE_1009 = "当前账套不能删除";

    /** 邮箱格式不正确 */
    public static final String RESULT_CODE_1010 = "邮箱格式不正确";

    /** 账套已存在 */
    public static final String RESULT_CODE_1011 = "账套已存在";

    /** 手机号格式不正确 */
    public static final String RESULT_CODE_1012 = "手机号格式不正确";

    /** 手机号或密码错误 */
    public static final String RESULT_CODE_1013 = "手机号或密码错误";

    /** 验证码不正确 */
    public static final String RESULT_CODE_1014 = "验证码不正确";
    
    /** 不支持的推送设备 */
    public static final String RESULT_CODE_1015 = "不支持的推送设备";

    /** 推送ChannelId为空 */
    public static final String RESULT_CODE_1016 = "推送ChannelId为空";

    /** 设备类型为空 */
    public static final String RESULT_CODE_1017 = "设备类型为空";

    /** 帐套不存在 */
    public static final String RESULT_CODE_1018 = "帐套不存在";

    /** 上级项目不存在 */
    public static final String RESULT_CODE_1019 = "上级项目不存在";

    /** 用户不存在 */
    public static final String RESULT_CODE_1020 = "用户不存在";

    /** 不正确的权重值 */
    public static final String RESULT_CODE_1021 = "不正确的权重值";
    
    
    

    /** 只取message 操作代码 */
    public static final int SPECIAL_CODE = -1;

//    public static final int TOKEN_CODE = 1;
//
//    public static final String TOKEN_MSG = "用户登录过期,请重新登录";
//
//    public static final int PARAM_CODE = 2;
//
//    public static final String PARAM_MSG = "参数错误";
//
//    public static final int UNKNOWN_CODE = 3;
//
//    public static final String UNKNOWN_MSG = "未知错误";

//    public static final int SELECT_VALID_TIME_CODE = 2001;

    public static final String RESULT_CODE_2001 = "查询结束时间不能在建账时间之前";

//    public static final int IMPORT_BALANCE_CODE = 2003;

    public static final String RESULT_CODE_2003 = "已有凭证生成或已存在余额信息,不能导入余额数据!";

//    public static final int SAVE_ACCOUNTVOUCHER_DATE_CODE = 2004;

    public static final String RESULT_CODE_2004 = "新增凭证时间不能比建账时间早,保存失败!";

//    public static final int SAVE_ACCOUNTVOUCHER_MONEY_CODE = 2005;

    public static final String RESULT_CODE_2005 = "分录金额不能全为0";

//    public static final int APPLICATIONFORM_DELETE_CODE = 2006;

    public static final String RESULT_CODE_2006 = "仅允许删除状态为未提交或已撤销的申请单";

//    public static final int MOBILE_PASSWORD_CODE = 2007;

    public static final String RESULT_CODE_2007 = "电话号码或密码不正确";

//    public static final int AUTH_CODE_CODE = 2008;

    public static final String RESULT_CODE_2008 = "验证码不正确";

//    public static final int PASSWORD_CODE = 2009;

    public static final String RESULT_CODE_2009 = "密码或新密码不正确";

//    public static final int MOBILE_CODE = 2010;

    public static final String RESULT_CODE_2010 = "电话号码不正确";

}
