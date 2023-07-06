
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.net.*;
import java.io.*;
import java.util.Scanner;
/**
 * Celula Dupla
 * @author Max do Val Machado
 * @version 2 01/2015
 */
class CelulaDupla {
    public Time elemento;
    public CelulaDupla ant;
    public CelulaDupla prox;

    /**
     * Construtor da classe.
     */
    public CelulaDupla() {
      this.elemento = elemento;
      this.ant = this.prox = null;
    }
    public CelulaDupla(Time elemento) {
      this.elemento = elemento;
      this.ant = this.prox = null;
    }


}
/**
 * Lista dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */
class ListaDupla {
    private CelulaDupla primeiro;
    private CelulaDupla ultimo;
    private int b=0;


    /**
     * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
     */
    public ListaDupla() {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }


    /**
     * Insere um elemento na primeira posicao da lista.
    * @param x int elemento a ser inserido.
     */
    public void inserirInicio(Time x) {
        CelulaDupla tmp = new CelulaDupla(x);
        tmp.ant = primeiro;
       tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }else{
            tmp.prox.ant=tmp;
        }
      tmp = null;
    }


    /**
     * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
     */
    public void inserirFim(Time x) {
        ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }


    /**
     * Remove um elemento da primeira posicao da lista.
    * @return resp int elemento a ser removido.
     * @throws Exception Se a lista nao contiver elementos.
     */
    public Time removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

      CelulaDupla tmp = primeiro;
        primeiro = primeiro.prox;
        Time resp = primeiro.elemento;
      tmp.prox = primeiro.ant=null;
      tmp = null;
        return resp;
    }


    /**
     * Remove um elemento da ultima posicao da lista.
    * @return resp int elemento a ser removido.
     * @throws Exception Se a lista nao contiver elementos.
     */
    public Time removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

      Time resp = ultimo.elemento;
      ultimo = ultimo.ant;
      ultimo.prox.ant = null;

        return resp;
    }


    /**
    * Insere um elemento em uma posicao especifica considerando que o
    * primeiro elemento valido esta na posicao 0.
    * @param x int elemento a ser inserido.
     * @param pos int posicao da insercao.
     * @throws Exception Se <code>posicao</code> invalida.
     */
   public void inserir(Time x, int pos) throws Exception {

      int tamanho = tamanho();

      if(pos < 0 || pos > tamanho){
            throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
      } else if (pos == 0){
         inserirInicio(x);
      } else if (pos == tamanho){
         inserirFim(x);
      } else {
           // Caminhar ate a posicao anterior a insercao
         CelulaDupla i = primeiro;
         for(int j = 0; j < pos; j++, i = i.prox);

         CelulaDupla tmp = new CelulaDupla(x);
         tmp.ant = i;
         tmp.prox = i.prox;
         tmp.ant.prox = tmp.prox.ant=tmp;
         tmp = i = null;
      }
   }


    /**
    * Remove um elemento de uma posicao especifica da lista
    * considerando que o primeiro elemento valido esta na posicao 0.
     * @param posicao Meio da remocao.
    * @return resp int elemento a ser removido.
     * @throws Exception Se <code>posicao</code> invalida.
     */
    public Time remover(int pos) throws Exception {
      Time resp;
      int tamanho = tamanho();

        if (primeiro == ultimo){
            throw new Exception("Erro ao remover (vazia)!");

      } else if(pos < 0 || pos >= tamanho){
            throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
      } else if (pos == 0){
         resp = removerInicio();
      } else if (pos == tamanho - 1){
         resp = removerFim();
      } else {
           // Caminhar ate a posicao anterior a insercao
         CelulaDupla i = primeiro.prox;
         for(int j = 0; j < pos; j++, i = i.prox);

         i.ant.prox=i.prox;
         i.prox.ant=i.ant;
         resp = i.elemento;
         i.prox = i.ant=null;
         i = null;
      }

        return resp;
    }

   /**
     * Mostra os elementos separados por espacos, comecando do topo.
     */
    public void mostrar() {
      for(CelulaDupla i=primeiro.prox;i!=null;i=i.prox){
            i.elemento.imprimir(b);
            b++;
      }
   }

   /**
    * Troca o conteudo de duas posicoes do array
    * @param i int primeira posicao
    * @param j int segunda posicao
    */
   public void swap(CelulaDupla i,CelulaDupla j) {
      Time tmp = new Time();
      tmp = i.elemento;
      i.elemento = j.elemento;
      j.elemento = tmp;
   }
    public void quicksort(){
            quick(primeiro.prox,ultimo);
    }

    public CelulaDupla quicksort(CelulaDupla esq,CelulaDupla dir) {
        String pivo=dir.elemento.getApelido();
        CelulaDupla x=esq.ant;
        for(CelulaDupla i=esq;i!=dir;i=i.prox){
            String l =i.elemento.getApelido();
            if(l.compareTo(pivo)<=0){
                x=(x==null)?esq:x.prox;
                swap(x,i);
            }
        }
        x=(x==null)?esq:x.prox;
        swap(x,dir);
        return x;
    }
    private void quick(CelulaDupla esq,CelulaDupla dir){
        if(dir!=null && esq!=dir & esq!=dir.prox){
            CelulaDupla tmp=quicksort(esq,dir);
            quick(esq,tmp.ant);
            quick(tmp.prox,dir);
}
    }
    /**
     * Calcula e retorna o tamanho, em numero de elementos, da lista.
     * @return resp int tamanho
     */
   public int tamanho() {
      int tamanho = 0;
      for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }
}
/**
 * Classe time
 * @author Ana Luiza Pacheco Leite
 */

