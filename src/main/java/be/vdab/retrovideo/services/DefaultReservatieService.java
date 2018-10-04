package be.vdab.retrovideo.services;

import be.vdab.retrovideo.entities.Reservatie;
import be.vdab.retrovideo.repositories.ReservatieRepository;

public class DefaultReservatieService implements ReservatieService {

		private final ReservatieRepository reservatieRepository; 
		
		public DefaultReservatieService(ReservatieRepository reservatieRepository) {
			this.reservatieRepository=reservatieRepository; 
		}
		
		@Override
		public void create(Reservatie reservatie) {
			reservatieRepository.create(reservatie);
		}
	
}
