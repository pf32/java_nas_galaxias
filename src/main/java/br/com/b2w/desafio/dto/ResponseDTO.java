package br.com.b2w.desafio.dto;

import java.util.List;

public class ResponseDTO {

    private Long count;
    private String next;
    private List<PlanetaDTO> results;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<PlanetaDTO> getResults() {
        return results;
    }

    public void setResults(List<PlanetaDTO> results) {
        this.results = results;
    }
}
