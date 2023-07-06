#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <iconv.h>
#include <err.h>
/**
 * Registro time
 * @author Ana Luiza Pacheco Leite
 */

#define MAXTAM 100
#define bool short
#define true 1
#define false 0

typedef struct Time {
    char* nome;
    char* apelido;
    char* estadio;
    char* tecnico;
    char* liga;
    char nomeArquivo[100];
    int capacidade;
    int fundacaoDia;
    int fundacaoMes;
    int fundacaoAno;
    long int paginaTam;
} Time;


/**
 * Pilha dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */
//====TIPO CELULA =====
typedef struct Celula {
	Time elemento;        // Elemento inserido na celula.
	struct Celula* prox; // Aponta a celula prox.
} Celula;

Celula* novaCelula(Time elemento){
   Celula* nova = (Celula*) malloc(sizeof(Celula));
   nova->elemento = elemento;
   nova->prox = NULL;
   return nova;
}// End novaCelula()

//PILHA PROPRIAMENTE DITA
Celula* topo; // Sem celula cabeca.

/**
 * Cria uma fila sem elementos.
 */
void start(){
   topo = NULL;
}// End start()

/**
 * Insere elemento na pilha (politica FILO).
 * @param x int elemento a inserir.
 */
void inserir(Time x){
   Celula* tmp = novaCelula(x);
   tmp->prox = topo;
   topo = tmp;
   tmp = NULL;
}// End inserir()

/**
 * Remove elemento da pilha (politica FILO).
 * @return Elemento removido.
 */
Time remover(){
   if (topo == NULL){
      errx(1, "Erro ao remover!");
   }// End remover()
   Time resp = topo->elemento;
   Celula* tmp = topo;
   topo = topo->prox;
   tmp->prox = NULL;
   free(tmp);
   tmp = NULL;
   return resp;
}// End remover()





/**
 * Substitui uma palavra por outra
 * @param string completa,o que quer substituir, pelo que quer substituir
 */
char *replaceWord(const char *s, const char *oldW,  const char *newW){
      char *result;
      int i, cnt = 0;
      int newWlen = strlen(newW);
      int oldWlen = strlen(oldW);
      for (i = 0; s[i] != '\0'; i++)    // Counting the number of times old wordoccur in the string
      {
          if (strstr(&s[i], oldW) == &s[i]){
              cnt++;
              // Jumping to index after the old word.
              i += oldWlen - 1;
          }
      }
      // Making new string of enough length
      result = (char *)malloc(i + cnt * (newWlen - oldWlen) + 1);
      i = 0;
      while (*s){
          // compare the substring with the result
          if (strstr(s, oldW) == s) {
              strcpy(&result[i], newW);
              i += newWlen;
              s += oldWlen;
          }else
              result[i++] = *s++;
      }
      result[i] = '\0';
      return result;
}




/**
 * Remove as tags html
 *
 */
char * removeTags(char * str){
   char * res;
   char * tres;
   size_t len = strlen(str);
   res = (char*) malloc(len + 1);
   int i = 0;
   int tam = strlen(str);
   bool aberta = false, lendo = true;
   while(i < tam){
       char p = str[i];
       aberta = (p == '<' && !aberta) || (p != '>' && aberta);
       if(!aberta && p != '>' && p != '<'){
           if(p == '&') lendo = false;
           else if(lendo){
               len = strlen(res);
               tres = (char*) malloc(len + 1 + 1 );
               strcpy(tres, res);
               tres[len] = p;
               tres[len + 1] = '\0';
               size_t lent = strlen(tres);
               strcpy(res, tres);
               res[lent + 1] = '\0';
           }
           else if(p == ';') lendo = true;
       }
       i++;
   }
   len = strlen(res);
   while(len > 0 && res[0] == ' '){
       len = strlen(res);
       char * tres = (char*) malloc(len);
       for(i = 0; i < len; i++){
           res[i] = res[i+1];
       }
       res[i] = '\0';
   }
   for(i = 0; i < len; i++){
       if(res[i] == '\n')
           res[i] = '\0';
   }
   return res;
}



/**
 * Transforma o mes em numero
 *
 */
