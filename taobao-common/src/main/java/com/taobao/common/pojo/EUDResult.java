package com.taobao.common.pojo;

import java.util.List;

/**
 * Created by chenwei on 2017/10/18.
 */
public class EUDResult {

    private long total;
    private List<?> rows;
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List<?> getRows() {
        return rows;
    }
    public void setRows(List<?> rows) {
        this.rows = rows;
    }
    public EUDResult(long total, List<?> rows) {
        super();
        this.total = total;
        this.rows = rows;
    }
    public EUDResult() {
        super();
    }
}
