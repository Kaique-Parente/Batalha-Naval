/*
nome do progama: Batalha Naval
grupo: Kaique Parente da Silva
Gabriel Ribeiro de Lima
Rodrigo Pereira Lima
Alex Olavo Pereira
data de criação: 27/10
data da ultima modificação: 29/11

 */
import java.util.Scanner;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class NewClass {

    public char mapa[][] = {{'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x',
        'x', 'x'}};
    public String tabuleiro[][] = {{"x", "x", "x", "x", "x"}, {"x", "x", "x", "x", "x"}, {"x", "x", "x", "x", "x"}, {"x", "x", "x", "x", "x"}, {"x", "x", "x",
        "x", "x"}};

    public static int contBarco = 0;
    public static int contBombas = 0;

    public int numBarcos = 0;
    public int numBomba = 0;
    
    // MAIS FACIL DE MANIPULAR PARA SAIR DA CONDIÇÃO DO WHILE POR ISSO = 1
    public static int contErros = 1; 
    public static int contAcertos = 1;
    
    public static int contTentativas = 0;
    public static Scanner e = new Scanner(System.in);

    public int barcoRandom() { // GERAR BARCOS

        for (numBarcos = 0; numBarcos < 3;) { // NÚMERO DE BARCOS
            Random sorteia = new Random();

            int posLin = sorteia.nextInt(5) + 0;

            if (inserir(posLin)) { // SE A FUNÇÃO INSERIR == true É AUMENTADO O NÚMERO DE BARCOS
                numBarcos++;
            }
        }
        return numBarcos;

    }

    public int bombaRandom() { // GERAR BOMBAS

        for (numBomba = 0; numBomba < 5;) {
            Random sorteia = new Random();

            int posLin = sorteia.nextInt(5) + 0;

            if (inserirBomba(posLin)) { // SE A FUNÇÃO INSERIR == true É AUMENTADO O NÚMERO DE BARCOS

                numBomba++;
            }
        }
        return numBomba;

    }

    public boolean inserir(int l) {
        int posCol, posLin;  // POSIÇÃO INICIAL DA COLUNA E LINHA

        if (cabeBarlin(l) && contBarco < 9) { // INSERIR NA LINHA
            if (mapa[l][0] == 'x' && mapa[l][1] == 'x' && mapa[l][2] == 'x') {  // {x, x, x, ~, ~} 
                posCol = 0;
            } else if (mapa[l][1] == 'x' && mapa[l][2] == 'x' && mapa[l][3] == 'x') { // {~, x, x, x, ~} 
                posCol = 1;
            } else { // {~, ~, x, x, x} 
                posCol = 2;
            }
            botarBarcoLinha(l, posCol); // INSERIR NA LINHA E NO POSICIONAMENTO INICIAL DA COLUNA
            return true;

        } else { //  CHECAR SE CABE NAS COLUNAS 
            for (int c = 0; c < 5; c++) { // VERIFICAR TODAS AS COLUNAS
                if (cabeBarcol(c) && contBarco < 9) { // INSERIR NA COLUNA
                    if (mapa[0][c] == 'x' && mapa[1][c] == 'x' && mapa[2][c] == 'x') {
                        posLin = 0;
                    } else if (mapa[1][c] == 'x' && mapa[2][c] == 'x' && mapa[3][c] == 'x') {
                        posLin = 1;
                    } else {
                        posLin = 2;
                    }

                    botarBarcoColuna(posLin, c); // INSERIR NO POSICIONAMENTO INICIAL DA LINHA E NO POSICIONAMENTO DA COLUNA

                }

            }
            return true;

        }
    }

    public boolean cabeBarlin(int l) {
        if ((mapa[l][0] == 'x' && mapa[l][1] == 'x' && mapa[l][2] == 'x')
                || (mapa[l][1] == 'x' && mapa[l][2] == 'x' && mapa[l][3] == 'x')
                || (mapa[l][2] == 'x' && mapa[l][3] == 'x' && mapa[l][4] == 'x')) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cabeBarcol(int c) {
        if ((mapa[0][c] == 'x' && mapa[1][c] == 'x' && mapa[2][c] == 'x')
                || (mapa[1][c] == 'x' && mapa[2][c] == 'x' && mapa[3][c] == 'x')
                || (mapa[2][c] == 'x' && mapa[3][c] == 'x' && mapa[4][c] == 'x')) {
            return true;
        } else {
            return false;
        }
    }

    public void botarBarcoColuna(int posLin, int c) {
        for (int k = posLin; k < posLin + 3; k++) { // O BARCO TEM 3 PARTES, POR ISSO O K = POSICIONAMENTO INICIAL E K < POSICIONAMENTO INICIAL + 3
            mapa[k][c] = '#'; // "#" É O SÍMBOLO DO BARCO NO "mapa[][]"
            contBarco++;
        }
    }

    public void botarBarcoLinha(int l, int posCol) {
        for (int k = posCol; k < posCol + 3; k++) { // O BARCO TEM 3 PARTES, POR ISSO O K = POSICIONAMENTO INICIAL E K < POSICIONAMENTO INICIAL + 3
            mapa[l][k] = '#'; // "#" É O SÍMBOLO DO BARCO NO "mapa[][]"
            contBarco++;
        }
    }

    public boolean inserirBomba(int l) {
        int posCol = 0;  // POSIÇÃO INICIAL DA COLUNA

        if (cabeBomba(l) && contBombas < 5) { // INSERIR BOMBAS

            if (mapa[l][0] == 'x') { // POSICIONAMENTO INICIAL DA COLUNA QUE CABE
                posCol = 0;
            } else if (mapa[l][1] == 'x') {
                posCol = 1;
            } else if (mapa[l][2] == 'x') {
                posCol = 2;
            } else if (mapa[l][3] == 'x') {
                posCol = 3;
            } else {
                posCol = 4;
            }

            botarBomba(l, posCol); // INSERIR NA LINHA E NO POSICIONAMENTO INICIAL DA COLUNA
            return true;
        } else {
            return false;
        }

    }

    public boolean cabeBomba(int l) { // VERIFICAR SE NAS COLUNAS DA LINHA CABE UMA BOMBA

        if (mapa[l][0] == 'x' || mapa[l][1] == 'x' || mapa[l][2] == 'x' || mapa[l][3] == 'x' || mapa[l][4] == 'x') {
            return true;
        } else {
            return false;
        }
    }

    public void botarBomba(int l, int c) {
        mapa[l][c] = 'F';  // "F" É O SÍMBOLO DA BOMBA NO "mapa[][]"
        contBombas++;
    }

    public void letrasMatriz() {

        int a;

        char aux = '.', letras[] = new char[5];

        for (a = 0; a < 5; a++) { // ESCREVER A LETRA DAS COLUNAS

            if (a == 0) {
                aux = 'A';

            } else if (a == 1) {

                aux = 'B';

            } else if (a == 2) {

                aux = 'C';

            } else if (a == 3) {

                aux = 'D';

            } else if (a == 4) {

                aux = 'E';

            } else {

                System.out.print("Não");

            }

            letras[a] = aux;
            if (a == 0) {  // ESPAÇAMENTO DA PRIMEIRA LETRA
                System.out.print(ANSI_YELLOW + "\t\t\t\t          " + letras[a]);
            } else { // ESPAÇAMENTO DO RESTANTE DAS LETRAS
                System.out.print("  " + letras[a]);
            }
        }

        System.out.print("\n" + ANSI_RESET);

    }

    public void escreverMatriz() {
        int cont = 1;
        int l, c;
        letrasMatriz();

        for (l = 0; l < 5; l++) {

            System.out.print(l + 1 + " "); // NÚMERO DAS LINHAS

            for (c = 0; c < 5; c++) {

                if (c == 0) { // ESPAÇAMENTO DO PRIMEIRO CICLO
                    System.out.print(" " + mapa[l][c] + "  ");
                } else { // ESPAÇAMENTO DO RESTANTE DO CICLO
                    System.out.print(mapa[l][c] + "  ");
                }
            }

            System.out.print("\n"); // PULAR PARA A PROXIMA LINHA
        }

    }

    public void escreverTabuleiro() {
        int cont = 1;
        int l, c;

        System.out.println(ANSI_CYAN + descricao() + ANSI_RESET);

        letrasMatriz();
        for (l = 0; l < 5; l++) {

            // NÚMERO DAS LINHAS
            System.out.print("\t\t\t\t      " + ANSI_YELLOW); 
            System.out.print(l + 1 + " | " + ANSI_RESET);

            for (c = 0; c < 5; c++) {
                if (c == 0) { // ESPAÇAMENTO DO PRIMEIRO CICLO
                    System.out.print("" + tabuleiro[l][c] + "  ");
                } else { // ESPAÇAMENTO DO RESTANTE DO CICLO
                    System.out.print(tabuleiro[l][c] + "  ");
                }

            }

            System.out.print("\n"); // PULAR PARA A PROXIMA LINHA
        }

    }

    public int lerPosicao() {

        int c = 0, l = 0;

        char dc;
        char dl;
        while (c <= 0) {
            System.out.println("Digite a coluna: ");

            dc = e.next().charAt(0);

            if (dc == 'A' || dc == 'a') {

                c = 0;  // A POSIÇÃO "A" É IGUAL A ZERO PORQUE UMA MATRIZ COMEÇA {0, 1, 2, 3, 4}
                break; //                                                         A  B  C  D  E

            } else if (dc == 'B' || dc == 'b') {

                c = 1;

            } else if (dc == 'C' || dc == 'c') {

                c = 2;

            } else if (dc == 'D' || dc == 'd') {

                c = 3;

            } else if (dc == 'E' || dc == 'e') {

                c = 4;

            } else {
                c = -1;  // SAIR DO WHILE
                PlayMusic(sERRO);
                System.out.println("Digite uma Opção Válida!");
            }
        }

        while (true) { // Escreva a Linha

            System.out.println("Escreva a linha: ");

            dl = e.next().charAt(0);

            if (dl == '1' || dl == '2' || dl == '3' || dl == '4' || dl == '5') { // VERIFICAR SE O NÚMERO DIGITADO É VALIDO
                int dc2 = Integer.parseInt(String.valueOf(dl));
                l = dc2 - 1;

                if ("x".equals(tabuleiro[l][c])) {  // SE A POSIÇÃO AINDA NÃO FOI MARCADA, ELA PRECISA SER = x
                    
                    if (mapa[l][c] == '#') { // VERIFICAR SE NA POSIÇÃO EXISTE UM SÍMBOLO NO MAPA, SE SIM TRANSCREVER O TABULEIRO COM O SÍMBOLO CORRETO NA MESMA POSIÇÃO
                        tabuleiro[l][c] = ANSI_VERDE + "V" + ANSI_RESET;                
                        contAcertos++;
                        PlayMusic(sBARCO);

                    } else if (mapa[l][c] == 'x') { // MAR
                        tabuleiro[l][c] = ANSI_AZUL + "~" + ANSI_RESET;
                        PlayMusic(sMAR);
                    } else if (mapa[l][c] == 'F') { // BOMBA
                        tabuleiro[l][c] = ANSI_VERMELHO + "Ø" + ANSI_RESET;
                        contErros++;
                        PlayMusic(sBOMBA);
                    }
                    contTentativas++;
                    break;
                } else { // SE NÃO A POSIÇÃO JÁ FOI DIGITADA
                    System.out.println("Você já Digitou essa coordenada!");
                    PlayMusic(sERRO);
                    break;
                }
            } else { // OPÇÃO INVÁLIDA
                PlayMusic(sERRO);
                System.out.println("Digite uma Opção Válida!");
            }

        }
        return contAcertos;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NewClass matriz = new NewClass();
        // PRIMEIRA GERAÇÃO DE BARCOS, BOMBAS E INICIO DA MÚSICA.
        matriz.barcoRandom();
        matriz.bombaRandom();
        String tecla = "";
        LoopMusic(sTEMA);
        while (tecla.isEmpty()) { // MENU
            matriz.limparConsole();
            System.out.println(ANSI_BLUE_BACKGROUND + ANSI_YELLOW + MENU() + ANSI_RESET);
            tecla = e.nextLine();
            
            if (tecla.isEmpty()) {  // SE A TECLA APERTADA FOR IGUAL A "Enter" EXECUTA
               matriz.limparConsole(); // LIMPAR DADOS DO INICIO DO CMD
                
               System.out.println(SAIDAINST());  // INSTRUÇOES
               String continuar = e.nextLine();
               
                if (continuar.equals("sim") || continuar.equals("Sim") || continuar.equals("SIm")    // CONTINUAR DEPOIS DAS INSTRUÇÕES
                        || continuar.equals("SIM") || continuar.equals("sIm") || continuar.equals("sIM")
                        || continuar.equals("siM") || continuar.equals("SiM")) {
                } else {
                    break;
                }
                
                String resposta = "sim";
                while (resposta.equals("sim") || resposta.equals("Sim") || resposta.equals("SIm") // CONTINUAR NO JOGO
                        || resposta.equals("SIM") || resposta.equals("sIm") || resposta.equals("sIM")
                        || resposta.equals("siM") || resposta.equals("SiM")) {

                    // RESET DOS CONTADORES
                    contAcertos = 1;  
                    contErros = 1;
                    contTentativas = 0;

                    while (contAcertos <= 9 && contErros <= 3) {  // VAI EXECUTAR ENQUANTO NÃO ACERTAR AS 9 PARTES DE BARCO OU AS 3 BOMBAS
                        matriz.limparConsole();
                        matriz.escreverTabuleiro();
                        matriz.lerPosicao();
                        matriz.limparConsole();
                        matriz.escreverTabuleiro();
                    }

                    if (contAcertos > 9) {   // GANHOU
                        matriz.limparConsole();
                        System.out.println(ANSI_AZUL + GANHOU() + ANSI_RESET);
                        PlayMusic(sGANHOU);
                    } else {                // PERDEU
                        matriz.limparConsole();
                        System.out.println(ANSI_VERMELHO + PERDEU() + ANSI_RESET);
                        PlayMusic(sPERDEU);
                    }

                    System.out.println("Digite 'Sim' para continuar jogando ou qualquer outra coisa pra sair.");
                    resposta = e.next(); // SE FOR "Sim" ELE CONTINUA NO WHILE E EXECUTA ATÉ O FIM 
                    
                    /* RESET DO JOGO */
                    matriz.resetMapa();
                    
                    // GERAR NOVOS BARCOS E BOMBAS
                    contBombas = 0;
                    contBarco = 0;
                    matriz.barcoRandom();
                    matriz.bombaRandom();

                }
                break; // SE A RESPOSTA FOR DIFERENTE DE "Sim" EXECUTA UM BREAK E SAI DOS WHILES
            } else {  
                break;  // SE A TECLA APERTADA FOR DIFERENTE DE "Enter" EXECUTA UM BREAK E SAI DO WHILE
            }
        }
    }

    public void limparConsole() throws IOException, InterruptedException {
        if (System.getProperty("os.name").contains("Windows")) { // SE FOR WINDOWS
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // EXECUTA "cls" COMANDO PARA LIMPAR CONSOLE
        } else { // SE NÃO
            Runtime.getRuntime().exec("clear");  // EXECUTA "clear" COMANDO PARA LIMPAR CONSOLE 

        }
    }

    public void resetMapa() { // REMOVER OS SÍMBOLOS DE BARCO, BOMBA E MAR
        for (int l = 0; l < 5; l++) {

            for (int c = 0; c < 5; c++) {
                mapa[l][c] = 'x';
                tabuleiro[l][c] = "x";
            }
        }
    }

    /* ESTILOS */
    
    //DESCRICAO
    public static String descricao() {
        String saida = String.format(
                """
             _________________________
             ***BATALHA NAVAL***      |
             _________________________|
             Objetivo:                |
             Afundar os três Barcos   | 
             sem acertar três bombas. |
                                      |
             Tentativas: %02d;          |                               
             _________________________|
             """, contTentativas);
        return saida;
    }

    public static String INSTRUCAO() {
        String saida = """
                    >>===============================================================<<
                    ||                    ***REGRAS DO JOGO***                       ||
      ||>>=======================================================================================<<||
      ||            |                                                                 |            ||
      ||            |   Para ganhar é necessario encontrar 3 embarcações escondidas,  |            ||
      ||            |      cada embarcação é composta por 3 partes, totalizando 9     |            ||
      ||            |           partes no jogo.  Mas não é tão simples assim,         |            ||
      ||            |            o tabuleiro é infestado por bombas onde só           |            ||
      ||            |            pode bater em até 3 para não perder o jogo.          |            || 
      ||            '================================================================='            ||
      ||                                          EXEMPLOS:                                        ||
      ||                                       /=============/                                     ||       
      ||                                                                                           ||
      ||                              A  B  C  D  E         A  B  C  D  E                          ||
      ||      	            1 | Ø  V  ~  V  Ø     1 | V  V  V  Ø  ~                          ||
      ||                          2 | Ø  V  Ø  V  ~     2 | V  V  V  ~  ~                          ||
      ||                          3 | ~  V  ~  V  ~     3 | V  Ø  Ø  Ø  ~                          ||
      ||                          4 | ~  ~  Ø  ~  ~     4 | V  ~  ~  ~  ~                          ||
      ||                          5 | V  V  V  ~  ~     5 | V  Ø  ~  ~  ~                          ||
      ||                                                                                           ||
      ||                      ~ = mar      V = parte da embarcação     Ø = Bomba                   ||
      ||                                                                                           ||
      ||                    Digite "sim" para continuar, ou qualquer coisa para sair               ||
      ||>>=======================================================================================<<||""";
        return saida;
    }
    
    public static String SAIDAINST() {
        String vBarco = "V";
        String vMar = "~";
        String vBomba = "Ø";
        
        String vTexto = "Para ganhar é necessario encontrar 3 embarcações escondidas, cada embarcação é composta por 3 partes, totalizando 9 partes no jogo.  Mas não é tão simples assim, o tabuleiro é infestado por bombas onde só pode bater em até 3 para não perder o jogo. ";
 
        // CORES DOS SIMBOLOS E DO TEXTO
        String subsBarco = ANSI_VERDE + vBarco + ANSI_CYAN;
        String subsMar = ANSI_AZUL + vMar + ANSI_CYAN;
        String subsBomba = ANSI_VERMELHO + vBomba + ANSI_CYAN;
        String subsTexto = ANSI_YELLOW + vTexto + ANSI_CYAN;
        
        String vInstrucao = ANSI_CYAN + INSTRUCAO() + ANSI_RESET;
        
        // IMPLEMENTAÇÃO DAS CORES
        String novoTexto = vInstrucao.replace(vBarco, subsBarco)
             .replace(vMar, subsMar)
             .replace(vBomba, subsBomba)
             .replace(vTexto, subsTexto);
        
        return novoTexto;
    }
    
    // CORES:
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_VERDE = "\u001B[32m";
    public static final String ANSI_AZUL = "\u001B[34m";
    public static final String ANSI_VERMELHO = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";

    // ASCII
    public static String MENU() {
        String saida = """
"     /================================================================================/      "
"     ||  ____    _  _____  _    _     _   _    _      _   _    ___     ___    _      ||      "
"     || | __ )  / ||_   _|/ |  | |   | | | |  / |    | | | |  / | |   / / |  | |     ||      "
"     || |  _ | / _ | | | / _ | | |   | |_| | / _ |   |  || | / _ | | / / _ | | |     ||      "
"     || | |_) / ___ || |/ ___ || |___|  _  |/ ___ |  | ||  |/ ___ | V / ___ || |___  ||      "
"     || |____/_/   |_|_/_/   |_|_____|_| |_/_/   |_| |_| |_/_/   |_|_/_/   |_|_____| ||      "
"     \'===============================================================================/       "
"                  ~~~             |                                                          "
"             ~~~~     ~~~~      -----                    |                                   "
"                  ~~~           )___(                  -----                                 "
"                                  |                    )___(                                 "
"                              ---------                  |                                   "
"                             /         |              -------                                "
"                            /___________|            /       |                               "
"                                  |                 /_________|                              "
"                           ---------------               |                                   "
"                          /               |        -------------                             "
"                         /                 |      /             |                            "
"                        /___________________|    /_______________|                           "
"                      ____________|______________________|__________                         "
"                       |_                                        _/                          "
"                         |______________________________________/                            "
"                  ~~..             ...~~~.           ....~~~...     ..~                      "
"                            Presione ENTER para iniciar o Jogo.                              "
"                             Ou qualquer outra coisa para sair.                              "
                       """;
        return saida;
    }

    public static String GANHOU() {
        String kk
                = """                                     
                           _                          _
 _ __    __ _  _ __  __ _ | |__   ___  _ __   ___    | |  
| '_ |  | _` || '__|| _` || '_ | | _ || '_ | / __|   | |
| |_) || (_| || |  | (_| || |_) || __|| | ||  __ |   |_|
| .__|  |__,_||_|   |__,_||_.__/ |___||_| |_||___/   (_)             
|_|                                                                                    
  ' V '             __'__     __'__      __'__        
                   /    /    /    /     /    /             
                  /|____|    |____|     |____| 
                 / ___!___   ___!___    ___!___              ' V '        
               // (      (  (      (   (      (
             / /   (______(  (______(   (______(                    
           /  /   ____!_____ ___!______ ____!_____        
         /   /   /         //         //         /
      /    /    |         ||         ||         |
     /_____/     \'         ||         ||         \'                
           \'     \'_________((_________((_________\'        
            \'         |          |         |        
             \'________!__________!_________!________/        
              \'|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_/|
               \'    _______________                /                                             
        """;
        return kk;
    }

    public static String PERDEU() {
        String vv
                = """
             \'|/
            `--+--'
              /|\
             ' | '
               |               ___   ___                                  _
               |               |  | |  |                                 | |
           ,--'#`--.           | .  .  |  ___   _ __  _ __   ___  _   _  | |
           |#######|           | ||/|  | / _  || '__|| '__|| _ ||| |  || | |
        _.-'#######`-._        | |  |  || (_) || |   | |   |  __/| |_| | |_|
     ,-'###############`-.     |_|  |_ | |___/ |_|   |_|    |___| |__,_| (_)
   ,'#####################`,                               
  /#########################|
 |###########################|
|#############################|
|#############################|
|#############################|
|#############################|
 |###########################|
  |#########################/
   `.#####################,'
     `._###############_,'
        `--..#####..--'
                    """;
        return vv;
    }
    
    /* SONS */
    public static String sTEMA = "recursos/Tema.wav";
    
    public static String sMAR = "recursos/Água.wav";
    public static String sBARCO = "recursos/MisseisBaixo.wav";
    public static String sBOMBA = "recursos/Bombas.wav";
    
    public static String sGANHOU = "recursos/Vitoria.wav";
    public static String sPERDEU = "recursos/Derrota.wav";
    
    public static String sERRO = "recursos/Erro.wav";

    // FUNÇÕES
     public static void PlayMusic(String location) { // TOCA MÚSICA
        
        try { // TENTE
            
            File musicPath = new File (location); // CRIAR UM OBJETO PARA POSSIBILITAR A INTERAÇÃO COM O ARQUIVO DE SOM 
            
            if (musicPath.exists()) { // SE EXISTIR O ARQUIVO
                
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);// CRIAR UM OBJETO DE FLUXO DE ENTRADA DE ÁUDIO // CARREGAR ÁUDIO
                Clip clip = AudioSystem.getClip(); // CRIAR UM OBJETO "Clip" PARA PEGAR A ENTRADA DE ÁUDIO DO NOSSO SISTEMA // TRANSMITIR ESSE ÁUDIO
                clip.open(audioInput); // COLOCAMOS A ENTRADA DE ÁUDIO DENTRO DO "clip"
                clip.start(); // INICIAMOS A MÚSICA
                
            } else { // SE NÃO EXISTIR
                System.out.println("Não foi póssivel encontrar");
            }
            
        } catch (Exception e) { // SE DER ERRADO EXECUTAMOS O "catch"
            System.out.println(e);
    }
    }
     public static void LoopMusic (String location) { // TOCA MÚSICA E FAZ UM LOOP
         try { // TENTE
            
            File musicPath = new File (location);
            
            if (musicPath.exists()) { // SE EXISTIR O ARQUIVO
                
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.loop(Clip.LOOP_CONTINUOUSLY); // LOOP
                clip.start();
                
            } else { // SE NÃO EXISTIR
                System.out.println("Não foi póssivel achar");
            }
            
        } catch (Exception e) { // SE DER ERRADO EXECUTAMOS O "catch"
            System.out.println(e);
    }
     }
}
