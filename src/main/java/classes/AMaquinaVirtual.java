package classes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import gui.Controller;

public class AMaquinaVirtual {
    private ATabelaInstrucoes tabelaInstrucoes;
    private ATabelaSimbolos tabelaSimbolos;
    private int ponteiro;
    private Object[] pilha;
    private int topo;
    private Controller controller;
    private boolean isRunning; // Variável de controle para parar a execução

    public AMaquinaVirtual(ATabelaInstrucoes tabelaInstrucoes, ATabelaSimbolos tabelaSimbolos, Controller controller) {
        this.tabelaInstrucoes = tabelaInstrucoes;
        this.tabelaSimbolos = tabelaSimbolos;
        this.ponteiro = 0;
        this.pilha = new Object[100]; // Tamanho da pilha pode ser ajustado conforme necessário
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
                executarJMF(instrucao.getParametro());
                break;
            case "JMP":
                executarJMP(instrucao.getParametro());
                break;
            case "JMT":
                executarJMT((int) instrucao.getParametro());
                break;
            case "LDV":
                executarLDV((int) instrucao.getParametro());
                break;
            case "LDB":
                executarLDB((Object) instrucao.getParametro());
                break;
            case "LDI":
                executarLDI((int) instrucao.getParametro());
                break;
            case "LDR":
                executarLDR((double) instrucao.getParametro());
                break;
            case "LDS":
                executarLDS((String) instrucao.getParametro());
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
        double valor1 = 0, valor2 = 0;
        boolean ambosInteiros = true;
    
        // Verificar e converter o valor no topo da pilha para double
        if (pilha[topo] instanceof Integer) {
            valor2 = ((Integer) pilha[topo]).doubleValue();
        } else if (pilha[topo] instanceof Double) {
            valor2 = (Double) pilha[topo];
            ambosInteiros = false;
        }
    
        // Verificar e converter o valor no topo-1 da pilha para double
        if (pilha[topo - 1] instanceof Integer) {
            valor1 = ((Integer) pilha[topo - 1]).doubleValue();
        } else if (pilha[topo - 1] instanceof Double) {
            valor1 = (Double) pilha[topo - 1];
            ambosInteiros = false;
        }
    
         // Realizar a adição
        double resultado = valor1 + valor2;

        // Verificar se o resultado é um número inteiro
        if (ambosInteiros || resultado == Math.floor(resultado)) {
            pilha[topo - 1] = (int) resultado;
        } else {
            pilha[topo - 1] = resultado;
        }
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
        pilha[topo - 1] = ((int) pilha[topo - 1] != 0 && (int) pilha[topo] != 0) ? 1 : 0;
        topo--;
        ponteiro++;
    }

    private void executarBGE() {
        pilha[topo - 1] = ((int) pilha[topo - 1] >= (int) pilha[topo]) ? 1 : 0;
        topo--;
        ponteiro++;
    }

    private void executarBGR() {
        pilha[topo - 1] = ((int) pilha[topo - 1] > (int) pilha[topo]) ? 1 : 0;
        topo--;
        ponteiro++;
    }

    private void executarDIF() {
        pilha[topo - 1] = (pilha[topo - 1] != pilha[topo]) ? 1 : 0;
        topo--;
        ponteiro++;
    }