class Time{

private String nome,apelido,estadio,tecnico,liga,nomeArquivo;
private int capacidade,fundacaoDia,fundacaoMes,fundacaoAno;
private long paginaTam;

/**
 * construtores
*/
 Time(){
   this.setNome("");
   this.setApelido("");
   this.setEstadio("");
   this.setTecnico("");
   this.setLiga("");
   this.setNomeArquivo("");
   this.setCapacidade(0);
   this.setFundacaoDia(0);
   this.setFundacaoMes(0);
   this.setFundacaoAno(0);
   this.setPaginaTam(0);
 }//fim Time

 Time(String nome,String apelido,String estadio,String tecnico,String liga,String nomeArquivo,int capacidade,int fundacaoDia,int fundacaoMes,int fundacaoAno,long paginaTam){
   this.setNome(nome);
   this.setApelido(apelido);
   this.setEstadio(estadio);
   this.setTecnico(tecnico);
   this.setLiga(liga);
   this.setNomeArquivo(nomeArquivo);
   this.setCapacidade(capacidade);
   this.setFundacaoDia(fundacaoDia);
   this.setFundacaoMes(fundacaoMes);
   this.setFundacaoAno(fundacaoAno);
   this.setPaginaTam(paginaTam);
 }//fim Time

 public void setNome(String nome){
   this.nome = nome;
 }
 public void setApelido(String apelido){
   this.apelido = apelido;
 }
 public void setEstadio(String estadio){
   this.estadio = estadio;
 }
 public void setTecnico(String tecnico){
   this.tecnico = tecnico;
 }
 public void setLiga(String liga){
   this.liga = liga;
 }
 public void setNomeArquivo(String nomeArquivo){
   this.nomeArquivo = nomeArquivo;
 }
 public void setCapacidade(int capacidade){
   this.capacidade = capacidade;
 }
 public void setFundacaoDia(int fundacaoDia){
   this.fundacaoDia = fundacaoDia;
 }
 public void setFundacaoMes(int fundacaoMes){
   this.fundacaoMes = fundacaoMes;
 }
 public void setFundacaoAno(int fundacaoAno){
   this.fundacaoAno = fundacaoAno;
 }
 public void setPaginaTam(long paginaTam){
   this.paginaTam = paginaTam;
 }


