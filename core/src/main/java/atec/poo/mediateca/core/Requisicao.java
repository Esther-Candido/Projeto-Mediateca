package atec.poo.mediateca.core;

import java.io.Serial;
import java.io.Serializable;

public class Requisicao implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int id;
    private final int userID;
    private final int obraID;
    private final int dataRequisicao;
    private final int dataEntrega;
    private int diasSemEntregar;

    public Requisicao(int id, int userID, int obraID, int dataRequisicao, int dataEntrega) {
        this.id = id;
        this.userID = userID;
        this.obraID = obraID;
        this.dataRequisicao = dataRequisicao;
        this.dataEntrega = dataEntrega;
        this.diasSemEntregar = 0;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public int getObraID() {
        return obraID;
    }


    public int getDataEntrega() {
        return dataEntrega;
    }

    public int getDiasSemEntregar() {
        return diasSemEntregar;
    }

    public void setDiasSemEntregar(int diasSemEntregar) {
        this.diasSemEntregar = diasSemEntregar;
    }

    @Override
    public String toString() {
        return "ID Requisito: "+this.id+" | ID User: "+this.userID+" | ID Obra: "+this.obraID+" | DataRequisicao: "+this.dataRequisicao+" | DataEntrega: "+this.dataEntrega + "\n";
    }
}

/**
 *
  Ao requisitar uma obra, o utente deve ser informado da data limite para a devolução. O tempo de requisição permitido para cada obra depende do número total de exemplares que constem do acervo da mediateca e da conduta do utente. Os prazos, em dias, são os seguintes:

  Obras com apenas um exemplar – valor de base: 3; utentes cumpridores: 8; utentes faltosos: 2;
  Obras com 5 exemplares ou menos – valor de base 8; utentes cumpridores: 15; utentes faltosos: 2;
  Obras com mais de 5 exemplares – valor de base 15; utentes cumpridores: 30; utentes faltosos: 2.


Se o utente não entregar as obras requisitadas no prazo devido, fica imediatamente SUSPENSO, não podendo requisitar mais obras até regularizar a situação.

        Por cada dia de atraso, o utente fica sujeito ao pagamento de uma multa de €5,00 (cinco euros).
        A situação só se considera regularizada após a devolução das obras em atraso e o pagamento da multa. >>>> FICA ATIVO depois de pagar multa e devolver obra

        Para efeitos de pagamento de multas, frações de dia contam como um dia (a unidade de tempo do sistema é o dia).
 */

