//*************************** ATENÇÃO! ****************************
// O método main() deve ser alterado somente nos comentários TODO.
// Todas as outras instruções devem permanecer intactas e o código
// deve funcionar conforme descrito no enunciado da atividade.
//*************************** ATENÇÃO! ****************************
// arquivo: src/MainApl2.java

// TODO: Colocar a identificação dos(as) integrantes aqui.
// Nome: Gabriel Pereira de Souza RA: 10440766
// Nome: Lucas dos Santos Bartolomeu RA: 10747984
// Nome: Joaquim Lange Lima Amaral RA: 10738376

// TODO: Listar todas as referências consultadas para solucionar a atividade.
// - Material das aulas teóricas e práticas sobre Listas Duplamente Encadeadas.
// - Documentação do Java para manipulação de Strings (split, format, Float.parseFloat).

import apl2.DLinkedList;
import apl2.LinkedListOriginal;
import apl2.Node;
import apl2.Operation;
import apl2.Data; 
import java.io.IOException;
import java.util.Scanner;

public class MainApl2 {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        
        // Declaramos as variáveis fora do loop para manter os dados entre as opções do menu
        LinkedListOriginal listMenu = new LinkedListOriginal();
        DLinkedList fixedListMenu = null;
        DLinkedList filteredGradedListMenu = null;
        DLinkedList filteredNonGradedListMenu = null;
        DLinkedList aboveAverageListMenu = null;
        float averageMenu = 0.0f;
        String contentsMenu = "";