    private void executarDIV() {
        double valor1 = 0, valor2 = 0;
        boolean ambosInteiros = true;

        if ((int) pilha[topo] == 0) {
            System.out.println("Erro: divisão por 0");
            System.exit(1);
        }
    
        // Verificar e converter o valor no topo da pilha para double
        if (pilha[topo] instanceof Integer) {
            valor2 = ((Integer) pilha[topo]).doubleValue();
        } else if (pilha[topo] instanceof Double) {
            valor2 = (Double) pilha[topo];
            ambosInteiros = false;
        }
    
        // Verificar e converter o valor no topo-1 da pilha para double
        if (pilha[topo - 1] instanceof Integer) {
            valor1 = ((Integer) pilha[topo - 1]).doubleValue();
        } else if (pilha[topo - 1] instanceof Double) {
            valor1 = (Double) pilha[topo - 1];
            ambosInteiros = false;
        }
    
        // Realizar a divisão e arredondar o resultado para 4 dígitos após a vírgula
        BigDecimal resultado = BigDecimal.valueOf(valor1).divide(BigDecimal.valueOf(valor2), 4, RoundingMode.HALF_UP);
    
        // Verificar se o resultado é um número inteiro
        if (resultado.stripTrailingZeros().scale() <= 0) {
            pilha[topo - 1] = resultado.intValue();
        } else {
            pilha[topo - 1] = resultado.doubleValue();
        }
        topo--;
        ponteiro++;
    }

    private void executarEQL() {
        pilha[topo - 1] = (pilha[topo - 1] == pilha[topo]) ? 1 : 0;
        topo--;
        ponteiro++;
    }

    private void executarJMF(Object parametro) {
        if (parametro instanceof Integer) {
            int endereco = (Integer) parametro;
            Object valor = pilha[topo--];
            if (valor instanceof Integer && (Integer) valor == 0) {
                ponteiro = endereco;
            } else if (valor instanceof Boolean && !(Boolean) valor) {
                ponteiro = endereco;
            } else {
                ponteiro++;
            }
        } else if (parametro instanceof String && "?".equals(parametro)) {
            // Endereço desconhecido, armazenar na pilha
            pilha[++topo] = parametro;
            ponteiro++;
        } else {
            throw new RuntimeException("Tipo inválido de parâmetro para operação JMF: " + parametro.getClass().getName());
        }
    }

    private void executarJMP(Object parametro) {
        if (parametro instanceof Integer) {
            int endereco = (Integer) parametro;
            ponteiro = endereco;
        } else if (parametro instanceof String && "?".equals(parametro)) {
            // Endereço desconhecido, armazenar na pilha
            pilha[++topo] = parametro;
            ponteiro++;
        } else {
            throw new RuntimeException("Tipo inválido de parâmetro para operação JMP: " + parametro.getClass().getName());
        }
    }
    private void executarJMT(int endereco) {
        if ((int)pilha[topo--] != 0) {
            ponteiro = endereco;
        } else {
            ponteiro++;
        }
    }

    private void executarLDV(int endereco) {
        pilha[++topo] = pilha[endereco];
        ponteiro++;
    }

    private void executarLDB(Object constante) {
        if (constante instanceof Integer) {
            pilha[++topo] = constante;
        } else if (constante instanceof Double) {
            pilha[++topo] = constante;
        } else if (constante instanceof String) {
            pilha[++topo] = constante;
        } else if (constante instanceof Boolean) {
            pilha[++topo] = (Boolean) constante ? 1 : 0; // Representar booleano como 1 ou 0
        } else {
            throw new RuntimeException("Tipo inválido na pilha para operação LDB: " + constante.getClass().getName());
        }
        ponteiro++;
    }

    private void executarLDI(int constante) {
        pilha[++topo] = constante;
        ponteiro++;
    }

    private void executarLDR(double constante) {
        pilha[++topo] = constante;
        ponteiro++;
    }

    private void executarLDS(String constante) {
        pilha[++topo] = constante;
        ponteiro++;
    }

