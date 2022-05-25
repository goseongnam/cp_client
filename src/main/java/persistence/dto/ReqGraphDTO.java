package persistence.dto;

import java.io.Serializable;

public class ReqGraphDTO implements Serializable {
    private static final long serialVersionUID = 2L;
    private String startDate;
    private String endDate;
    private String forex;

    public ReqGraphDTO(String startDate, String endDate, String forex) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.forex = forex;
    }

    public ReqGraphDTO() {
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getForex() {
        return forex;
    }

    public void setForex(String forex) {
        this.forex = forex;
    }
}