
#include <iostream>

#include <string>

#include <vector>

#include <bits/stdc++.h>

using namespace std;

/*Variável com a função de auxiliar nos caracteres lidos*/
char aux = '\0';

/*Variável com a função de contar a quantidade de linhas */
int linhasCompiladas = 1;

/*Variável com a função de informar o ultimo caractere lido */
char ultimoCaractereLido;

/*Variável com a função de informar o fim de arquivo */
bool finalDoArq = false;

/* Variável com a função de armazenar a Memoria de temporarios*/
int temporarioDados = 0x0; //

/* Variável com a função de armazenar a Memoria de dados que começa na pos 10.000*/
int memoriaDados = 0x10000;


/* Função global que retorna o endereço em hexadecimal*/
string getHexa(int end) {

    stringstream stream;
    stream << 0  << uppercase << hex << end;

    return stream.str() + 'h';
}

/* Função global que retorna a proxima memoria disp e incrementa o tam da alocação*/
int criarTemp(int tam) {

    int temp = temporarioDados;
    temporarioDados += tam;

    return temp;
}


/*Tipos pré-definidos da Linguagem*/
char CHAR = 3;
char STRING = 4;
char INT = 5;
char FLOAT = 6;
char BOOL = 7;

char classe_Constante = 0;
char classe_Vazia = 1;
char classe_Var = 2;

/*Classe responsável por criar os simbolos que serão utlizados na linguagem*/
class Simbolos {

  public:
  string lexema;
  char token;
  unsigned char tipo;
  char classe;
  int tamanho;
  int endereco;

  Simbolos() {

  }

  Simbolos(string lexema, char token) {
    this -> token = token;
    this -> lexema = lexema;
  }

  Simbolos(string lexema, char token, char classe, int endereco) {
    this -> token = token;
    this -> lexema = lexema;
    this -> classe = classe;
    this -> endereco = endereco;
  }

  void valorTipo(char tipo){
    this ->tipo = tipo;
  }

    void valorClasse(char classe){
    this ->classe = classe;
  }

};


/*Classe responsável por criar a tabela de simbolos que serão utlizados na linguagem*/
class Tabela_Simbolos {

  public:

  char Constante = 0;
  char Int = 1;
  char Char = 2;
  char While = 3;
  char If = 4;
  char Float = 5;
  char Else = 6;
  char And = 7;
  char Or = 8;
  char Not = 9;
  char Atribuicao = 10;
  char Igual = 11;
  char Parenteses_Esquerdo = 12;
  char Parenteses_Direito = 13;
  char Simbolo_Menor = 14;
  char Simbolo_Maior = 15;
  char Simbolo_Diferente = 16;
  char Simbolo_Maior_Igual = 17;
  char Simbolo_Menor_Igual = 18;
  char Virgula = 19;
  char Adicao = 20;
  char Diferenca = 21;
  char Multiplicacao = 22;
  char Divisao = 23;
  char Ponto_E_Virgula = 24;
  char Chave_Esquerdo = 25;
  char Chave_Direita = 26;
  char Readln = 27;
  char Div = 28;
  char Write = 29;
  char Writeln = 30;
  char Mod = 31;
  char Colchete_Esquerdo = 32;
  char Colchete_Direito = 33;
  char Id = 34;
  char Token_Default = 35;
  char End = 36;
  char Const = 37;
  char String = 38;
  char n_encontrado = 39;

/*Foi utilizado a classe map para armazenar os simbolos e seus respectivos valores */
  map<string, Simbolos> simbolos;

/*Construtor com o objetivo de armazenar os simbolos e seus respectivos valores na classe map */
  Tabela_Simbolos() {
    simbolos["int"]= Simbolos("int", Int);
    simbolos["char"]= Simbolos("char", Char);
    simbolos["while"]= Simbolos("while", While);
    simbolos["if"]= Simbolos("if", If);
    simbolos["float"]= Simbolos("float", Float);
    simbolos["else"]= Simbolos("else", Else);
    simbolos["&&"]= Simbolos("&&", And);
    simbolos["||"]= Simbolos("||", Or);
    simbolos["!"]= Simbolos("!", Not);
    simbolos["<-"]= Simbolos("<-", Atribuicao);
    simbolos["="]= Simbolos("=", Igual);
    simbolos["("]= Simbolos("(", Parenteses_Esquerdo);
    simbolos[")"]= Simbolos(")", Parenteses_Direito);
    simbolos["<"]= Simbolos("<", Simbolo_Menor);
    simbolos[">"]= Simbolos(">", Simbolo_Maior);
    simbolos["!="]= Simbolos("!=", Simbolo_Diferente);
    simbolos[">="]= Simbolos(">=", Simbolo_Maior_Igual);
    simbolos["<="]= Simbolos("<=", Simbolo_Menor_Igual);
    simbolos[","]= Simbolos(",", Virgula);
    simbolos["+"]= Simbolos("+", Adicao);
    simbolos["-"]= Simbolos("-", Diferenca);
    simbolos["*"]= Simbolos("*", Multiplicacao);
    simbolos["/"]= Simbolos("/", Divisao);
    simbolos[";"]= Simbolos(";", Ponto_E_Virgula);
    simbolos["{"]= Simbolos("{", Chave_Esquerdo);
    simbolos["}"]= Simbolos("}", Chave_Direita);
    simbolos["readln"]= Simbolos("readln", Readln);
    simbolos["div"]= Simbolos("div", Div);
    simbolos["write"]= Simbolos("write", Write);
    simbolos["writeln"]= Simbolos("writeln", Writeln);
    simbolos["mod"]= Simbolos("mod", Mod);
    simbolos["["]= Simbolos("[", Colchete_Esquerdo);
    simbolos["]"]= Simbolos("]", Colchete_Direito);
    simbolos["const"]= Simbolos("const", Const);
    simbolos["string"]= Simbolos("string", String);
  }

/* Metodo com função de pesquisar se o token existe na tabela de simbolos
* Caso exista, é retornado o token na tabela
* Caso não exista, é retornado o token do Id
*/
  char pesquisar(string lex) {
    if(simbolos.find(lex) == simbolos.end()){
      return n_encontrado;
    }
    return simbolos[lex].token;
  }

/*Metodo com função de inserir os tokens de Id na tabela de simbolos */
  void inserir(string lex) {
    simbolos[lex] = Simbolos(lex,Id, classe_Vazia,memoriaDados);
  }


};

/*Classe responsável por receber os erros contidos no Léxico e Sintático
  E retornar qual o erro encontrado durante a compilação*/
class Erros {
  public:
    Erros() {};

/*Metodo responsável por informar o erro de fim de arquivo inesperado */
  void erroFim(char c) {
    if (ultimoCaractereLido == '\n') linhasCompiladas--;
    cout << linhasCompiladas << endl;
    cout << "fim de arquivo nao esperado." << endl;
    exit(-1);
  }

/*Metodo responsável por informar o erro de lexema e informar qual é. */
  void erroLexema(string lexema) {
    if (ultimoCaractereLido == '\n') linhasCompiladas--;
    cout << linhasCompiladas << endl;
    cout << "lexema nao identificado ["<< lexema << "]." << endl;
    exit(-1);
  }

/*Metodo responsável por informar o erro de token e informar qual é. */
  void erroToken(string lexema) {
    if (ultimoCaractereLido == '\n') linhasCompiladas--;
    cout << linhasCompiladas << endl;
    cout << "token nao esperado ["<< lexema << "]." << endl;
    exit(-1);
  }
/*Metodo responsável por informar o erro de caracter inválido */
  void erroChar(char c) {
    cout << linhasCompiladas << endl;
    cout << "caractere invalido."  <<endl;
    exit(-1);
  }

