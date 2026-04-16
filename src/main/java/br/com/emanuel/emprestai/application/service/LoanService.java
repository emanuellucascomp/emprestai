package br.com.emanuel.emprestai.application.service;

import br.com.emanuel.emprestai.adapter.inbound.mapper.LoanMapper;
import br.com.emanuel.emprestai.adapter.inbound.mapper.InstallmentMapper;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoanRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoanResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.InstallmentResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateLoanStatusRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateInstallmentStatusRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateLoanRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateInstallmentRequest;
import br.com.emanuel.emprestai.application.usecases.CreateLoanUseCase;
import br.com.emanuel.emprestai.application.usecases.GetLoanUseCase;
import br.com.emanuel.emprestai.application.usecases.UpdateLoanStatusUseCase;
import br.com.emanuel.emprestai.application.usecases.UpdateInstallmentStatusUseCase;
import br.com.emanuel.emprestai.application.usecases.UpdateLoanUseCase;
import br.com.emanuel.emprestai.application.usecases.UpdateInstallmentDataUseCase;
import br.com.emanuel.emprestai.common.exceptions.NotFoundException;
import br.com.emanuel.emprestai.common.exceptions.ValidationException;
import br.com.emanuel.emprestai.domain.model.Loan;
import br.com.emanuel.emprestai.domain.model.Installment;
import br.com.emanuel.emprestai.domain.model.LoanStatus;
import br.com.emanuel.emprestai.domain.model.InstallmentStatus;
import br.com.emanuel.emprestai.domain.ports.outbound.LoanRepository;
import br.com.emanuel.emprestai.domain.ports.outbound.CustomerRepository;
import br.com.emanuel.emprestai.domain.ports.outbound.InstallmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanService implements CreateLoanUseCase, GetLoanUseCase, UpdateLoanStatusUseCase,
        UpdateInstallmentStatusUseCase, UpdateLoanUseCase, UpdateInstallmentDataUseCase {

    private final LoanRepository loanRepository;
    private final InstallmentRepository installmentRepository;
    private final CustomerRepository customerRepository;
    private final LoanMapper loanMapper;
    private final InstallmentMapper installmentMapper;

    public LoanService(LoanRepository loanRepository, InstallmentRepository installmentRepository,
                      CustomerRepository customerRepository, LoanMapper loanMapper, InstallmentMapper installmentMapper) {
        this.loanRepository = loanRepository;
        this.installmentRepository = installmentRepository;
        this.customerRepository = customerRepository;
        this.loanMapper = loanMapper;
        this.installmentMapper = installmentMapper;
    }

    @Override
    @Transactional
    public LoanResponse create(LoanRequest request) {
        // Validar se o customer existe
        customerRepository.findById(request.getCustomerId())
            .orElseThrow(() -> new NotFoundException("Cliente não encontrado com id: " + request.getCustomerId()));

        // Converter request para domain
        Loan loan = loanMapper.toDomain(request);
        loan.setLoanStatus(LoanStatus.WAITING_APPROVAL);

        // Salvar empréstimo
        Loan saved = loanRepository.save(loan);

        // Salvar parcelas
        if (saved.getInstallments() != null && !saved.getInstallments().isEmpty()) {
            for (Installment installment : saved.getInstallments()) {
                installment.setInstallmentStatus(InstallmentStatus.NOT_PAYED);
                installmentRepository.save(installment);
            }
        }

        // Recarregar loan com installments
        Loan loanWithInstallments = loanRepository.findById(saved.getId())
            .orElseThrow(() -> new NotFoundException("Erro ao recuperar empréstimo criado"));

        return loanMapper.toResponse(loanWithInstallments);
    }

    @Override
    @Transactional(readOnly = true)
    public LoanResponse getById(Long id) {
        Loan loan = loanRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Empréstimo não encontrado com id: " + id));
        return loanMapper.toResponse(loan);
    }

    @Override
    @Transactional
    public LoanResponse updateStatus(Long id, UpdateLoanStatusRequest request) {
        Loan loan = loanRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Empréstimo não encontrado com id: " + id));

        try {
            LoanStatus newStatus = LoanStatus.valueOf(request.getLoanStatus());
            loan.setLoanStatus(newStatus);
            Loan updated = loanRepository.save(loan);
            return loanMapper.toResponse(updated);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Status inválido: " + request.getLoanStatus());
        }
    }

    @Override
    @Transactional
    public InstallmentResponse updateStatus(Long loanId, Long installmentId, UpdateInstallmentStatusRequest request) {
        // Validar se o empréstimo existe
        Loan loan = loanRepository.findById(loanId)
            .orElseThrow(() -> new NotFoundException("Empréstimo não encontrado com id: " + loanId));

        // Validar se a parcela existe
        Installment installment = installmentRepository.findById(installmentId)
            .orElseThrow(() -> new NotFoundException("Parcela não encontrada com id: " + installmentId));

        try {
            InstallmentStatus newStatus = InstallmentStatus.valueOf(request.getInstallmentStatus());
            installment.setInstallmentStatus(newStatus);
            Installment updated = installmentRepository.save(installment);
            return installmentMapper.toResponse(updated);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Status inválido: " + request.getInstallmentStatus());
        }
    }

    @Override
    @Transactional
    public LoanResponse update(Long id, UpdateLoanRequest request) {
        Loan loan = loanRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Empréstimo não encontrado com id: " + id));

        // Atualizar apenas os campos fornecidos (não nulos)
        if (request.getAmount() != null) {
            loan.setAmount(request.getAmount());
        }
        if (request.getStartDate() != null) {
            loan.setStartDate(request.getStartDate());
        }

        Loan updated = loanRepository.save(loan);
        return loanMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public InstallmentResponse update(Long loanId, Long installmentId, UpdateInstallmentRequest request) {
        // Validar se o empréstimo existe
        Loan loan = loanRepository.findById(loanId)
            .orElseThrow(() -> new NotFoundException("Empréstimo não encontrado com id: " + loanId));

        // Validar se a parcela existe
        Installment installment = installmentRepository.findById(installmentId)
            .orElseThrow(() -> new NotFoundException("Parcela não encontrada com id: " + installmentId));

        // Atualizar apenas os campos fornecidos (não nulos)
        if (request.getAmount() != null) {
            installment.setAmount(request.getAmount());
        }
        if (request.getDueDate() != null) {
            installment.setDueDate(request.getDueDate());
        }

        Installment updated = installmentRepository.save(installment);
        return installmentMapper.toResponse(updated);
    }
}
