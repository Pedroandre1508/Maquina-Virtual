package classes;

import java.util.List;

import gui.Controller;

public class AMaquinaVirtual {
    private ATabelaInstrucoes tabelaInstrucoes;
    private ATabelaSimbolos tabelaSimbolos;
    private int ponteiro;
    private int[] pilha;
    private int topo;
    private Controller controller;
    private boolean isRunning; // Variável de controle para parar a execução

    public AMaquinaVirtual(ATabelaInstrucoes tabelaInstrucoes, ATabelaSimbolos tabelaSimbolos, Controller controller) {
        this.tabelaInstrucoes = tabelaInstrucoes;
        this.tabelaSimbolos = tabelaSimbolos;
        this.ponteiro = 0;
        this.pilha = new int[100]; // Tamanho da pilha pode ser ajustado conforme necessário
        this.topo = 0;
        this.controller = controller;
        this.isRunning = true; // Inicializa como true
    }

    public void executar() {
        List<Instrucao> instrucoes = tabelaInstrucoes.getInstrucoes();
        while (ponteiro < instrucoes.size() && isRunning) {
            Instrucao instrucao = instrucoes.get(ponteiro);
            System.out.println("Executando instrução: " + instrucao.getCodigo());
            executarInstrucao(instrucao);
        }
    }

    private void executarInstrucao(Instrucao instrucao) {
        switch (instrucao.getCodigo()) {
            case "ADD":
                executarADD();
                break;
            case "ALB":
                executarALB((int) instrucao.getParametro());
                break;
            case "ALI":
                executarALI((int) instrucao.getParametro());
                break;
            case "ALR":
                executarALR((int) instrucao.getParametro());
                break;
            case "ALS":
                executarALS((int) instrucao.getParametro());
                break;
            case "AND":
                executarAND();
                break;
            case "BGE":
                executarBGE();
                break;
            case "BGR":
                executarBGR();
                break;
            case "DIF":
                executarDIF();
                break;
            case "DIV":
                executarDIV();
                break;
            case "EQL":
                executarEQL();
                break;
            case "JMF":
                executarJMF((int) instrucao.getParametro());
                break;
            case "JMP":
                executarJMP((int) instrucao.getParametro());
                break;
            case "JMT":
                executarJMT((int) instrucao.getParametro());
                break;
            case "LDV":
                executarLDV((int) instrucao.getParametro());
                break;
            case "LDB":
                executarLDB((int) instrucao.getParametro());
                break;
            case "LDI":
                executarLDI((int) instrucao.getParametro());
                break;
            case "LDR":
                executarLDR((int) instrucao.getParametro());
                break;
            case "LDS":
                executarLDS((int) instrucao.getParametro());
                break;
            case "MUL":
                executarMUL();
                break;
            case "NOT":
                executarNOT();
                break;
            case "OR":
                executarOR();
                break;
            case "REA":
                executarREA();
                break;
            case "SME":
                executarSME();
                break;
            case "SMR":
                executarSMR();
                break;
            case "STR":
                executarSTR((int) instrucao.getParametro());
                break;
            case "STP":
                executarSTP();
                break;
            case "SUB":
                executarSUB();
                break;
            case "WRT":
                executarWRT();
                break;
            case "STC":
                executarSTC((int) instrucao.getParametro());
                break;
            default:
                throw new RuntimeException("Instrução desconhecida: " + instrucao.getCodigo());
        }
    }

    // Implementação das instruções

    private void executarADD() {
        pilha[topo - 1] = pilha[topo - 1] + pilha[topo];
        topo--;
        ponteiro++;
    }

    private void executarALB(int deslocamento) {
        for (int i = topo + 1; i <= topo + deslocamento; i++) {
            pilha[i] = 0; // Supondo que FALSE seja representado por 0
        }
        topo += deslocamento;
        ponteiro++;
    }

    private void executarALI(int deslocamento) {
        for (int i = topo + 1; i <= topo + deslocamento; i++) {
            pilha[i] = 0;
        }
        topo += deslocamento;
        ponteiro++;
    }

    private void executarALR(int deslocamento) {
        for (int i = topo + 1; i <= topo + deslocamento; i++) {
            pilha[i] = 0; // Supondo que 0.0 seja representado por 0
        }
        topo += deslocamento;
        ponteiro++;
    }

