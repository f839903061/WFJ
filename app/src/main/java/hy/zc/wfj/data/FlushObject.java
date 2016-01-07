package hy.zc.wfj.data;

import java.io.Serializable;

/**
 * Created by feng on 2016/1/6.
 */
public class FlushObject  implements Serializable{


    private static final long serialVersionUID = -3327178484794614272L;
    /**
     * Status : true
     * Data : {"customerId":459,"email":"237443659@qq.com","isCanBuy":1,"lockedDate":null,"lockedState":0,"loginName":"hkl_xjgs","nickName":"Lucy","password":"14e1b600b1fd579f47433b88e8d85291","phone":"15629883399","photoUrl":"","registerDate":{"date":11,"day":5,"hours":16,"minutes":20,"month":11,"nanos":0,"seconds":32,"time":1449822032000,"timezoneOffset":-480,"year":115},"registerIp":"0:0:0:0:0:0:0:1","shareType":1,"type":3}
     * proCount : 0
     * shopCount : 0
     * obligation : 2
     * undelivered : 0
     * unreceived : 0
     * unevaluated : 0
     * returned : 0
     */

    private boolean Status;
    /**
     * customerId : 459
     * email : 237443659@qq.com
     * isCanBuy : 1
     * lockedDate : null
     * lockedState : 0
     * loginName : hkl_xjgs
     * nickName : Lucy
     * password : 14e1b600b1fd579f47433b88e8d85291
     * phone : 15629883399
     * photoUrl :
     * registerDate : {"date":11,"day":5,"hours":16,"minutes":20,"month":11,"nanos":0,"seconds":32,"time":1449822032000,"timezoneOffset":-480,"year":115}
     * registerIp : 0:0:0:0:0:0:0:1
     * shareType : 1
     * type : 3
     */

    private DataEntity Data;
    private int proCount;
    private int shopCount;
    private int obligation;
    private int undelivered;
    private int unreceived;
    private int unevaluated;
    private int returned;

    public void setStatus(boolean Status) {
        this.Status = Status;
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

    public void setObligation(int obligation) {
        this.obligation = obligation;
    }

    public void setUndelivered(int undelivered) {
        this.undelivered = undelivered;
    }

    public void setUnreceived(int unreceived) {
        this.unreceived = unreceived;
    }

    public void setUnevaluated(int unevaluated) {
        this.unevaluated = unevaluated;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public boolean isStatus() {
        return Status;
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

    public int getObligation() {
        return obligation;
    }

    public int getUndelivered() {
        return undelivered;
    }

    public int getUnreceived() {
        return unreceived;
    }

    public int getUnevaluated() {
        return unevaluated;
    }

    public int getReturned() {
        return returned;
    }

    public static class DataEntity implements Serializable{

        private static final long serialVersionUID = -2338497155059601817L;
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
         * date : 11
         * day : 5
         * hours : 16
         * minutes : 20
         * month : 11
         * nanos : 0
         * seconds : 32
         * time : 1449822032000
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

        public static class RegisterDateEntity implements Serializable{

            private static final long serialVersionUID = -5429028348199881731L;
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
