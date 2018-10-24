package nju.trust.payloads.target;

import java.time.LocalDate;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/10/24
 * Description:
 */
public class SurplusPrediction {

    private List<LocalDate> time;

    private List<Double> Kn;

    private List<Double> An;

    private List<TableItem> tableData;

    public SurplusPrediction(List<LocalDate> time, List<Double> kn, List<Double> an, List<TableItem> tableData) {
        this.time = time;
        Kn = kn;
        An = an;
        this.tableData = tableData;
    }

    public List<LocalDate> getTime() {
        return time;
    }

    public List<Double> getKn() {
        return Kn;
    }

    public List<Double> getAn() {
        return An;
    }

    public List<TableItem> getTableData() {
        return tableData;
    }

    public static class TableItem {

        private Integer month;

        private Double valX;

        private Double valY;

        private Double valZ;

        public TableItem(Integer month, Double valX, Double valY, Double valZ) {
            this.month = month;
            this.valX = valX;
            this.valY = valY;
            this.valZ = valZ;
        }

        public Integer getMonth() {
            return month;
        }

        public Double getValX() {
            return valX;
        }

        public Double getValY() {
            return valY;
        }

        public Double getValZ() {
            return valZ;
        }
    }
}