  void erroId_Nao_Declarado(string lexema){
    cout << linhasCompiladas << endl;
        cout << "identificador nao declarado ["<< lexema << "]." << endl;
    exit(-1);
  }

  void erroIdDeclarado(string lexema){
    cout << linhasCompiladas << endl;
        cout << "identificador ja declarado ["<< lexema << "]." << endl;
    exit(-1);
  }

  void erroClasseIncomp(string lexema){
    cout << linhasCompiladas << endl;
        cout << "classe de identificador incompativel ["<< lexema << "]." << endl;
    exit(-1);
  }

  void erroTiposIncomp(){
    cout << linhasCompiladas << endl;
    cout << "tipos incompativeis." <<endl;
    exit(-1);
  }

};

/*Atribuindo como objeto global da classe Simbolos, para se usar no léxico e Sintático*/
Simbolos lex;

/*Classe responsável por criar o análisador léxico com o objetivo de identificar os tokens no programa fonte*/
class analisadorLexico {

  public:
  Tabela_Simbolos * tabela;
  Erros * er;

  char c;
  string lexema = "";

  analisadorLexico(Tabela_Simbolos * tabela) {
    this -> tabela = tabela;
  }

  analisadorLexico() {

  }

/*Método responsável por rodar o automato do código fonte*/
  void analisador() {

    /*Variável com a função de inciar o estado no automato */
    int estadoInicial = 0; // Estado inicial

    /*Variável com a função de finalizar o automato */
    int estadoFinal = 18; // Estado final

    lex.lexema = "";
    lex.tamanho = 0;

    char c;
    string teste = "";

    /*Laço com a função de repetir o automato até o arquivo chegar ao seu fim e chegar no estado final */
    while (!finalDoArq && estadoInicial != estadoFinal ) {

      /*Teste com a função de verificar se ocorre o unget */
      if(aux){
        c  = aux;
        aux = '\0';
      }else{
        c = ler();
      }
      ultimoCaractereLido = c;

      switch (estadoInicial) {

      /*Caso quando estamos no estado inicial */
      case 0:
        if (isspace(c)) { /*Teste quando há espaço*/
          estadoInicial = 0;
        } else if (isalpha(c)) { /*Teste quando há caracteres de A-Z*/
          lex.lexema += c;
          estadoInicial = 1;
        } else if (c == '_') { /*Teste quando indica início de Id*/
          lex.lexema += c;
          estadoInicial = 2;
        } else if (c > '0' && c <= '9') { /*Teste quando há caracteres numéricos*/
          lex.lexema += c;
          estadoInicial = 3;
        } else if (c == '0') { /*Teste quando há indícios inteiro ou hexadecimal*/
          lex.lexema += c;
          estadoInicial = 4;
        } else if (c == '>') { /*Teste quando há indícios de simbolos maior ou maior e igual*/
          lex.lexema += c;
          estadoInicial = 9;
        } else if (c == '<') { /*Teste quando há indícios de simbolos menor <, menor e igual <= ou atribuição <-*/
          lex.lexema += c;
          estadoInicial = 10;
        } else if (c == '!') { /*Teste quando há indícios de simbolo de diferente !=*/
            lex.lexema += c;
            estadoInicial = 11;
        } else if (c == '/') { /*Teste quando há indícios de simbolo divisão ou comentário*/
          lex.lexema += c;
          estadoInicial = 12;
        } else if (c == '"') { /*Teste quando há indícios de uma possível string*/
          lex.lexema += c;
          estadoInicial = 15;
        } else if (c == '\'') { /*Teste quando há indícios de um possível char*/
          lex.lexema += c;
          estadoInicial = 16;
        } else if (c == '(') { /*Teste quando há um abre parenteses*/
          lex.token = (int) tabela -> Parenteses_Esquerdo;
          lex.lexema += c;
          estadoInicial = 18;
        } else if (c == ')') { /*Teste quando há um fecha parenteses*/
          lex.token = (int) tabela -> Parenteses_Direito;
          lex.lexema += c;
          estadoInicial = 18;
        } else if (c == '[') { /*Teste quando há um abre colchete*/
          lex.token = (int) tabela -> Colchete_Esquerdo;
          lex.lexema += c;
          estadoInicial = 18;
        } else if (c == ']') { /*Teste quando há um fecha colchete*/
          lex.token = (int) tabela -> Colchete_Direito;
          lex.lexema += c;
          estadoInicial = 18;
        } else if (c == '{') { /*Teste quando há um abre chave*/
          lex.token = (int) tabela -> Chave_Esquerdo;
          lex.lexema += c;
          estadoInicial = 18;
        } else if (c == '}') { /*Teste quando há um fecha chave*/
          lex.token = (int) tabela -> Chave_Direita;
          lex.lexema += c;
          estadoInicial = 18;
        } else if (c == ',') { /*Teste quando há uma virgula*/
          lex.token = (int) tabela -> Virgula;
          lex.lexema += c;
          estadoInicial = 18;
        } else if (c == ';') { /*Teste quando há um ponto e virgula*/
          lex.token = (int) tabela -> Ponto_E_Virgula;
          lex.lexema += c;
          estadoInicial = 18;
        } else if (c == '*') { /*Teste quando há um simbolo multiplicação */
          lex.token = (int) tabela -> Multiplicacao;
          lex.lexema += c;
          estadoInicial = 18;
        } else if (c == '+') { /*Teste quando há um simbolo de adição */
          lex.token = (int) tabela -> Adicao;
          lex.lexema += c;
          estadoInicial = 18;
        } else if (c == '-') { /*Teste quando há um simbolo diferença */
          lex.token = (int) tabela -> Diferenca;
          lex.lexema += c;
          estadoInicial = 10;
        } else if (c == '=') { /*Teste quando há o simbolo igual */
          lex.token = (int) tabela -> Igual;
          lex.lexema += c;
          estadoInicial = 18;
        } else if (c == '&') { /*Teste quando há o simbolo And */
          lex.lexema += c;
          estadoInicial = 20;
        } else if (c == '|') { /*Teste quando há o simbolo Or */
          lex.lexema += c;
          estadoInicial = 21;
        } else if  (c == -1) { finalDoArq = true; /*Teste quando temos um final de arquivo inesperado */
          lex.token = (int) tabela -> End;
        } else { /*Teste quando temos um lexema não identificado */
          lex.lexema += c;
            er->erroLexema(lex.lexema);
        }
        break;

      /*Caso quando temos indícios de um Id ou palavra reservada */
      case 1:
        /*Teste quando temos indicios de [a-z][A-Z][0-9] ou Id */
        if (isalpha(c) || isdigit(c) || c == '_') {
          if (lex.lexema.size() < 32) {
            lex.lexema += c;
          }
          estadoInicial = 1;

        } else {
          /*Caso não seja uma palavra ou identificador, o automato será redirecionado para o estado final*/
          estadoInicial = 18;

          /*Teste quando temos um final de arquivo inesperado */
          if  (c == -1) { finalDoArq = true;
            er -> erroFim(c);
          }
          /*Teste para realizar o unget, recuperando o ultimo caractere lido  */
          if(!isspace(c)) {
                  aux = c;
          }

          lex.token = tabela -> pesquisar(lex.lexema);

          /*Teste quando verificamos se temos um Id e inserimos na tabela de símbolos */
          if ((int) lex.token == 39) {
            lex.token = tabela -> Id;
            tabela -> inserir(lex.lexema);
          }

          break;

        /*Caso quando temos indícios de possíveis Id */
          case 2:

            /*Teste quando temos indicios de um Id começando com _ */
            if (c == '_') {
              if (lex.lexema.size() < 32) {
                lex.lexema += c;
              }
              estadoInicial = 2;

            } else
            /*Teste quando temos [a-z][A-Z][0-9] no Id */
              if (isalpha(c) || isdigit(c)) {
              if (lex.lexema.size() < 32) {
              lex.lexema += c;
            }

            estadoInicial = 1;

          } else {
            if  (c == -1) { finalDoArq = true; /*Teste quando temos um final de arquivo inesperado */
              er -> erroFim(c);
            } else { /*Teste quando temos um lexema não identificado */
              er -> erroLexema(lex.lexema);
            }
          }
          break;

          /*Caso quando temos indícios de números inteiros */
          case 3:
            if (isdigit(c)) { /*Teste quando temos indicios de números inteiros */
              lex.lexema += c;
              estadoInicial = 3;
            } else if(c == '.'){ /*Teste quando temos indicios de números do tipo float */
              lex.lexema += c;
               estadoInicial = 19;
            } else {

              /*Caso não seja um digito, o automato será redirecionado para o estado final*/
              estadoInicial = 18;

              /*Teste para realizar o unget, recuperando o ultimo caractere lido  */
                if(!isspace(c)) {
                  aux = c;
                }

              /*Teste quando verificamos se temos um numero inteiro e atribuimos o tipo com tamanho 2 */
              lex.token = tabela -> Constante;
              lex.tipo = INT;
              lex.tamanho = 2;
            }
          break;

          /*Caso quando temos indícios de um Hexadecimal */
          case 4:
            if (c == 'x' || c == 'X') { /*Teste se temos um X depois de um 0 */
              lex.lexema += c;
              estadoInicial = 6;
          }else if (isdigit(c)) { /*Teste se temos um inteiro depois de um 0 */
            lex.lexema += c;
            estadoInicial = 5;
            } else {
              /*Caso não seja um digito ou X, o automato será redirecionado para o estado final*/
              estadoInicial=18;

              /*Teste para realizar o unget, recuperando o ultimo caractere lido  */
              if(!isspace(c)) {
                aux = c; // Unget
              }
              /*Após identificar um hexadecimal atribuimos o tipo Constante */
              lex.token = tabela -> Constante;
          }
          break;

          /*Caso quando temos indícios de um inteiro */
          case 5:

            /*Teste quando temos números no hexadecimal */
            if (isdigit(c)) { // Possivel inteiro
              lex.lexema += c;
              estadoInicial = 5;

            } else {
            /*O automato será redirecionado para o estado final*/
            estadoInicial = 18;

            /*Teste para realizar o unget, recuperando o ultimo caractere lido  */
              if(!isspace(c)) {
                aux = c; // Unget
              }

            /*Após identificar um inteiro atribuimos o tipo Constante */
            lex.token = tabela -> Constante;
            lex.tipo = INT;
            lex.tamanho = 2;
          }
          break;

          /*Caso quando temos indícios de um Hexadecimal */
          case 6:
          if (isdigit(c) || (isxdigit(c) && isupper(c))) {  /*Teste quando temos números inteiros ou hexadecimais depois do 0x */
            lex.lexema += c;
            estadoInicial = 7;
          } else {
            if  (c == -1) {
              finalDoArq = true; /*Teste quando temos um final de arquivo inesperado */
              er -> erroFim(c);
            } else { /*Teste quando temos um lexema não identificado */
              er -> erroLexema(lex.lexema);
            }
        }
        break;

          /*Caso quando temos indícios de um Hexadecimal */
          case 7:
            if (isdigit(c) || (isxdigit(c) && isupper(c))) { /*Teste quando temos números inteiros ou hexadecimais depois do 0x */
              lex.lexema += c;
              lex.tamanho++;

              /*Caso não seja um digito ou um hexadecimal, o automato será redirecionado para o estado final*/
              estadoInicial = 18;

            } else {
              if  (c == -1) {
                finalDoArq = true; /*Teste quando temos um final de arquivo inesperado */
                er -> erroFim(c);
              } else { /*Teste quando temos um lexema não identificado */
                er -> erroLexema(lex.lexema);
              }
            }
          break;

          /*Caso quando temos indícios de um número inteiro */
          case 8:

          /*Teste quando temos indícios de um número inteiro */
          if (isdigit(c)) {
            lex.lexema += c;
            estadoInicial = 8;
          } else {

            /*Caso não seja um digito, o automato será redirecionado para o estado final*/
            estadoInicial = 18;

            /*Teste para realizar o unget, recuperando o ultimo caractere lido  */
              if(!isspace(c)) {
                aux = c; // Unget
              }
            lex.token = tabela -> Constante;
            lex.tipo = INT;
            lex.tamanho = 2;
          }
        break;

          /*Caso quando temos idícios de maior > ou maior e igual >= */
          case 9:

          /*Teste quando temos apenas o símbolo maior */
            if (c == '>') {
              lex.lexema += c;
              lex.token = tabela -> pesquisar(lex.lexema);

              /*O automato será redirecionado para o estado final*/
              estadoInicial = 18;

            } else {
              /*Teste quando temos o símbolo maior e igual */
            if (c == '=') {
              lex.lexema += c;
              lex.token = tabela -> pesquisar(lex.lexema);

              /*O automato será redirecionado para o estado final*/
              estadoInicial = 18;

            } else {
              /*Caso não seja um > ou =, o automato será redirecionado para o estado final*/
                  estadoInicial = 18;

            /*Teste para realizar o unget, recuperando o ultimo caractere lido  */
                if(!isspace(c)) {
                  aux = c; // Unget
                }
                lex.token = tabela -> pesquisar(lex.lexema);
              }
        }
          break;

          /*Caso quando temos indícios de um símbolo igual ou diferença */
          case 10:
            /*Teste quando temos o símbolo igual */
            if (c == '=') {
              lex.lexema += c;
              lex.token = tabela -> pesquisar(lex.lexema);

              /*O automato será redirecionado para o estado final*/
              estadoInicial = 18;

            }

            /*Teste quando temos o símbolo de diferença */
            else if (c == '-') {
            lex.lexema += c;
            lex.token = tabela -> pesquisar(lex.lexema);

            /*O automato será redirecionado para o estado final*/
            estadoInicial = 18;

          } else {

            /*Caso não seja um = ou -, o automato será redirecionado para o estado final*/
            estadoInicial = 18;

            /*Teste para realizar o unget, recuperando o ultimo caractere lido  */
            if(!isspace(c)) {
              aux = c; // Unget
            }
            lex.token = tabela -> pesquisar(lex.lexema);
          }

          break;

          /*Caso quando temos indícios do símbolo diferente != */
          case 11:

          /*Teste quando temos o símbolo exclamação */
            if (c == '!') {
              lex.lexema += c;
              lex.token = tabela -> pesquisar(lex.lexema);

              /*O automato será redirecionado para o estado final*/
              estadoInicial = 18;
            }

            /*Teste quando temos o símbolo igual */
          else {if (c == '=') { // Reconhece !=
            lex.lexema += c;
            lex.token = tabela -> pesquisar(lex.lexema);

            /*O automato será redirecionado para o estado final*/
            estadoInicial = 18;

          } else {
            /*Caso não seja um ! ou =, o automato será redirecionado para o estado final*/
            estadoInicial = 18;

            /*Teste para realizar o unget, recuperando o ultimo caractere lido  */

            if(!isspace(c)) {
              aux = c; // Unget
            }
            lex.token = tabela -> pesquisar(lex.lexema);
          }
        }
          break;

          /*Caso quando temos indícios de um comentario */
          case 12:
              /*Teste quando temos * após /  */
             if(c == '*') { // Comentario
                estadoInicial = 13;

            }else{
              /*O automato será redirecionado para o estado final*/
              estadoInicial = 18;

              /*Teste para realizar o unget, recuperando o ultimo caractere lido  */

              if(!isspace(c)){
                aux = c;
              }
            lex.token = tabela -> pesquisar(lex.lexema);
            }
            break;

          /*Caso quando temos indícios de um comentario */
          case 13:

          /*Caso quando temos indícios de um comentario */
          if (c == '*') {
            estadoInicial = 14;
          } else if(c == -1){ /*Teste quando temos um final de arquivo inesperado */
              finalDoArq = true;
              er -> erroFim(c);
          }else{
            estadoInicial = 13;

          }
            break;

          /*Caso quando temos indícios de um finalizador comentario */
          case 14:
          if (c == '/') { /*Teste quando temos / após * */
              lex.lexema = "";
              estadoInicial = 0;
            }else if(c == '*'){ /*Teste quando temos * após * */
              estadoInicial = 14;
            }else{
              estadoInicial = 13;
            }
          break;

          /*Caso quando temos indícios de uma String */
          case 15:

          /*Teste quando temos aspas duplas */
            if (c == '"') {
              lex.lexema += c;
              lex.token = tabela->Constante;
              lex.tipo = STRING;

              /*O automato será redirecionado para o estado final*/
              estadoInicial = 18;

              /*Teste quando temos os caracteres da string*/
            }  else if(c != '\n' && c != -1) {
                  lex.lexema += c;
                  lex.tamanho++;
                  estadoInicial = 15;
          } else {
              if(c == -1) {
                  finalDoArq = true; /*Teste quando temos um final de arquivo inesperado */
                  er -> erroFim(c);
              } else { /*Teste quando temos um lexema não identificado */
                  er -> erroLexema(lex.lexema);
              }
          }
          break;

          /*Caso quando temos indícios de um caractere */
          case 16:
          /*Teste quando temos qualquer caracter*/
          if(!iscntrl(c)) {
              lex.lexema += c;
              lex.tamanho = 1;
              estadoInicial = 17;

          } else {
            if  (c == -1) {
              finalDoArq = true; /*Teste quando temos um final de arquivo inesperado */
              er -> erroFim(c);
            } else { /*Teste quando temos um lexema não identificado */
              er -> erroLexema(lex.lexema);
            }
          }
          break;

          /*Caso quando temos indícios de reconhecer um comentario */
          case 17:
          /*Teste quando temos aspas simples */
            if (c == '\'') {
              lex.lexema += c;
              lex.token = tabela -> Constante;
              lex.tipo = CHAR;

              /*O automato será redirecionado para o estado final*/
              estadoInicial = 18;

            } else {
              if  (c == -1) { finalDoArq = true; /*Teste quando temos um final de arquivo inesperado */
                er -> erroFim(c);
              } else { /*Teste quando temos um lexema não identificado */
                er -> erroLexema(lex.lexema);

              }
            }
          break;

        /*Caso quando temos indícios de um numero decimal */
        case 19:
        /*Teste quando temos indicios de numeros decimais */
          if (isdigit(c)) { // [0-9]
            lex.lexema += c;
            estadoInicial = 19;
          } else {
          if(!isspace(c)) {
            aux = c;
          }
          if  (c == -1) {
            finalDoArq = true; /*Teste quando temos um final de arquivo inesperado */
            er -> erroFim(c);
          } else { /*Teste quando temos um lexema não identificado */
          estadoInicial = 18;
          lex.token = tabela->Constante;
          lex.tipo = FLOAT;
          }
          }
        break;

        /*Caso quando temos indícios do simbolo And */
        case 20:
        /*Teste quando temos indicios do simbolo And */
        if (c == '&') {
            lex.lexema += c;
            lex.token = tabela -> pesquisar(lex.lexema);
            estadoInicial = 18;

          } else {/*Teste quando temos um lexema não identificado */
              estadoInicial = 18;
              er -> erroLexema(lex.lexema);
        }
        break;

        /*Teste quando temos indicios do simbolo Or */
        case 21:
        /*Teste quando temos indicios do simbolo Or */
        if (c == '|') {
            lex.lexema += c;
            lex.token = tabela -> pesquisar(lex.lexema);
            estadoInicial = 18;

          } else {/*Teste quando temos um lexema não identificado */
              estadoInicial = 18;
              er -> erroLexema(lex.lexema);
        }
        break;


        }
      }
    }
  }