int getMes(char* s) {
 int mes = 0;
 if (strstr(s, "January") != NULL) mes = 1;
 if (strstr(s, "February") != NULL) mes = 2;
 if (strstr(s, "March") != NULL) mes = 03;
 if (strstr(s, "April") != NULL) mes = 4;
 if (strstr(s, "May") != NULL) mes = 5;
 if (strstr(s, "June") != NULL) mes = 6;
 if (strstr(s, "July") != NULL) mes = 7;
 if (strstr(s, "August") != NULL) mes = 8;
 if (strstr(s, "September") != NULL) mes = 9;
 if (strstr(s, "October") != NULL) mes = 10;
 if (strstr(s, "November") != NULL) mes = 11;
 if (strstr(s, "December") != NULL) mes = 12;
 return mes;
}



/**
 *imprime as informacoes
 *
 */
void imprimir(Time tmp) {
      int a=0;
      printf("[%d]",a);
	    printf("%s ## ",tmp.nome);
            printf("%s ## ",tmp.apelido);

            if(tmp.fundacaoDia==0){
                printf("0%d/",tmp.fundacaoDia);
            }else{
              if(tmp.fundacaoDia>='0' && tmp.fundacaoDia<='9'){
                  printf("0%d/",tmp.fundacaoDia);
              }else{
                printf("%d/",tmp.fundacaoDia);
              }
            }
            if(tmp.fundacaoMes==0){
                printf("0%d/",tmp.fundacaoMes);
            }else{
              if(tmp.fundacaoMes>='0' && tmp.fundacaoMes<='9'){
                  printf("0%d/",tmp.fundacaoMes);
              }else{
                printf("%d/",tmp.fundacaoMes);
              }
            }
            printf("%d ## ",tmp.fundacaoAno);
            printf("%s ## ",tmp.estadio);
            printf("%d ## ",tmp.capacidade);
            printf("%s ## ",tmp.tecnico);
            printf("liga ## ");
            printf("%s ## ",tmp.nomeArquivo);
            printf("%ld ##",tmp.paginaTam);
            printf("\n");
            a++;
}


/**
 * Retorna só o ano da string
 *
 */
char* removeAno(char* str){
     char* resp = (char*)malloc(100*sizeof(char));
     int j = 0;
     for(int i = 0; i <=4; i++){
         while(j < i){
           resp[j] = str[j];
           j++;
       }
     }
     return resp;
}


/**
 * Retorna string sem os numeros
 *
 */
char* RemoveDigits(char* input){
   char* dest = input;
   char* src = input;
   while(*src){
       if (isdigit(*src)) { src++; continue; }
       *dest++ = *src++;
   }
   *dest = '\0';
   return input;
}


/**
 *  Retorna só o dia da string caso ele tenha 2 digitos
 *
 */
char* removeDia(char* str){
 char* resp = (char*)malloc(100*sizeof(char));
 int j = 0;
 for(int i = 0; i <=2; i++){
     while(j < i){
       resp[j] = str[j];
       j++;
   }
 }
 return resp;
}

/**
 *  Retorna só o dia da string caso ele tenha 1 digito
 *
 */
char* removeDia1(char* str){
 char* resp = (char*)malloc(100*sizeof(char));
 int j = 0;
 for(int i = 0; i <=1; i++){
     while(j < i){
       resp[j] = str[j];
       j++;
   }
 }
 return resp;
}

