package hy.zc.wfj.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by feng on 2015/12/22.
 */
public class CommentObject implements Serializable {


    private static final long serialVersionUID = -476183777153667375L;
    /**
     * customerId : 459
     * date : 1451355455.919347
     * ordersId : 1192
     * evaluateGoods : [{"orderDetailId":123,"level":3,"content":"content"},{"orderDetailId":125,"level":2,"content":"content"}]
     */

    private int customerId;
    private String date;
    private int ordersId;
    /**
     * orderDetailId : 123
     * level : 3
     * content : content
     */

    private List<EvaluateGoodsEntity> evaluateGoods;

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public void setEvaluateGoods(List<EvaluateGoodsEntity> evaluateGoods) {
        this.evaluateGoods = evaluateGoods;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getDate() {
        return date;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public List<EvaluateGoodsEntity> getEvaluateGoods() {
        return evaluateGoods;
    }

    public static class EvaluateGoodsEntity implements Serializable{

        private static final long serialVersionUID = -3931707761345852306L;

        private int orderDetailId;
        private int level;
        private String content;

        public void setOrderDetailId(int orderDetailId) {
            this.orderDetailId = orderDetailId;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getOrderDetailId() {
            return orderDetailId;
        }

        public int getLevel() {
            return level;
        }

        public String getContent() {
            return content;
        }
    }
}