  /*Metodo com objetivo de ler os próximos caracteres da entrada
   * E contar a quantidade de linhas compiladas
   *Caso o caractere não exista, é retornado um erro no char
   *Caso exista, é retornado o caracter previamente verificado */
  char ler() {
    c = getchar();
    if (c == '\n') linhasCompiladas++;
    if (validarCarectere(c) == c) {
      return c;
    }
    else{
      er->erroChar(c);
    }
  }
  /*Método com o objetivo de verificar se o caracter é valido na linguagem
   * Caso seja, é retornado o caracter
   * Caso não seja, o código termina*/
  char validarCarectere(char c) {
    if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') ||
      (c == '.') || (c == '_') || (isspace(c)) || (c == ',') || (c == ';') ||
      (c == ':') || (c == '(') || (c == ')') || (c == '{') || (c == '}') ||
      (c == '+') || (c == '-') || (c == '"') || (c == '\'') || (c == '|') ||
      (c == '/') || (c == '&') || (c == '%') || (c == '!') || (c == '?') ||
      (c == '>') || (c == '<') || (c == '=') || (c == 0xD) || (c == 0xA) ||
      (c == '[') || (c == ']') || (c == '*') || c == EOF ) {
      return c;
}
  }

};

/*Classe responsável por criar as expressões do sematico */
class Exp{
  public:
  char tipo;
  int endereco;
  int tamanho;