 public String getNome(){
   return nome;
 }
 public String getApelido(){
   return apelido;
 }
 public String getEstadio(){
   return estadio;
 }
 public String getTecnico(){
   return tecnico;
 }
 public String getLiga(){
   return liga;
 }
 public String getNomeArquivo(){
   return nomeArquivo;
 }
 public int getCapacidade(){
   return capacidade;
 }
 public int getFundacaoDia(){
   return fundacaoDia;
 }
 public int getFundacaoMes(){
   return fundacaoMes;
 }
 public int getFundacaoAno(){
   return fundacaoAno;
 }
 public long getPaginaTam(){
   return paginaTam;
 }


 public Time clone(){
     Time t = new Time();
     t.setNome(getNome());
     t.setApelido(getApelido());
     t.setEstadio(getEstadio());
     t.setTecnico(getTecnico());
     t.setLiga(getLiga());
     t.setNomeArquivo(getNomeArquivo());
      t.setCapacidade(getCapacidade());
     t.setFundacaoDia(getFundacaoDia());
     t.setFundacaoMes(getFundacaoMes());
     t.setFundacaoAno(getFundacaoAno());
     t.setPaginaTam(getPaginaTam());
      return t;
 }//fim clone

 /**
 * le todas as informacoes do arquivo html e salva o que precisa
 * @aparam endereco
 */

