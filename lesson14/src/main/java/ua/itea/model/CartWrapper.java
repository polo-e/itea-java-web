package ua.itea.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class CartWrapper {

    private String productId;
    private String count;
    private String total;
    private boolean isReloadPage = false;

    public CartWrapper() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCount() {
        return count;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public boolean isReloadPage() {
        return isReloadPage;
    }

    public void setReloadPage(boolean reloadPage) {
        isReloadPage = reloadPage;
    }
}
