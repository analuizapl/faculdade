/**
 * Aquecimento recursivo em C
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

bool isUpper(char x){
   return (x >= 'A' && x <= 'Z');
}

int contarLetrasMaiusculas(char* s, int i){
   int cont = 0;
   if(i < strlen(s)){
      if(isUpper(s[i]) == true){
         cont++;
      }
      cont += contarLetrasMaiusculas(s, i + 1);
   }
   return cont;
}

int contarLetrasMaiusculas(char* s){
   return contarLetrasMaiusculas(s, 0);
}
bool isFim(char*s){
  return (strlen(s)>=3 && s[0]=='F' && s[1]=='I' && s[2]=='M');
}
int main(int argc,char**argv){
  char entrada[NUMENTRADA][TAMLINHA];
  int numEntrada=0;
  int j=0;
  do{
    fgets(entrada[numEntrada],TAMLINHA,stdin);
  }while(isFim(entrada[numEntrada++])==false);
  numEntrada--;
  for(int i=0;i<numEntrada;i++){
    printf("%i\n",contarLetrasMaiusculas(entrada[i],j));
  }
}//fim main
