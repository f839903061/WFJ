package hy.zc.wfj.data;

import java.io.Serializable;

/**
 * Created by feng on 2015/11/16.
 */
public class UserLoginObject implements Serializable{

    /**
     * status : true
     * Data : {"customerId":458,"email":"89652484@qq.com","isCanBuy":1,"lockedDate":null,"lockedState":0,"loginName":"89652484_ivwb","nickName":"傻帽","password":"ff92a240d11b05ebd392348c35f781b2","phone":"18511611111","photoUrl":"shop/image/customer/20151113/2015111315410002370621.png","registerDate":{"date":27,"day":2,"hours":15,"minutes":20,"month":9,"nanos":0,"seconds":19,"time":1445930419000,"timezoneOffset":-480,"year":115},"registerIp":"127.0.0.1","shareType":1,"type":3}
     * proCount : 2
     * shopCount : 0
     */

    private boolean status;
    /**
     * customerId : 458
     * email : 89652484@qq.com
     * isCanBuy : 1
     * lockedDate : null
     * lockedState : 0
     * loginName : 89652484_ivwb
     * nickName : 傻帽
     * password : ff92a240d11b05ebd392348c35f781b2
     * phone : 18511611111
     * photoUrl : shop/image/customer/20151113/2015111315410002370621.png
     * registerDate : {"date":27,"day":2,"hours":15,"minutes":20,"month":9,"nanos":0,"seconds":19,"time":1445930419000,"timezoneOffset":-480,"year":115}
     * registerIp : 127.0.0.1
     * shareType : 1
     * type : 3
     */

    private DataEntity Data;
    private int proCount;
    private int shopCount;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setData(DataEntity Data) {
        this.Data = Data;
    }

    public void setProCount(int proCount) {
        this.proCount = proCount;
    }

    public void setShopCount(int shopCount) {
        this.shopCount = shopCount;
    }

    public boolean isStatus() {
        return status;
    }

    public DataEntity getData() {
        return Data;
    }

    public int getProCount() {
        return proCount;
    }

    public int getShopCount() {
        return shopCount;
    }

    public static class DataEntity {
        private int customerId;
        private String email;
        private int isCanBuy;
        private Object lockedDate;
        private int lockedState;
        private String loginName;
        private String nickName;
        private String password;
        private String phone;
        private String photoUrl;
        /**
         * date : 27
         * day : 2
         * hours : 15
         * minutes : 20
         * month : 9
         * nanos : 0
         * seconds : 19
         * time : 1445930419000
         * timezoneOffset : -480
         * year : 115
         */

        private RegisterDateEntity registerDate;
        private String registerIp;
        private int shareType;
        private int type;

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setIsCanBuy(int isCanBuy) {
            this.isCanBuy = isCanBuy;
        }

        public void setLockedDate(Object lockedDate) {
            this.lockedDate = lockedDate;
        }

        public void setLockedState(int lockedState) {
            this.lockedState = lockedState;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public void setRegisterDate(RegisterDateEntity registerDate) {
            this.registerDate = registerDate;
        }

        public void setRegisterIp(String registerIp) {
            this.registerIp = registerIp;
        }

        public void setShareType(int shareType) {
            this.shareType = shareType;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCustomerId() {
            return customerId;
        }

        public String getEmail() {
            return email;
        }

        public int getIsCanBuy() {
            return isCanBuy;
        }

        public Object getLockedDate() {
            return lockedDate;
        }

        public int getLockedState() {
            return lockedState;
        }

        public String getLoginName() {
            return loginName;
        }

        public String getNickName() {
            return nickName;
        }

        public String getPassword() {
            return password;
        }

        public String getPhone() {
            return phone;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public RegisterDateEntity getRegisterDate() {
            return registerDate;
        }

        public String getRegisterIp() {
            return registerIp;
        }

        public int getShareType() {
            return shareType;
        }

        public int getType() {
            return type;
        }

        public static class RegisterDateEntity {
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
    }
}
