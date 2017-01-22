package com.cxb.accountbooklibrary.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class AllListItem implements Serializable {
	private String id;
	private String name;
	private String type;
	private String classify;
	private boolean isChecked = false;
	private boolean isEdit = false;
	private boolean noEdit = false;
	private int taxpayerType;
	private ArrayList<AccountingBusinessItems> accountingBusinessItems;

	/**
	 * @return the taxpayerType
	 */
	public int getTaxpayerType() {
		return taxpayerType;
	}

	/**
	 * @param taxpayerType
	 *            the taxpayerType to set
	 */
	public void setTaxpayerType(int taxpayerType) {
		this.taxpayerType = taxpayerType;
	}

	private String initial;

	/**
	 * @return the noEdit
	 */
	public boolean isNoEdit() {
		return noEdit;
	}

	/**
	 * @param noEdit
	 *            the noEdit to set
	 */
	public void setNoEdit(boolean noEdit) {
		this.noEdit = noEdit;
	}

	/**
	 * @return the isEdit
	 */
	public boolean isEdit() {
		return isEdit;
	}

	/**
	 * @param isEdit
	 *            the isEdit to set
	 */
	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	/**
	 * @return the isChecked
	 */
	public boolean isChecked() {
		return isChecked;
	}

	/**
	 * @param isChecked
	 *            the isChecked to set
	 */
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the initial
	 */
	public String getInitial() {
		return initial;
	}

	/**
	 * @param initial
	 *            the initial to set
	 */
	public void setInitial(String initial) {
		this.initial = initial;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the classify
	 */
	public String getClassify() {
		return classify;
	}

	/**
	 * @param classify
	 *            the classify to set
	 */
	public void setClassify(String classify) {
		this.classify = classify;
	}

	/**
	 * @return the accountingBusinessItems
	 */
	public ArrayList<AccountingBusinessItems> getAccountingBusinessItems() {
		return accountingBusinessItems;
	}

	/**
	 * @param accountingBusinessItems
	 *            the accountingBusinessItems to set
	 */
	public void setAccountingBusinessItems(
			ArrayList<AccountingBusinessItems> accountingBusinessItems) {
		this.accountingBusinessItems = accountingBusinessItems;
	}

	public class AccountingBusinessItems implements Serializable {
		private String id;
		private MainAccountItem accountItemByDetailAccountItem;
		private MainAccountItem accountItemByMainAccountItem;
		private String direction;
		private String required;
		private String collectionItem;
		private String money = "0";
		private String colectionName;
		private String augmentOrReduce; // 0增加，1递减
//		private FormulaBean formula; // 公式对象

		/**
		 * @return the money
		 */
		public String getMoney() {
			return money;
		}

		/**
		 * @param money
		 *            the money to set
		 */
		public void setMoney(String money) {
			this.money = money;
		}

		/**
		 * @return the colectionName
		 */
		public String getColectionName() {
			return colectionName;
		}

		/**
		 * @param colectionName
		 *            the colectionName to set
		 */
		public void setColectionName(String colectionName) {
			this.colectionName = colectionName;
		}

//		/**
//		 * @return the formula
//		 */
//		public FormulaBean getFormula() {
//			return formula;
//		}
//
//		/**
//		 * @param formula
//		 *            the formula to set
//		 */
//		public void setFormula(FormulaBean formula) {
//			this.formula = formula;
//		}

		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the accountItemByDetailAccountItem
		 */
		public MainAccountItem getAccountItemByDetailAccountItem() {
			return accountItemByDetailAccountItem;
		}

		/**
		 * @param accountItemByDetailAccountItem
		 *            the accountItemByDetailAccountItem to set
		 */
		public void setAccountItemByDetailAccountItem(
				MainAccountItem accountItemByDetailAccountItem) {
			this.accountItemByDetailAccountItem = accountItemByDetailAccountItem;
		}

		/**
		 * @return the accountItemByMainAccountItem
		 */
		public MainAccountItem getAccountItemByMainAccountItem() {
			return accountItemByMainAccountItem;
		}

		/**
		 * @param accountItemByMainAccountItem
		 *            the accountItemByMainAccountItem to set
		 */
		public void setAccountItemByMainAccountItem(
				MainAccountItem accountItemByMainAccountItem) {
			this.accountItemByMainAccountItem = accountItemByMainAccountItem;
		}

		/**
		 * @return the direction
		 */
		public String getDirection() {
			return direction;
		}

		/**
		 * @param direction
		 *            the direction to set
		 */
		public void setDirection(String direction) {
			this.direction = direction;
		}

		/**
		 * @return the required
		 */
		public String getRequired() {
			return required;
		}

		/**
		 * @param required
		 *            the required to set
		 */
		public void setRequired(String required) {
			this.required = required;
		}

		/**
		 * @return the collectionItem
		 */
		public String getCollectionItem() {
			return collectionItem;
		}

		/**
		 * @param collectionItem
		 *            the collectionItem to set
		 */
		public void setCollectionItem(String collectionItem) {
			this.collectionItem = collectionItem;
		}

		/**
		 * @return the augmentOrReduce
		 */
		public String getAugmentOrReduce() {
			return augmentOrReduce;
		}

		/**
		 * @param augmentOrReduce
		 *            the augmentOrReduce to set
		 */
		public void setAugmentOrReduce(String augmentOrReduce) {
			this.augmentOrReduce = augmentOrReduce;
		}

	}

	public class MainAccountItem implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8128292813180947567L;
		private String id;
		private String name;
		private String code;
		private String oldCode;
		private String status;
		private int taxpayerType;
		private ArrayList<MainAccountItem> accountItems;
		private ArrayList<FormulaHelps> formulaHelps; // 是否有公式

		/**
		 * @return the formulaHelps
		 */
		public ArrayList<FormulaHelps> getFormulaHelps() {
			return formulaHelps;
		}

		/**
		 * @param formulaHelps
		 *            the formulaHelps to set
		 */
		public void setFormulaHelps(ArrayList<FormulaHelps> formulaHelps) {
			this.formulaHelps = formulaHelps;
		}

		/**
		 * @return the oldCode
		 */
		public String getOldCode() {
			return oldCode;
		}

		/**
		 * @param oldCode
		 *            the oldCode to set
		 */
		public void setOldCode(String oldCode) {
			this.oldCode = oldCode;
		}

		/**
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}

		/**
		 * @param status
		 *            the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}

		/**
		 * @return the accountItems
		 */
		public ArrayList<MainAccountItem> getAccountItems() {
			return accountItems;
		}

		/**
		 * @param accountItems
		 *            the accountItems to set
		 */
		public void setAccountItems(ArrayList<MainAccountItem> accountItems) {
			this.accountItems = accountItems;
		}

		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		/**
		 * @param code
		 *            the code to set
		 */
		public void setCode(String code) {
			this.code = code;
		}

	}

	// public class DetailAccountItem implements Serializable {
	// private String id;
	// private String name;
	// private String code;
	//
	// /**
	// * @return the id
	// */
	// public String getId() {
	// return id;
	// }
	//
	// /**
	// * @param id
	// * the id to set
	// */
	// public void setId(String id) {
	// this.id = id;
	// }
	//
	// /**
	// * @return the name
	// */
	// public String getName() {
	// return name;
	// }
	//
	// /**
	// * @param name
	// * the name to set
	// */
	// public void setName(String name) {
	// this.name = name;
	// }
	//
	// /**
	// * @return the code
	// */
	// public String getCode() {
	// return code;
	// }
	//
	// /**
	// * @param code
	// * the code to set
	// */
	// public void setCode(String code) {
	// this.code = code;
	// }
	// }
	public class AccountItems {

	}

	public class FormulaHelps {

	}
}
