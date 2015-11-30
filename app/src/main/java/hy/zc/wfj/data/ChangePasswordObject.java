package hy.zc.wfj.data;

/**
 * Created by feng on 2015/11/30.
 */
public class ChangePasswordObject {

    /**
     * Status : false
     * Data : 原密码不正确
     */

    private boolean Status;
    private String Data;

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public boolean isStatus() {
        return Status;
    }

    public String getData() {
        return Data;
    }
}