 public void ler(String endereco){
   setNomeArquivo(endereco);
   Arq.openRead(endereco,"UTF-8");						//abre arquivo
   MyIO.setCharset("UTF-8");

   String findName ="";
   String nomeAux ="";

   String findNick ="";
   String apelidoAux ="";

   String findStadium ="";
   String estadioAux ="";

   String findCoach ="";
   String tecnicoAux ="";

   String findCapacity ="";
   String capacidadeAux ="";

   String findLeague ="";
   String ligaAux ="";

   String findDate ="";
   String dataAux ="";
   String diaAux ="";
   String mesAux ="";
   String anoAux ="";


 try{
     while(endereco.contains("ull name")==false) endereco=Arq.readLine();
           findName= endereco.replaceAll("<[^>]*>","");
           findName= findName.replaceAll("&#32","");
           findName= findName.replaceAll("&#160","");
           String aName[] = findName.split(" name");
           findName = aName[1];
           String nomeOficial[] = findName.split("Nickname");
           nomeAux= nomeOficial[0];
           nomeAux= nomeAux.replaceAll("&#91;1&#93;","  ");
           nomeAux= nomeAux.replaceAll("&#91;citation needed&#93","");
           nomeAux= nomeAux.replaceAll("&amp;"," ");
           nomeAux=nomeAux.trim();
           nomeAux= nomeAux.replaceAll(";"," ");
           nomeAux= nomeAux.replaceAll("&#91 2&#93"," ");
           nomeAux= nomeAux.replaceAll("&#91 note 1&#93","");
           nomeAux=nomeAux.replaceAll("\"","");
           setNome(nomeAux);

     while(endereco.contains("Nickname(s)")==false) endereco=Arq.readLine();
           findNick= endereco.replaceAll("<[^>]*>","");
           findNick= findNick.replaceAll("&#32","");
           String aNick[] = findNick.split("Nickname");
           findNick = aNick[1];
           String apelidoOficial[] = findNick.split("Founded");
           apelidoAux= apelidoOficial[0];
           apelidoAux= apelidoAux.replaceFirst("[/.(]","");
           apelidoAux= apelidoAux.replaceFirst("s","");
           apelidoAux= apelidoAux.replaceFirst("[/.)]","");
           if(apelidoAux.contains("Short name")){
             String apelidoOficial2[] = apelidoAux.split("Short");
             apelidoAux= apelidoOficial2[0];
           }
           apelidoAux= apelidoAux.replaceAll("&amp;"," ");
           apelidoAux= apelidoAux.replace("\"", "");
           apelidoAux= apelidoAux.replace("&#91;2&#93;&#91;3&#93;", "");
           apelidoAux= apelidoAux.replace("&#91;1&#93;", "");
           apelidoAux= apelidoAux.replace("&#91;2&#93;", "");
           apelidoAux= apelidoAux.replace("&#160;"," ");
           apelidoAux= apelidoAux.replace("&#91;3&#93;"," ");
           apelidoAux= apelidoAux.replace("&#91;note 1&#93;"," ");
           apelidoAux= apelidoAux.replace("Academia Cruceña","Academia Cruceña Celestes");
           setApelido(apelidoAux);

     while(endereco.contains("ounded")==false) endereco=Arq.readLine();
           findDate= endereco;
           String aDate[] = findDate.split("ounded");
           findDate = aDate[1];
           String dataOficial[] = findDate.split("<span class");
           dataAux= dataOficial[0];
           dataAux= dataAux.replaceAll("</th><td>","");
           dataAux= dataAux.replaceAll("<span","");
           if(dataAux.contains("</td></tr>")){
             String aDate2[] = dataAux.split("</td></tr>");
             dataAux = aDate2[0];
             mesAux = aDate2[1];
           }

           if(dataAux.contains("&#160")){
             String aDate5[] = dataAux.split("&#160");
             diaAux=aDate5[0];
           }
           if(dataAux.contains("&#160;")==false){
             if(dataAux.length()==4){
               anoAux=dataAux;
               mesAux="0";
               diaAux="0";
             }else{
                 dataAux= dataAux.replaceAll(" ","/");
                 dataAux= dataAux.replaceAll(",","");
                 dataAux= dataAux.replaceAll("th","");
                 String aDate4[] = dataAux.split("/");
                 diaAux=aDate4[0];
                 mesAux=aDate4[1];
               if(diaAux.length()==1||diaAux.length()==2){
                   mesAux=replaceMes(mesAux);
               }else{
                   String mesAux2=mesAux;
                   diaAux=mesAux;
                   mesAux=mesAux2;
                   mesAux=replaceMes(mesAux);
               }
             }
           }else{
             dataAux= dataAux.replaceAll("&#160;"," ");
             String aDate3[] = dataAux.split(" ");
             if(aDate3[0].length()==1||aDate3[0].length()==2){
               diaAux=aDate3[0];
               mesAux="0";
             }else{
               mesAux=aDate3[0];
               mesAux=replaceMes(mesAux);
               diaAux="0";
             }
               String teste=aDate3[1];
               if(teste.equals("January")||teste.equals("February")||teste.equals("March")||teste.equals("April")||teste.equals("May")||teste.equals("June")||teste.equals("July")||
               teste.equals("August")||teste.equals("September")||teste.equals("October")||teste.equals("November")||teste.equals("December")){
                 mesAux=aDate3[1];
                 anoAux=aDate3[2];
                 mesAux=replaceMes(mesAux);
               }

             }
             if(diaAux.equals("badge")){
               anoAux="1880";
               diaAux="0";
               mesAux="0";
             }



           if(anoAux==""){
             String dataAux2=dataAux;
             dataAux2=replaceMes(dataAux);
             dataAux2=dataAux2.replaceAll(",", "");
              dataAux2=dataAux2.replaceAll(" ", "/");
             String[] dataAux3=dataAux2.split("/");

             if(dataAux3.length==3){
             diaAux=dataAux3[0];
             mesAux=dataAux3[1];
             anoAux=dataAux3[2];
             if(diaAux.charAt(0)=='0') {
               String aux= mesAux;
               mesAux=diaAux;
               diaAux=aux;
             }
           }else{

             mesAux=dataAux3[0];
             anoAux=dataAux3[1];
             }
           }

           if(Integer.parseInt(mesAux)>12){
               String aux=mesAux;
               mesAux=diaAux;
               diaAux=aux;
           }


         if(diaAux!=""){
             setFundacaoDia(Integer.parseInt(diaAux));
         }
         if(mesAux!=""){
             setFundacaoMes(Integer.parseInt(mesAux));
         }
         if(anoAux!=""){
             setFundacaoAno(Integer.parseInt(anoAux));
         }


     while(endereco.contains("Ground")==false) endereco=Arq.readLine();
           findStadium= endereco.replaceAll("<[^>]*>","");
           findStadium= findStadium.replaceAll("&#32","");
           String aStadium[] = findStadium.split("Ground",2);
           findStadium = aStadium[1];
           String estadioOficial[] = findStadium.split("Capacity");
           estadioAux= estadioOficial[0];
           if(estadioAux.contains("2018")){
             String aStadium2[] = estadioAux.split("2018");
             estadioAux=aStadium2[0];
           }
           estadioAux=estadioAux.replaceAll("Ground","");
           estadioAux=estadioAux.replaceAll("\"","");
           estadioAux=estadioAux.replaceAll("&#91;1&#93;","");
           estadioAux=estadioAux.replaceAll("&#91;2&#93;","");
           estadioAux=estadioAux.replaceAll("&#91;3&#93;","");
           setEstadio(estadioAux);

   while(endereco.contains("Capacity")==false) endereco=Arq.readLine();
           findCapacity= endereco;
           String aCapacity[] = findCapacity.split("Capacity");
           findCapacity = aCapacity[1];
           String capacidadeOficial[] = findCapacity.split("</td></tr>");
           capacidadeAux= capacidadeOficial[0];
           capacidadeAux= capacidadeAux.replaceAll("</th><td>","");
           capacidadeAux= capacidadeAux.replaceAll("<[^>]*>","");
           capacidadeAux= capacidadeAux.replaceAll("&#91;1&#93;","");
           capacidadeAux= capacidadeAux.replaceAll("&#91;2&#93;","");
           capacidadeAux= capacidadeAux.replaceAll("&#91;3&#93;","");
           capacidadeAux= capacidadeAux.replaceAll("&#91;4&#93;","");
           capacidadeAux= capacidadeAux.replace("[1]","");
           capacidadeAux= capacidadeAux.replaceAll(" ","");
           capacidadeAux= capacidadeAux.replaceAll(",","");
           capacidadeAux= capacidadeAux.replaceAll("[/..]","");
           capacidadeAux= capacidadeAux.replaceAll("2371821500","23718");
           capacidadeAux= capacidadeAux.replaceAll("4200014000","42000");
           capacidadeAux= capacidadeAux.replaceAll("1507312500","15073");
           capacidadeAux= capacidadeAux.replaceAll("&#91","");
           if(capacidadeAux.contains("(")){
             String aCapacity2[] = capacidadeAux.split("[/.(]");
             capacidadeAux = aCapacity2[0];
           }
           if(capacidadeAux.contains(";")){
             String aCapacity3[] = capacidadeAux.split("[/.;]");
             capacidadeAux = aCapacity3[0];
           }
           if(capacidadeAux.contains("or")){
             String aCapacity4[] = capacidadeAux.split("or");
             capacidadeAux = aCapacity4[0];
           }

         capacidadeAux= capacidadeAux.replaceAll("42000ChairmanHenrySalinas","42000");
         capacidadeAux= capacidadeAux.replaceAll("(Independência)","");
         capacidadeAux= capacidadeAux.replaceAll("61846","");
         capacidadeAux= capacidadeAux.replaceAll("(Mineirão)","");
       if(capacidadeAux!=""){
           setCapacidade(Integer.parseInt(capacidadeAux));
       }

         if(endereco.contains("Head coach")){
           while(endereco.contains("coach")==false)		endereco=Arq.readLine();
                 findCoach= endereco.replaceAll("<[^>]*>","");
                 findCoach= findCoach.replaceAll("&#32","");
                 findCoach= findCoach.replaceAll("-"," ");
                 String aCoach[] = findCoach.split("coach");
                 findCoach = aCoach[1];
                 String tecnicoOficial[] = findCoach.split("League");
                 tecnicoAux= tecnicoOficial[0];
                 tecnicoAux=tecnicoAux.replaceAll("&#91;1&#93;","");
                 tecnicoAux=tecnicoAux.replaceAll("&#91;2&#93;","");
                 tecnicoAux=tecnicoAux.replaceAll("&#91;3&#93;","");
                 setTecnico(tecnicoAux);
             }else{
               if(endereco.contains("Coach")){
                   while(endereco.contains("Coach")==false)		endereco=Arq.readLine();
                   findCoach= endereco.replaceAll("<[^>]*>","");
                   findCoach= findCoach.replaceAll("&#32","");
                   findCoach= findCoach.replaceAll("-"," ");
                   String aCoach[] = findCoach.split("Coach");
                   findCoach = aCoach[1];
                   String tecnicoOficial[] = findCoach.split("League");
                   tecnicoAux= tecnicoOficial[0];
                   tecnicoAux=tecnicoAux.replaceAll("&#91;1&#93;","");
                   tecnicoAux=tecnicoAux.replaceAll("&#91;2&#93;","");
                   tecnicoAux=tecnicoAux.replaceAll("&#91;3&#93;","");
                   setTecnico(tecnicoAux);
               }else{
                 if(endereco.contains("Manager")){
                   while(endereco.contains("Manager")==false)		endereco=Arq.readLine();
                   findCoach= endereco.replaceAll("<[^>]*>","");
                   findCoach= findCoach.replaceAll("&#32","");
                   findCoach= findCoach.replaceAll("-"," ");
                   String aCoach[] = findCoach.split("Manager");
                   findCoach = aCoach[1];
                   if(findCoach.contains("Coach")==false){
                       String tecnicoOficial[] = findCoach.split("League");
                       tecnicoAux= tecnicoOficial[0];
                   }else{
                     String tecnicoOficial[] = findCoach.split("Coach");
                     tecnicoAux= tecnicoOficial[0];
                   }
                   tecnicoAux=tecnicoAux.trim();
                   tecnicoAux=tecnicoAux.replaceAll("&#91;1&#93;","");
                   tecnicoAux=tecnicoAux.replaceAll("&#91;2&#93;","");
                   tecnicoAux=tecnicoAux.replaceAll("&#91;3&#93;","");
                   setTecnico(tecnicoAux);
             }
           }
}

     while(endereco.contains(">League")==false) endereco=Arq.readLine();
           int i=0;
           findLeague= endereco.replaceAll("<[^>]*>","");
           findLeague= findLeague.replaceAll("&#32","");
           findLeague= findLeague.replaceAll("-"," ");
           String aLeague[] = findLeague.split("League",2);
           findLeague = aLeague[1];
           String ligaOficial[] = findLeague.split("Website");
           ligaAux= ligaOficial[0];
           if(ligaAux.contains("2018")){
             String aLeague2[] = ligaAux.split("2018");
             ligaAux=aLeague2[0];
           }
           if(ligaAux.contains("2017")){
             String aLeague3[] = ligaAux.split("2017");
             ligaAux=aLeague3[0];
           }
           if(ligaAux.contains("2019")){
             String aLeague4[] = ligaAux.split("2019");
             ligaAux=aLeague4[0];
           }
           setLiga(ligaAux);

          long cont = 0;
          File arquivo=new File(endereco);
          cont+=Arq.length();
          setPaginaTam(cont);

   }catch (IllegalStateException exc){
     MyIO.println("Erro");
   }//fim try -catch
 }//fim ler

