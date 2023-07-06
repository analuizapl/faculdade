
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.net.*;
import java.io.*;
import java.util.Scanner;

class No {
   public int elemento; // Conteudo do no.
   public No esq; // No da esquerda.
   public No dir; // No da direita.
   public No2 outro;

   /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     */
   No(int elemento) {
      this(elemento,null,null,null);
   }

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     * @param esq No da esquerda.
     * @param dir No da direita.
     */
   No(int elemento, No esq, No dir, No2 outro) {
      this.elemento = elemento;
      this.esq = esq;
      this.dir = dir;
      this.outro = outro;
   }
}

class No2 {
   public Time elemento; // Conteudo do no.
   public No2 esq; // No da esquerda.
   public No2 dir; // No da direita.

   /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     */
   No2(Time elemento) {
      this.elemento = elemento;
      this.esq = this.dir = null;

   }

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     * @param esq No2 da esquerda.
     * @param dir No2 da direita.
     */
   No2(Time elemento, No2 esq, No2 dir) {
      this.elemento = elemento;
      this.esq = null;
      this.dir = null;
   }
}


class ArvoreArvore {
   private No raiz; // Raiz da arvore.

    /**
     * Construtor da classe.
     */
   public ArvoreArvore() throws Exception{
      raiz = null;
      int array[] = {7,3,11,1,5,9,12,0,2,4,6,8,10,13,14};
      for(int i = 0;i<array.length;i++){
         inserir(array[i]);
      }


   }
   /**
     * Metodo publico iterativo para pesquisar elemento.
     * @param elemento Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */

   public boolean pesquisar(String elemento) {
      return pesquisar(raiz, elemento);
   }

   private boolean pesquisar(No no, String x) {

     boolean resp = false;
        if (no != null) {
	  resp = pesquisarSegundaArvore(no.outro, x);
	  if(resp == false){     
	  	MyIO.print(" esq");
          	resp=pesquisar(no.esq,x); 
          	if(resp == false){
             		MyIO.print(" dir");
             		resp = pesquisar(no.dir, x);
          	}
	  }
      }
        return resp;
   }

