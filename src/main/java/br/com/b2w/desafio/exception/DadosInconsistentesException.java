package br.com.b2w.desafio.exception;

public class DadosInconsistentesException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public DadosInconsistentesException(String msg) {
        super(msg);
    }
}