    private void executarMUL() {
        double valor1, valor2;
        boolean ambosInteiros = true;
    
        // Verificar e converter o valor no topo da pilha para double
        if (pilha[topo] instanceof Integer) {
            valor2 = ((Integer) pilha[topo]).doubleValue();
        } else if (pilha[topo] instanceof Double) {
            valor2 = (Double) pilha[topo];
            ambosInteiros = false;
        } else {
            throw new RuntimeException("Tipo inválido na pilha para operação MUL: " + pilha[topo].getClass().getName());
        }
    
        // Verificar e converter o valor no topo-1 da pilha para double
        if (pilha[topo - 1] instanceof Integer) {
            valor1 = ((Integer) pilha[topo - 1]).doubleValue();
        } else if (pilha[topo - 1] instanceof Double) {
            valor1 = (Double) pilha[topo - 1];
            ambosInteiros = false;
        } else {
            throw new RuntimeException("Tipo inválido na pilha para operação MUL: " + pilha[topo - 1].getClass().getName());
        }
    
        // Realizar a multiplicação
        double resultado = valor1 * valor2;

        // Verificar se o resultado é um número inteiro
        if (ambosInteiros || resultado == Math.floor(resultado)) {
            pilha[topo - 1] = (int) resultado;
        } else {
            pilha[topo - 1] = resultado;
        }
            topo--;
            ponteiro++;
        }

    private void executarNOT() {
        pilha[topo] = ((int)pilha[topo] == 0) ? 1 : 0;
        ponteiro++;
    }

    private void executarOR() {
        pilha[topo - 1] = ((int)pilha[topo - 1] != 0 || (int)pilha[topo] != 0) ? 1 : 0;
        topo--;
        ponteiro++;
    }

    private void executarSME() {
        pilha[topo - 1] = ((int)pilha[topo - 1] <= (int)pilha[topo]) ? 1 : 0;
        topo--;
        ponteiro++;
    }

    private void executarSMR() {
        pilha[topo - 1] = ((int)pilha[topo - 1] < (int)pilha[topo]) ? 1 : 0;
        topo--;
        ponteiro++;
    }

    private void executarSTR(int endereco) {
        pilha[endereco] = pilha[topo--];
        ponteiro++;
    }

    private void executarSTP() {
        String mensagem = "\n" +"----------------------" + "\n" + "Programa finalizado com sucesso";
        controller.exibirMensagem(mensagem);
        isRunning = false; // Parar a execução
    }

    private void executarSUB() {
        double valor1 = 0, valor2 = 0;
        boolean ambosInteiros = true;
    
        // Verificar e converter o valor no topo da pilha para double
        if (pilha[topo] instanceof Integer) {
            valor2 = ((Integer) pilha[topo]).doubleValue();
        } else if (pilha[topo] instanceof Double) {
            valor2 = (Double) pilha[topo];
            ambosInteiros = false;
        }
    
        // Verificar e converter o valor no topo-1 da pilha para double
        if (pilha[topo - 1] instanceof Integer) {
            valor1 = ((Integer) pilha[topo - 1]).doubleValue();
        } else if (pilha[topo - 1] instanceof Double) {
            valor1 = (Double) pilha[topo - 1];
            ambosInteiros = false;
        }
    
         // Realizar a subtração
        double resultado = valor1 - valor2;

        // Verificar se o resultado é um número inteiro
        if (ambosInteiros || resultado == Math.floor(resultado)) {
            pilha[topo - 1] = (int) resultado;
        } else {
            pilha[topo - 1] = resultado;
        }
            topo--;
            ponteiro++;
        }

    private void executarREA() {
        // int valor = controller.solicitarEntrada();
        int valor = 0;
        pilha[++topo] = valor;
        ponteiro++;
    }

    private void executarWRT() {
        if (topo < 0) {
            throw new RuntimeException("Pilha vazia na operação WRT");
        }
        Object valor = pilha[topo--];
        String mensagem;
        if (valor instanceof Integer) {
            mensagem = String.valueOf(valor);
        } else if (valor instanceof Double) {
            mensagem = String.valueOf(valor);
        } else if (valor instanceof String) {
            mensagem = (String) valor;
        } else if (valor instanceof Boolean) {
            mensagem = (Boolean) valor ? "true" : "false";
        } else {
            throw new RuntimeException("Tipo inválido na pilha para operação WRT: " + valor.getClass().getName());
        }
        controller.exibirMensagem(mensagem);
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