package nju.trust.entity.user;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/8/28
 * Description:
 */
@Entity
public class UserMonthStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;

    private Double dress;

    private Double learning;

    private Double dailyNecessity;

    /**
     * 经常性支出
     */
    private Double recurrentExpenditure;

    private Double medical;

    /**
     * 偶发性支出
     */
    private Double sporadicExpenditure;

    private Double schoolCanteen;

    /**
     * 外卖
     */
    private Double takeOut;

    /**
     * 外出就餐
     */
    private Double eatingOut;

    /**
     * 零食水果
     */
    private Double snackAndFruit;

    private Double hotel;

    private Double dailyTransportation;

    /**
     * 汽车票火车票机票等
     */
    private Double ticket;

    private Double electronicDevices;

    private Double hobby;

    /**
     * 蚂蚁财富及公益支出
     */
    private Double antWealthAndPublicWelfare;

    /**
     * 余额宝
     */
    private Double yuEBao;

    private Double insurance;

    /**
     * 红包
     */
    private Double redEnvelope;

    /**
     * 蚂蚁花呗
     */
    private Double antCheckLater;

    /**
     * 转账收款
     */
    private Double transferReceipt;

    /**
     * 转账支出
     */
    private Double transferExpenses;

    private Double income;

    private Double expense;

    private Double surplus;

    private Double rigid;

    /**
     * 可调指出
     */
    private Double disc;

    /**
     * 日常支出
     */
    private Double daily;

    /**
     * 月负债
     */
    private Double debt;

    /**
     * 净资产
     */
    private Double asset;

    /**
     * 月出行
     */
    private Double travel;

    /**
     * 月娱乐
     */
    private Double fun;

    /**
     * 月饮食
     */
    private Double food;

    /**
     * 恩格尔系数
     */
    private Double engel;

    private Double rigidRatio;

    private Double dailyToAll;

    private Double funToAll;

    private Double foodToAll;

    private Double travelToAll;

    private Double debtToAssetRatio;

    private Double debtPayingAbility;

    private Double leverage;

    private Double consumptionRatio;

    private Double savingRatio; // 储蓄率

    private Double liability;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getDress() {
        return dress;
    }

    public void setDress(Double dress) {
        this.dress = dress;
    }

    public Double getLearning() {
        return learning;
    }

    public void setLearning(Double learning) {
        this.learning = learning;
    }

    public Double getDailyNecessity() {
        return dailyNecessity;
    }

    public void setDailyNecessity(Double dailyNecessity) {
        this.dailyNecessity = dailyNecessity;
    }

    public Double getRecurrentExpenditure() {
        return recurrentExpenditure;
    }

    public void setRecurrentExpenditure(Double recurrentExpenditure) {
        this.recurrentExpenditure = recurrentExpenditure;
    }

    public Double getMedical() {
        return medical;
    }

    public void setMedical(Double medical) {
        this.medical = medical;
    }

    public Double getSporadicExpenditure() {
        return sporadicExpenditure;
    }

    public void setSporadicExpenditure(Double sporadicExpenditure) {
        this.sporadicExpenditure = sporadicExpenditure;
    }

    public Double getSchoolCanteen() {
        return schoolCanteen;
    }

    public void setSchoolCanteen(Double schoolCanteen) {
        this.schoolCanteen = schoolCanteen;
    }

    public Double getTakeOut() {
        return takeOut;
    }

    public void setTakeOut(Double takeOut) {
        this.takeOut = takeOut;
    }

    public Double getEatingOut() {
        return eatingOut;
    }

    public void setEatingOut(Double eatingOut) {
        this.eatingOut = eatingOut;
    }

    public Double getSnackAndFruit() {
        return snackAndFruit;
    }

    public void setSnackAndFruit(Double snackAndFruit) {
        this.snackAndFruit = snackAndFruit;
    }

    public Double getHotel() {
        return hotel;
    }

    public void setHotel(Double hotel) {
        this.hotel = hotel;
    }

    public Double getDailyTransportation() {
        return dailyTransportation;
    }

    public void setDailyTransportation(Double dailyTransportation) {
        this.dailyTransportation = dailyTransportation;
    }

    public Double getTicket() {
        return ticket;
    }

    public void setTicket(Double ticket) {
        this.ticket = ticket;
    }

    public Double getElectronicDevices() {
        return electronicDevices;
    }

    public void setElectronicDevices(Double electronicDevices) {
        this.electronicDevices = electronicDevices;
    }

    public Double getHobby() {
        return hobby;
    }

    public void setHobby(Double hobby) {
        this.hobby = hobby;
    }

    public Double getAntWealthAndPublicWelfare() {
        return antWealthAndPublicWelfare;
    }

    public void setAntWealthAndPublicWelfare(Double antWealthAndPublicWelfare) {
        this.antWealthAndPublicWelfare = antWealthAndPublicWelfare;
    }

    public Double getYuEBao() {
        return yuEBao;
    }

    public void setYuEBao(Double yuEBao) {
        this.yuEBao = yuEBao;
    }

    public Double getInsurance() {
        return insurance;
    }

    public void setInsurance(Double insurance) {
        this.insurance = insurance;
    }

    public Double getRedEnvelope() {
        return redEnvelope;
    }

    public void setRedEnvelope(Double redEnvelope) {
        this.redEnvelope = redEnvelope;
    }

    public Double getAntCheckLater() {
        return antCheckLater;
    }

    public void setAntCheckLater(Double antCheckLater) {
        this.antCheckLater = antCheckLater;
    }

    public Double getTransferReceipt() {
        return transferReceipt;
    }

    public void setTransferReceipt(Double transferReceipt) {
        this.transferReceipt = transferReceipt;
    }

    public Double getTransferExpenses() {
        return transferExpenses;
    }

    public void setTransferExpenses(Double transferExpenses) {
        this.transferExpenses = transferExpenses;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public Double getSurplus() {
        return surplus;
    }

    public void setSurplus(Double surplus) {
        this.surplus = surplus;
    }

    public Double getRigid() {
        return rigid;
    }

    public void setRigid(Double rigid) {
        this.rigid = rigid;
    }

    public Double getDisc() {
        return disc;
    }

    public void setDisc(Double disc) {
        this.disc = disc;
    }

    public Double getDaily() {
        return daily;
    }

    public void setDaily(Double daily) {
        this.daily = daily;
    }

    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
    }

    public Double getAsset() {
        return asset;
    }

    public void setAsset(Double asset) {
        this.asset = asset;
    }

    public Double getTravel() {
        return travel;
    }

    public void setTravel(Double travel) {
        this.travel = travel;
    }

    public Double getFun() {
        return fun;
    }

    public void setFun(Double fun) {
        this.fun = fun;
    }

    public Double getFood() {
        return food;
    }

    public void setFood(Double food) {
        this.food = food;
    }

    public Double getEngel() {
        return engel;
    }

    public void setEngel(Double engel) {
        this.engel = engel;
    }

    public Double getRigidRatio() {
        return rigidRatio;
    }

    public void setRigidRatio(Double rigidRatio) {
        this.rigidRatio = rigidRatio;
    }

    public Double getDailyToAll() {
        return dailyToAll;
    }

    public void setDailyToAll(Double dailyToAll) {
        this.dailyToAll = dailyToAll;
    }

    public Double getFunToAll() {
        return funToAll;
    }

    public void setFunToAll(Double funToAll) {
        this.funToAll = funToAll;
    }

    public Double getFoodToAll() {
        return foodToAll;
    }

    public void setFoodToAll(Double foodToAll) {
        this.foodToAll = foodToAll;
    }

    public Double getTravelToAll() {
        return travelToAll;
    }

    public void setTravelToAll(Double travelToAll) {
        this.travelToAll = travelToAll;
    }

    public Double getDebtToAssetRatio() {
        return debtToAssetRatio;
    }

    public void setDebtToAssetRatio(Double debtToAssetRatio) {
        this.debtToAssetRatio = debtToAssetRatio;
    }

    public Double getDebtPayingAbility() {
        return debtPayingAbility;
    }

    public void setDebtPayingAbility(Double debtPayingAbility) {
        this.debtPayingAbility = debtPayingAbility;
    }

    public Double getLeverage() {
        return leverage;
    }

    public void setLeverage(Double leverage) {
        this.leverage = leverage;
    }

    public Double getConsumptionRatio() {
        return consumptionRatio;
    }

    public void setConsumptionRatio(Double consumptionRatio) {
        this.consumptionRatio = consumptionRatio;
    }

    public Double getSavingRatio() {
        return savingRatio;
    }

    public void setSavingRatio(Double savingRatio) {
        this.savingRatio = savingRatio;
    }

    public Double getLiability() {
        return liability;
    }

    public void setLiability(Double liability) {
        this.liability = liability;
    }
}
