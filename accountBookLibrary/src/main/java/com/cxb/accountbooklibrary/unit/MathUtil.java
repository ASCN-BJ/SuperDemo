package com.cxb.accountbooklibrary.unit;

import java.math.BigDecimal;
import java.util.List;

import android.content.Context;

import com.cxb.accountbooklibrary.R;

/**
 * 计算工具类
 * 
 * @author liuc
 * 
 */
public class MathUtil {
	/**
	 * 精确的加法运算
	 * 
	 * @param value1
	 * @param value2
	 * @param newScale
	 *            小数点后保留的位数
	 * @return 两个参数的和
	 */
	public static String add(String value1, String value2, int newScale) {
		BigDecimal b1 = new BigDecimal(value1);
		BigDecimal b2 = new BigDecimal(value2);
		return new BigDecimal(b1.add(b2).doubleValue()).setScale(newScale,
				BigDecimal.ROUND_HALF_UP).toPlainString();
	}

	/**
	 * 精确的减法运算
	 * 
	 * @param value1
	 * @param value2
	 * @param newScale
	 *            小数点后保留的位数
	 * @return 两个参数的差
	 */
	public static String subtract(String value1, String value2, int newScale) {
		BigDecimal b1 = new BigDecimal(value1);
		BigDecimal b2 = new BigDecimal(value2);
		return new BigDecimal(b1.subtract(b2).doubleValue()).setScale(newScale,
				BigDecimal.ROUND_HALF_UP).toPlainString();
	}

	/**
	 * 精确的乘法运算
	 * 
	 * @param value1
	 * @param value2
	 * @param newScale
	 *            小数点后保留的位数
	 * @return 两个参数的积
	 */
	public static String multiply(String value1, String value2, int newScale) {
		BigDecimal b1 = new BigDecimal(value1);
		BigDecimal b2 = new BigDecimal(value2);
		return new BigDecimal(b1.multiply(b2).doubleValue()).setScale(newScale,
				BigDecimal.ROUND_HALF_UP).toPlainString();
	}

	/**
	 * 精确的除法运算
	 * 
	 * @param value1
	 * @param value2
	 * @param newScale
	 *            小数点后保留的位数
	 * @return 两个参数的商
	 */
	public static String divide(String value1, String value2, int newScale) {
		BigDecimal b1 = new BigDecimal(value1);
		BigDecimal b2 = new BigDecimal(value2);
		return new BigDecimal(b1.divide(b2, newScale, BigDecimal.ROUND_HALF_UP)
				.doubleValue()).setScale(newScale, BigDecimal.ROUND_HALF_UP)
				.toPlainString();
	}

	/**
	 * 四舍五入
	 * 
	 * @param value
	 *            要处理的数字
	 * @param scale
	 *            小数点后保留的位数
	 * @return
	 */
	public static String round(String value, int scale) {
		// BigDecimal b1 = new BigDecimal(value);
		// BigDecimal b2 = new BigDecimal("1");
		// return new BigDecimal(b1.divide(b2).doubleValue()).
		// setScale(scale, BigDecimal.ROUND_HALF_UP).toPlainString();

		return new BigDecimal(value).setScale(scale, BigDecimal.ROUND_HALF_UP)
				.toPlainString();
	}
	/**
	 * 非四舍五入
	 * 
	 * @param value
	 *            要处理的数字
	 * @param scale
	 *            小数点后保留的位数
	 * @return
	 */
	public static String interceptTwoDecimal(String value, int scale) {
		return new BigDecimal(value).setScale(scale, BigDecimal.ROUND_HALF_UP)
				.toPlainString();
	}

	/**
	 * 截取金额字符串，保留两位（非四舍五入）
	 * 
	 * @param money
	 * @return
	 */
	public static String subMoney(String money) {
		int length = money.length(); // 获取字符串长度
		int index = money.indexOf("."); // 获取小数点位置
		if (index == -1) { // 如果没有出现小数点则拼接“.00”返回
			return money + ".00";
		} else {
			if ((length - index) == 2) { // 如果小数点后只有一位则拼接一个“0”返回
				return money + "0";
			} else if ((length - index) >= 3) { // 如果小数点后有两位或两位以上则保留两位
				return money.substring(0, index + 3);
			}
		}
		return "";
	}

