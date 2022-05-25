package persistence.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ResGraphDTO implements Serializable {
    private static final long serialVersionUID = 2L;
    private ArrayList<String> list;

    public ResGraphDTO(ArrayList<String> list) {
        this.list = list;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }
}