  Exp(){}
};


/*Classe responsável por gerar o código assembly */
class gerador{
public:
  string codigo;
  string codigoConstante;
  string codigoTeste;

  gerador() {
      this-> codigo = "";
      this-> codigoConstante = "";
      this-> codigoTeste = "";
  }


  void geracaoCodigo() {
    codigo += "section .data\n";
    codigo += "M:\n";
    codigo += codigoConstante;
    codigo += "section .text            ; Sessão de código\n";
    codigo += "global _start            ; Ponto inicial do programa\n";
    codigo += "_start:                  ; Início do programa\n";
    codigo += codigoTeste;
    codigo += "     mov eax, 1\n";
    codigo += "     syscall\n";

  }
};

/*Classe responsável por criar o análisador sintático com o objetivo de verificar a sintaxe da Linguagem*/
class analisadorSintatico {
  public:

  Tabela_Simbolos * tab;
  analisadorLexico * lexico;
  Erros * er;
  gerador * cod;
  int numeroRotulo = 1;

  analisadorSintatico() {

  }

  analisadorSintatico(Tabela_Simbolos * tab, analisadorLexico * lexico,gerador *cod) {
    this -> lexico = lexico;
    this -> tab = tab;
    this -> cod = cod;
    this -> numeroRotulo = 1;
  }


/* Metodo que gera o rotulo do assembly */
  string rotuloGerado() {
      int rot = numeroRotulo;
      numeroRotulo++;
      return "Rot" + to_string(rot);
  }

