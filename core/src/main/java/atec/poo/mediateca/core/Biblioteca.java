package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.*;
import atec.poo.mediateca.core.utilidades.CompareObraByID;

import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;


public class Biblioteca implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private final HashMap<Integer, User> users;
    private final HashMap<Integer, Obra> obras;
    private final HashMap<Integer, Requisicao> requisicoes;
    private int nextUserID;
    private int nextObraID;
    private int nextReqID;
    private int data;
    private static final int multaDiaria = 5;
    /**
     * Construtor
     */
    public Biblioteca() {
        this.users = new HashMap<>();
        this.obras = new HashMap<>();
        this.requisicoes = new HashMap<>();
        this.nextUserID = 1;
        this.nextObraID = 1;
        this.nextReqID = 1;
        this.data = 0;
    }

    /**
     * Mostra a data atual
     * @return Data atual
     */
    public int getData() {
        return data;
    }

    /**
     * Define a data atual
     * @param data data
     */
    public void setData(int data) {
        if (data > 0)
            this.data += data;
    }

    /**
     * Registra um novo utente
     * @param nome nome utente
     * @param email email utente
     * @return Cria um novo utente
     */
    public int registarUser(String nome, String email) {
        User u = new User(this.nextUserID, nome, email);
        this.users.put(u.getId(), u);
        this.nextUserID++;
        return u.getId();
    }

    /**
     * Obtém informações sobre um utente específico
     * @param id id utente
     * @return Informações do utente pretendido
     * @throws UserNotFoundException Verificar se o utente existe ou não
     */
    public String mostrarUtente(int id) throws UserNotFoundException {
        if (this.users.containsKey(id))
            return this.users.get(id).toString();
        throw new UserNotFoundException(id);
    }

    /**
     * Mostra informações sobre todos os utentes
     * @return Informações de todos os utentes
     */
    public ArrayList<User> listUsers() {
        ArrayList<User> users_array = new ArrayList<>(this.users.values());
        Collections.sort(users_array);
        return users_array;
    }

    /** AINDA POR FAZER!!!!!!!!!!!!!!!!!!!
     * Obtém notificações de um utente específico
     * @param id id utente
     * @return Notificações do utente pretendido
     * @throws UserNotFoundException Verificar se o utente existe ou não
     */
    public String mostrarNotificacao(int id) throws UserNotFoundException {
        if (this.users.containsKey(id))
            return this.users.get(id).toString(); // Em vez mostrar informação do Utente mostrar as notificações desse Utente (Entrega/Requisição)
            // Exemplos:
            /*
                ENTREGA: 4 - 2 de 4 - DVD - Casamento Real - 8 Ficção - António Fonseca - 200400500
                REQUISIÇÃO: 5 - 4 de 22 - Livro - Dicionário - 45 - Referência - Pedro Casanova - 1234567893
             */
        throw new UserNotFoundException(id);
    }

    /**
     * Paga a multa de um utente especifico
     * @param userID id utente
     */
    public void pagarMulta(int userID) throws ActiveUserException{
        User user = this.users.get(userID);
        if (user.getEstado().toString().equals("SUSPENSO")) {
            // DEVOLVER OBRA
            user.setMulta(0);
            user.setEstado(Estado.valueOf("ACTIVO"));
            return;
        }
        throw new ActiveUserException(userID);
    }

    /**
     * Registra um novo livro na biblioteca.
     * @param titulo titulo livro.
     * @param autor autor livro.
     * @param preco preço livro.
     * @param categoria categoria livro.
     * @param isbn valor de ISBN livro.
     * @param exemplares Nº de exemplares livro.
     */
    public void registarLivro(String titulo, String autor, Double preco, String categoria, String isbn, int exemplares) {
        Livro l = new Livro(this.nextObraID, titulo, autor, preco, categoria, isbn, exemplares);
        this.obras.put(l.getId(), l);
        this.nextObraID++;
    }

    /**
     * Registra um novo dvd na biblioteca
     * @param titulo titulo dvd
     * @param realizador realizador dvd
     * @param preco preço dvd
     * @param categoria categoria dvd
     * @param igac valor de igac dvd
     * @param exemplares Nº de exemplares dvd
     */
    public void registarDVD(String titulo, String realizador, Double preco, String categoria, String igac, int exemplares) {
        DVD d = new DVD(this.nextObraID, titulo, realizador, preco, categoria, igac, exemplares);
        this.obras.put(d.getId(), d);
        this.nextObraID++;
    }

    /**
     * Obtém informações sobre uma obra específica
     * @param id id obra
     * @return Informações da obra pretendido
     * @throws WorkNotFoundException Verificar se a obra existe ou não
     */
    public String mostrarObra(int id) throws WorkNotFoundException {
        if (this.obras.containsKey(id))
            return this.obras.get(id).toString();
        throw new WorkNotFoundException(id);
    }

    /**
     * Mostra informações sobre todas as obras por ordem crescente do ID da obra
     * @return Informações de todas as obras (ordem crescente id obra)
     */
    public ArrayList<Obra> listObrasByID() {
        ArrayList<Obra> obras_array = new ArrayList<>(this.obras.values());
        obras_array.sort(new CompareObraByID()); //Collections.sort(obras_array,new CompareObraByID());
        return obras_array;
    }

    /**
     * Mostra informações sobre todas as requisições feitas
     * @return Informações de todas as requisições
     */
    public ArrayList<Requisicao> listRequisicao() {
        ArrayList<Requisicao> requisicaos_array = new ArrayList<>(this.requisicoes.values());
        return requisicaos_array;
    }

    /**
     * Registra uma nova Requisicão
     * @param userID id utente
     * @param obraID id obra
     * @param dataRequisicao data requisicao obra
     * @param dataEntrega data entregue obra
     */
    public int registarRequisicao(int userID, int obraID, int dataRequisicao, int dataEntrega) {
        Requisicao req = new Requisicao(this.nextReqID, userID, obraID, dataRequisicao, dataEntrega);
        this.requisicoes.put(req.getId(), req);
        System.out.println(listRequisicao());
        return this.nextReqID++;
    }

    /**
     * Requisita obra especifica para um utente especifico
     * @param userID id utente
     * @param obraID id obra
     * @throws RuleException Mostra cada erro especifico
     */
    public void requisitarObra(int userID, int obraID) throws RuleException {
        Obra obra = this.obras.get(obraID);
        User user = this.users.get(userID);

        if (user.getEstado().toString().equals("SUSPENSO")) {
            throw new RuleException(userID, obraID, 2);
        }

        if (obra.getStock() <= 0) {
            throw new RuleException(userID, obraID, 3);
        }

        if (obra.getCategoria().equals("REFERENCE"))
            throw new RuleException(userID, obraID, 5);

        if (obra.getPreco() > 25.00 && !user.getComportamento().equals("CUMPRIDOR"))
            throw new RuleException(userID, obraID, 6);

        if (!user.getObraID(obraID)) {
            String comportamento = user.getComportamento().toString();
            int requisicaoLimite = 0;

            switch (comportamento) {
                case "NORMAL":
                    requisicaoLimite = 3;
                    break;
                case "CUMPRIDOR":
                    requisicaoLimite = 5;
                    break;
                case "FALTOSO":
                    requisicaoLimite = 1;
                    break;
            }

            if (user.numRequisicoes < requisicaoLimite) {
                user.numRequisicoes++;
                user.requisicao.add(obraID);
                int dataRequisicao = getData();
                int dataEntrega = getData() + requisicaoMaxDias(userID,obraID);
                int reqID = registarRequisicao(userID, obraID, dataRequisicao, dataEntrega);
                user.requisicaoID.add(reqID);

                int novoStock = obra.getStock() - 1;
                obra.setStock(novoStock);
                //user.setMulta(39);
                //user.setEstado(Estado.valueOf("SUSPENSO"));
            } else {
                throw new RuleException(userID, obraID, 4);
            }
        } else {
            throw new RuleException(userID, obraID, 1);
        }
    }

    /**
     * Procura a quantidade de dias que o utente tem para devolver a obra dependendo do comportamento e a quantidade de exemplos de cada obra
     * @param userID id utente
     * @param obraID id obra
     * @return Declara a quantidade de dias para devolver a obra dependendo da sua situação
     */
    public int requisicaoMaxDias (int userID, int obraID) {
        User user = users.get(userID);
        Obra obra = obras.get(obraID);
        if (obra.getExemplares() > 5) {
            return switch (user.getComportamento()) {
                case CUMPRIDOR -> 30;
                case NORMAL -> 15;
                case FALTOSO -> 2;
            };
        } else if (obra.getExemplares() > 1 && obra.getExemplares() <= 5) {
            return switch (user.getComportamento()) {
                case CUMPRIDOR -> 15;
                case NORMAL -> 8;
                case FALTOSO -> 2;
            };
        } else if (obra.getExemplares() == 1) {
            return switch (user.getComportamento()) {
                case CUMPRIDOR -> 8;
                case NORMAL -> 3;
                case FALTOSO -> 2;
            };
        }
        return 0;
    }

    /**
     * Faz a devolução da Obra que foi requisitada
     * @param userID id utente
     * @param obraID id obra
     */
    public void devolverObra(int userID, int obraID) throws BorrowException {
        verificarUtenteObra(userID, obraID);

        User user = this.users.get(userID);
        Obra obra = this.obras.get(obraID);

        verificarTempoEntrega(userID,obraID);
        int reqRemoverID = acharRequisicao(userID,obraID);

        //remove o id da requisição da pessoa que pegou
        user.requisicaoID.remove(Integer.valueOf(reqRemoverID));

        //pegar o objeto REQUISICAO, logo em seguida remover ele da class para liberar memoria
        Requisicao req = this.requisicoes.get(reqRemoverID);
        this.requisicoes.values().remove(req);


        user.numRequisicoes--;

        user.requisicao.remove(Integer.valueOf(obraID));
        int novoStock = obra.getStock() + 1;
        obra.setStock(novoStock);

    }

    /**
     * Verificar se o Utente possui aquela Obra
     * @param userID id utente
     * @param obraID id obra
     */
    public void verificarUtenteObra(int userID, int obraID)throws BorrowException {
        User user = this.users.get(userID);
        if (user.getObraID(obraID)) {
            return;
        }
        throw new BorrowException(userID,obraID);
    }

    public int acharRequisicao(int userID, int obraID){
        User user = this.users.get(userID);
        for ( Integer req: user.requisicaoID){
            Requisicao reqID = this.requisicoes.get(req);
            if (reqID.getObraID() == obraID){
                return reqID.getId();
            }
        }
        return 0;
    }
    public void verificarTempoEntrega(int userID, int obraID) {
        User user = this.users.get(userID);
        Requisicao reqID = this.requisicoes.get(acharRequisicao(userID,obraID));

        if(getData() > reqID.getDataEntrega()){
            reqID.setDiasSemEntregar(getData() - reqID.getDataEntrega());
            user.setMulta(reqID.getDiasSemEntregar() * multaDiaria);
        }


    }

    /*
    Se o utente não entregar as obras requisitadas no prazo devido, fica imediatamente suspenso (até pagar a multa).
    Por cada dia de atraso, o utente fica sujeito ao pagamento de uma multa de €5,00.
    Sò deixa de estar suspenso após a devolução das obras em atraso e o pagamento da multa.
    Cada dia depois do prazo ultrapassado é aumentado €5,00 por cada dia que passar sem ser paga a multa.
     */

    /**
     * Procura a multa de um utente especifico
     * @param userID utente id
     * @return Retorna a multa do utente
     */
    public int mostraMulta(int userID){
        User user = this.users.get(userID);
            return user.getMulta();
    }

    /**
     * Read the text input file at the beginning of the program and populates the
     * instances of the various possible types (books, DVDs, users).
     *
     * @param filename of the file to load
     * @throws BadEntrySpecificationException A especificação do ficheiro não é correta
     * @throws IOException                    Erro na abertura e/ou Leitura do ficheiro
     */
    void importFile(String filename) throws BadEntrySpecificationException, IOException {
        Scanner s = new Scanner(new File(filename));
        while (s.hasNextLine()) {
            String line = s.nextLine();
            //System.out.println(line);
            String[] elementos = line.split(":", 0);
            switch (elementos[0]) {
                case "USER" -> this.registarUser(elementos[1], elementos[2]);
                case "BOOK" -> this.registarLivro(elementos[1], elementos[2], Double.parseDouble(elementos[3]), elementos[4], elementos[5], Integer.parseInt(elementos[6]));
                case "DVD" -> this.registarDVD(elementos[1], elementos[2], Double.parseDouble(elementos[3]), elementos[4], elementos[5], Integer.parseInt(elementos[6]));
                default -> throw new BadEntrySpecificationException("Unknow type of category");
            }
        }
        s.close();
    }

}