package br.com.emanuel.emprestai.adapter.inbound.web.dtos;

public class UpdateInstallmentStatusRequest {
    private String installmentStatus;

    public UpdateInstallmentStatusRequest() {
    }

    public UpdateInstallmentStatusRequest(String installmentStatus) {
        this.installmentStatus = installmentStatus;
    }

    public String getInstallmentStatus() {
        return installmentStatus;
    }

    public void setInstallmentStatus(String installmentStatus) {
        this.installmentStatus = installmentStatus;
    }
}

