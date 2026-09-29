package br.edu.ifpb.pweb2.dindin.model.enums;

public enum Natureza {
    ENTRADA("text-green-600"),
    SAIDA("text-red-500"),
    INVESTIMENTO("text-amber-500");

    private final String classeCor;

    Natureza(String classeCor) {
        this.classeCor = classeCor;
    }

    public String getClasseCor() {
        return classeCor;
    }
}
