package com.eng_sherif.modern_engineering_industries.Model;

public class Machine {

    private String userId;
    private String filing, closingCapping, packing, handlingProcess;

    public Machine() {
    }

    public Machine(String userId, String filing, String closingCapping, String packing, String handlingProcess) {
        this.userId = userId;
        this.filing = filing;
        this.closingCapping = closingCapping;
        this.packing = packing;
        this.handlingProcess = handlingProcess;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFiling() {
        return filing;
    }

    public void setFiling(String filing) {
        this.filing = filing;
    }

    public String getClosingCapping() {
        return closingCapping;
    }

    public void setClosingCapping(String closingCapping) {
        this.closingCapping = closingCapping;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getHandlingProcess() {
        return handlingProcess;
    }

    public void setHandlingProcess(String handlingProcess) {
        this.handlingProcess = handlingProcess;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "userId='" + userId + '\'' +
                ", filing='" + filing + '\'' +
                ", closingCapping='" + closingCapping + '\'' +
                ", packing='" + packing + '\'' +
                ", handlingProcess='" + handlingProcess + '\'' +
                '}';
    }
}
