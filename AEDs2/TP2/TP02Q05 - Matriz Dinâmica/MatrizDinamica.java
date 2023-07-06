import java.io.*;
import java.util.*;

class Celula {
	public int elemento;
	public Celula inf,sup,esq,dir;

	public Celula() {
        this(0, null, null, null, null);
    }

    public Celula(int elemento) {
        this(elemento, null, null, null, null);
    }

    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir) {
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir  = dir;
    }
}

class Matriz {
	
	private Celula x;
	private int linha, coluna;

    public Matriz(int tam){
		this.linha = this.coluna = tam;
	}

	public Matriz(int linha, int coluna){
		this.linha = linha;
		this.coluna= coluna;
		Celula colunas = null;
		Celula linhas = null;
		for(int i = 0; i < linha; i++){
			for(int j = 0; j < coluna; j++){
				if(i == 0 && j == 0){
					x = new Celula();
					colunas = x;
					linhas = x;
				}else 
                    if(j!=0){
    					colunas.dir = new Celula();
    					colunas.dir.esq = colunas;
    					colunas = colunas.dir;
					if(i!=0){
						colunas.esq.sup.dir.inf = colunas;
						colunas.sup = colunas.esq.sup.dir;
					}
				}
			}
			if(i+1!=linha){
				linhas.inf = new Celula();
				linhas.inf.sup = linhas;
				linhas = linhas.inf;
				colunas = linhas;
			}
		}
	}


	public void inserir(){
		String[] aux = new String[linha];
        for (int i=0; i<linha; aux[i]=MyIO.readLine(),i++);
        int i=0, j=0;
        for (Celula lin = x; lin != null; lin = lin.inf, j++) {
            String[] aux2 = aux[j].split(" ");
            i = 0;
            for (Celula col = lin; col != null; col = col.dir, i++) {
                col.elemento = Integer.parseInt(aux2[i]);
            }
        }
	}


	public static Matriz soma(Matriz m1, Matriz m2) {
        Matriz resp = new Matriz(m1.linha, m2.coluna);
        for (Celula lin = resp.x, l1 = m1.x, l2 = m2.x; lin != null; lin = lin.inf, l1 = l1.inf, l2 = l2.inf) {
            for (Celula col = lin, c1 = l1, c2 = l2; col != null; col = col.dir, c1 = c1.dir, c2 = c2.dir) {
                col.elemento = c1.elemento + c2.elemento;
            }
        }
        return resp;
    }


    public static Matriz multiplicacao(Matriz m1, Matriz m2, int m) {
        Matriz resp = new Matriz(m1.linha, m2.coluna);
        for (Celula lin = resp.x , ll = m1.x; lin != null; lin = lin.inf , ll = ll.inf) {
            for (Celula col = lin , cc = m2.x; col != null; col = col.dir , cc = cc.dir) {
                m = 0;
                for (Celula cc1 = ll , ll1 = cc; cc1 != null; cc1 = cc1.dir , ll1 = ll1.inf) {
                m += cc1.elemento * ll1.elemento;
                }
                col.elemento = m;
            }
        }
        return resp;
    }

    public void mostrarDiagonalPrincipal() {
        mostrarDiagonalPrincipal(x);
        System.out.println();
    }



    public void mostrarDiagonalPrincipal(Celula c) {
        System.out.print(c.elemento + " ");
        if ((c.dir != null) && (c.dir.inf != null)) 
            mostrarDiagonalPrincipal(c.dir.inf);
    }

    public void mostrarDiagonalSecundaria() {
        Celula ultima;
        for (ultima = x; ultima.dir != null; ultima = ultima.dir);
            mostrarDiagonalSecundaria(ultima);
        System.out.println();
    }


    public void mostrarDiagonalSecundaria(Celula c) {
        System.out.print(c.elemento + " ");
        if ((c.esq != null) && (c.esq.inf != null)) 
            mostrarDiagonalSecundaria(c.esq.inf);
    }


    public void mostrar() {
        for (Celula lin = x; lin != null; lin = lin.inf) {
            for (Celula col = lin; col != null; col = col.dir) {
                System.out.print(col.elemento + " ");
            }
            System.out.println();
        }
    }
}


public class MatrizDinamica {
	public static void main (String[]args){
		int quant = Integer.parseInt(MyIO.readLine());
    	int linha = 0;
    	int coluna = 0;
    	for (int i = 0; i < quant; i++) {

        	linha = Integer.parseInt(MyIO.readLine());
         	coluna = Integer.parseInt(MyIO.readLine());

         	Matriz m1 = new Matriz(linha, coluna);
        	m1.inserir();

         	linha = Integer.parseInt(MyIO.readLine());
         	coluna = Integer.parseInt(MyIO.readLine());

         	Matriz m2 = new Matriz(linha, coluna);
         	m2.inserir();

         	m1.mostrarDiagonalPrincipal();
         	m1.mostrarDiagonalSecundaria();
         	Matriz.soma(m1, m2).mostrar();
         	Matriz.multiplicacao(m1, m2, 0).mostrar();
    	}
    }
}