  /* Método responsável por comparar o token do léxico com o token esperado pela gramática
   * Caso sejam iguais, o próximo token do léxico é chamado
   * Caso contrário será emitido uma mensagem de erro*/
  void casaToken(char sintatico_token) {
    if (sintatico_token == lex.token) {
      lexico -> analisador();
    } else {
      if (lex.token == tab -> End) { /*Teste quando temos um final de arquivo inesperado */
        er->erroFim(lex.token);
    }else{ /*Teste quando temos um token não esperado */
      er->erroToken(lex.lexema);
    }
  }
  }

  /* Método para a regra F
   * Gramática: F -> ! F | '(' Exp ')' | Id ['[' Exp ']'] | Constante | (int| float) '('Exp')'*/
  void fatores(Exp &expre) {

    Exp exp1;
    Exp exp2;
    Exp exp3;
    Exp exp4;
    Exp exp5;
    char tipo_Constante;
    //char id_tipo = tab->simbolos[lex.lexema].tipo;
    char id_tipo;
    string Id_lexema = "";
    bool acesso;
    string valorConst;
    int tam_Constante;

    /*Constante*/
     if (lex.token == tab -> Constante) {
      tipo_Constante = lex.tipo;
      valorConst = lex.lexema;
      casaToken(tab -> Constante);
            if(tipo_Constante == STRING){
              // assembly para gerar uma string
              cod -> codigoConstante +="section .data\n";
              cod -> codigoConstante +="     db "+ valorConst +",0\n";
              cod -> codigoConstante +="section .text\n";
              expre.endereco = memoriaDados;
              memoriaDados += tam_Constante;
            }else{
              if(tipo_Constante == FLOAT){
                cod -> codigoConstante +="section .data\n";
                cod -> codigoConstante +="     dd "+valorConst+"\n";
                cod -> codigoConstante +="section .text\n";
                expre.endereco = memoriaDados;
                memoriaDados += 8;
              }else{
                if(tipo_Constante == CHAR){
                  expre.endereco = criarTemp(1);
              }else{
                expre.endereco = criarTemp(4);
              }
              cod -> codigoConstante += "     mov eax, "+ valorConst +"\n";
              cod -> codigoConstante += "     mov [M+"+ getHexa(expre.endereco) +"], eax\n";
            }
          }
      /*Tradução (20)*/
      expre.tipo = tipo_Constante;
      expre.tamanho = tam_Constante;
      /*!F */
    } else if (lex.token == tab -> Not) {
      casaToken(tab -> Not);
      fatores(exp1);

      /*Tradução (18)*/
      expre.tipo = exp2.tipo;
      if(exp2.tipo != BOOL ){
        er-> erroTiposIncomp();
      }

      expre.tamanho = exp1.tamanho;
      expre.tipo = exp1.tipo;
      expre.endereco = criarTemp(4);

      cod -> codigoConstante += "     mov eax, [qword M+"+getHexa(exp1.endereco)+"]\n";
      cod -> codigoConstante += "     neg eax";
      cod -> codigoConstante += "     add eax,1";
      cod -> codigoConstante += "     mov [qword M+"+ getHexa(expre.endereco)+"], eax";

      /*'(' Exp ')'*/
    } else if (lex.token == tab -> Parenteses_Esquerdo) {
      casaToken(tab -> Parenteses_Esquerdo);
      expressoes(exp2);
      casaToken(tab -> Parenteses_Direito);

      /*Tradução (19)*/
      expre.tipo = exp2.tipo;
      expre.endereco = exp2.endereco;
      expre.tamanho = exp2.tamanho;


      /*Id ['[' Exp ']']*/
    } else if (lex.token == tab -> Id) {
      Id_lexema = lex.lexema;
      casaToken(tab -> Id);


    /*Tradução 7*/
    if(tab->simbolos[Id_lexema].classe == classe_Vazia){
      er-> erroId_Nao_Declarado(Id_lexema);
    }

    /*Tradução (21)*/
    expre.tipo = tab->simbolos[Id_lexema].tipo;
    expre.tamanho = tab->simbolos[Id_lexema].tamanho;
    expre.endereco = tab->simbolos[Id_lexema].endereco;

      if (lex.token == tab -> Colchete_Esquerdo) {
        casaToken(tab -> Colchete_Esquerdo);
        expressoes(exp3);

        /*Tradução (9)*/
        if(exp3.tipo != tab-> Int){
        er-> erroTiposIncomp();

      }
      acesso = true;
        casaToken(tab -> Colchete_Direito);

      if(acesso && id_tipo != tab->String ){
        er->erroTiposIncomp();
      }else{
        expre.tipo = CHAR;
      }
      }
    }else {/*(int| float) '('Exp')'*/

      if(lex.token == tab -> Int){
        casaToken(tab -> Int);
        casaToken(tab -> Parenteses_Esquerdo);
        expressoes(exp4);
        if(exp4.tipo != INT && exp4.tipo != FLOAT) { er->erroTiposIncomp(); }
        casaToken(tab -> Parenteses_Direito);
        expre.tipo = INT;
      }else{/**/
        casaToken(tab -> Float);
        casaToken(tab -> Parenteses_Esquerdo);
        expressoes(exp5);
        if(exp5.tipo != INT && exp5.tipo != FLOAT) { er->erroTiposIncomp(); }
        casaToken(tab -> Parenteses_Direito);
        expre.tipo = FLOAT;
      }
    }

  };

   /* Método para a regra T
   * Gramática: F {(*|/|&&|div|mod) F} */
  void termos(Exp &expre) {

    Exp exp1;
    Exp exp2;

    char tipo_Tok;

    fatores(exp1);

    /*Tradução (16)*/
    expre.tipo = exp1.tipo;
    expre.endereco = exp1.endereco;

    while (lex.token == tab -> Mod || lex.token == tab -> Div ||
      lex.token == tab -> And || lex.token == tab -> Divisao ||
      lex.token == tab -> Multiplicacao) {
        tipo_Tok = lex.token;
      if (lex.token == tab -> Mod) {
        casaToken(tab -> Mod);
      } else if (lex.token == tab -> Div) {
        casaToken(tab -> Div);
      } else if (lex.token == tab -> And) {
        casaToken(tab -> And);
      } else if (lex.token == tab -> Divisao) {
        casaToken(tab -> Divisao);
      } else if (lex.token == tab -> Multiplicacao) {
        casaToken(tab -> Multiplicacao);
      }

      fatores(exp2);

      /*Tradução (17)*/
        if(tipo_Tok != tab->And){
        if(exp1.tipo != exp2.tipo){
          if((exp1.tipo == INT && exp2.tipo != FLOAT) || (exp2.tipo == INT && exp1.tipo != FLOAT) ||
          (exp1.tipo != INT && exp2.tipo == FLOAT) || (exp2.tipo != INT && exp1.tipo == FLOAT)){
          er-> erroTiposIncomp();
         }
        }
      }else{
        if(exp1.tipo != exp2.tipo){
          er-> erroTiposIncomp();
        }else if(exp1.tipo != tab-> Float || exp1.tipo != tab-> Int){
          er-> erroTiposIncomp();
        }
        expre.tipo = BOOL;
        if(exp1.tipo != BOOL &&  exp2.tipo != BOOL){
        er-> erroTiposIncomp();
      }
      }

      if(tipo_Tok == tab -> Mod){
        if(exp1.tipo != exp2.tipo){
        er-> erroTiposIncomp();
       }
      }

    }
  }

