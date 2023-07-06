/**
 * Palindromo em C
 * @author Ana Luiza Pacheco Leite
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define bool short
#define true 1
#define false 0
#define equals(a,b)(((strcmp(a,b)==0) ? true:false))
#define NUMENTRADA 1000
#define TAMLINHA 1000


/**
* verifica se uma string é palindromo ou nao
* @param string entrada
*/

bool palindromo(char* entrada){
	int inicio = 0;
	int fim = strlen(entrada)-1;
	bool resp = false;
	while(inicio < fim && fim > inicio){
		if(entrada[inicio] == entrada[fim]){
			resp = true;
		}else{
			resp= false;
		}
		inicio++;fim--;
	}
	return resp;
}//fim palindromo


bool isFim(char* s){
	return(strlen(s)>=3 && s[0]=='F' && s[1]=='I' && s[2]=='M');
}


int main(){
	char entrada[10000];
	for(scanf("%[^\n]\n",entrada); !isFim(entrada); scanf("%[^\n]\n",entrada)){
		bool palindromo(char* entrada);
		bool resp = palindromo(entrada);
		if(resp == true){
			printf("SIM\n");
		}else{
			printf("NAO\n");
		}
	}
	}//fim main
//fim palindromo
		






