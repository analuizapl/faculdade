/**
 * Is
 * @author Ana Luiza Pacheco Leite
 */ 

public class Is{	
/**
* verifica se a string é composta só por vogais
* @param string entrada
*/ 
public static boolean isVogal(String entrada){
		boolean resp=true;
		for(int i=0;i<entrada.length();i++){
			if(entrada.charAt(i)=='a'||entrada.charAt(i)=='e'||entrada.charAt(i)=='i'||entrada.charAt(i)=='o'||entrada.charAt(i)=='u'||entrada.charAt(i)=='A'||
			entrada.charAt(i)=='E'||entrada.charAt(i)=='I'||entrada.charAt(i)=='O'||entrada.charAt(i)=='U'){
				resp=true;
			}else{
				resp=false;
				i=entrada.length();
			}
		}
		return resp;
	}//fim isvogal

/**
* verifica se a string é composta só por consoantes
* @param string entrada
*/ 
public static boolean isConsoante(String entrada){
		boolean resp=true;
		for(int i=0;i<entrada.length();i++){
			if(entrada.charAt(i)!='a'||entrada.charAt(i)!='e'||entrada.charAt(i)!='i'||entrada.charAt(i)!='o'||entrada.charAt(i)!='u'||entrada.charAt(i)!='A'||
			entrada.charAt(i)!='E'||entrada.charAt(i)!='I'||entrada.charAt(i)!='O'||entrada.charAt(i)!='U'||entrada.charAt(i)!='1'||entrada.charAt(i)!='2'||
			entrada.charAt(i)!='3'||entrada.charAt(i)!='4'||entrada.charAt(i)!='5'||entrada.charAt(i)!='6'||entrada.charAt(i)!='7'||entrada.charAt(i)!='8'||
			entrada.charAt(i)=='9'|| entrada.charAt(i)=='0'){
				resp=false;
				i=entrada.length();
			}
		}
		return resp;
	}//fim isconsoante

/**
* verifica se a string é composta por inteiros
* @param string entrada
*/ 
public static boolean isInteiro(String entrada){
		boolean resp=true;
		for(int i=0;i<entrada.length();i++){
			if(entrada.charAt(i)=='1'||entrada.charAt(i)=='2'||entrada.charAt(i)=='3'||entrada.charAt(i)=='4'||entrada.charAt(i)=='5'||entrada.charAt(i)=='6'||
			entrada.charAt(i)=='7'||entrada.charAt(i)=='8'||entrada.charAt(i)=='9'||entrada.charAt(i)=='0'){
				resp=true;
			}else{
				resp=false;
				i=entrada.length();
			}
		}
		return resp;
	}//fim isinteiro

/**
* verifica se a string é composta por reais
* @param string entrada
*/ 
public static boolean isReal(String entrada){
		boolean resp=true;
		int quant=0;
		for(int i=0;i<entrada.length();i++){
			if(entrada.charAt(i)=='0'||entrada.charAt(i)=='1'||entrada.charAt(i)=='2'||entrada.charAt(i)=='3'||entrada.charAt(i)=='4'||entrada.charAt(i)=='5'||
			entrada.charAt(i)=='6'||entrada.charAt(i)=='7'||entrada.charAt(i)=='8'||entrada.charAt(i)=='9'){
				if(entrada.charAt(i)==',' || entrada.charAt(i)=='.'){
					quant++;
			}else{
				resp=false;
				i=entrada.length();
			}
		}
		if(quant>1||quant==0){
			resp=false;
		}
		}
		return resp;
	}//fim isreal

/**
* para a leitura da entrada quando for igual a FIM
* @param string entrada, string a
*/
	public static boolean equals(String entrada,String a){
		boolean fim=false;
		int tamanho=entrada.length();
		int tamanho2=a.length();
		if(tamanho==tamanho2){
			for(int i=0;i<tamanho;i++){
				if(entrada.charAt(i)==a.charAt(i)){
					fim=true;
				}else{
					fim=false;
					i=tamanho;
				}
			}
		}
		return fim;
	}//fim equals

public static void main(String[]args){
		String a="FIM";
      		String entrada=MyIO.readLine();
		while(equals(entrada,a)==false){
			if(isVogal(entrada)){
				MyIO.println("SIM ");
			}else{
				MyIO.print("NAO ");
			}//fim if(isVogal)
			if(isConsoante(entrada)){
				MyIO.println("SIM ");
			}else{
				MyIO.print("NAO ");
			}//fim isConsoante
			if(isInteiro(entrada)){
				MyIO.println("SIM ");
			}else{
				MyIO.print("NAO ");
			}//fim isInteiro
			if(isReal(entrada)){
				MyIO.println("SIM");
			}else{
				MyIO.println("NAO");
			}
			entrada = MyIO.readLine();
		}//fim while
	}//fim main
}//fim is