 public void imprimir(int a){
       int dia = getFundacaoDia();
       int mes = getFundacaoMes();
       String diaS=""+dia;
       String mesS=""+mes;
       MyIO.setCharset("UTF-8");

                 if(diaS.length()==1 ){
                   if( mesS.length()==1){
                     MyIO.println(getNome()+" ## "+getApelido()+" ## "+"0"+getFundacaoDia()+"/"+"0"+getFundacaoMes()+"/"+getFundacaoAno()+" ## "+getEstadio()+" ## "+getCapacidade()+" ## "+
                     getTecnico()	+" ## "+ getLiga()+" ## "+ getNomeArquivo()+" ## "+getPaginaTam()+" ## ");
                   }
                 }
                 if(diaS.length()==2 && mesS.length()==1 ){
                   MyIO.println(getNome()+" ## "+getApelido()+" ## "+getFundacaoDia()+"/"+"0"+getFundacaoMes()+"/"+getFundacaoAno()+" ## "+getEstadio()+" ## "+getCapacidade()+" ## "+
                   getTecnico()	+" ## "+ getLiga()+" ## "+ getNomeArquivo()+" ## "+getPaginaTam()+" ## ");
                 }
                 if(diaS.length()==1 && mesS.length()==2){
                   MyIO.println(getNome()+" ## "+getApelido()+" ## "+"0"+getFundacaoDia()+"/"+getFundacaoMes()+"/"+getFundacaoAno()+" ## "+getEstadio()+" ## "+getCapacidade()+" ## "+
                   getTecnico()	+" ## "+ getLiga()+" ## "+ getNomeArquivo()+" ## "+getPaginaTam()+" ## ");
                 }
                 if(diaS.length()==2 && mesS.length()==2){
                   MyIO.println(getNome()+" ## "+getApelido()+" ## "+getFundacaoDia()+"/"+getFundacaoMes()+"/"+getFundacaoAno()+" ## "+getEstadio()+" ## "+getCapacidade()+" ## "+
                   getTecnico()	+" ## "+ getLiga()+" ## "+ getNomeArquivo()+" ## "+getPaginaTam()+" ## ");
                }
 }//fim imprimir


