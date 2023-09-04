package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Biblioteca {
    private Hashtable<Integer,Obra> obras;
    private Hashtable<Integer,Utente> utentes;
    private int data;
    private int userIdCounter = 1;
    public Biblioteca() {
        this.data = 0;
        this.obras = new Hashtable<>();
        this.utentes = new Hashtable<>();
        //TODO: Inicialização dos atributos biblioteca
    }

    /**
     * Gera um novo ID de usuário.
     * @return O novo ID de usuário gerado.
     */
    public int generateNewUserId() {
        return userIdCounter++;
    }

    /**
     * Obtém o valor atual da data.
     * @return O valor atual da data.
     */
    public int getData() {
        return data;
    }

    /**
     * Define o valor da data.
     * @param data O novo valor da data.
     */
    public void setData(int data) {
        if (data > 0) {
            this.data = data;
        }
    }

    /**
     * Registra um novo utente na biblioteca.
     * @param id   O ID do utente.
     * @param nome O nome do utente.
     * @param email O email do utente.
     * @throws RegistoNomeIncorretoException Se o nome estiver incorreto.
     * @throws RegistroEmail_incorretoException Se o email estiver incorreto.
     */
    public void registrarUtente(int id, String nome, String email) throws RegistoNomeIncorretoException, RegistroEmail_incorretoException {
        for (Utente utente : this.utentes.values()) {
            if (utente.getNome().equals(nome)) {
                throw new RegistoNomeIncorretoException(nome);
            }
            if (utente.getEmail().equals(email)) {
                throw new RegistroEmail_incorretoException(email);
            }
        }
        Utente novoUtente = new Utente(nome, email);
        utentes.put(id, novoUtente);
    }

    /**
     * Obtém informações sobre um usuário específico.
     * @param id O ID do usuário.
     * @return As informações do usuário formatadas.
     * @throws UtilizadorNaoExisteException Se o usuário não existe.
     */
    public String getUserInformacao(int id) throws UtilizadorNaoExisteException {
        Utente utente = this.utentes.get(id);
        if (utente == null) {
            throw new UtilizadorNaoExisteException(id);
        }
        String userInfo = String.format("%d - %s - %s - %s - %s",id ,utente.getNome(), utente.getEmail(), utente.getClassificacao(), utente.getEstado());
        if (utente.getMultas() != 0){
            userInfo += " - " + utente.getMultas() + "€";
        }
        return userInfo;
    }

    /**
     * Retorna uma lista de informações dos utentes ordenados por nome e ID.
     * @return Lista de informações ordenadas dos utentes
     */
    public List<String> getUtentesOrdenadosInfo() {
        // Lista para armazenar as informações ordenadas dos utentes
        List<String> utentesOrdenadosInfo = new ArrayList<>();

        // Cria uma lista ordenada de utentes com base em seus nomes
        List<Utente> utentesOrdenados = new ArrayList<>(utentes.values());
        Collections.sort(utentesOrdenados, new Comparator<Utente>() {
            @Override
            public int compare(Utente utente1, Utente utente2) {
                // Compara os nomes dos utentes
                int nomeComparison = utente1.getNome().compareTo(utente2.getNome());
                if (nomeComparison == 0) {
                    // Se os nomes forem iguais, usa os IDs para desempatar
                    Integer id1 = getKeyByValue(utentes, utente1);
                    Integer id2 = getKeyByValue(utentes, utente2);
                    return Integer.compare(id1, id2);
                }
                return nomeComparison;
            }
        });

        // Cria informações formatadas dos utentes ordenados
        for (Utente utente : utentesOrdenados) {
            // Encontra o ID (chave) correspondente ao utente
            Integer id = getKeyByValue(utentes, utente);
            // Formata as informações do utente, incluindo classificação, estado e multas
            String utenteInfo = String.format(
                    "%d - %s - %s - %s - %s",
                    id, utente.getNome(), utente.getEmail(),
                    utente.getClassificacao(), utente.getEstado());
            if (utente.getMultas() != 0){
                utenteInfo += " - " + utente.getMultas() + "€";
            }

            // Adiciona as informações formatadas à lista de informações ordenadas
            utentesOrdenadosInfo.add(utenteInfo);
        }

        // Retorna a lista de informações dos utentes ordenados
        return utentesOrdenadosInfo;
    }

    /**
     * Obtém a chave (ID) associada a um valor (Utente) em um mapa (Hashtable).
     *
     * @param map   O mapa (Hashtable) no qual procurar a chave
     * @param value O valor (Utente) para o qual encontrar a chave
     * @return A chave (ID) correspondente ao valor no mapa, ou nulo se não encontrada
     */
    private Integer getKeyByValue(Hashtable<Integer, Utente> map, Utente value) {
        // Itera sobre as entradas do mapa (utentes)
        for (Map.Entry<Integer, Utente> entry : map.entrySet()) {
            // Se o valor (utente) na entrada atual for igual ao utente procurado (value)
            if (entry.getValue().equals(value)) {
                // Retorna a chave (ID) correspondente ao utente
                return entry.getKey();
            }
        }
        // Retorna nulo se não encontrar a chave correspondente
        return null;
    }

    /**
     * Paga a multa de um utente.
     * @param id O ID do utente.
     * @return A mensagem de pagamento bem-sucedido.
     * @throws UtilizadorNaoExisteException Se o utente não existir.
     * @throws UtilizadorAtivoException Se o utente estiver ativo.
     */
    public String PagarMulta(int id) throws UtilizadorNaoExisteException, UtilizadorAtivoException {
        Utente utente = this.utentes.get(id);
        if (utente == null) {
            throw new UtilizadorNaoExisteException(id);
        }
        if (utente.getEstado().equals("ATIVO")) {

            utente.setMultas(0);
            utente.setEstado("NORMAL");
        } else {
            throw new UtilizadorAtivoException(id);
        }
        return " User " + id + " está ATIVO novamente";
    }

    /**
     * Requisita uma obra por um utente.
     * @param id_user O ID do utente.
     * @param id_obra O ID da obra.
     * @throws ObraNaoExisteException Se a obra não existir.
     * @throws UtilizadorNaoExisteException Se o utente não existir.
     */
    public void requisitar_obra(int id_user,int id_obra) throws UtilizadorNaoExisteException, ObraNaoExisteException {
        if (!this.utentes.containsKey(id_user))
            throw new UtilizadorNaoExisteException(id_user);
        if (!this.obras.containsKey(id_obra))
            throw new ObraNaoExisteException(id_obra);

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

                case "USER":
                    //this.registerUser(elementos[1],elementos[2]);
                    //TODO: A implementar pelos alunos dependente dos métodos que implementarem
                    break;
                case "BOOK":
                    //this.registerLivro(elementos[1],elementos[2],elementos[3],elementos[4],elementos[5],elementos[6]);
                    //TODO: A implementar pelos alunos dependente dos métodos que implementarem
                    break;
                case "DVD":
                    //this.registerDVD(elementos[1],elementos[2],elementos[3],elementos[4],elementos[5],elementos[6]);
                    //TODO: A implementar pelos alunos dependente dos métodos que implementarem
                    break;
                default:
                    throw new BadEntrySpecificationException("Unknow type of category");
            }
        }
        s.close();
    }
}
