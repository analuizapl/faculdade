import java.net.*;
import java.io.*;
import java.lang.*;

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

 	public void imprimir(){
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


class Lista {
   private Time[] array;
   private int n;
   private int comparacao=0;


   /**
    * Construtor da classe.
    */
   public Lista () {
      this(6);
   }


   /**
    * Construtor da classe.
    * @param tamanho Tamanho da lista.
    */
   public Lista (int tamanho){
      array = new Time[tamanho];
      n = 0;
   }


   /**
    * Insere um elemento na primeira posicao da lista e move os demais
    * elementos para o fim da lista.
    * @param x int elemento a ser inserido.
    * @throws Exception Se a lista estiver cheia.
    */
   public void inserirInicio(Time x) throws Exception {

      //validar insercao
      if(n >= array.length){
         throw new Exception("Erro ao inserir!");
      }

      //levar elementos para o fim do array
      for(int i = n; i > 0; i--){
         array[i] = array[i-1];
      }

      array[0] = x.clone();
      n++;
   }


   /**
    * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
    * @throws Exception Se a lista estiver cheia.
    */
   public void inserirFim(Time x) throws Exception {

      //validar insercao
      if(n >= array.length){
         throw new Exception("Erro ao inserir!");
      }

      array[n] = x;
      n++;
   }


   /**
    * Insere um elemento em uma posicao especifica e move os demais
    * elementos para o fim da lista.
    * @param x int elemento a ser inserido.
    * @param pos Posicao de insercao.
    * @throws Exception Se a lista estiver cheia ou a posicao invalida.
    */
   public void inserir(Time x, int pos) throws Exception {

      //validar insercao
      if(n >= array.length || pos < 0 || pos > n){
         throw new Exception("Erro ao inserir!");
      }

      //levar elementos para o fim do array
      for(int i = n; i > pos; i--){
         array[i] = array[i-1];
      }

      array[pos] = x.clone();
      n++;
   }


   /**
    * Remove um elemento da primeira posicao da lista e movimenta
    * os demais elementos para o inicio da mesma.
    * @return resp int elemento a ser removido.
    * @throws Exception Se a lista estiver vazia.
    */
   public Time removerInicio() throws Exception {

      //validar remocao
      if (n == 0) {
         throw new Exception("Erro ao remover!");
      }

      Time resp = array[0];
      n--;

      for(int i = 0; i < n; i++){
         array[i] = array[i+1];
      }

      return resp;
   }


   /**
    * Remove um elemento da ultima posicao da lista.
    * @return resp int elemento a ser removido.
    * @throws Exception Se a lista estiver vazia.
    */
   public Time removerFim() throws Exception {

      //validar remocao
      if (n == 0) {
         throw new Exception("Erro ao remover!");
      }

      return array[--n];
   }


   /**
    * Remove um elemento de uma posicao especifica da lista e
    * movimenta os demais elementos para o inicio da mesma.
    * @param pos Posicao de remocao.
    * @return resp int elemento a ser removido.
    * @throws Exception Se a lista estiver vazia ou a posicao for invalida.
    */
   public Time remover(int pos) throws Exception {

      //validar remocao
      if (n == 0 || pos < 0 || pos >= n) {
         throw new Exception("Erro ao remover!");
      }

      Time resp = array[pos];
      n--;

      for(int i = pos; i < n; i++){
         array[i] = array[i+1];
      }

      return resp;
   }


   /**
    * Mostra os elementos da lista separados por espacos.
    */
   public void mostrar (){
    //  System.out.print("[ ");
      for(int i = 0; i < n; i++){
					array[i].imprimir();
         //System.out.print(array[i] + " ");
      }
    //  System.out.println("]");
   }


   /**
    * Procura um elemento e retorna se ele existe.
    * @param x int elemento a ser pesquisado.
    * @return <code>true</code> se o array existir,
    * <code>false</code> em caso contrario.
    */
   public boolean pesquisar(Time x) {
     boolean retorno = false;
       int i=0;
      do {
         if(x.equals(array[i].getNome())){
               retorno=true;
            }
            i++;
       }while(i < n && !retorno);
      return retorno;
   }
   public void mostrarRec (){
      System.out.print("[ ");
      mostrarRec(0);
      System.out.println("]");
   }

   public void mostrarRec(int i){
      if(i < n){
         System.out.print(array[i] + " ");
         mostrarRec(i + 1);
      }
   }
   /**
    * Algoritmo de ordenacao Countingsort.
    */
   public int countingsort() {
     //Array para contar o numero de ocorrencias de cada elemento
     int[] count = new int[100000];
     Time[] ordenado = new Time[n];
     int cont=0;
     //Inicializar cada posicao do array de contagem
       for (int i = 0; i < count.length; i++){
           count[i]=0;
           cont++;
       }
     //Agora, o count[i] contem o numero de elemento iguais a i
     for (int i = 0; i < n; i++){
       count[array[i].getCapacidade()]++;
       cont++;
     }
     //Agora, o count[i] contem o numero de elemento menores ou iguais a i
     for(int i = 1; i < count.length; i++){
       count[i]+=count[i-1];
       cont++;
     }
     //Ordenando
     for(int i = n-1; i >= 0; i--){
         ordenado[count[array[i].getCapacidade()]-1]=array[i];
         count[array[i].getCapacidade()]--;
         cont++;
    }
     //Copiando para o array original
     for(int i = 0; i < n; i++){
           array[i] = ordenado[i];
           cont++;
     }
    //   selecao();
       return cont;
}


 public int nComparacoes(){
     return comparacao;
 }

/**
    * Troca o conteudo de duas posicoes do array
    * @param i int primeira posicao
    * @param j int segunda posicao
    */
   public void swap(int i, int j) {
      Time tmp = new Time();
      tmp = array[i];
      array[i] = array[j];
      array[j] = tmp;
   }


}//fim lista

public class TimesCounting{
	public static void main(String args[]) throws Exception{
  		MyIO.setCharset("UTF-8");
		//Time time = new Time();
		 	String linha[]=new String[1000];
			int numEntrada=0;
			Lista lista=new Lista(1000);
			String []linha2 = new String [1000];
			String []linha3;
			int numEntrada2=0;
      long tempo=System.nanoTime();


		do{
			linha[numEntrada]=MyIO.readLine();
		}while(linha[numEntrada++].equals("FIM")==false);
		numEntrada--;

		 for(int i=0;i<numEntrada;i++){
			 Time t = new Time();
			 t.ler(linha[i]);
			 lista.inserirFim(t);
		 }

     for(int j=0;j<numEntrada;j++){
        lista.countingsort();
     }

     lista.mostrar();
     /*
     *cria arquivo de log
     */
           long f=System.nanoTime();
           long compara=f-tempo;
           Arq.openWrite("591514_countingsort.txt","ISO-8859-1");
           Arq.println("591514 \t tempo="+compara+" \t comparacoes="+lista.nComparacoes());
           Arq.close();
}//fim main
}//fim class times
