/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gerenciador.de.arquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanildo
 */
public class ThreadServidor1 extends Thread {
    
    Socket server,cli;
    PrintStream out;
    int metodo;
    String Nome;
    String Caminho;
    
    public ThreadServidor1(Socket sc,Socket cli, int method, String Nome,String Caminho){
        this.cli=cli;
        server = sc;
        metodo=method;
        this.Nome=Nome;
        this.Caminho=Caminho;
    }
    @Override
    public void run(){
        PrintStream out;
        Scanner in,in2;
        String msg;
        try {
        switch (metodo){
            case 1:
                for (ThreadServidor1 At: Controlador.Servidores){
                if(At == this){
                escritor("/"+server.getInetAddress().getHostAddress()+"/C:/Arquivos/Teste/"+Nome+".txt",leitor(Caminho));              
                    }
                }
                break;
            case 2:
                visualizar("/"+server.getInetAddress().getHostAddress()+"/C:/Arquivos/Teste/");
            break;  
            case 3:
                out = new PrintStream(cli.getOutputStream());
                    out.println(leitor("/"+server.getInetAddress().getHostAddress()+"/C:/Arquivos/Teste/"+Nome+".txt"));
                break;
            case 4:
                File file = new File( "/"+server.getInetAddress().getHostAddress()+"/C:/Arquivos/Teste/"+Nome+".txt" );
                file.delete();
                break;
            }
        } catch (Exception e) {
            }
    }
      
        public static String leitor(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        String Texto="";
        while (true) {
            if (linha != null) {
                Texto+=linha+"\n";
 
            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();
        return Texto;
    }
 
    public static void escritor(String path,String texto ) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        String linha = "";
        Scanner in = new Scanner(System.in);
        linha = in.nextLine();
        buffWrite.append(linha + "\n");
        buffWrite.close();
    }
    
    public void visualizar(String Caminho) throws IOException {

	File file = new File(Caminho);
	File afile[] = file.listFiles();
	int i = 0;
	for (int j = afile.length; i < j; i++) {
		File arquivos = afile[i];
		System.out.println(arquivos.getName());
	}
    }
}