	public static double getMaxValue(List<Double> array) {
		double min, max;
		min = max = array.get(0);
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) > max)
				max = array.get(i);
			if (array.get(i) < min)
				min = array.get(i);
		}
		return max;
	}

	public static double getMinValue(List<Double> array) {
		double min, max;
		min = max = array.get(0);
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) > max)
				max = array.get(i);
			if (array.get(i) < min)
				min = array.get(i);
		}
		return min;
	}

	/**
	 * 向上取整
	 * 
	 * @param number
	 * @return
	 */
	public static String toUpperInteger(String number) {

		number = number.indexOf(".") != -1 ? number.substring(0,
				number.indexOf(".")) : number; // 舍去小数

		boolean isN = number.indexOf("-") != -1 ? true : false; // 是否是负数

		int zeroCount = 0; // 0的个数
		for (int i = 0; i < number.length(); i++) {
			if ("0".equals(String.valueOf(number.charAt(i)))) {
				zeroCount++;
			}
		}
		if (zeroCount == number.length() - (isN ? 2 : 1)) {
			return number;
		}

		String startNum = isN ? String.valueOf(number.charAt(1)) : String
				.valueOf(number.charAt(0)); // 获取开头数字

		startNum = isN ? "-" + (Integer.parseInt(startNum) + 1) : (Integer
				.parseInt(startNum) + 1) + "";

		int count = 0; // 除开头数字外的字符位数
		for (int i = isN ? 2 : 1; i < number.length(); i++) {
			count++;
		}

		for (int i = 0; i < count; i++) { // 补位
			startNum += "0";
		}

		return startNum + "";
	}
	
	/**
	 * 采购商品计算商品单价
	 * @param productNum  商品数量
	 * @param productTaxRate  税率
	 * @param amountOfMoney  金额
	 * @return
	 * 
	 */
	public static String unitPrice(String productNum, String productTaxRate, String amountOfMoney){
		//单价 = 价税合计/(销售数量*(1+税率))
		if (productNum == null || "".equals(productNum)) {
			productNum = "0";
		}
		if (productTaxRate == null || "".equals(productTaxRate)) {
			productTaxRate = "0%";
		}
		if (amountOfMoney == null || "".equals(amountOfMoney)) {
			amountOfMoney = "0";
		}
		String tax_bate = new BigDecimal(productTaxRate.replace("%", "")).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
		
		String unit_price = "";
		String count = new BigDecimal(productNum).multiply(new BigDecimal((Double.valueOf("1")+Double.valueOf(tax_bate))+"")).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
		if (!"0".equals(count) && !"0.0".equals(count) && !"0.00".equals(count)) {
			unit_price = new BigDecimal(amountOfMoney).divide(new BigDecimal(count), 2, BigDecimal.ROUND_HALF_EVEN).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
		}
		return unit_price;
	}
	
	/**
	 * 采购商品计算税金
	 * @param productNum
	 * @param unitPrice
	 * @param productTaxRate
	 * @return
	 */
	public static String Tax(String productNum, String unitPrice, String productTaxRate){
		//税金 = 单价*销售数量*税率
		if (productNum == null || "".equals(productNum)) {
			productNum = "0";
		}
		if (productTaxRate == null || "".equals(productTaxRate)) {
			productTaxRate = "0%";
		}
		if (unitPrice == null || "".equals(unitPrice)) {
			unitPrice = "0";
		}
		
		String tax_bate = new BigDecimal(productTaxRate.replace("%", "")).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
		
		String count = new BigDecimal(unitPrice).multiply(new BigDecimal(productNum)).toPlainString();
		String tax = new BigDecimal(count).multiply(new BigDecimal(tax_bate)).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
		return tax;
		
	}
	
	/**
	 * 无税率单价计算
	 * @param productNum
	 * @param productTaxRate
	 * @return
	 */
	public static String unitPriceNoTax(String productNum, String unitPrice, String productTaxRate){
		String text1 = "";
		if (unitPrice == null || "".equals(unitPrice)) {
			unitPrice = "0";
		}
		if(productTaxRate == null || "".equals(productTaxRate)) {
			productTaxRate = "0";
		}
		text1 = new BigDecimal(productTaxRate).divide(new BigDecimal(productNum), 2, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
		return text1;
	}
	
	/**
	 * 无税率单价计算
	 * @param productNum
	 * @param productTaxRate
	 * @return
	 */
	public static String productTotalNoTax(String productNum, String unitPrice, String productTaxRate){
		String text2;
		if (unitPrice == null || "".equals(unitPrice)) {
			unitPrice = "0";
		}
		if(productTaxRate == null || "".equals(productTaxRate)) {
			productTaxRate = "0";
		}
		text2 = new BigDecimal(productNum).multiply(new BigDecimal(unitPrice)).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
		return text2;
	}
	
	/**
	 * 设置金额分位及加金额符号
	 * @param money
	 * @return
	 */
	public static String getMoney(Context mContext, String money) {
		String str = "";
		if (money != null && !"".equals(money)) {
			str = money;
		}else {
			str = "0";
		}
		return mContext.getString(R.string.money_type) + FontUtils.setMoneyFormat(str);
	}
}