    private void executarALS(int deslocamento) {
        for (int i = topo + 1; i <= topo + deslocamento; i++) {
            pilha[i] = ' '; // Supondo que ' ' seja representado por 0
        }
        topo += deslocamento;
        ponteiro++;
    }

    private void executarAND() {
        pilha[topo - 1] = (pilha[topo - 1] != 0 && pilha[topo] != 0) ? 1 : 0;
        topo--;
        ponteiro++;
    }

    private void executarBGE() {
        pilha[topo - 1] = (pilha[topo - 1] >= pilha[topo]) ? 1 : 0;
        topo--;
        ponteiro++;
    }

    private void executarBGR() {
        pilha[topo - 1] = (pilha[topo - 1] > pilha[topo]) ? 1 : 0;
        topo--;
        ponteiro++;
    }

    private void executarDIF() {
        pilha[topo - 1] = (pilha[topo - 1] != pilha[topo]) ? 1 : 0;
        topo--;
        ponteiro++;
    }

    private void executarDIV() {
        if (pilha[topo] == 0) {
            System.out.println("RUNTIME error: divisão por 0");
            System.exit(1);
        }
        pilha[topo - 1] = pilha[topo - 1] / pilha[topo];
        topo--;
        ponteiro++;
    }

    private void executarEQL() {
        pilha[topo - 1] = (pilha[topo - 1] == pilha[topo]) ? 1 : 0;
        topo--;
        ponteiro++;
    }

    private void executarJMF(int endereco) {
        if (pilha[topo--] == 0) {
            ponteiro = endereco;
        } else {
            ponteiro++;
        }
    }

    private void executarJMP(int endereco) {
        ponteiro = endereco;
    }

    private void executarJMT(int endereco) {
        if (pilha[topo--] != 0) {
            ponteiro = endereco;
        } else {
            ponteiro++;
        }
    }

    private void executarLDV(int endereco) {
        pilha[++topo] = pilha[endereco];
        ponteiro++;
    }

    private void executarLDB(int constante) {
        pilha[++topo] = constante;
        ponteiro++;
    }

    private void executarLDI(int constante) {
        pilha[++topo] = constante;
        ponteiro++;
    }

    private void executarLDR(int constante) {
        pilha[++topo] = constante;
        ponteiro++;
    }

    private void executarLDS(int constante) {
        pilha[++topo] = constante;
        ponteiro++;
    }

    private void executarMUL() {
        pilha[topo - 1] = pilha[topo - 1] * pilha[topo];
        topo--;
        ponteiro++;
    }

    private void executarNOT() {
        pilha[topo] = (pilha[topo] == 0) ? 1 : 0;
        ponteiro++;
    }

    private void executarOR() {
        pilha[topo - 1] = (pilha[topo - 1] != 0 || pilha[topo] != 0) ? 1 : 0;
        topo--;
        ponteiro++;
    }

    private void executarSME() {
        pilha[topo - 1] = (pilha[topo - 1] <= pilha[topo]) ? 1 : 0;
        topo--;
        ponteiro++;
    }

    private void executarSMR() {
        pilha[topo - 1] = (pilha[topo - 1] < pilha[topo]) ? 1 : 0;
        topo--;
        ponteiro++;
    }

    private void executarSTR(int endereco) {
        pilha[endereco] = pilha[topo--];
        ponteiro++;
    }

    private void executarSTP() {
        String mensagem = "Programa finalizado com sucesso";
        controller.exibirMensagem(mensagem);
        isRunning = false; // Parar a execução
    }

    private void executarSUB() {
        pilha[topo - 1] = pilha[topo - 1] - pilha[topo];
        topo--;
        ponteiro++;
    }

    private void executarREA() {
        int valor = controller.solicitarEntrada();
        pilha[++topo] = valor;
        ponteiro++;
    }

    private void executarWRT() {
        String mensagem = String.valueOf(pilha[topo]);
        controller.exibirMensagem(mensagem.toString().trim());
        topo--;
        ponteiro++;
    }

    private void executarSTC(int deslocamento) {
        for (int i = topo - deslocamento; i <= topo - 1; i++) {
            pilha[i] = pilha[topo];
        }
        topo--;
        ponteiro++;
    }

    public String exibirPilha() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estado atual da pilha:\n");
        for (int i = 0; i <= topo; i++) {
            sb.append("pilha[" + i + "] = " + pilha[i] + "\n");
        }
        return sb.toString();
    }
}