   public String fixDate(String str){
     int dia= Integer.parseInt(str);
     String diaCerto = String.format("%02d", dia);
     return diaCerto;
   }

   public String replaceMes(String mesAux){
     mesAux = mesAux.replaceAll("January","01");
     mesAux = mesAux.replaceAll("February","02");
     mesAux = mesAux.replaceAll("March","03");
     mesAux = mesAux.replaceAll("April","04");
     mesAux = mesAux.replaceAll("May","05");
     mesAux = mesAux.replaceAll("June","06");
     mesAux = mesAux.replaceAll("July","07");
     mesAux = mesAux.replaceAll("August","08");
     mesAux = mesAux.replaceAll("September","09");
     mesAux = mesAux.replaceAll("October","10");
     mesAux = mesAux.replaceAll("November","11");
     mesAux = mesAux.replaceAll("December","12");
     return mesAux;
   }
}//fim class time

public class QuickDuplo{
		public static void main(String args[]) throws Exception{
					MyIO.setCharset("UTF-8");
					String linha[]=new String[1000];
					int numEntrada=0;
					ListaDupla lista=new ListaDupla();
					String []linha2 = new String [1000];
					String []linha3;
					int numEntrada2=0;
                    long tempo=System.nanoTime();

//ler entradas
					do{
						linha[numEntrada]=MyIO.readLine();
					}while(linha[numEntrada++].equals("FIM")==false);
						numEntrada--;

//inserir elementos na lista
					for(int i=0;i<numEntrada;i++){
						Time r = new Time();
						r.ler(linha[i]);
						lista.inserirFim(r);
					}

         for(int k=0;k<numEntrada;k++){
           lista.quicksort();
       }

				lista.mostrar();

	   }//fim main

	}
