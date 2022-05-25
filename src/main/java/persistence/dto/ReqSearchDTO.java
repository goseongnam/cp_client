package persistence.dto;

import java.io.Serializable;

public class ReqSearchDTO implements Serializable {
    private static final long serialVersionUID = 2L;
    private String dateInput;
    private String forex;

    public ReqSearchDTO(String dateInput, String forex) {
        this.dateInput = dateInput;
        this.forex = forex;
    }

    public ReqSearchDTO() {
    }

    public String getDateInput() {
        return dateInput;
    }

    public void setDateInput(String dateInput) {
        this.dateInput = dateInput;
    }

    public String getForex() {
        return forex;
    }

    public void setForex(String forex) {
        this.forex = forex;
    }
}
