package nju.trust.payloads.verifyInfo;

import nju.trust.entity.user.CreditCrossCheck;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/21
 */
public class CrossCheckInfo {

    long id;

    private String studentId;

    private String institution;

    private String type;

    public CrossCheckInfo() {

    }

    public CrossCheckInfo(long id, String studentId, String institution, String type) {
        this.id = id;
        this.studentId = studentId;
        this.institution = institution;
        this.type = type;
    }

    public CrossCheckInfo(CreditCrossCheck creditCrossCheck){
        this.id = creditCrossCheck.getId();
        this.studentId = creditCrossCheck.getUser().getStudentId();
        this.institution =creditCrossCheck.getUser().getInstitution();
        if(creditCrossCheck.isRandom()){
            type="系统随机";
        }else{
            type="用户选择";
        }
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
