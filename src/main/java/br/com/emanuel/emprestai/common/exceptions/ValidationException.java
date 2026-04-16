package br.com.emanuel.emprestai.common.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException() { super(); }
    public ValidationException(String message) { super(message); }
    public ValidationException(String message, Throwable cause) { super(message, cause); }
}