  /* Método para a regra S
   * Gramática: [-] T {(+|-|||) T} */
  void operacoes(Exp &expre) {

    Exp exp1;
    Exp exp2;

    char tipo_Tok;
    char tipo_Constante;
    char token_Diferenca = 21;

    bool sinal = false;

    /*[-]*/
    if (lex.token == tab -> Diferenca) {
      sinal = true;
      casaToken(tab -> Diferenca);

    }
    /*T {(+|-|||) T}*/
    termos(exp1);

    /*Tradução (3)*/
    if(sinal && !(exp1.tipo == INT ||  exp1.tipo == FLOAT)){
        er->erroTiposIncomp();
    }

    /*Tradução (14)*/
    expre.tipo = exp1.tipo;
    expre.endereco = exp1.endereco;
    expre.tamanho = exp1.tamanho;

    if(sinal){
      cod -> codigoConstante += "mov eax,[M+"+getHexa(exp1.endereco)+"] \n";
      cod -> codigoConstante += "neg eax \n";
      cod -> codigoConstante += "mov [M+"+getHexa(exp1.endereco)+"],eax \n";
    }

    while (lex.token == tab -> Diferenca || lex.token == tab -> Adicao ||
      lex.token == tab -> Or) {
        tipo_Tok = lex.token;
      if (lex.token == tab -> Diferenca) {
        casaToken(tab -> Diferenca);
      } else if (lex.token == tab -> Adicao) {

        casaToken(tab -> Adicao);
      } else if (lex.token == tab -> Or) {
        casaToken(tab -> Or);
      }
      termos(exp2);

      /*Tradução (15)*/
      if(tipo_Tok != tab->Or){
        if(exp1.tipo != exp2.tipo){
          er-> erroTiposIncomp();
        }
      }else{
        expre.tipo = BOOL;
        if(exp1.tipo != BOOL &&  exp2.tipo != BOOL){
        er-> erroTiposIncomp();
      }
      }
    }
  }

  /* Método para a regra Exp
   * Gramática: S [(=|!=|<|>|<=|>=) S] */
  void expressoes(Exp &expre) {

    Exp exp1;
    Exp exp2;

    char tipo_Tok;

    operacoes(exp1);

    /*Tradução (12)*/
    expre.tipo = exp1.tipo;
    expre.endereco = exp1.endereco;


    string codigoMaiorMenor;

    if (lex.token == tab -> Simbolo_Maior || lex.token == tab -> Simbolo_Menor ||
      lex.token == tab -> Simbolo_Menor_Igual || lex.token == tab -> Simbolo_Maior_Igual ||
      lex.token == tab -> Simbolo_Diferente || lex.token == tab -> Igual ) {
        tipo_Tok = lex.token;
      if (lex.token == tab -> Simbolo_Maior) {
        codigoMaiorMenor = "     ja ";
        casaToken(tab -> Simbolo_Maior);
      } else if (lex.token == tab -> Simbolo_Menor) {
        codigoMaiorMenor = "     jb ";
        casaToken(tab -> Simbolo_Menor);
      } else if (lex.token == tab -> Simbolo_Menor_Igual) {
        codigoMaiorMenor = "     jbe ";
        casaToken(tab -> Simbolo_Menor_Igual);
      } else if (lex.token == tab -> Simbolo_Maior_Igual) {
        codigoMaiorMenor = "     jae ";
        casaToken(tab -> Simbolo_Maior_Igual);
      } else if (lex.token == tab -> Simbolo_Diferente) {
        codigoMaiorMenor = "     jne ";
        casaToken(tab -> Simbolo_Diferente);
      } else if (lex.token == tab -> Igual) {
        codigoMaiorMenor = "     je ";
        casaToken(tab -> Igual);
      }

      operacoes(exp2);

      /*Tradução (13)*/
      expre.tipo = BOOL;
      if(exp1.tipo != exp2.tipo){
        if((exp1.tipo == INT && exp2.tipo != FLOAT) || (exp2.tipo == INT && exp1.tipo != FLOAT) ||
        (exp1.tipo != INT && exp2.tipo == FLOAT) || (exp2.tipo != INT && exp1.tipo == FLOAT)){
          er-> erroTiposIncomp();
        }
      }
      else if(exp1.tipo == STRING && tipo_Tok!=tab->Igual){
         er-> erroTiposIncomp();
      }
    }

          cod -> codigoTeste += "     mov eax,"+ getHexa(exp1.endereco) +"\n";
          cod -> codigoTeste += "     mov ebx,"+ getHexa(exp2.endereco) +"\n";
          cod -> codigoTeste += "     mov eax,[qword M+"+ getHexa(exp1.endereco) +"]\n";
          cod -> codigoTeste += "     mov ebx,[qword M+"+ getHexa(exp2.endereco) +"]\n";
          cod -> codigoTeste += "     cmp eax, ebx\n";
          string rotVerdadeiro;
          rotVerdadeiro = rotuloGerado();
          if(codigoMaiorMenor !=""){ cod -> codigoTeste += codigoMaiorMenor + rotVerdadeiro + "\n";}
          cod -> codigoTeste += "     mov eax, 0\n";
          cod -> codigoTeste += "     mov ebx, 0\n";
          string rotFim;
          rotFim = rotuloGerado();
          cod -> codigoTeste += "     jmp " + rotFim + "\n";
          cod -> codigoTeste += rotVerdadeiro + ":\n";
          cod -> codigoTeste += "     mov eax, 1\n";
          cod -> codigoTeste += rotFim + ":\n";
          cod -> codigoTeste += "     mov [qword M+"+ getHexa(exp1.endereco) +"], eax\n";  //ERRO NO ENDERECO

  }

  /* Método para a regra W
   * Gramática: write '(' Exp {, Exp} ')' ;
   * Gramática: writeln '(' Exp {, Exp} ')' ; */
  void cmdWrite(){

    Exp exp1;
    Exp exp2;

    /*Write*/
    if(lex.token == tab -> Write){
      casaToken(tab -> Write);
    }
    else{/*Writeln*/
      casaToken(tab -> Writeln);
    }

    /*'(' Exp {, Exp} ')' ;*/
    casaToken(tab-> Parenteses_Esquerdo);
    expressoes(exp1);
    while(lex.token == tab -> Virgula){
      casaToken(tab-> Virgula);
      expressoes(exp2);
    }
    casaToken(tab -> Parenteses_Direito);
    casaToken(tab -> Ponto_E_Virgula);

  }

