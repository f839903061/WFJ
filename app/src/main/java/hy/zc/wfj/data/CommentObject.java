package hy.zc.wfj.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by feng on 2015/12/22.
 */
public class CommentObject implements Serializable {

    private static final long serialVersionUID = -5310403520527922965L;

    /**
     * orderDetailId : 1234
     * level : 123
     * content : 评价
     */

    private List<OrdersIdEntity> ordersId;

    public void setOrdersId(List<OrdersIdEntity> ordersId) {
        this.ordersId = ordersId;
    }

    public List<OrdersIdEntity> getOrdersId() {
        return ordersId;
    }

    public static class OrdersIdEntity implements Serializable{

        private static final long serialVersionUID = -3217802492039391886L;

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
