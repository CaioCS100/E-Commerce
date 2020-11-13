package cesmac.si.shared.enuns;

public enum Ufs {

    ACRE("AC", "ACRE"),
    ALAGOAS("AL", "ALAGOAS"),
    AMAPA("AP", "AMAPA"),
    AMAZONAS("AM", "AMAZONAS"),
    BAHIA("BA", "BAHIA"),
    CEARA("CE", "CEARA"),
    DISTRITO_FEDERAL("DF", "DISTRITO FEDERAL"),
    ESPIRITO_SANTO("ES", "ESPIRITO SANTO"),
    GOIAS("GO", "GOIAS"),
    MARANHAO("MA", "MARANHAO"),
    MATO_GROSSO("MT", "MATO GROSSO"),
    MATO_GROSSO_DO_SUL("MS", "MATO GROSSO DO SUL"),
    MINAS_GERAIS("MG", "MINAS GERAIS"),
    PARA("PA", "PARA"),
    PARAIBA("PB", "PARAIBA"),
    PARANA("PR", "PARANA"),
    PERNAMBUCO("PE", "PERNAMBUCO"),
    PIAUI("PI", "PIAUI"),
    RIO_DE_JANEIRO("RJ", "RIO DE JANEIRO"),
    RIO_GRANDE_DO_NORTE("RN", "RIO GRANDE DO NORTE"),
    RIO_GRANDE_DO_SUL("RS", "RIO GRANDE DO SUL"),
    RORAIMA("RR", "RORAIMA"),
    RONDONIA("RO", "RONDONIA"),
    SANTA_CATARINA("SC", "SANTA CATARINA"),
    SAO_PAULO("SP", "SAO PAULO"),
    SERGIPE("SE", "SERGIPE"),
    TOCANTINS("TO", "TOCANTINS");

    private String sigla;
    private String descricao;

    Ufs(String sigla, String descricao) {
        this.sigla = sigla;
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public String getDescricao() {
        return descricao;
    }
}
