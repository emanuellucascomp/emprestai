package br.com.emanuel.emprestai.adapter.inbound.web.dtos;

public class UpdateLoanStatusRequest {
    private String loanStatus;

    public UpdateLoanStatusRequest() {
    }

    public UpdateLoanStatusRequest(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }
}

