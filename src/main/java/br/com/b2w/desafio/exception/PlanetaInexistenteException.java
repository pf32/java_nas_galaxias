package br.com.b2w.desafio.exception;

public class PlanetaInexistenteException extends ApplicationException{

    private static final long serialVersionUID = 1L;

    public PlanetaInexistenteException(String msg) {
        super(msg);
    }
}