    /* Método para a regra R
   * Gramática: readln '(' Id ')' ; */
  void cmdRead() {
    string Id_lexema = "";
    casaToken(tab -> Readln);
    casaToken(tab -> Parenteses_Esquerdo);
    Id_lexema = lex.lexema;
    casaToken(tab -> Id);
    /*Tradução 7*/
    if(tab->simbolos[Id_lexema].classe == classe_Vazia){
      er-> erroId_Nao_Declarado(Id_lexema);
    }
    /*Tradução 8*/
    if(tab->simbolos[Id_lexema].classe == classe_Constante){
      er-> erroClasseIncomp(Id_lexema);
    }
    casaToken(tab -> Parenteses_Direito);
    casaToken(tab -> Ponto_E_Virgula);
  }

  /* Método para a regra Tes
   * Gramática: if Exp (Cmd | '{' {Cmd} '}') [else (Cmd | '{' {Cmd} '}')] */
  void cmdTeste() {

    Exp exp1;

    /*if Exp*/
    casaToken(tab -> If);

    expressoes(exp1);

    string rotIf;
    rotIf = rotuloGerado();
    cod -> codigoTeste += rotIf + ":\n";
    cod -> codigoTeste += "     mov eax, [M + " + getHexa(exp1.endereco) + "]\n";
    cod -> codigoTeste += "     cmp eax, 0 \n";
    cod -> codigoTeste += "     je " + rotIf + "\n";

    /*Cmd*/
    if (lex.token == tab -> Id || lex.token == tab -> While ||
      lex.token == tab -> If || lex.token == tab -> Readln ||
      lex.token == tab -> Write || lex.token == tab -> Writeln
      || lex.token == tab -> Ponto_E_Virgula) {
      cmd();
    } else {/*'{' {Cmd} '}'*/
      casaToken(tab -> Chave_Esquerdo);
      while (lex.token == tab -> Id || lex.token == tab -> While ||
        lex.token == tab -> If || lex.token == tab -> Readln ||
        lex.token == tab -> Write || lex.token == tab -> Writeln
        || lex.token == tab -> Ponto_E_Virgula) {
          cmd();
      }
      casaToken(tab -> Chave_Direita);
    }

    /*else (Cmd | '{' {Cmd} '}')*/
    if (lex.token == tab -> Else) {
      casaToken(tab -> Else);
      string rotElse;
      rotElse =  rotuloGerado();

      cod -> codigoTeste += "     jmp " + rotElse + "\n";
      cod -> codigoTeste += rotElse + ":\n";
      if (lex.token == tab -> Id || lex.token == tab -> While ||
        lex.token == tab -> If || lex.token == tab -> Readln ||
        lex.token == tab -> Write || lex.token == tab -> Writeln
        || lex.token == tab -> Ponto_E_Virgula) {
        cmd();
      } else {
        casaToken(tab -> Chave_Esquerdo);
        while (lex.token == tab -> Id || lex.token == tab -> While ||
          lex.token == tab -> If || lex.token == tab -> Readln ||
          lex.token == tab -> Write || lex.token == tab -> Writeln
          || lex.token == tab -> Ponto_E_Virgula) {
            cmd();
        }
        casaToken(tab -> Chave_Direita);
      }
    }
  }

  /* Método para a regra Rep
   * Gramática: while Exp (Cmd | '{' {Cmd} '}') */
  void cmdRepeticao() {

    Exp exp1;

    /* while Exp*/
    casaToken(tab -> While);

    string rotWhile;
    rotWhile  = rotuloGerado();

    cod -> codigoTeste += rotWhile + ":\n";


    expressoes(exp1);
    if(exp1.tipo != BOOL) {
      er-> erroTiposIncomp();
    }

    string rotWhile2;
    rotWhile2 = rotuloGerado();
    cod -> codigoTeste += rotWhile2 + ":\n";
    cod -> codigoTeste += "     mov eax, [M + " + getHexa(exp1.endereco) + "]\n";
    cod -> codigoTeste += "     cmp eax, 0 \n";
    cod -> codigoTeste += "     je " + rotWhile2 + "\n";

    /* Cmd*/
    if (lex.token == tab -> Id || lex.token == tab -> While ||
      lex.token == tab -> If || lex.token == tab -> Readln ||
      lex.token == tab -> Write || lex.token == tab -> Writeln
      || lex.token == tab -> Ponto_E_Virgula) {
      cmd();
    } else {/*'{' {Cmd} '}'*/
      casaToken(tab -> Chave_Esquerdo);
      while (tab -> Chave_Direita != lex.token) {
        cmd();
      }
      casaToken(tab -> Chave_Direita);
    }

  }

  /* Método para a regra Atr
   * Gramática: Id ['[' Exp ']'] <- Exp ; */
  void cmdAtribuicao(string lex_valor) {

    Exp exp1;
    Exp exp2;
    char id_tipo = tab->simbolos[lex_valor].tipo;

    bool acesso = false;
    string idlexema = "";

    /*Id ['[' Exp ']']*/
    idlexema = lex.lexema;
    casaToken(tab -> Id);

    /*Tradução 7*/

    if(tab->simbolos[idlexema].classe == classe_Vazia){
      er-> erroId_Nao_Declarado(idlexema);
    }


    /*Tradução 8*/
    if(tab->simbolos[lex_valor].classe == classe_Constante){
      er-> erroClasseIncomp(lex_valor);
    }

    if (lex.token == tab -> Colchete_Esquerdo) {
      casaToken(tab -> Colchete_Esquerdo);
      expressoes(exp1);

      /*Tradução 9*/
      if(exp1.tipo != INT){
        er-> erroTiposIncomp();

      }
      acesso = true;
      casaToken(tab -> Colchete_Direito);
    }
    /*<- Exp ;*/
    casaToken(tab -> Atribuicao);
    expressoes(exp2);

    /*Tradução 10*/
    if(id_tipo != STRING && acesso == true){
      //std::cout << "/* message 10 */" << '\n';
      er->erroTiposIncomp();
    }

    /*Tradução 11*/
    if((id_tipo != exp2.tipo && !acesso) && ( id_tipo != CHAR  || exp2.tipo != STRING)){
      //std::cout << "/* message 11*/" << '\n';
      er->erroTiposIncomp();
    }

    casaToken(tab -> Ponto_E_Virgula);
  }

  /* Método para a regra Cmd
   * Gramática: Atr | Rep | Tes | R | W | ; */
  void cmd() {

    string lex_valor = "";
    lex_valor = lex.lexema;

    if (lex.token == tab -> While) {/*Rep*/
      cmdRepeticao();
    } else if (lex.token == tab -> If) {/*Tes*/
      cmdTeste();
    } else if (lex.token == tab -> Id) {/*Atr*/
      cmdAtribuicao(lex_valor);
    } else if (lex.token == tab -> Readln) {/*R*/
      cmdRead();
    } else if (lex.token == tab -> Write) {/*W*/
      cmdWrite();
    }else if(lex.token == tab -> Writeln){/*W*/
      cmdWrite();
    }else {/*;*/
      casaToken(tab -> Ponto_E_Virgula);
    }
  }

