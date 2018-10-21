package nju.trust.payloads.verifyInfo;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/21
 */
public class NameAndEvidence {

    //证书或奖项名称
    private String value;

    //证明路径
    private String file;

    public NameAndEvidence() {

    }

    public NameAndEvidence(String value, String file) {
        this.value = value;
        this.file = file;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