        do  { 
            System.out.println("\n======== Sistema Conversor de Notas ========");
            System.out.println("1) Dados originais (Ler arquivo e exibir legado)");
            System.out.println("2) Dados convertidos (Gerar dados.csv e exibir nova estrutura)");
            System.out.println("3) Lista notas filtradas válidas");
            System.out.println("4) Lista notas filtradas inválidas");
            System.out.println("5) Média das notas válidas");
            System.out.println("6) Notas acima da média");
            System.out.println("7) Lista mapeada para uma unica string");
            System.out.println("8) Finalizar sistema");
            System.out.println("============================================");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n>>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
                    listMenu = new LinkedListOriginal(); 
                    try { 
                        String conteudoArquivo = Data.loadTextFileToString("dados.txt");
                        String[] linhas = conteudoArquivo.split("\\r?\\n|\\r");

                        for (String linha : linhas) { 
                            if (!linha.trim().isEmpty()) { 
                                String[] partes = linha.split("#");
                                int id = Integer.parseInt(partes[0].trim());
                                String nome = partes[1].trim();
                                int inteiro = Integer.parseInt(partes[2].trim());
                                int decimo = Integer.parseInt(partes[3].trim());
                                listMenu.append(id, nome, inteiro, decimo);
                            }
                        }
                    } catch (IOException e) { 
                        System.err.println("Erro ao carregar o arquivo dados.txt: " + e.getMessage());
                    }
                    System.out.println(listMenu);
                    System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<\n");
                    break;
                    
                case 2:
                    if (listMenu.isEmpty()) {
                        System.out.println("AVISO: Carregue os dados originais primeiro (Opção 1).");
                        break;
                    }
                    fixedListMenu = Operation.map(listMenu);
                    contentsMenu = Operation.mapToString(fixedListMenu);
                    try { 
                        Data.saveStringToTextFile("dados.csv", contentsMenu);
                    } catch (IOException e) { 
                        System.err.println("Erro ao salvar o arquivo dados.csv: " + e.getMessage());
                    }   
                    System.out.println("\n>>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>");
                    System.out.println(fixedListMenu);
                    System.out.println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<\n");
                    break;
                    
                case 3:
                    if (fixedListMenu == null) {
                        System.out.println("AVISO: Converta os dados primeiro (Opção 2).");
                        break;
                    }
                    filteredGradedListMenu = Operation.filterRemoveNonGraded(fixedListMenu);
                    System.out.println("\n>>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
                    System.out.println(filteredGradedListMenu);
                    System.out.println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<\n");
                    break;
                    
                case 4:
                    if (fixedListMenu == null) {
                        System.out.println("AVISO: Converta os dados primeiro (Opção 2).");
                        break;
                    }
                    filteredNonGradedListMenu = Operation.filterRemoveGraded(fixedListMenu);
                    System.out.println("\n>>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
                    System.out.println(filteredNonGradedListMenu);
                    System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<\n");
                    break;
                    
                case 5:
                    if (filteredGradedListMenu == null) {
                        System.out.println("AVISO: Gere a lista de notas válidas primeiro (Opção 3).");
                        break;
                    }
                    averageMenu = Operation.reduce(filteredGradedListMenu);
                    System.out.println("\n>>>>>>>>>> Média das notas válidas >>>>>>>>>>");
                    System.out.println(averageMenu);
                    System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<\n");
                    break;
                    
                case 6:
                    if (filteredGradedListMenu == null || averageMenu == 0.0f) {
                        System.out.println("AVISO: Calcule a média das notas válidas primeiro (Opção 5).");
                        break;
                    }
                    aboveAverageListMenu = Operation.filterRemoveBelowAverage(filteredGradedListMenu, averageMenu);
                    System.out.println("\n>>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
                    System.out.println(aboveAverageListMenu);
                    System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<\n");
                    break;
                    
                case 7:
                    if (fixedListMenu == null || contentsMenu.isEmpty()) {
                        System.out.println("AVISO: Converta os dados primeiro (Opção 2).");
                        break;
                    }
                    System.out.println("\n>>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
                    System.out.println(contentsMenu);
                    System.out.println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<\n");
                    break;
                    
                case 8:
                    System.out.println("\nFinalizando o sistema...");
                    break;
                    
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }
        } while (opcao != 8);
        
        scanner.close();


        /* ================= CÓDIGO ORIGINAL COMENTADO CONFORME PDF ================= */
        /*
        LinkedListOriginal list = new LinkedListOriginal();

        // TODO: Carregar o conteúdo do arquivo "dados.txt" e adicionar cada linha como um nó na LinkedListOriginal list.
        try { 
            String conteudoArquivo = Data.loadTextFileToString("dados.txt");
            String[] linhas = conteudoArquivo.split("\\r?\\n|\\r");

            for (String linha : linhas) { 
                if (!linha.trim().isEmpty()) { 
                    String[] partes = linha.split("#");
                    int id = Integer.parseInt(partes[0].trim());
                    String nome = partes[1].trim();
                    int inteiro = Integer.parseInt(partes[2].trim());
                    int decimo = Integer.parseInt(partes[3].trim());
                    list.append(id, nome, inteiro, decimo);
                }
            }
        } catch (IOException e) { 
            System.err.println("Erro ao carregar o arquivo dados.txt: " + e.getMessage());
            return;
        }

        System.out.println(">>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
        System.out.println(list);
        System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<\n");
        
        DLinkedList fixedList = Operation.map(list);
        System.out.println(">>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>");
        System.out.println(fixedList);
        System.out.println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<\n");
        
        DLinkedList filteredGradedList = Operation.filterRemoveNonGraded(fixedList);
        System.out.println(">>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
        System.out.println(filteredGradedList);
        System.out.println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<\n");
        
        DLinkedList filteredNonGradedList = Operation.filterRemoveGraded(fixedList);
        System.out.println(">>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
        System.out.println(filteredNonGradedList);
        System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<\n");

        float average = Operation.reduce(filteredGradedList);
        System.out.println(">>>>>>>>>> Média das notas válidas >>>>>>>>>>");
        System.out.println(average);
        System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<\n");
        
        DLinkedList aboveAverageList = Operation.filterRemoveBelowAverage(filteredGradedList, average);
        System.out.println(">>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
        System.out.println(aboveAverageList);
        System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<\n");
        
        String contents = Operation.mapToString(fixedList);
        System.out.println(">>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
        System.out.println(contents);
        System.out.println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<\n");
        
        // TODO: Salvar o conteúdo da String contents em um arquivo chamado "dados.csv".
        try { 
            Data.saveStringToTextFile("dados.csv", contents);
        } catch (IOException e) { 
            System.err.println("Erro ao salvar o arquivo dados.csv: " + e.getMessage());
        }   

        Node test1 = fixedList.getNode("23.S1-999");
        System.out.println(">>>>>>>>>> test1 >>>>>>>>>>\n" + test1 + "\n<<<<<<<<<< test1 <<<<<<<<<<\n");

        Node test2 = fixedList.removeNode("23.S1-999");
        System.out.println(">>>>>>>>>> test2 >>>>>>>>>>\n" + test2 + "\n<<<<<<<<<< test2 <<<<<<<<<<\n");

        Node test3 = fixedList.getNode("23.S1-999");
        System.out.println(">>>>>>>>>> test3 >>>>>>>>>>\n" + test3 + "\n<<<<<<<<<< test3 <<<<<<<<<<\n");

        aboveAverageList.clear();
        System.out.println(">>>>>>>>>> aboveAverageList.clear() >>>>>>>>>>\n" + aboveAverageList + "\n<<<<<<<<<< aboveAverageList.clear() <<<<<<<<<<\n");

        DLinkedList testList = new DLinkedList();
        // TODO: Inserir um nó no início da lista testList com os dados ("ABC", "John Doe", 4.7f).
        testList.insert("ABC", "John Doe", 4.7f);
        // TODO: Inserir um nó no final da lista testList com os dados ("XYZ", "Jane Doe", 9.9f).
        testList.append("XYZ", "Jane Doe", 9.9f);
        // TODO: Inserir um nó no início da lista testList com os dados ("321", "Test", 2.3f).
        testList.insert("321", "Test", 2.3f);
        // TODO: Inserir um nó no final da lista testList com os dados ("Nothing", "Yada yada yada", 99.9f).
        testList.append("Nothing", "Yada yada yada", 99.9f);
        System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
        System.out.println("testList.getHead(): " + testList.getHead());
        System.out.println("testList.getTail(): " + testList.getTail());
        System.out.println("testList.removeHead(): " + testList.removeHead());
        System.out.println("testList.removeTail(): " + testList.removeTail() + '\n');
        System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
        System.out.println("testList.getHead(): " + testList.getHead());
        System.out.println("testList.getTail(): " + testList.getTail());
        System.out.println("testList.removeNode(\"ABC\"): " + testList.removeNode("ABC") + '\n');
        System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
        System.out.println("testList.getHead(): " + testList.getHead());
        System.out.println("testList.getTail(): " + testList.getTail() + '\n');
        // TODO: Inserir um nó no início da lista testList com os dados ("qwerty", "QWERTY", 1.2f).
        testList.insert("qwerty", "QWERTY", 1.2f);
        // TODO: Inserir um nó no final da lista testList com os dados ("WASD", "wasd", 3.4f).
        testList.append("WASD", "wasd", 3.4f);
        // TODO: Inserir um nó no início da lista testList com os dados ("ijkl", "IJKL", 5.6f).
        testList.insert("ijkl", "IJKL", 5.6f);
        // TODO: Inserir um nó no final da lista testList com os dados ("1234", "Um Dois Tres Quatro", 7.8f).
        testList.append("1234", "Um Dois Tres Quatro", 7.8f);
        System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
        testList.clear();
        System.out.println(">>>>>>>>>> testList.clear() >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList.clear() <<<<<<<<<<\n");
        */
    }
}
