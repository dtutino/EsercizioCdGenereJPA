package it.prova.manytomanycdmaven.test;

import java.text.SimpleDateFormat;
import java.util.List;

import it.prova.manytomanycdmaven.dao.EntityManagerUtil;
import it.prova.manytomanycdmaven.model.Cd;
import it.prova.manytomanycdmaven.model.Genere;
import it.prova.manytomanycdmaven.service.CdService;
import it.prova.manytomanycdmaven.service.GenereService;
import it.prova.manytomanycdmaven.service.MyServiceFactory;

public class MyTest {

	public static void main(String[] args) {
		CdService cdServiceInstance = MyServiceFactory.getCdServiceInstance();
		GenereService genereServiceInstance = MyServiceFactory.getGenereServiceInstance();

		try {
			// *********************************************************************************
			// TEST INSERIMENTO NUOVO CD
			// ******************************************************
			// *********************************************************************************
			System.out.println("Inserimento nuovo cd...");
			Cd cdInstance = new Cd("titolo1","autore1",new SimpleDateFormat("dd/MM/yyyy").parse("24/09/2019"));
			cdServiceInstance.inserisciNuovo(cdInstance);
			if(cdServiceInstance.caricaSingoloElemento(cdInstance.getId()) != null)
				System.out.println("Inserimento nuovo cd...OK: "+cdInstance);
//			
//			//*********************************************************************************
//			//TEST CARICA CD  *****************************************************************
//			//*********************************************************************************
			Long idCdDaCaricare = 1L;
			System.out.println("Carica cd con id.." + idCdDaCaricare);
			Cd cdDaCaricare = cdServiceInstance.caricaSingoloElemento(idCdDaCaricare);
			if (cdDaCaricare != null)
				System.out.println("Cd caricato con successo: " + cdDaCaricare);
			else
				System.out.println("Cd non trovato.");

			// TEST ELENCA CD
			System.out.println("Carica tutti i cd presenti sul db..");
			List<Cd> listaCd = cdServiceInstance.listAll();
			if (!listaCd.isEmpty())
				for (Cd cdItem : listaCd) {
					System.out.println(cdItem);
				}
			else
				System.out.println("Non sono presenti cd sul db.");

			// TEST AGGIORNA CD
//			System.out.println("Aggiorno il campo autore di un cd..");
//			Cd cdDaAggiornare = cdServiceInstance.caricaSingoloElemento(3L);
//			System.out.println("Attualmente l'autore del cd è "+cdDaAggiornare.getAutore());
//			cdDaAggiornare.setAutore("Pippo Pluto");
//			cdServiceInstance.aggiorna(cdDaAggiornare);
//			System.out.println("Adesso l'autore è cambiato in "+cdDaAggiornare.getAutore());

			// *********************************************************************************
			// RIMUOVIAMO UN CD E VEDIAMO COSA ACCADE AI GENERI
			// ********************************
			// per eseguire questo test dobbiamo prendere un cd esistente collegato a due
			// generi
			// il risultato atteso è la rimozione dalla tabella cd, la rimozione dalla
			// tabella
			// di legame lasciando inalterate le voci nella tabella genere. Tutto ciò
			// a prescindere della presenza dei Cascade. Se mettiamo CascadeType.ALL o
			// REMOVE...
			// DISASTRO!!!
			// *********************************************************************************
//			System.out.println("RIMUOVIAMO UN CD E VEDIAMO COSA ACCADE AI GENERI...");
//			Long idCdDaCaricarePerRimozione = 4L;
//			Cd cdDaCaricarePerRimozione = cdServiceInstance.caricaSingoloElemento(idCdDaCaricarePerRimozione);
//			if(cdDaCaricarePerRimozione != null) {
//				System.out.println("Cd caricato con successo: "+cdDaCaricarePerRimozione);
//				//rimuovo
//				cdServiceInstance.rimuovi(cdDaCaricarePerRimozione);
//			}else
//				System.out.println("Cd non trovato.");
//			System.out.println("...end...");

			// *********************************************************************************
//			//COLLEGHIAMO GENERE A CD ****************************************************
//			//*********************************************************************************
//			Long idCdDaCaricarePerCollegamentoGenere = 1L;
//			System.out.println("Carica cd con id.."+idCdDaCaricarePerCollegamentoGenere);
//			Cd cdDaCaricarePerCollegamentoGenere = cdServiceInstance.caricaSingoloElemento(idCdDaCaricarePerCollegamentoGenere);
//			Genere genereDaCaricarePerCollegamente = genereServiceInstance.cercaPerDescrizione(descrizioneGenere);
//			if(cdDaCaricarePerCollegamentoGenere != null) {
//				cdServiceInstance.aggiungiGenere(cdDaCaricarePerCollegamentoGenere, genereDaCaricarePerCollegamente);
//			}
//			
//			//*********************************************************************************
//			//TEST INSERIMENTO NUOVO GENERE****************************************************
//			//*********************************************************************************
//			String descrizioneGenere = "rock";
//			if(genereServiceInstance.cercaPerDescrizione(descrizioneGenere) == null) {
//				genereServiceInstance.inserisciNuovo(new Genere(descrizioneGenere));
//				System.out.println("Nuovo genere inserito.");
//			}

			// TEST CARICA GENERE
			Long idGenereDaCaricare = 1L;
			System.out.println("Carica genere con id.." + idGenereDaCaricare);
			Genere genereDaCaricare = genereServiceInstance.caricaSingoloElemento(idGenereDaCaricare);
			if (genereDaCaricare != null)
				System.out.println("Genere caricato con successo: " + genereDaCaricare);
			else
				System.out.println("Genere non trovato.");

			// TEST AGGIORNA GENERE
//			System.out.println("Aggiorno il campo descrizione di un genere..");
//			Genere genereDaAggiornare = genereServiceInstance.caricaSingoloElemento(3L);
//			System.out.println("Attualmente la descrizione del genere è "+genereDaAggiornare.getDescrizione());
//			genereDaAggiornare.setDescrizione("Pop");
//			genereServiceInstance.aggiorna(genereDaAggiornare);
//			System.out.println("Adesso la descrizione è cambiata in "+genereDaAggiornare.getDescrizione());

			// TEST RIMUOVI GENERE
//			System.out.println("RIMUOVIAMO UN GENERE...");
//			Long idGenereDaCaricarePerRimozione = 1L;
//			Genere genereDaCaricarePerRimozione = genereServiceInstance
//					.caricaSingoloElemento(idGenereDaCaricarePerRimozione);
//			if (genereDaCaricarePerRimozione != null) {
//				System.out.println("Genere caricato con successo: " + genereDaCaricarePerRimozione);
//				// rimuovo
//				genereServiceInstance.rimuovi(genereDaCaricarePerRimozione);
//			} else
//				System.out.println("Genere non trovato.");
//			System.out.println("...end...");
//			
//			//*********************************************************************************
//			//CREAZIONE CD E CREAZIONE GENERE  IN UN SOLO COLPO  ******************************
//			//*********************************************************************************
//			Cd cdInstanceX = new Cd("titoloX","autoreX",new SimpleDateFormat("dd/MM/yyyy").parse("10/08/2020"));
//			Genere genereX = new Genere("X");
//			cdServiceInstance.creaECollegaCdEGenere(cdInstanceX, genereX);

			// *********************************************************************************
			// COLLEGHIAMO UN CD A DUE GENERI ED UN GENERE A DUE CD
			// ******************************
			// *********************************************************************************
//			System.out.println("COLLEGHIAMO UN  CD A DUE GENERI ED UN GENERE A DUE CD...");
//			Cd cdInstance3 = new Cd("titolo3","autore3",new SimpleDateFormat("dd/MM/yyyy").parse("07/04/2018"));
//			Cd cdInstance4 = new Cd("titolo4","autore4",new SimpleDateFormat("dd/MM/yyyy").parse("07/04/2018"));
//			Genere genereJ = new Genere("J");
//			Genere genereK = new Genere("K");
//			//cd3 con J e cd4 con J e K
//			cdServiceInstance.creaECollegaCdEGenere(cdInstance3, genereJ);
//			cdServiceInstance.creaECollegaCdEGenere(cdInstance4, genereK);
//			cdServiceInstance.aggiungiGenere(cdInstance4, genereJ);
//			System.out.println("...end...");


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}

}