/**
 *  Lê todas as informaçoes necessarias
 *
 */
 Time read(char diretorio[]) {
              FILE* f = fopen(diretorio, "r");
              Time time;
              char* pagina = (char * ) malloc(100 * sizeof(char));
              char* infoaux;
              char* nomeaux;
              char* nickaux;
              char* dataaux;
              char* estadioaux;
              char* capacidadeaux;
              char* tecnicoaux;
              char* anoaux;
              char* diaaux;
              char* mesaux;


              char* resp = (char*)malloc(10000*sizeof(char));
              const char s[4] = "=";
              char* tok;
              char str[100+1];
              int i=0;
              char teste;
              char*  line = NULL;
              size_t len = 0;
              ssize_t read;
              const char d[4] = "=";
 	     Time tmp;
             /*============================TAMANHO DA PAGINA==========================*/
              strcpy(pagina, diretorio);
              fseek(f, 0, SEEK_END);
              tmp.paginaTam = ftell(f);
              /*============================ NOME DA PAGINA==========================*/
              fseek(f, 0, SEEK_SET);
              strcpy(tmp.nomeArquivo,pagina);

              if (f != NULL) {
                 do {
                    resp = fgets(str, 100, f);
                    if(resp != NULL){
                      while (((read = getline(&line, &len, f)) != -1) && (strstr(line, ">Full name</th>") == NULL));
                            infoaux=removeTags(line);

                            /*============================ NOME==========================*/
                            nomeaux=replaceWord(infoaux,"Nickname(s)","=");
                            nomeaux=replaceWord(nomeaux,"Full name","=");
                            tok = strtok(nomeaux, s);
                            int j=0;
                            while (tok != 0) {
                              if(j==1){
                                nomeaux=tok;
                                tmp.nome=nomeaux;
                              }
                              tok = strtok(0, s);
                              j++;
                            }
                            /*=====================APELIDO==========================*/
                            nickaux=replaceWord(infoaux,"Nickname(s)","=");
                            nickaux=replaceWord(nickaux,"Founded","=");
                            nickaux=replaceWord(nickaux,"Short name","=");
                            tok = strtok(nickaux, s);
                            int k=0;
                            while (tok != 0) {
                              if(k==1){
                                nickaux=tok;
                                tmp.apelido=nickaux;
                              }
                              tok = strtok(0, s);
                              k++;
                            }
                            int dia=0;
                            int ano=0;
                            int mes=0;
                           /*=====================DATA==========================*/
                           dataaux=replaceWord(infoaux,"Founded","=");
                           dataaux=replaceWord(dataaux,"Ground","=");
                           tok = strtok(dataaux, d);
                           int l=0;
                           while (tok != 0) {
                             if(l==1){
                               dataaux=tok;
                               dataaux=replaceWord(dataaux,"years ago","=");
                               char*  tok2 = strtok(dataaux,"=");
                               for(int a=0;a<strlen(dataaux);a++){
                                   if((dataaux[0]>='0' && dataaux[0]<='9') && (dataaux[2]>='A' && dataaux[2]<='Z')){
                                     anoaux=replaceWord(dataaux," ","/");
                                     int p=0;
                                     while (tok != 0) {
                                       if(p==1){
                                         anoaux=tok;
                                         anoaux=replaceWord(anoaux,"(","");
                                         anoaux=removeAno(anoaux);
                                         ano=atoi(anoaux);
                                         tmp.fundacaoAno=ano;
                                        ///////////////////////////////////////// printf("%d\n",ano);  NAO FOI O ANO

                                       }
                                       tok = strtok(0, "/");
                                       p++;
                                     }

                                     diaaux=removeDia(dataaux);
                                     mesaux=RemoveDigits(dataaux);
                                     mesaux=replaceWord(mesaux," ","");
                                     tok = strtok(anoaux,"/");
                                     mes=getMes(mesaux);
                                     dia= atoi(diaaux);
                                     tmp.fundacaoDia=dia;
                                     tmp.fundacaoMes=mes;
                                     ano=atoi(anoaux);
                                     tmp.fundacaoAno=ano;

                                   }else{
                                       diaaux=0;
                                       mesaux=0;
                                       anoaux=removeAno(dataaux);
                                       ano= atoi(anoaux);
                                       tmp.fundacaoDia=dia;
                                       tmp.fundacaoMes=mes;
                                       tmp.fundacaoAno=ano;
                                       if((dataaux[0]>='0' && dataaux[0]<='9') && (dataaux[1]>='A' && dataaux[1]<='Z')){
                                          diaaux=removeDia1(dataaux);
                                          anoaux=replaceWord(dataaux," ","/");
                                          int q=0;
                                          while (tok != 0) {
                                            if(q==1){
                                              anoaux=tok;
                                              anoaux=replaceWord(anoaux,"(","");
                                              anoaux=removeAno(anoaux);
                                              ano=atoi(anoaux);
                                              tmp.fundacaoAno=ano;
                                            }
                                            tok = strtok(0, "/");
                                            q++;
                                          }
                                          mesaux=RemoveDigits(dataaux);
                                          mesaux=replaceWord(mesaux," ","");
                                          mes=getMes(mesaux);
                                          ano= atoi(anoaux);
                                          mes= atoi(mesaux);
                                          dia= atoi(diaaux);
                                          tmp.fundacaoDia=dia; ///////////////////////////////SO FOI O DIA
                                          tmp.fundacaoMes=mes;
                                          tmp.fundacaoAno=ano;

                                       }
                                   }
                                 }
                    //      printf("%s\n",dataaux);    //   ===============================================================DATA
                             }
                             tok = strtok(0, d);
                             l++;
                           }

                           /*=====================ESTADIO==========================*/
                               estadioaux=replaceWord(infoaux,"Capacity","=");
                               estadioaux=replaceWord(estadioaux,"Ground","=");
                               tok = strtok(estadioaux, d);
                               int m=0;
                               while (tok != 0) {
                                 if(m==1){
                                   estadioaux=tok;
                                   tmp.estadio=estadioaux;
                                 }
                                 tok = strtok(0, d);
                                 m++;
                               }

                               /*=====================CAPACIDADE==========================*/
                               capacidadeaux=replaceWord(infoaux,"Capacity","=");
                               capacidadeaux=replaceWord(capacidadeaux,"Owner","=");
                               capacidadeaux=replaceWord(capacidadeaux,"Chairman","=");
                               capacidadeaux=replaceWord(capacidadeaux,"Head coach","=");
                               capacidadeaux=replaceWord(capacidadeaux,"President","=");
                               capacidadeaux=replaceWord(capacidadeaux,"Manager","=");
                               capacidadeaux=replaceWord(capacidadeaux,"General","=");
                               tok = strtok(capacidadeaux, d);
                               int n=0;
                               while (tok != 0) {
                                 if(n==1){
                                   capacidadeaux=tok;
                                   capacidadeaux=replaceWord(capacidadeaux,",","");
                                   capacidadeaux=replaceWord(capacidadeaux," (2700 seated)","");
                                   capacidadeaux=replaceWord(capacidadeaux," ","");
                                   int c= atoi(capacidadeaux);
                                   tmp.capacidade=c;
                                 }
                                 tok = strtok(0, d);
                                 n++;
                               }

                               /*=====================TECNICO==========================*/
                               if(strstr(infoaux,"Manager")){
                                 tecnicoaux=replaceWord(infoaux,"Manager","=");
                               }
                               if(strstr(infoaux,"Head coach")){
                                 tecnicoaux=replaceWord(infoaux,"Head coach","=");
                               }
                               tecnicoaux=replaceWord(tecnicoaux,"League","=");
                               tok = strtok(tecnicoaux, d);
                               int o=0;
                               while (tok != 0) {
                                 if(o==1){
                                   tecnicoaux=tok;
                                   tmp.tecnico=tecnicoaux;
                                 }
                                 tok = strtok(0, d);
                                 o++;
                               }

                              i++;
                           }
                        } while (resp != NULL);
                        fclose(f);
                     }

        		return tmp;
        }//FIM READ

/**
 * Mostra os elementos separados por espacos, comecando do topo.
 */
void mostrar() {
   Celula* i;
   for(i = topo; i != NULL; i = i->prox) {
      imprimir(i->elemento);
   }
}


int main() {
	     start();

       int cont=0;
       int i = 0;
       char diretorio[200];
       int posicao;
       scanf("%s", diretorio);
       Time time;

		while(strcmp(diretorio,"FIM")){
                  inserir(read(diretorio));
                  //mostrar();
                  scanf("%s", diretorio);
              }

              int cont2;
              scanf("%d", &cont2);
	            scanf("%s ", diretorio);
              for(int i = 0; i < cont2; i++){
                  if(!(strcmp(diretorio,"I"))){
                      scanf("%s", diretorio);
                      inserir(read(diretorio));
                    //  mostrar();
                  }
                  else if(!(strcmp(diretorio,"R"))){
                      printf("(R) %s\n", remover().nome);
                      scanf("%s ", diretorio);
                  }
                  scanf("%s ", diretorio);
      }
      mostrar();
       return 0;
}
