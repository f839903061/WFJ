package hy.zc.wfj.data;

/**
 * Created by feng on 2015/11/17.
 */
public class UserLoginErrorObject {

    /**
     * status : false
     * Data : 用户名或密码错误
     */

    private boolean status;
    private String Data;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public boolean isStatus() {
        return status;
    }

    public String getData() {
        return Data;
    }
}
