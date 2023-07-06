#include <cstdlib>
#include <iostream>
#include <string>

using namespace std;

class Grafo{

public:

    int cor;
    bool estado;

    Grafo(){
        cor = -1;
        estado = true;
    }

};

bool verificaadj(int n, int p, Grafo grafo[], int **adj);
void busca(int v, int n, Grafo grafo[], int **adj);

int main() {
    int k, n, m, p, t, v1, v2;
    bool possivel;
    cin >> t;
    while(t > 0){
        cin >> n;
        cin >> m;
        cin >> p;
        cin >> k;
        Grafo grafo[n];
        int **adjacencies;

        adjacencies = (int **) malloc(n * sizeof(int *));
        for(int i = 0; i < n; i++){                               //criando lista/vetor de adjacencies
            adjacencies[i] = (int *)malloc(n * sizeof(int));
            for(int j = 0; j < n; j++){
                adjacencies[i][j] = 0;
            }
        }

        for(int i = 0; i < n; i++){                               //lendo a cor dos vertices
            cin >> grafo[i].cor;
        }

        for(int i = 0; i < m; i++){                                           //adiciona as adjacencias
            cin >> v1;
            cin >> v2;
            adjacencies[v1-1][v2-1] = 1;
            adjacencies[v2-1][v1-1] = 1;
        }

        possivel = verificaadj(n,p,grafo,adjacencies);

        if(possivel){
            cout << "Y" << endl << endl;
        }else{
            cout << "N" << endl << endl;
        }
        t--;
    }

    return 0;
}

bool verificaadj(int n, int p, Grafo grafo[], int **adj){
    int comp = 0;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            if((p > 0) && (i != j) && (adj[i][j] == 0) && (grafo[i].cor != grafo[j].cor)){
                adj[i][j] = 1;
                p--;
            }
        }
    }
    for(int i = 0; i < n; i++){
        if(grafo[i].estado == true){
            busca(i,n,grafo,adj);
            comp = comp + 1;
        }
    }
    if(p > 0 || comp > 1){
        return false;
    }else{
        return true;
    }
}


void busca(int v, int n, Grafo grafo[], int **adj){
    grafo[v].estado = false;
    for(int i = 0; i < n; i++){
        if(adj[v][i] == 1){
            if(grafo[i].estado == true){
                busca(i, n, grafo, adj);
            }
        }
    }
    grafo[v].estado = false;
}