  /* Método para a regra Decl
   * Gramática: (int| float| string | char) Id [<- [-]Constante] {, Id [<- [-]Constante]} ;
   * Gramática: const Id = [-]Constante ;  */
  void declaracao() {

    char tipo_Id;
    char tipo_Tok;
    char tipo_Constante;
    char token_Diferenca = 35;
    string id_lexema = "";
    int constTipo ;
    string lex_valor = "";
    string lexlexemaconst ="";


    bool sinal = false;

    /*(int| float| string | char) Id*/
    if (lex.token == tab -> Int || lex.token == tab -> Float || lex.token == tab -> String ||
      lex.token == tab -> Char) {
      tipo_Tok = lex.token;
      lexico -> analisador();
      lex_valor = lex.lexema;
      id_lexema = lex.lexema;
      casaToken(tab -> Id);

      /*Tradução (1)*/
      if(tab->simbolos[lex_valor].classe == classe_Vazia){
          tab->simbolos[lex_valor].valorClasse(classe_Var);
      }else{
        er->erroIdDeclarado(lex_valor);
      }

      /*Tradução (2)*/

      if(tab->Int == tipo_Tok){
        tipo_Id= INT;
      }else if(tab->Float == tipo_Tok){
        tipo_Id = FLOAT;
      }else if(tab->String == tipo_Tok){
        tipo_Id = STRING;
      }else{
        tipo_Id = CHAR;
      }

      //tab->simbolos[id_lexema].valorTipo(tipo_Id); // Acao ()

      /*[<- [-]Constante]*/
      if (lex.token == tab -> Atribuicao) {
        casaToken(tab -> Atribuicao);

        if (lex.token == tab -> Diferenca) {
          sinal = true;
          casaToken(tab -> Diferenca);
        }
        tipo_Constante = lex.tipo;
        lexlexemaconst = lex.lexema;
        casaToken(tab -> Constante);
        if(tipo_Id !=  tipo_Constante){
            er->erroTiposIncomp();
        }
      }


      if(tab->Int == tipo_Tok){
        cod-> codigoConstante += "     dd " + lexlexemaconst +  "             ;Inteiro em "+ getHexa(memoriaDados)+"\n";
        memoriaDados += 4;
      }else if(tab->Float == tipo_Tok){
        cod-> codigoConstante += "     dd " + lexlexemaconst + "              ;Float em "+ getHexa(memoriaDados)+"\n";
        memoriaDados += 8;  //VER QUANTO É O FLOAT
      }else if(tab->String == tipo_Tok){
        cod-> codigoConstante += "    db " + lexlexemaconst + ",0            ;String em "+ getHexa(memoriaDados)+"\n";
        memoriaDados += tab->simbolos[lexlexemaconst].tamanho +1 ;
      }else{
        cod-> codigoConstante += " db "+ lexlexemaconst +"                 ;Car. em "+ getHexa(memoriaDados)+"\n";
        memoriaDados += 1;
      }

      tab->simbolos[id_lexema].valorTipo(tipo_Id); // Acao ()

      /*Tradução (3)*/
      if(sinal && !(tipo_Constante == INT || tipo_Constante == FLOAT)){
          er->erroTiposIncomp();
      }

      /*{, Id [<- [-]Constante]} ;*/
      while (lex.token == tab -> Virgula) {
        //lex_valor = lex.lexema;
        casaToken(tab -> Virgula);
        lex_valor = lex.lexema;
        casaToken(tab -> Id);

      /*Tradução (1)*/
      if(tab->simbolos[lex_valor].classe == classe_Vazia){
          tab->simbolos[lex_valor].valorClasse(classe_Var);
      }else{
        er->erroIdDeclarado(lex_valor);
      }

      /*Tradução (2)*/
      if(tab->Int == tipo_Tok){
        tipo_Id= INT;
      }else if(tab->Float == tipo_Tok){
        tipo_Id = FLOAT;
      }else if(tab->String == tipo_Tok){
        tipo_Id = STRING;
      }else{
        tipo_Id = CHAR;
      }

      tab->simbolos[lex_valor].valorTipo(tipo_Id); // Acao ()


        if (lex.token == tab -> Atribuicao) {
          casaToken(tab -> Atribuicao);
          if (lex.token == tab -> Diferenca) {
            casaToken(tab -> Diferenca);
          }
          tipo_Constante = lex.tipo;
          casaToken(tab -> Constante);

      /*Tradução (3)*/
      if(sinal && !(tipo_Constante == INT || tipo_Constante == FLOAT)){
          er->erroTiposIncomp();
      }

      /*Tradução (4)*/
      if(tipo_Id != tipo_Constante){
          er->erroTiposIncomp();
      }

        }
      }
      casaToken(tab -> Ponto_E_Virgula);

      /*const Id = [-]Constante ;*/
    } else if (lex.token == tab -> Const) {
      //lex_valor = lex.lexema;
      casaToken(tab -> Const);
      lex_valor = lex.lexema;
      casaToken(tab -> Id);

      /*Tradução (5)*/
      if(tab->simbolos[lex_valor].classe == classe_Vazia){
          tab->simbolos[lex_valor].valorClasse(classe_Constante);
      }else{
        //std::cout << "/*Tradução (5)*/" << '\n';
        er->erroIdDeclarado(lex_valor);
      }

      casaToken(tab -> Igual);

      if (lex.token == tab -> Diferenca) {
        casaToken(tab -> Diferenca);
      }
      constTipo = lex.tipo;
      casaToken(tab -> Constante);
      tab->simbolos[lex_valor].valorTipo(constTipo);

      /*Tradução (3)*/
      if(sinal && !(tipo_Constante == INT || tipo_Constante == FLOAT)){
        //std::cout << "/* message 3*/" << '\n';
          er->erroTiposIncomp();

      }

      casaToken(tab -> Ponto_E_Virgula);
    }
  }

  /* Método para a regra ProgF
   * Gramática: {(Decl | Cmd)} EOF */
  void regraInicial() {

    /*{(Decl | Cmd)}*/
    while (lex.token == tab -> Int || lex.token == tab -> Float ||
      lex.token == tab -> String || lex.token == tab -> Char ||
      lex.token == tab -> Const || lex.token == tab -> While ||
      lex.token == tab -> If || lex.token == tab -> Id ||
      lex.token == tab -> Readln || lex.token == tab -> Write ||
      lex.token == tab -> Writeln) {

      /*Decl*/
      if (lex.token == tab -> Int || lex.token == tab -> Float ||
        lex.token == tab -> String || lex.token == tab -> Char ||
        lex.token == tab -> Const) {
        declaracao();

        /*Cmd*/
      } else if (lex.token == tab -> While ||
        lex.token == tab -> If || lex.token == tab -> Id ||
        lex.token == tab -> Readln || lex.token == tab -> Write ||
        lex.token == tab -> Writeln) {

        cmd();
      }
    }
    /*EOF*/
    casaToken(tab -> End);

  }

};


int main() {
  /*Instanciando as classes*/
  Tabela_Simbolos s;
  analisadorLexico lex2;
  analisadorSintatico sint;
  gerador cod;


    ofstream file;


  /*Chamando construtores das classes*/
  lex2 = analisadorLexico(&s);
  cod = gerador();
  sint = analisadorSintatico(&s, &lex2, &cod);


  lex2.analisador();
  sint.regraInicial();
  cod.geracaoCodigo();
  cout << linhasCompiladas << " linhas compiladas.";


  file.open("programa.asm");
  file << cod.codigo;
  file.close();

  return 0;
}
