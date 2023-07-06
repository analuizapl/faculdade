#include <cstdlib>
#include <iostream>
#include <string>

using namespace std;

class Grafo{

public:

    bool estado;    //se ja foi buscado
    char no;

    Grafo(){
        estado = true;
        no = 'a';
    }

};

void busca(int v, Grafo grafo[], int **adj, int size);

int main() {
    int n, v, e, cont,casenumber = 1,size;
    char l1, l2;
    cin >> n;
    while(n > 0){
        cin >> v;
        cin >> e;
        Grafo grafo[v];
        int **adjacencies;


        adjacencies = (int **) malloc(v * sizeof(int *));               //criando lista/vetor de adjacencias
        for(int i = 0; i < v; i++){
            adjacencies[i] = (int *)malloc(v * sizeof(int));
            for(int j = 0; j < v; j++){
                adjacencies[i][j] = 0;
            }
        }





        for(int i = 0; i < v; i++){
            grafo[i].no = 97 + i;         //iniciando cada vertice com uma letra
        }


        for(int i = 0; i < e; i++){                     //adiciona as adjacencias
           cin >> l1;
           cin >> l2;
           adjacencies[l1-97][l2-97] = 1;
           adjacencies[l2-97][l1-97] = 1;
        }



        cout << "Case #" << casenumber << ":" << endl;
        cont = 0;
        size=v;



        for(int i = 0; i < v; i++){                                       //faz a busca pelos vertices
            if(grafo[i].estado == true){
                busca(i, grafo, adjacencies,size);
                cont = cont + 1;
                cout << endl;
            }
        }


        cout << cont << " connected components" << endl;
        cout << endl;
        n--;
        casenumber++;
    }
    return 0;
}


void busca(int v, Grafo grafo[], int **adj,int size){             //busca se sao adjacentes ou nao
    grafo[v].estado = false;
    cout << grafo[v].no << ",";
    for(int i = 0; i < size; i++){
        if(adj[grafo[v].no-97][i] == 1){
            if(grafo[i].estado == true){
                busca(i, grafo, adj, size);
            }
        }
    }
    grafo[v].estado = false;
}