   private boolean pesquisarSegundaArvore(No2 no, String x) {
      boolean resp=false;
      if (no != null) {
         if (x.equals(no.elemento.getNome())) {
         resp = true;
      } else {
         MyIO.print(" ESQ");
         resp = pesquisarSegundaArvore(no.esq, x);
      if(resp==false){
         MyIO.print(" DIR");
         resp = pesquisarSegundaArvore(no.dir, x);
      }
      }
      }
      return resp;
   }
/**
*inserir elemento.
*/
   public void inserir(int x) throws Exception {
      raiz = inserir(x, raiz);
   }
   private No inserir(int x, No i) throws Exception {
      if (i == null) {
         i = new No(x);
      } else if (x < i.elemento) {
         i.esq = inserir(x, i.esq);
      } else if (x > i.elemento) {
         i.dir = inserir(x, i.dir);
      }else {
         throw new Exception("Erro ao inserir!");
      }
      return i;
   }
   public void inserir(Time x) throws Exception {
      inserir(x, raiz);
   }
   private void inserir(Time x, No i) throws Exception {
       if (x.getFundacaoAno() % 15 == i.elemento){
		 i.outro = inserir(x,i.outro);
	}else if (x.getFundacaoAno() % 15 < i.elemento) {
        	 inserir(x, i.esq);
     	 } else if (x.getFundacaoAno() % 15 > i.elemento) {
         	 inserir(x, i.dir);
 	}
   }
/*
   private void inserir2(Time x, No i) throws Exception {
      if (i == null) {
      } else if (x.getFundacaoAno() % 15 < i.elemento) {
         inserir(x, i.esq);
      } else if (x.getFundacaoAno() % 15 > i.elemento) {
         inserir(x, i.dir);
      }else {
         i.outro = inserir(x,i.outro);
      }
   }
*/
   private No2 inserir(Time x, No2 i) throws Exception {
      if (i == null) {
         i = new No2(x);

      } else if (x.getNome().compareTo(i.elemento.getNome())<0) {
         i.esq = inserir(x, i.esq);
      } else if (x.getNome().compareTo(i.elemento.getNome())>0) {
         i.dir = inserir(x, i.dir);
      }
      return i;
   }



}//Fim classe
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
            capacidadeAux= capacidadeAux.replaceAll("23018(Independência)61846(Mineirão)","23018");
            if(capacidadeAux.contains("seated")||capacidadeAux.contains("Mineirão")||capacidadeAux.contains("all-seater")||capacidadeAux.contains("seats")||
            capacidadeAux.contains("expanding")||capacidadeAux.contains("Arena")||capacidadeAux.contains("underrenovation")||capacidadeAux.contains("available")||
            capacidadeAux.contains("Maracanã")||capacidadeAux.contains("increasing")||capacidadeAux.contains("safe")||capacidadeAux.contains("matches")||
            capacidadeAux.contains("planned")||capacidadeAux.contains("Independência"))   {
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
                  tecnicoAux=tecnicoAux.replaceAll("&#91;8&#93;","");
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
                    tecnicoAux=tecnicoAux.replaceAll("&#91;8&#93;","");
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
                    tecnicoAux=tecnicoAux.replaceAll("&#91;8&#93;","");
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
 											MyIO.println("["+a+"] "+getNome()+" ## "+getApelido()+" ## "+"0"+getFundacaoDia()+"/"+"0"+getFundacaoMes()+"/"+getFundacaoAno()+" ## "+getEstadio()+" ## "+getCapacidade()+" ## "+
 											getTecnico()	+" ## "+ getLiga()+" ## "+ getNomeArquivo()+" ## "+getPaginaTam()+" ## ");
 										}
 									}
 									if(diaS.length()==2 && mesS.length()==1 ){
 										MyIO.println("["+a+"] "+getNome()+" ## "+getApelido()+" ## "+getFundacaoDia()+"/"+"0"+getFundacaoMes()+"/"+getFundacaoAno()+" ## "+getEstadio()+" ## "+getCapacidade()+" ## "+
 										getTecnico()	+" ## "+ getLiga()+" ## "+ getNomeArquivo()+" ## "+getPaginaTam()+" ## ");
 									}
 									if(diaS.length()==1 && mesS.length()==2){
 										MyIO.println("["+a+"] "+getNome()+" ## "+getApelido()+" ## "+"0"+getFundacaoDia()+"/"+getFundacaoMes()+"/"+getFundacaoAno()+" ## "+getEstadio()+" ## "+getCapacidade()+" ## "+
 										getTecnico()	+" ## "+ getLiga()+" ## "+ getNomeArquivo()+" ## "+getPaginaTam()+" ## ");
 									}
 									if(diaS.length()==2 && mesS.length()==2){
 										MyIO.println("["+a+"] "+getNome()+" ## "+getApelido()+" ## "+getFundacaoDia()+"/"+getFundacaoMes()+"/"+getFundacaoAno()+" ## "+getEstadio()+" ## "+getCapacidade()+" ## "+
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


public class ArvArv{
        public static void main(String args[]) throws Exception{
                    MyIO.setCharset("UTF-8");
                    String linha[]=new String[1000];
                    int numEntrada=0;
                    String []linha2 = new String [1000];
                    String []linha3;
                    int numEntrada2=0;
                    ArvoreArvore arvore = new ArvoreArvore();

		do{
		linha[numEntrada]=MyIO.readLine();
	}while(linha[numEntrada++].equals("FIM")==false);
		numEntrada--;
	do{
		linha2[numEntrada2]=MyIO.readLine();
	}while(linha2[numEntrada2++].equals("FIM")==false);
		numEntrada2--;
	for(int i=0;i<numEntrada;i++){
		Time r = new Time();
		r.ler(linha[i]);
		arvore.inserir(r);
	}

	 for(int k=0;k<numEntrada2;k++){
            MyIO.print(linha2[k]+" raiz");
           if(arvore.pesquisar(linha2[k])){
            MyIO.println(" SIM");
        }else{
            MyIO.println(" NÃO");
       }
        }
       }//fim main
}
