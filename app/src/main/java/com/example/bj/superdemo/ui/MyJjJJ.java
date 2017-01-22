package com.example.bj.superdemo.ui;

import java.util.List;

/**
 * Created by Administrator on 2017/1/15.
 */

public class MyJjJJ {

    /**
     * customId : 1
     * tax : 1
     * cost : 2
     * loss : 3
     * productList : [{"productId":1,"productNum":2,"productUnitPrice":3,"productSumMoeny":4}]
     */

    private String customId;
    private String tax;
    private String cost;
    private String loss;
    private List<ProductListBean> productList;

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getLoss() {
        return loss;
    }

    public void setLoss(String loss) {
        this.loss = loss;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class ProductListBean {
        /**
         * productId : 1
         * productNum : 2
         * productUnitPrice : 3
         * productSumMoeny : 4
         */

        private String productId;
        private String productNum;
        private String productUnitPrice;
        private String productSumMoeny;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductNum() {
            return productNum;
        }

        public void setProductNum(String productNum) {
            this.productNum = productNum;
        }

        public String getProductUnitPrice() {
            return productUnitPrice;
        }

        public void setProductUnitPrice(String productUnitPrice) {
            this.productUnitPrice = productUnitPrice;
        }

        public String getProductSumMoeny() {
            return productSumMoeny;
        }

        public void setProductSumMoeny(String productSumMoeny) {
            this.productSumMoeny = productSumMoeny;
        }
    }
}
