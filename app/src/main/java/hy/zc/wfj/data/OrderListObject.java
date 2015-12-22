package hy.zc.wfj.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by feng on 2015/12/14.
 */
public class OrderListObject implements Serializable{


    private static final long serialVersionUID = -710891667491152880L;
    /**
     * Status : true
     * Data : [{"address":"北京海淀区","consignee":"wh","creatTime":"2014-11-28 18:33:58.0","createTime":null,"customerId":324,"finalAmount":10,"freight":0,"invoiceInfo":"","invoiceType":1,"list":[{"count":1,"logoImage":"shop/image/product/20141128/2014112813010005572024.jpg","orderDetailId":1103,"ordersId":850,"ordersNo":"20141128183358493866","productFullName":"晨光 环保铅笔 [红色,大]","productId":648,"salesPrice":10,"shopInfoId":170,"shopName":"宛寺旗舰店"}],"mobilePhone":"13121358968","ordersId":324,"ordersNo":"20141128183358493866","ordersState":6,"payMode":1,"sendType":2,"shopInfoId":0},{"address":"北京海淀区","consignee":"wh","creatTime":"2014-11-28 18:35:23.0","createTime":null,"customerId":324,"finalAmount":20,"freight":10,"invoiceInfo":"","invoiceType":1,"list":[{"count":1,"logoImage":"shop/image/product/20141128/2014112813010005572024.jpg","orderDetailId":1104,"ordersId":851,"ordersNo":"2014112818352315","productFullName":"晨光 环保铅笔 [红色,大]","productId":648,"salesPrice":10,"shopInfoId":170,"shopName":"宛寺旗舰店"}],"mobilePhone":"13121358968","ordersId":324,"ordersNo":"2014112818352315","ordersState":9,"payMode":1,"sendType":2,"shopInfoId":0}]
     */

    private boolean Status;
    /**
     * address : 北京海淀区
     * consignee : wh
     * creatTime : 2014-11-28 18:33:58.0
     * createTime : null
     * customerId : 324
     * finalAmount : 10
     * freight : 0
     * invoiceInfo :
     * invoiceType : 1
     * list : [{"count":1,"logoImage":"shop/image/product/20141128/2014112813010005572024.jpg","orderDetailId":1103,"ordersId":850,"ordersNo":"20141128183358493866","productFullName":"晨光 环保铅笔 [红色,大]","productId":648,"salesPrice":10,"shopInfoId":170,"shopName":"宛寺旗舰店"}]
     * mobilePhone : 13121358968
     * ordersId : 324
     * ordersNo : 20141128183358493866
     * ordersState : 6
     * payMode : 1
     * sendType : 2
     * shopInfoId : 0
     */

    private List<DataEntity> Data;

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public void setData(List<DataEntity> Data) {
        this.Data = Data;
    }

    public boolean isStatus() {
        return Status;
    }

    public List<DataEntity> getData() {
        return Data;
    }

    public static class DataEntity implements Serializable{

        private static final long serialVersionUID = -7555173734611129886L;
        private String address;
        private String consignee;
        private String creatTime;
        private Object createTime;
        private int customerId;
        private float finalAmount;
        private float freight;
        private String invoiceInfo;
        private int invoiceType;
        private String mobilePhone;
        private int ordersId;
        private String ordersNo;
        private int ordersState;
        private int payMode;
        private int sendType;
        private int shopInfoId;
        /**
         * count : 1
         * logoImage : shop/image/product/20141128/2014112813010005572024.jpg
         * orderDetailId : 1103
         * ordersId : 850
         * ordersNo : 20141128183358493866
         * productFullName : 晨光 环保铅笔 [红色,大]
         * productId : 648
         * salesPrice : 10
         * shopInfoId : 170
         * shopName : 宛寺旗舰店
         */

        private List<ListEntity> list;

        public void setAddress(String address) {
            this.address = address;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public void setCreatTime(String creatTime) {
            this.creatTime = creatTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public void setFinalAmount(float finalAmount) {
            this.finalAmount = finalAmount;
        }

        public void setFreight(float freight) {
            this.freight = freight;
        }

        public void setInvoiceInfo(String invoiceInfo) {
            this.invoiceInfo = invoiceInfo;
        }

        public void setInvoiceType(int invoiceType) {
            this.invoiceType = invoiceType;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public void setOrdersId(int ordersId) {
            this.ordersId = ordersId;
        }

        public void setOrdersNo(String ordersNo) {
            this.ordersNo = ordersNo;
        }

        public void setOrdersState(int ordersState) {
            this.ordersState = ordersState;
        }

        public void setPayMode(int payMode) {
            this.payMode = payMode;
        }

        public void setSendType(int sendType) {
            this.sendType = sendType;
        }

        public void setShopInfoId(int shopInfoId) {
            this.shopInfoId = shopInfoId;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public String getAddress() {
            return address;
        }

        public String getConsignee() {
            return consignee;
        }

        public String getCreatTime() {
            return creatTime;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public int getCustomerId() {
            return customerId;
        }

        public float getFinalAmount() {
            return finalAmount;
        }

        public float getFreight() {
            return freight;
        }

        public String getInvoiceInfo() {
            return invoiceInfo;
        }

        public int getInvoiceType() {
            return invoiceType;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public float getOrdersId() {
            return ordersId;
        }

        public String getOrdersNo() {
            return ordersNo;
        }

        /**
         * @return 1、未处理(生成订单)；2、已付款；3、正在配货；4、已发货；5、已收货；6、订单取消；7、异常订单(退换货等、问题订单)；9、已评价
         */
        public int getOrdersState() {
            return ordersState;
        }

        public int getPayMode() {
            return payMode;
        }

        public int getSendType() {
            return sendType;
        }

        public int getShopInfoId() {
            return shopInfoId;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public static class ListEntity implements Serializable {

            private static final long serialVersionUID = -4898199389151834737L;
            private int count;
            private String logoImage;
            private int orderDetailId;
            private int ordersId;
            private String ordersNo;
            private String productFullName;
            private int productId;
            private float salesPrice;
            private int shopInfoId;
            private String shopName;

            public void setCount(int count) {
                this.count = count;
            }

            public void setLogoImage(String logoImage) {
                this.logoImage = logoImage;
            }

            public void setOrderDetailId(int orderDetailId) {
                this.orderDetailId = orderDetailId;
            }

            public void setOrdersId(int ordersId) {
                this.ordersId = ordersId;
            }

            public void setOrdersNo(String ordersNo) {
                this.ordersNo = ordersNo;
            }

            public void setProductFullName(String productFullName) {
                this.productFullName = productFullName;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public void setSalesPrice(float salesPrice) {
                this.salesPrice = salesPrice;
            }

            public void setShopInfoId(int shopInfoId) {
                this.shopInfoId = shopInfoId;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public int getCount() {
                return count;
            }

            public String getLogoImage() {
                return logoImage;
            }

            public int getOrderDetailId() {
                return orderDetailId;
            }

            public int getOrdersId() {
                return ordersId;
            }

            public String getOrdersNo() {
                return ordersNo;
            }

            public String getProductFullName() {
                return productFullName;
            }

            public int getProductId() {
                return productId;
            }

            public float getSalesPrice() {
                return salesPrice;
            }

            public int getShopInfoId() {
                return shopInfoId;
            }

            public String getShopName() {
                return shopName;
            }
        }
    }
}
