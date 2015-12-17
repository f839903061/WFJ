package hy.zc.wfj.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by feng on 2015/12/14.
 */
public class OrderListObject implements Serializable{

    /**
     * Status : true
     * Data : [{"address":"北京市东城区内环到三环里,银河sohoC座30601","consignee":"天山童姥","createTime":{"date":11,"day":5,"hours":16,"minutes":26,"month":11,"nanos":0,"seconds":11,"time":1449822371000,"timezoneOffset":-480,"year":115},"customerId":459,"finalAmount":150,"freight":0,"invoiceInfo":"","invoiceType":1,"list":[{"count":0,"logoImage":"shop/image/product/20150416/2015041617500000796233.png","orderDetailId":1492,"ordersId":1180,"ordersNo":"2015121116261111","productFullName":"耐克 运动T恤 [黑色]","productId":1559,"salesPrice":150,"shopInfoId":275,"shopName":"金利来专卖店"}],"mobilePhone":"15629773794","ordersId":459,"ordersNo":"2015121116261111","ordersState":5,"payMode":0,"sendType":0,"shopInfoId":0},{"address":"北京市东城区内环到三环里,银河sohoC座30601","consignee":"天山童姥","createTime":{"date":11,"day":5,"hours":16,"minutes":25,"month":11,"nanos":0,"seconds":40,"time":1449822340000,"timezoneOffset":-480,"year":115},"customerId":459,"finalAmount":6826,"freight":0,"invoiceInfo":"","invoiceType":1,"list":[{"count":0,"logoImage":"shop/image/product/20150402/2015040216370000821078.jpg","orderDetailId":1489,"ordersId":1179,"ordersNo":"2015121116254055","productFullName":"麦包包 手提包 [其它]","productId":845,"salesPrice":298,"shopInfoId":272,"shopName":"OPPO专卖店"},{"count":0,"logoImage":"shop/image/product/20150406/2015040609510001522610.png","orderDetailId":1490,"ordersId":1179,"ordersNo":"2015121116254055","productFullName":"微星 笔记本 [黑]","productId":998,"salesPrice":5999,"shopInfoId":272,"shopName":"OPPO专卖店"},{"count":0,"logoImage":"shop/image/product/20150403/2015040308410001199700.jpg","orderDetailId":1491,"ordersId":1179,"ordersNo":"2015121116254055","productFullName":"Story Of Shanghai/上海故事 丝巾","productId":877,"salesPrice":529,"shopInfoId":272,"shopName":"OPPO专卖店"}],"mobilePhone":"15629773794","ordersId":459,"ordersNo":"2015121116254055","ordersState":5,"payMode":0,"sendType":0,"shopInfoId":0}]
     */

    private boolean Status;
    /**
     * address : 北京市东城区内环到三环里,银河sohoC座30601
     * consignee : 天山童姥
     * createTime : {"date":11,"day":5,"hours":16,"minutes":26,"month":11,"nanos":0,"seconds":11,"time":1449822371000,"timezoneOffset":-480,"year":115}
     * customerId : 459
     * finalAmount : 150
     * freight : 0
     * invoiceInfo :
     * invoiceType : 1
     * list : [{"count":0,"logoImage":"shop/image/product/20150416/2015041617500000796233.png","orderDetailId":1492,"ordersId":1180,"ordersNo":"2015121116261111","productFullName":"耐克 运动T恤 [黑色]","productId":1559,"salesPrice":150,"shopInfoId":275,"shopName":"金利来专卖店"}]
     * mobilePhone : 15629773794
     * ordersId : 459
     * ordersNo : 2015121116261111
     * ordersState : 5
     * payMode : 0
     * sendType : 0
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

    public static class DataEntity {
        private String address;
        private String consignee;
        /**
         * date : 11
         * day : 5
         * hours : 16
         * minutes : 26
         * month : 11
         * nanos : 0
         * seconds : 11
         * time : 1449822371000
         * timezoneOffset : -480
         * year : 115
         */

        private CreateTimeEntity createTime;
        private int customerId;
        private int finalAmount;
        private int freight;
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
         * count : 0
         * logoImage : shop/image/product/20150416/2015041617500000796233.png
         * orderDetailId : 1492
         * ordersId : 1180
         * ordersNo : 2015121116261111
         * productFullName : 耐克 运动T恤 [黑色]
         * productId : 1559
         * salesPrice : 150
         * shopInfoId : 275
         * shopName : 金利来专卖店
         */

        private List<ListEntity> list;

        public void setAddress(String address) {
            this.address = address;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public void setCreateTime(CreateTimeEntity createTime) {
            this.createTime = createTime;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public void setFinalAmount(int finalAmount) {
            this.finalAmount = finalAmount;
        }

        public void setFreight(int freight) {
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

        public CreateTimeEntity getCreateTime() {
            return createTime;
        }

        public int getCustomerId() {
            return customerId;
        }

        public int getFinalAmount() {
            return finalAmount;
        }

        public int getFreight() {
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

        public int getOrdersId() {
            return ordersId;
        }

        public String getOrdersNo() {
            return ordersNo;
        }

        /**
         * @return 1、未处理(生成订单)；2、已付款；3、正在配货；4、已发货；5、已收货；6、订单取消；7、异常订单(退换货等、问题订单)；9、已评价；
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

        public static class CreateTimeEntity {
            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public void setDate(int date) {
                this.date = date;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getDate() {
                return date;
            }

            public int getDay() {
                return day;
            }

            public int getHours() {
                return hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public int getMonth() {
                return month;
            }

            public int getNanos() {
                return nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public long getTime() {
                return time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public int getYear() {
                return year;
            }
        }

        public static class ListEntity {
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
